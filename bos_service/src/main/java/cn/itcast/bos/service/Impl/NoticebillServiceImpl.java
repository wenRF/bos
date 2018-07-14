package cn.itcast.bos.service.Impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IDecidedzoneDao;
import cn.itcast.bos.dao.INoticebillDao;
import cn.itcast.bos.dao.IWorkbillDao;
import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.domain.Noticebill;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.domain.Workbill;
import cn.itcast.bos.service.INoticebillService;
import cn.itcast.bos.utils.BosUtils;
import cn.itcast.bos.utils.PageBean;
import cn.itcast.crm.service.ICustomerService;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {

	@Resource
	private INoticebillDao noticebillDao;
	@Resource
	private ICustomerService customerService;
	@Resource
	private IDecidedzoneDao decidedzoneDao;
	@Resource
	private IWorkbillDao workbillDao;
	
	
	@Override
	public void add(Noticebill model) {
		
		noticebillDao.save(model);
		
		//业务通知单，关联user
		User loginUser = BosUtils.loginUser();
		model.setUser(loginUser);
		
		//根据客户地址(取件地址)查询对应的定区的id
		String decidedzoneId = customerService.findDecidedzoneIdByAddress(model.getPickaddress());
		
		//查找定区
		if(decidedzoneId!=null){
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
			//获取与定区关联的取派员
			Staff staff = decidedzone.getStaff();
			//给取派员关联业务通知单
			model.setStaff(staff);
			model.setOrdertype("自动分单");
			
			//给取派员生成工单
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);//设置追单次数 默认0
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//工单生成时间，当前系统的时间
			workbill.setPickstate("未取件"); //取件状态
			workbill.setNoticebill(model);   //业务通知单
			workbill.setRemark(model.getRemark());  //备注
			workbill.setType("新单"); //订单类型
			workbill.setStaff(staff); //该工单的取派员
			
			
			//保存工单信息
			workbillDao.save(workbill);
			
		}else{
			model.setOrdertype("手动分单");
		}
			
	}


	@Override
	public void pageQuery(PageBean pageBean) {
		this.noticebillDao.pageQuery(pageBean);
	}


	

}
