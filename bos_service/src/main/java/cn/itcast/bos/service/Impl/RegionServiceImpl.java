package cn.itcast.bos.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cn.itcast.bos.dao.IRegionDao;
import cn.itcast.bos.dao.ISubareaDao;
import cn.itcast.bos.domain.Region;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.IRegionService;
import cn.itcast.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

	@Resource
	private IRegionDao regionDao;
	
	private ISubareaDao subareaDao;
	
	@Override
	public void saveBatch(List<Region> list) {
		for (Region region : list) {
			regionDao.saveOrUpdate(region);
		}
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	@Override
	public List<Region> findByQ(String q) {
		return regionDao.findByQ(q);
	}

	@Override
	public void saveOrUpdate(Region model) {
		regionDao.saveOrUpdate(model);
	}

	@Override
	public void save(Region model) {
		regionDao.save(model);
	}

	//删除区域
	@Override
	public void delete(String ids) {
		//通过拆分成多个数组，然后循环更新
		String[] Ids = ids.split(",");
		
		for (String id : Ids) {
			
			
			Region region = regionDao.findById(id);
			regionDao.delete(region);
				 
			} 
			//regionDao.de
			
		
	}

	@Override
	public Region findById(String region_id) {
		Region region = regionDao.findById(region_id);
		return region;
	}


}
