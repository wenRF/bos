package cn.itcast.bos.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.IUserDao;
import cn.itcast.bos.dao.base.Impl.BaseDaoImpl;
import cn.itcast.bos.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{

	@Override
	public User finByUsernameAndPassword(String username, String password) {
											
		String hql="from User where username=? and password =?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username,password);
		
		if(list!=null&&list.size()>0){
			
			return list.get(0);
 		}
		
		return null;
	}

}
