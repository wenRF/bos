package cn.itcast.bos.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IWorkordermanageDao;
import cn.itcast.bos.domain.Workordermanage;
import cn.itcast.bos.service.IWorkordermanageService;
import cn.itcast.bos.utils.PageBean;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {

	@Resource
	private IWorkordermanageDao workordermanageDao;

	@Override
	public void add(Workordermanage model) {
		workordermanageDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		workordermanageDao.pageQuery(pageBean);
	}

	public void saveBatch(List<Workordermanage> list) {
		for(Workordermanage wd : list){
			workordermanageDao.saveOrUpdate(wd);
		}
	}

	@Override
	public List<Workordermanage> findAll() {
		return workordermanageDao.findAll();
	}

}
