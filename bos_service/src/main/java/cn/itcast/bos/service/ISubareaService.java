package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.utils.PageBean;

public interface ISubareaService{

	void save(Subarea model);

	void pageQuery(PageBean pageBean);

	List<Subarea> findAll();

	List<Subarea> findNoGuanLian();

	void saveFile(List<Subarea> list);

	List<Subarea> findByDecidedzoneId(String decidedzoneId);

	List<Object> findGroupSubareas();

	void saveOrUpdate(Subarea model);

}
