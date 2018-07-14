package cn.itcast.bos.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.IDecidedzoneDao;
import cn.itcast.bos.dao.ISubareaDao;
import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.IDecidedzoneService;
import cn.itcast.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {

	@Resource
	private IDecidedzoneDao decidezoneDao;
	@Resource
	private ISubareaDao subareaDao;
	
	
	//添加
	@Override
	public void add(Decidedzone model, String[] subareaid) {
		//1.保存model，同时保存定区关联的取派员:更新外键
		decidezoneDao.save(model);
		
		//2.保存定区关联的分区
		for (String id : subareaid) {
			Subarea subarea = subareaDao.findById(id);
			
			subarea.setDecidedzone(model);
		}
		
	}


	@Override
	public void pageQuery(PageBean pageBean) {
		decidezoneDao.pageQuery(pageBean);
	}
	
	
}
