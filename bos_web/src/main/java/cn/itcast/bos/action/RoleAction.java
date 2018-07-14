package cn.itcast.bos.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Role;
import cn.itcast.bos.service.IRoleService;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	
	@Resource
	private IRoleService roleService;
	
	//接收页面提交的functionIds
	private String functionIds;

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	
	
	
	public String add(){
		
		roleService.add(model,functionIds);
		return "list";
		
	}
	
	public String pageQuery(){
		roleService.pageQuery(pageBean);
		String[] excludes={"functions","users"};
		this.writeObject2Json(pageBean, excludes);
		return NONE;
		
	}
	
	public String listajax(){
		List<Role>list=roleService.findAll();
		String[] excludes={"functions","users"};
		this.writeList2Json(list, excludes);
		return NONE;
		
	}
}
