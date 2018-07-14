package cn.itcast.bos.action;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Noticebill;
import cn.itcast.bos.service.INoticebillService;
import cn.itcast.crm.service.Customer;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
	
	@Resource
	private INoticebillService noticebillService;
	
	
	public String findCustomerByTelephone(){
		Customer customer=customerService.findCustomerByTelephone(model.getTelephone());
		String[] excludes={};
		this.writeObject2Json(customer, excludes);
		return NONE;
		
	}
	
   //提交新单
	
	public String add(){
		noticebillService.add(model);
		return "list";
		
	}
	
	//分页查询
		public String pageQuery(){
			noticebillService.pageQuery(pageBean);
			String[] excludes={"workbills","decidedzones","noticebills"};
			this.writeObject2Json(pageBean, excludes);
			return null;
			
		}
		
		
	//查询未分派的订单
		
		public String findnoassigment(){
			DetachedCriteria dc = pageBean.getDc();
			dc.add(Restrictions.eq("ordertype", "手动分单"));
			pageBean.setDc(dc);	
			noticebillService.pageQuery(pageBean);
			String[] excludes={"workbills","user","staff"};
			this.writeObject2Json(pageBean, excludes);
			return NONE;
			
		}
}
