package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Noticebill;
import cn.itcast.bos.utils.PageBean;

public interface INoticebillService{

	void add(Noticebill model);

	void pageQuery(PageBean pageBean);


}
