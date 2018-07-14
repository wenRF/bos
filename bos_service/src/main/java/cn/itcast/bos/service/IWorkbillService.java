package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Workbill;
import cn.itcast.bos.utils.PageBean;

public interface IWorkbillService{

	void pageQuery(PageBean pageBean);

	void add(Workbill model);

	List<Workbill> findWorkBills();

}
