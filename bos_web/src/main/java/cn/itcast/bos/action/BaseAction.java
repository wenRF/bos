package cn.itcast.bos.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.utils.PageBean;
import cn.itcast.crm.service.ICustomerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	//封装数据
	protected PageBean pageBean = new PageBean();	
	
	@Resource
	protected ICustomerService customerService;
	/*
	 * 接收页面的数据，下面的set方法进行封装，然后封装到pagebean中
	 * 可以直接将页面接收的数据封装到pageBean中
	 * private int page;
	   private int rows;
	 * 
	 */
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
	
	protected T model;
	
	@Override
	public T getModel() {
		
		return model;
	}
		
	//动态获取泛型
	public  BaseAction(){
		//UserAction extends BaseAction<User>
		//UserAction userAction = new UserAction();
		//this == userAction
		//this.getClass() == UserAction.class;
		//this.getClass().getGenericSuperclass() == BaseAction<User>.class
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		//parameterizedType.getActualTypeArguments() == [User]
		Type[] types = parameterizedType.getActualTypeArguments();
		//entityClass == User.class
		Class<T> entityClass = (Class<T>) types[0];
		//创建离线查询条件(分页查询)
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		pageBean.setDc(dc);
		
		try {
			// entityClass.newInstance()=new User(); 创建实例对象
			model=entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void writeObject2Json(Object object, String[] excludes){//排除多余的不一定就是这几个   转换Json格式的不一定就是pagebean，所以有参数
		//将pageBean转化成Jason数据格式
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(object,jsonConfig);
		String json = jsonObject.toString();//转化字符串
		//使用response将json返回到前台
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void writeList2Json(List list,String[] excludes){//排除多余的不一定就是这几个   转换Json格式的不一定就是pagebean，所以有参数
		//将pageBean转化成Jason数据格式
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		String json = jsonArray.toString();//转化字符串
		//使用response将json返回到前台
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}







