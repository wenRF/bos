package cn.itcast.bos.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.INoticebillDao;
import cn.itcast.bos.dao.IStaffDao;
import cn.itcast.bos.dao.IWorkbillDao;
import cn.itcast.bos.domain.Noticebill;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.domain.Workbill;
import cn.itcast.bos.service.IWorkbillService;
import cn.itcast.bos.utils.PageBean;

@Service
@Transactional
public class WorkbillServiceImpl implements IWorkbillService {

	@Resource
	private IWorkbillDao workbillDao;
	@Resource
	private IStaffDao staffDao;
	@Resource
	private INoticebillDao noticebillDao;
	
	@Override
	public void pageQuery(PageBean pageBean) {
		
		workbillDao.pageQuery(pageBean);
	}

	@Override
	public void add(Workbill model) {
		
		Staff staff = staffDao.findById(model.getStaff().getId());
		System.out.println(model.getStaff().getId());
		Noticebill noticebill = noticebillDao.findById(model.getId());
		
		noticebill.setStaff(staff);
		
		
		//给取派员生成工单
		Workbill workbill = new Workbill();
		workbill.setAttachbilltimes(0);//设置追单次数 默认0
		workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//工单生成时间，当前系统的时间
		workbill.setPickstate("未取件"); //取件状态
		workbill.setNoticebill(noticebill);   //业务通知单
		workbill.setRemark(model.getRemark());  //备注
		workbill.setType("新单"); //订单类型
		workbill.setStaff(staff); //该工单的取派员
		
		//保存工单信息
		workbillDao.save(workbill);
	}

	
	//发邮件的查询
	@Override
	public List<Workbill> findWorkBills() {
		DetachedCriteria dc = DetachedCriteria.forClass(Workbill.class);
		dc.add(Restrictions.eq("type", "新单"));
		dc.add(Restrictions.eq("pickstate", "未取件"));
		return workbillDao.findByCriteria(dc);
	}

}
