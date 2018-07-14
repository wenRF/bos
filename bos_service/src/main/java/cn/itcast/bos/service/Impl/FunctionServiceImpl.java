package cn.itcast.bos.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IFunctionDao;
import cn.itcast.bos.domain.Function;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.IFunctionService;
import cn.itcast.bos.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

	@Resource
	private IFunctionDao functionDao;

	@Override
	public List<Function> findAll() {
		
		return functionDao.findAll();
	}

	@Override
	public void save(Function model) {
		functionDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}

	@Override
	public List<Function> findFunctionByUser(User user) {
		String username = user.getUsername();
		List<Function>list=null;
		if("admin".equals(username)){
			list=functionDao.findAll(); //如果是超级管理员 获取所有的权限
		}else{
			list=functionDao.findFunctionByuserId(user.getId());//不是超级管理员通过id查询角色，然后通过角色查询权限
		}
		return list;
	}

	@Override
	public List<Function> findMenu(User loginUser) {
		String username = loginUser.getUsername();
		List<Function>list=null;
		if("admin".equals(username)){
			list=functionDao.findAllMenu(); //如果是超级管理员 获取所有的权限
		}else{
			list=functionDao.findMenuByuserId(loginUser.getId());//不是超级管理员通过id查询角色，然后通过角色查询权限
		}
		return list;
	}

}
