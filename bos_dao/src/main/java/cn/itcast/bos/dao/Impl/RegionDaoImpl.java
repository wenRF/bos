package cn.itcast.bos.dao.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.IRegionDao;
import cn.itcast.bos.dao.base.Impl.BaseDaoImpl;
import cn.itcast.bos.domain.Region;
import cn.itcast.bos.utils.PageBean;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	@Override
	public List<Region> findByQ(String q) {
		String hql="from Region where shortcode like ? or citycode like ?";
		
		return (List<Region>) this.getHibernateTemplate().find(hql,"%"+q+"%","%"+q+"%");
	}



}
