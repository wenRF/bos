package cn.itcast.bos.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Workbill;
import cn.itcast.bos.service.IWorkbillService;

@Controller
@Scope("prototype")
public class WorkbillAction extends BaseAction<Workbill> {
	
	@Resource
	private IWorkbillService workbillService;
	
	
	
	
	public String pageQuery(){
		
		workbillService.pageQuery(pageBean);
		
		String[] excludes={"noticebills","decidedzones","workbills","user"};
		this.writeObject2Json(pageBean, excludes);
		return NONE;
		
	}
	
	
	public String add(){
		workbillService.add(model);
		return "list";
		
	}
}
