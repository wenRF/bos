package cn.itcast.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Function entity. @author MyEclipse Persistence Tools
 */

public class Function implements java.io.Serializable {

	// Fields

	private String id;
	private Function parentFunction;//父权限
	private String name;//名称
	private String code;//关键字
	private String description;//描述
	private String page;//请求地址
	private String generatemenu;//是否生成菜单1-生成，0-不生成
	private Integer zindex;//排序的优先级
	private Set functions = new HashSet(0);//子权限集合
	private Set roles = new HashSet(0);//关联的角色
	
	
	// Constructors

	public String getpId() {
		if(null!=parentFunction){
			return parentFunction.getId();
		}
		return "0";
	}

	
	/** default constructor */
	public Function() {
	}

	/** minimal constructor */
	public Function(String id) {
		this.id = id;
	}

	/** full constructor */
	public Function(String id, Function parentFunction, String name, String code,
			String description, String page, String generatemenu,
			Integer zindex, Set functions, Set roles) {
		this.id = id;
		this.parentFunction = parentFunction;
		this.name = name;
		this.code = code;
		this.description = description;
		this.page = page;
		this.generatemenu = generatemenu;
		this.zindex = zindex;
		this.functions = functions;
		this.roles = roles;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Function getParentFunction() {
		return this.parentFunction;
	}

	public void setParentFunction(Function function) {
		this.parentFunction = function;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getGeneratemenu() {
		return this.generatemenu;
	}

	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}

	public Integer getZindex() {
		return this.zindex;
	}

	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}

	public Set getFunctions() {
		return this.functions;
	}

	public void setFunctions(Set functions) {
		this.functions = functions;
	}

	public Set getRoles() {
		return this.roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

}