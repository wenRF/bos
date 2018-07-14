package cn.itcast.bos.service;

import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.utils.PageBean;

public interface IDecidedzoneService {

	void add(Decidedzone model, String[] subareaid);

	void pageQuery(PageBean pageBean);

}
