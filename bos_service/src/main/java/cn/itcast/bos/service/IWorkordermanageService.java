package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Workordermanage;
import cn.itcast.bos.utils.PageBean;

public interface IWorkordermanageService{

	void add(Workordermanage model);

	void pageQuery(PageBean pageBean);

	void saveBatch(List<Workordermanage> list);

	List<Workordermanage> findAll();

}
