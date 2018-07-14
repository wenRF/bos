package cn.itcast.bos.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.service.IStaffAervice;
import cn.itcast.bos.utils.PageBean;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	@Resource
	private IStaffAervice staffService;
	
	public String add(){
		staffService.add(model);
		return "list";
		
	}
	


	//分页查询
	public String pageQuery(){
			
		DetachedCriteria dc = pageBean.getDc();
		
		String name = model.getName();
		if(StringUtils.isNoneBlank(name)){
			dc.add(Restrictions.like("name", "%"+name+"%"));
		}
		String station = model.getStation();
		if(StringUtils.isNoneBlank(station)){
			dc.add(Restrictions.like("ststion", "%"+station+"%"));
		}
		String standard = model.getStandard();
		if(StringUtils.isNoneBlank(standard)){
			dc.add(Restrictions.like("standard", "%"+standard+"%"));
		}
		
		//执行查询  "decidedzones"
		staffService.pageQuery(pageBean);
		String[] excludes={"pageSize", "workbills","noticebills","decidedzones"};//排除多余的 
		//调用，转换pageBean为Json格式
		this.writeObject2Json(pageBean, excludes);
		//this.writeObject2Json(pageBean, new String[]{ "pageSize", "currentPage", "dc" });
		return NONE;
		
	}
	
	
	//删除  取派员作废 将data改为1
	
	private String  ids;
	
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	@RequiresPermissions("staff") ////添加权限校验的注解
	public String delete(){
		
		staffService.delete(ids);
		
		return "list";
		
	}
	
	public String edit(){
		staffService.edit(model);
		return "list";
		
	}
	
	//还原restore
	
	public String restore(){
		staffService.restore(ids);
		return "list";
		
	}
	
	
	//decidedzone中添加取派员信息 查询未作废的取派员
	public String listAjax(){
		List<Staff>list=staffService.findNotDelete();
		String[] excludes={"decidedzones","noticebills","workbills"};
		this.writeList2Json(list, excludes);
		return NONE;
		
	}
}
