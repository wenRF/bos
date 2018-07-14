package cn.itcast.bos.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IRoleDao;
import cn.itcast.bos.dao.IUserDao;
import cn.itcast.bos.domain.Role;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.IUserService;
import cn.itcast.bos.utils.MD5Utils;
import cn.itcast.bos.utils.PageBean;


@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public User login(User model) {
		
		//获取用户名和密码
		String username=model.getUsername();
		
		String password=model.getPassword();
		
		//加密密码
		password = MD5Utils.md5(password);
		
		
		return userDao.finByUsernameAndPassword(username,password);
	}


	@Override
	public void editPwd(String password, String id) {
		
		userDao.executeUpdate("user.editPwd",MD5Utils.md5(password),id);
	}


	//添加用户
	@Override
	public void save(User model, String[] roleIds) {
		model.setPassword(MD5Utils.md5(model.getPassword()));
		userDao.save(model);
		
		//关联角色
		for(String id : roleIds){
			Role role = roleDao.findById(id);
			model.getRoles().add(role);
		}
		
	}


	@Override
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}

	
	
	

	
	
}
