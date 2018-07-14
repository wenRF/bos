package cn.itcast.bos.dao;

import cn.itcast.bos.dao.base.IBaseDao;
import cn.itcast.bos.domain.User;

public interface IUserDao extends IBaseDao<User>{

	User finByUsernameAndPassword(String username, String password);



}
