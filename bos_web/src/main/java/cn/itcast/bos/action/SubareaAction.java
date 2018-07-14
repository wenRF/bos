package cn.itcast.bos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.domain.Region;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.IRegionService;
import cn.itcast.bos.service.ISubareaService;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	
	@Resource
	private ISubareaService subareaService;
	@Resource
	private IRegionService regionService;
	
	public String add(){
		subareaService.save(model);
		return "list";
		
	}
	
	public String pageQuery(){
		//离线对象
		DetachedCriteria dc = pageBean.getDc();
		//addresskey组装到离线查询中  先判断是否为空
		
		if(StringUtils.isNotBlank(model.getAddresskey())){
			dc.add(Restrictions.like("addresskey", "%"+model.getAddresskey()+"%"));
		}
		
		//组装region中的数据到离线查询
		Region region = model.getRegion();
		
		if(null!=region){	
			//给region起个别名，hql中如果一张表(subarea)引用了另一张表(region)中的字段就要起个别名
			dc.createAlias("region", "r");			
			String province = region.getProvince();
			if(StringUtils.isNotBlank(province)){
				dc.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			String city = region.getCity();
			if(StringUtils.isNotBlank(city)){
				dc.add(Restrictions.like("r.city","%"+city+"%"));
			}
			String district = region.getDistrict();
			if(StringUtils.isNotBlank(district)){
				dc.add(Restrictions.like("r.district","%"+district+"%"));
			}
		}
		//执行查询
		subareaService.pageQuery(pageBean);
		String[] excludes={"pageSize","currentPage","dc","decidedzone","subareas"};//排除多余的
		this.writeObject2Json(pageBean, excludes);
		return NONE;
		
	}
	
	
	
	//导出
	public String exportXls(){
		//1.查询所有的数据
		List<Subarea>list=subareaService.findAll();
		//2.创建excel POI
			//创建空的excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
			//创建空的sheet页
		HSSFSheet sheet = workbook.createSheet();
			//创建标题行 
		HSSFRow row = sheet.createRow(0);
			//创建标题列
		row.createCell(0).setCellValue("分区编号");
		row.createCell(1).setCellValue("区域编号");
		row.createCell(2).setCellValue("关键字");
		row.createCell(3).setCellValue("位置信息");
		
		//将数据放入excel表格中 先判断是否为空 循环添加
		
		if(list!=null&&list.size()>0){
			//从第二行开始 除去标题列
			int index=1;
			for(Subarea subarea:list){
				row = sheet.createRow(index++);
				row.createCell(0).setCellValue(subarea.getId());
				row.createCell(1).setCellValue(null!=subarea.getRegion()?subarea.getRegion().getId():"" );
				row.createCell(2).setCellValue(subarea.getAddresskey());
				row.createCell(3).setCellValue(subarea.getPosition());
			}
		}
		
		//将表格返回到前台
		String filename="分区列表.xls";
		//从request中获取浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");				
		try {
			//头:content-type:设置返回的数据类型
			String mimeType = ServletActionContext.getServletContext().getMimeType(filename);	
			//一个流两个头
			//流：response输出流
			ServletOutputStream os = ServletActionContext.getResponse().getOutputStream();
			ServletActionContext.getResponse().setContentType(mimeType);
			//头：content-disposition:告诉浏览器以什么方法打开文件
			ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);	
						
			//通过POI将excel返回到前台
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return NONE;
		
	}
	

	//查询分区
	
	public String listAjax(){
		
		//查询未关联的分区
		List<Subarea>list=subareaService.findNoGuanLian();
		
		String[] excludes={"decidedzone","subareas"};
		this.writeList2Json(list, excludes);	
		
		return NONE;
		
	}
	
	
	//导入

	private File subareaFile;
	
	public void setSubareaFile(File subareaFile) {
		this.subareaFile = subareaFile;
	}

	public String importXls(){
		//1.读取整个文件
		String flag="1";
		try {
			HSSFWorkbook wb=new HSSFWorkbook(new FileInputStream(subareaFile));
			//2.读取一个sheet页
			HSSFSheet sheet = wb.getSheetAt(0);
			//3.循环读取每一行
			int index=0;
			List<Subarea> list = new ArrayList<Subarea>();
			for (Row row : sheet) {
				if(index==0){
					index++;
					continue;
				}
				//4.获取每一列的数据
				String id = row.getCell(0).getStringCellValue();
				String  region_id= row.getCell(1).getStringCellValue();
				String  addresskey = row.getCell(2).getStringCellValue();
				String  startnum = row.getCell(3).getStringCellValue();
				String  endnum = row.getCell(4).getStringCellValue();
				String  single = row.getCell(5).getStringCellValue();
				String  position = row.getCell(6).getStringCellValue();
				
				Region region  = regionService.findById(region_id);
				//创建subarea对象，封装数据
				Subarea subarea = new Subarea(id, null, region, addresskey, startnum, endnum, single, position);
				//添加
				list.add(subarea);	
				
			}
			subareaService.saveFile(list);
		} catch (IOException e) {
			flag="0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		
		try {
			//返回前台页面
			ServletActionContext.getResponse().getWriter().print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "list";
		}
	
	//定区中关联分区查询
	private String decidedzoneId;
	
	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}

	public String findByDecidedzoneId(){
		List <Subarea> list=subareaService.findByDecidedzoneId(decidedzoneId);
		
		String[] excludes={"decidedzone","subareas"};
		this.writeList2Json(list, excludes);
		return NONE;
		
	}
	
	
	
	public String findGroupSubareas(){
		List <Object> list =subareaService.findGroupSubareas();
		
		String[] excludes={};
		this.writeList2Json(list, excludes);
		return NONE;
		
	}
	
	
	//分区修改
	
	public String edit(){
		subareaService.saveOrUpdate(model);
		return "list";
		
	}
}
