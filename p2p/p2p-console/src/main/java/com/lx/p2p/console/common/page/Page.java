package com.lx.p2p.console.common.page;

import java.util.List;

/**
 * 基类page中包含公有翻页参数及保存查询到的结果以被页面遍历， 被子类继承后将增加不同的查询条件 。
 */
public class Page {

	/** 每页显示条数默认为10条 */
	public static final int DEFAULT_SIZE = 10;

	/** 当前页码， 从1开始计 */
	private long number;

	/** 每页条数 */
	private int size;

	/** 总条数 */
	private long totalElements;

	/** 当前页数据 */
	private List<? extends Object> datas;

	private long beginPage; // 开始页数

	private long endPage; // 结束页数

	private int pagerRange = 7; // 中间显示多少页数

	private boolean allTag;// 全部显示结果列表的标志

	public long getBeginPage() {
		return this.beginPage = Math.max(1, this.getNumber() - this.getPagerRange() / 2);
	}

	public long getEndPage() {
		return Math.min(this.getBeginPage() + (this.getPagerRange() - 1), this.getTotalPages());
	}

	public int getPagerRange() {
		return pagerRange;
	}

	public void setPagerRange(int pagerRange) {
		this.pagerRange = pagerRange;
	}

	public Page() {
		// 设置默认值
		number = 1;
		size = DEFAULT_SIZE;
	}

	/** 获取当前页码 */
	public long getNumber() {
		return number;
	}

	/** 设置当前页码 */
	public void setNumber(long number) {
		this.number = number;
	}

	/** 获取每页显示条数 */
	public int getSize() {
		return size;
	}

	/** 设置每页显示条数 */
	public void setSize(int size) {
		this.size = size;
	}

	/** 获取当前页数据 */
	public List<? extends Object> getDatas() {
		return datas;
	}

	/** 设置当前页数据 */
	public void setDatas(List<? extends Object> datas) {
		this.datas = datas;
	}

	/** 是否有数据 */
	public boolean hasDatas() {
		if (datas != null && !datas.isEmpty())
			return true;

		return false;
	}

	/** 获取总条数 */
	public long getTotalElements() {
		return totalElements;
	}

	/** 设置总条数 */
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	/** 获取总页数 */
	public long getTotalPages() {
		if (!hasDatas())
			return 0;

		long totalPages = totalElements / size;
		if (totalElements % size != 0) {
			totalPages++;
		}

		return totalPages;
	}

	/** 是否是第一页 */
	public boolean isFirstPage() {
		return number == 1 ? true : false;
	}

	/** 是否是最后一页 */
	public boolean isLastPage() {
		return number == getTotalPages() ? true : false;
	}

	/** 获取从第几条数据开始查询 */
	public long getStart() {
		return (number - 1) * size;
	}

	public void setBeginPage(long beginPage) {
		this.beginPage = beginPage;
	}

	public void setEndPage(long endPage) {
		this.endPage = endPage;
	}

	public boolean getAllTag() {
		return allTag;
	}

	/**
	 * 
	 * 设置全部查出结果，即sql语句不添加limit子句
	 * 
	 * @version 2017年5月22日下午11:25:16
	 * @author zhuwenbin
	 * @param allTag
	 */
	public void setAllTag(boolean allTag) {
		this.allTag = allTag;
	}

}