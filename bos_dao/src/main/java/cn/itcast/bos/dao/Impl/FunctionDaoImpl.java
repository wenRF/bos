package cn.itcast.bos.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.IFunctionDao;
import cn.itcast.bos.dao.base.Impl.BaseDaoImpl;
import cn.itcast.bos.domain.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

	@Override
	public List<Function> findFunctionByuserId(String id) {
		
		String hql="select distinct f from Function f join f.roles r join r.users u where u.id=?";
		return (List<Function>) this.getHibernateTemplate().find(hql, id);
	}

	@Override
	public List<Function> findAllMenu() {
		String hql="from Function where generatemenu='1'";
		return (List<Function>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<Function> findMenuByuserId(String id) {
		
		String hql="select distinct f from Function f join f.roles r join r.users u where u.id=? and f.generatemenu='1'"
				+ "order by f.zindex";
		return (List<Function>) this.getHibernateTemplate().find(hql, id);
	}

}
