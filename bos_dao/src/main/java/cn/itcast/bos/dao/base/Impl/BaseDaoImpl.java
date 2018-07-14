package cn.itcast.bos.dao.base.Impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.bos.dao.base.IBaseDao;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	Class<T> entityClass;
	
	//构造方法动态获取泛型
	public  BaseDaoImpl(){
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		
		Type[] types = parameterizedType.getActualTypeArguments();
		
		entityClass=(Class<T>) types[0];
	}
	
	
	@Resource//可以根据id属性注入，也可以根据类型
//	@Autowired//只能可以根据类型，如果想通过id注入
//	@Qualifier(value="")
	public void setMySessionFactory(SessionFactory sessionFactory){
		
		this.setSessionFactory(sessionFactory);
	}
	
	
	
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		
		
		return this.getHibernateTemplate().get(entityClass, id);
		
	}

	@Override
	public List<T> findAll() {
		
		String hql="from "+entityClass.getName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}


	@Override
	public void executeUpdate(String namedQuery, Object... objects) {
		//获取当前线程session
		Session currentSession = this.getSessionFactory().getCurrentSession();
		//根据session获得sql  在映射文件中写sql语句
		Query query = currentSession.getNamedQuery(namedQuery);
		//给sql中的？赋值
		int index=0;
		for (Object object : objects) {
			query.setParameter(index++, object);
		}
		//更新
		query.executeUpdate();
	}


	
	//分页查询
	@Override
	public void pageQuery(PageBean pageBean) {
		//获取离线查询
		DetachedCriteria dc = pageBean.getDc();
		//1.查询总条数
			//1.1修改hibernate发送SQL的格式：从select * from Staff修改成select count(*) from Staff
		dc.setProjection(Projections.rowCount()); //添加聚合函数
		List<Long> totalList = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
			//封装到pageBean
		pageBean.setTotal(totalList.get(0));
		//2.查询每页的具体数据列表
			//2.1恢复hibernate发送sql语句的格式
		dc.setProjection(null);
		
		dc.setResultTransformer(DetachedCriteria.ROOT_ENTITY);//不添加这一句不分页显示
		int currentPage = pageBean.getCurrentPage();		
		int pageSize = pageBean.getPageSize();
		int firstResult=(currentPage-1)*pageSize;//从第几条开始查询
		int maxResults=pageSize;//最多查询多少条
		List<?> rows = this.getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
			//2.2将返回的数据封装到pageBean
		pageBean.setRows(rows);
	}


	@Override
	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}


	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}
	
	
}
