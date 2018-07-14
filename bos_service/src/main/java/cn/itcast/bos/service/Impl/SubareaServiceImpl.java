package cn.itcast.bos.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.ISubareaDao;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.ISubareaService;
import cn.itcast.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

	@Resource
	private ISubareaDao subareaDao;

	@Override
	public void save(Subarea model) {
		subareaDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}

	@Override
	public List<Subarea> findNoGuanLian() {
		DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
		dc.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(dc);
			
	}

	@Override
	public void saveFile(List<Subarea> list) {
		
		for (Subarea subarea : list) {
			subareaDao.saveOrUpdate(subarea);
		}
	}

	@Override
	public List<Subarea> findByDecidedzoneId(String decidedzoneId) {
		DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
		dc.createAlias("decidedzone", "d");
		dc.add(Restrictions.eq("d.id",decidedzoneId ));
		return subareaDao.findByCriteria(dc);
	}

	@Override
	public List<Object> findGroupSubareas() {
		
		
		return subareaDao.findGroupSubareas();
	}

	@Override
	public void saveOrUpdate(Subarea model) {
		subareaDao.saveOrUpdate(model);
		
	}

}
