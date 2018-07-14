package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Role;
import cn.itcast.bos.utils.PageBean;

public interface IRoleService{

	void add(Role model, String functionIds);

	void pageQuery(PageBean pageBean);

	List<Role> findAll();

}
