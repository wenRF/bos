package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.utils.PageBean;

public interface IStaffAervice {

	void add(Staff model);

	void pageQuery(PageBean pageBean);

	void delete(String ids);

	void edit(Staff model);

	void restore(String ids);

	List<Staff> findNotDelete();

}
