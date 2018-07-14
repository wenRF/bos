package cn.itcast.bos.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IFunctionDao;
import cn.itcast.bos.dao.IRoleDao;
import cn.itcast.bos.domain.Function;
import cn.itcast.bos.domain.Role;
import cn.itcast.bos.service.IRoleService;
import cn.itcast.bos.utils.PageBean;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDao roleDao;
	
	@Resource
	private IFunctionDao functionDao;

	@Override
	public void add(Role model, String functionIds) {
		
		roleDao.save(model);//持久态，自动保存更新
		
		//保存角色和权限的关系  多对多关系，由角色来维护
		String[] ids = functionIds.split(",");
		
		for (String id : ids) {
			Function function = functionDao.findById(id);
			
			model.getFunctions().add(function);
		}
	}

	
	@Override
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
	}


	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

}
