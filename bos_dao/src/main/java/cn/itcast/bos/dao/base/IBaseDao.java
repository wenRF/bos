package cn.itcast.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.utils.PageBean;

/*
 * 通用的增删改查接口
 */
public interface IBaseDao<T> {

	public void save(T entity);
	public void saveOrUpdate(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T findById(Serializable id);
	public List<T> findAll();
	//命名查询  通用的更新
	void executeUpdate(String namedQuery,Object...objects);
	
	//分页查询
	void pageQuery(PageBean pageBean);
	
	//离线条件查询
	List<T> findByCriteria(DetachedCriteria detachedCriteria);
}
