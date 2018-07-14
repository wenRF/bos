package cn.itcast.bos.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.ISubareaDao;
import cn.itcast.bos.dao.base.Impl.BaseDaoImpl;
import cn.itcast.bos.domain.Subarea;

@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements ISubareaDao {

	@Override
	public List<Object> findGroupSubareas() {
		String hql="select r.province, count(*) from Subarea s join s.region r group by r.province";
		return (List<Object>) this.getHibernateTemplate().find(hql);
	}

}
