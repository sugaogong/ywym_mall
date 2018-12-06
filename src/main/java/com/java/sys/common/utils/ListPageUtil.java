package com.java.sys.common.utils;

import java.util.Collections;
import java.util.List;

/**
 * list集合分页工具
 * @author Administrator
 *
 */
public class ListPageUtil<T> {
	
	private List<T> data;// 原集合
	private int lastPage;// 上一页
	private int nowPage;// 当前页
	private int nextPage;// 下一页
	private int pageSize;// 页面大小
	private int totalPage;// 总页数
	private int totalCount;// 总数据条数

	public int getPageSize() {
		return pageSize;
	}

	public List<T> getData() {
		return data;
	}

	public int getLastPage() {
		return lastPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}
	
	public ListPageUtil(List<T> data, int nowPage, int pageSize) {
		if (data == null || data.isEmpty()) {
			throw new IllegalArgumentException("data must be not empty!");
		}

		this.data = data;
		this.pageSize = pageSize;
		this.nowPage = nowPage;
		this.totalCount = data.size();
		this.totalPage = (totalCount + pageSize - 1) / pageSize;
		this.lastPage = nowPage - 1 > 1 ? nowPage - 1 : 1;
		this.nextPage = nowPage >= totalPage ? totalPage : nowPage + 1;

	}

	/**
	 * 得到分页后的数据
	 * 页码
	 * @return 分页后结果
	 */
	public List<T> getPagedList() {
		int fromIndex = (nowPage - 1) * pageSize;
		if (fromIndex >= data.size()) {
			return Collections.emptyList();// 空数组
		}
		if (fromIndex < 0) {
			return Collections.emptyList();// 空数组
		}
		int toIndex = nowPage * pageSize;
		if (toIndex >= data.size()) {
			toIndex = data.size();
		}
		return data.subList(fromIndex, toIndex);
	}
}