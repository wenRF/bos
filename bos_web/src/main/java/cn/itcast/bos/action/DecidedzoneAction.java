package cn.itcast.bos.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.service.IDecidedzoneService;
import cn.itcast.crm.service.Customer;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

	@Resource
	private IDecidedzoneService decidedzoneService;
	
	private String[] subareaid;
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}


	/*
	 *  定区保存方法，保存定区数据，定区关联的联系人，定区关联分区，返回list
	 */
	public String add(){
		decidedzoneService.add(model,subareaid);
		return "list";
		
	}
	
	//分页显示
	
	public String pageQuery(){
		decidedzoneService.pageQuery(pageBean);	
		String[] excludes={"subareas","decidedzones","workbills","noticebills","dc"};
		this.writeObject2Json(pageBean, excludes);
		return NONE;
			
	}
	
	//关联  查询未关联的客户
	
	public String findUnassociatedCustomers(){
		List<Customer> list = customerService.findnoassociationCustomers();
		String[] excludes={};
		this.writeList2Json(list, excludes);
		return NONE;
		
	}
	
	//关联的客户
	public String findAssociatedCustomers(){
		List<Customer> list = customerService.findhasassociationCustomers(model.getId());
		String[] excludes={};
		this.writeList2Json(list, excludes);
		return NONE;
		
	}
	
	//保存关联客户
	
	private List<Integer> customerIds;//页面下拉框中的选中的客户的id
	
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	//定区关联客户
	public String guanlianCustomer(){
		customerService.assignCustomersToDecidedZone(customerIds,model.getId());
		return "list";
		
	}
}
