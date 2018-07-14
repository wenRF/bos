package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Function;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.utils.PageBean;

public interface IFunctionService{

	List<Function> findAll();

	void save(Function model);

	void pageQuery(PageBean pageBean);

	List<Function> findFunctionByUser(User user);

	List<Function> findMenu(User loginUser);

}
