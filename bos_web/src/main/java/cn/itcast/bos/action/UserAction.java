package cn.itcast.bos.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.IUserService;
import cn.itcast.bos.utils.BosUtils;
import cn.itcast.bos.utils.MD5Utils;


@Controller
@Scope("prototype")  //多例
public class UserAction extends BaseAction<User> {

	private String checkcode;

	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}


	@Resource
	private IUserService userService;
	
	
	public String login(){
		//调用service
			User user=userService.login(model);
		//判断验证码
		//获取图片中的验证码
		String  code = (String) ServletActionContext.getRequest().getSession().getAttribute("key");	
		//如果验证码正确  进行验证用户名和密码
		if(StringUtils.isNoneBlank(checkcode)&& checkcode.equalsIgnoreCase(code)){
			
			/*//验证用户名和密码
			if(user==null){
				//错误提示
				this.addActionError("用户名或密码错误！");
				return "login";
			}else{
				//用户存在 放到session域
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "home";
			}*/
			
			
			
			
			//使用shiro实现用用户登录
			//获取shiro中的subject 对象，未认证状态的用户对象
			Subject subject = SecurityUtils.getSubject();
			//创建一个密码令牌
			AuthenticationToken authenticationtoken = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
			
			try {
				//调用subject的login 方法
				subject.login(authenticationtoken);
				//登录成功
				//获取登录的用户
				User loginUser = (User) subject.getPrincipal();
				//放入session中
				BosUtils.getSession().setAttribute("loginUser", loginUser);
				return "home";
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}
			//登录失败
			this.addActionError("用户名或密码错误！");
			return "login";
			
		}else{
			//错误信息
			this.addActionError("验证码错误！");
			//验证码错误则调转到登录页面
			return "login";
		}
		
	}
	
	
	
	//修改密码	
	public String editPwd(){
		//获取登录的用户
		User user = BosUtils.loginUser();
		String password = model.getPassword();
		//获取标识
		String flag="1";
		
		try {
			userService.editPwd(password,user.getId());
		} catch (Exception e1) {
			flag="0";
			e1.printStackTrace();
		}
		
		//将标识返回页面
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		
		try {
			ServletActionContext.getResponse().getWriter().print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
		
	}
	
	
	//注销
	
	public String loginOut(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
		
	}
	
	
	//添加用户
	
	private String[] roleIds;
	
	
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}



	public String add(){
		userService.save(model,roleIds);
		return "list";
		
	}
	
	public String pageQuery(){
		userService.pageQuery(pageBean);
		String[] excludes={"noticebills","roles"};
		this.writeObject2Json(pageBean, excludes);
		return NONE;
		
	}
}
