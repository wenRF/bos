package cn.itcast.bos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.service.IRegionService;
import cn.itcast.bos.utils.PageBean;
import cn.itcast.bos.utils.PinYin4jUtils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

	@Resource
	private IRegionService regionService;
	
	private File regionFile;

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
	
	
	public String importXls(){
		String flag="1";
		try {
			//1.读取整个文件
			HSSFWorkbook wb=new HSSFWorkbook(new FileInputStream(regionFile));
			//2.读取一个sheet页
			HSSFSheet sheet = wb.getSheetAt(0);
			//3.循环读取每一行
			int index=0;
			List<Region> list = new ArrayList<Region>();
			for (Row row : sheet) {
				if(index==0){
					index++;
					continue;
				}
				//4.获取每一列的数据
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				
				//5.创建region对象，封装数据  (构造函数封装数据)
				Region region = new Region(id, province, city, district, postcode, null, null, null);
				//简码  截取  去掉最后一位
				province= province.substring(0, province.length()-1);  //北京市->北京
				city=city.substring(0, city.length()-1);
				district=district.substring(0, district.length()-1);
				//拼接
				String temp=province+city+district;//汉子拼接
				//查找字符串首字母  
				String[] headArr = PinYin4jUtils.getHeadByString(temp);
				String shortcode = StringUtils.join(headArr,"");
				//封装
				region.setShortcode(shortcode);
				
				//城市码：石家庄->shijiazhuang
				String citycode = PinYin4jUtils.hanziToPinyin(city,"");
				region.setCitycode(citycode);
				
				list.add(region);
			}	
			
			regionService.saveBatch(list);
		} catch (IOException e) {
			flag="0";
			e.printStackTrace();
		}
		
		//页面 
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		
		try {
			ServletActionContext.getResponse().getWriter().print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
		
	}
	
	
	
	/*
	 * 分页查询
	 */
	
	
	public String pageQuery(){
		//执行查询
		regionService.pageQuery(pageBean);				 
		//将pageBean转化成Jason数据格式			
		String[] excludes={"pageSize","currentPage","dc","subareas"};//排除多余的
		this.writeObject2Json(pageBean, excludes);
		return NONE;
		
	}
	
	
	
	/*
	 * 分区添加时页回显区域
	 * 根据q判断查询是否为模糊查询
	 */
	
	private String q;
	
	public void setQ(String q) {
		this.q = q;
	}

	public String listajax(){
		List<Region> list = null;
		if(StringUtils.isBlank(q)){
			//q为空，查询所有
			list=regionService.findAll();
		}else{
			//不为空，根据q查询
			list=regionService.findByQ(q);
		}
		//排除多余项
		String[] excludes = {"subareas"};
		//调用抽取的函数转换为Json
		this.writeList2Json(list, excludes);
		
		return NONE;
		
	}
	
	
	//修改
	
	public String edit(){
		regionService.saveOrUpdate(model);
		return "list";
		
	}
	
	//保存
	
	public String add(){
		regionService.save(model);
		return "list";
		
	}
	
	//区域删除
	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}


	public String delete(){
		
		regionService.delete(ids);
		return "list";
		
	}
	
}
