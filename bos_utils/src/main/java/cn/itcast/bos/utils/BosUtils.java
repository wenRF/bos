package cn.itcast.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.itcast.bos.domain.User;

public class BosUtils {

	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	public static User loginUser(){
		return (User) getSession().getAttribute("loginUser");
	}
}
