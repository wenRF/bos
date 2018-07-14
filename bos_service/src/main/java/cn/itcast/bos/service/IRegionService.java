package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.utils.PageBean;

public interface IRegionService {

	void saveBatch(List<Region> list);

	void pageQuery(PageBean pageBean);

	List<Region> findAll();

	List<Region> findByQ(String q);

	void saveOrUpdate(Region model);

	void save(Region model);

	void delete(String ids);

	Region findById(String region_id);


}
