package cn.itcast.bos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Workordermanage;
import cn.itcast.bos.service.IWorkordermanageService;

@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {
	
	@Resource
	private IWorkordermanageService workordermanageService;
	
	
	//快速录入，返回标识
	
	public String add(){
		
		String flag="1";
		try {
			workordermanageService.add(model);
		} catch (Exception e) {
			flag="0";
			e.printStackTrace();
		}
		
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		
		try {
			ServletActionContext.getResponse().getWriter().print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
		
	}
	
	
	//导入
	private File upload;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	public String batchImport(){
		String flag = "success";
		try {
			
			//读取整个excle文件
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(upload));
			//2读取一个sheet页
			HSSFSheet sheet = wb.getSheetAt(0);
			//3循环读取每一行
			List<Workordermanage> list = new ArrayList<Workordermanage>();
			int index = 0;
			for(Row row : sheet){
				if(index == 0){
					index++;
					continue;
				}
				//4获取每行数据
				String id = row.getCell(0).getStringCellValue();
				String product = row.getCell(1).getStringCellValue();
				String prodtimelimit = row.getCell(2).getStringCellValue();
				String prodtype = row.getCell(3).getStringCellValue();
				String sendername = row.getCell(4).getStringCellValue();
				String senderphone = row.getCell(5).getStringCellValue();
				String senderaddr = row.getCell(6).getStringCellValue();
				String receivername = row.getCell(7).getStringCellValue();
				String receiverphone = row.getCell(8).getStringCellValue();
				String receiveraddr = row.getCell(9).getStringCellValue();
				String actlweit_s = row.getCell(10).getStringCellValue();
				double actlweit = Double.parseDouble(actlweit_s);
				
				//创建Workordermanage对象，封装数据
				Workordermanage wd = new Workordermanage(id, null, product,
						null, null, null, prodtimelimit, prodtype, sendername, 
						senderphone, senderaddr, receivername, receiverphone, receiveraddr, 
						null, actlweit, null, null, null);
				
				//添加
				list.add(wd);
			}
			workordermanageService.saveBatch(list);//Batch  一批，保存一批
			
		} catch (IOException e) {
			flag = "false" ;
			e.printStackTrace();
		}
		
		//编码
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		
		try {
			//返回前台页面
			ServletActionContext.getResponse().getWriter().print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return  null;
	}
	
	
	
	
	//分页查询
	public String pageQuery(){
		workordermanageService.pageQuery(pageBean);
		String[] excludes={};
		this.writeObject2Json(pageBean, excludes);
		return NONE;
		
	}
	
	
	//模板下载(导出)
	
	public String download(){
		//查询所有的数据
		List<Workordermanage>list =workordermanageService.findAll();
		
		//创建excel表格
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建新的sheet页
		HSSFSheet sheet = workbook.createSheet();
		//创建标题行
		HSSFRow row = sheet.createRow(0);
		//创建标题列
		row.createCell(0).setCellValue("编号");			
		row.createCell(1).setCellValue("产品");			
		row.createCell(2).setCellValue("产品时限");			
		row.createCell(3).setCellValue("产品类型");
		row.createCell(4).setCellValue("发件人姓名");
		row.createCell(5).setCellValue("发件人电话");
		row.createCell(6).setCellValue("发件人地址");
		row.createCell(7).setCellValue("收件人姓名");
		row.createCell(8).setCellValue("收件人电话");
		row.createCell(9).setCellValue("收件人地址");
		row.createCell(10).setCellValue("实际重量");
		
		//将数据放入表格中 先进行判断是否为空 循环添加
		if(list !=null && list.size()>0){
			//从第二行开始 去除标题列
			int index=1;
			for (Workordermanage workordermanage : list) {
				row = sheet.createRow(index++);
				row.createCell(0).setCellValue(workordermanage.getId());
				row.createCell(1).setCellValue(workordermanage.getProduct());
				row.createCell(2).setCellValue(workordermanage.getProdtimelimit());
				row.createCell(3).setCellValue(workordermanage.getProdtype());
				row.createCell(4).setCellValue(workordermanage.getSendername());
				row.createCell(5).setCellValue(workordermanage.getSenderphone());
				row.createCell(6).setCellValue(workordermanage.getSenderaddr());
				row.createCell(7).setCellValue(workordermanage.getReceivername());
				row.createCell(8).setCellValue(workordermanage.getReceiverphone());
				row.createCell(9).setCellValue(workordermanage.getReceiveraddr());
				row.createCell(10).setCellValue(workordermanage.getActlweit());
			}
		}
		//将表格返回到前台
		String filename="工作单导入模板.xls";
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
}

