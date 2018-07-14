package cn.itcast.bos.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IStaffDao;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.service.IStaffAervice;
import cn.itcast.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements IStaffAervice {

	@Resource
	private IStaffDao staffDao;
	@Override
	public void add(Staff model) {
		staffDao.save(model);
	}
	
	
	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}


	@Override
	public void delete(String ids) {
		//通过拆分成多个数组，然后循环更新
		String[] staffIds = ids.split(",");
		for (String id : staffIds) {
			staffDao.executeUpdate("staff.delete",id);
		}
		
	}


	@Override
	public void edit(Staff model) {
		//先查询 然后覆盖，保存更新 数据库， 是持久态，可以自动保存更新
		Staff staff = staffDao.findById(model.getId());
		//将model编辑的数据覆盖staff
		staff.setName(model.getName());
		staff.setEmail(model.getEmail());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStation());
		staff.setTelephone(model.getTelephone());
		staff.setHaspda(model.getHaspda());
	}


	@Override
	public void restore(String ids) {
		String[] sid = ids.split(",");
		for (String id : sid) {
			staffDao.executeUpdate("staff.restore",id);
		}
	}


	@Override
	public List<Staff> findNotDelete() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		return staffDao.findByCriteria(detachedCriteria);
	}

}
