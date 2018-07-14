package cn.itcast.bos.dao;

import java.util.List;

import cn.itcast.bos.dao.base.IBaseDao;
import cn.itcast.bos.domain.Function;

public interface IFunctionDao extends IBaseDao<Function>{

	List<Function> findFunctionByuserId(String id);

	List<Function> findAllMenu();

	List<Function> findMenuByuserId(String id);

}
