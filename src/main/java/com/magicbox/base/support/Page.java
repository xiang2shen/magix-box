package com.magicbox.base.support;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 封装分页数据
 * 
 * @author xiangshuo
 *
 */
public class Page<T> implements Serializable, Iterable<T> {

	private static final long serialVersionUID = 1L;

	// 页码，从1开始
	private int pageNo = 1;
	// 分页大小
	private int pageSize = 10;
	// 记录集合
	private List<T> items = Collections.emptyList();
	// 总记录数
	private long count = 0L;
	
	/** 最大分页 */
	public static final int MAX_PAGE_SIZE = 1000;
	
	public Page(Integer pageNo, Integer pageSize, List<T> items, long count) {
		pageNo = null == pageNo ? 1 : pageNo;
		pageSize = null == pageSize ? 10 : pageSize;
		
		if (pageNo > 1) {
			this.pageNo = pageNo;
		}
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
		if (null != items) {
			this.items = items;
		}
		if (count > 0) {
			this.count = count;
		}
	}
	
	/**
	 * 获取当前页码对应的起始记录行数,从0开始
	 * 
	 * @return
	 */
	public int getStartRow() {
		return (pageNo - 1) * pageSize;
	}
	
	/**
	 * 获取当前页码对应的结尾记录行数,从0开始
	 * 
	 * @return
	 */
	public int getEndRow() {
		return pageNo * pageSize - 1;
	}
	
	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public int getPages() {
		return (int) (count / pageSize + ((count % pageSize == 0) ? 0 : 1));
	}
	
	/**
	 * 是否第一页
	 * 
	 * @return
	 */
	public boolean isFirstPage() {
		return pageNo <= 1;
	}
	
	/**
	 * 是否最后一页
	 * 
	 * @return
	 */
	public boolean isLastPage() {
		return pageNo >= getPages();
	}
	
	/**
	 * 是否有上一页
	 * 
	 * @return
	 */
	public boolean hasPrevPage() {
		return ! isFirstPage();
	}
	
	/**
	 * 是否有下一页
	 * 
	 * @return
	 */
	public boolean hasNextPage() {
		return ! isLastPage();
	}

	/**
	 * 获取页码，从1开始
	 * 
	 * @return
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 获取分页大小
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 获取记录集合
	 * 
	 * @return
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public long getCount() {
		return count;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageSize=" + pageSize + ", items=" + items + ", count=" + count + "]";
	}

	@Override
	public Iterator<T> iterator() {
		return items.iterator();
	}
	
}
