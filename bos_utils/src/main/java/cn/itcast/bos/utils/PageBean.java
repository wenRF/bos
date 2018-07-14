package cn.itcast.bos.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean {

	private int currentPage;//当前页
	private int pageSize;//每页条数
	private long total;//总条数
	private List<?> rows;//返回的当前页的数据
	private DetachedCriteria dc;//离线查询条件，封装查询条件的
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public DetachedCriteria getDc() {
		return dc;
	}
	public void setDc(DetachedCriteria dc) {
		this.dc = dc;
	}
	
	
}
