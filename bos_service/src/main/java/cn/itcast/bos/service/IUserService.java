package cn.itcast.bos.service;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.utils.PageBean;

public interface IUserService {

	User login(User model);

	void editPwd(String password, String id);

	void save(User model, String[] roleIds);

	void pageQuery(PageBean pageBean);

}
