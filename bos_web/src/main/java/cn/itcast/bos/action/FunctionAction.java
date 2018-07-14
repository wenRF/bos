package cn.itcast.bos.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Function;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.IFunctionService;
import cn.itcast.bos.utils.BosUtils;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
	
	@Resource
	private IFunctionService functionService;
	
	
	
	public String listAjax(){
		
		List<Function> list=functionService.findAll();	
		String[] excludes = {"parentFunction", "functions", "roles"};
		this.writeList2Json(list, excludes);
		return NONE;
		
	}
	
	
	
	public String add(){
		
		Function parentFunction = model.getParentFunction();
		if(null!=parentFunction&&StringUtils.isBlank(parentFunction.getId())){
			model.setParentFunction(null);//父节点不存在 将父节点的对象设置为null防止外键冲突
		}
		functionService.save(model);
		return "list";
		
	}
	
	
	public String pageQuery(){
		String page = model.getPage();
		
		pageBean.setCurrentPage(Integer.valueOf(page));
		functionService.pageQuery(pageBean);
		
		String[] excludes={"pageSize", "currentPage", "dc","parentFunction", "functions", "roles"};
		this.writeObject2Json(pageBean, excludes);
		return NONE;
		
	}
	
	
	public String findMenu(){
		//获取当前登录的用户
		User loginUser = BosUtils.loginUser();
		List<Function>list=functionService.findMenu(loginUser);
		String[] excludes={"parentFunction", "functions", "roles"};
		this.writeList2Json(list, excludes);
		return NONE;
		
	}
}
