package net.dqsy.papermg.util;

import java.io.Serializable;
import java.util.List;

public class PagingSupport implements Serializable {
	private int lastPage;
	private int nowPage;
	private int nextPage;
	private int totalPage;
	private int totalCount;
	private List list;

	public PagingSupport(List list, int nowPage, int totalCount, int length) {
		this.list = list;
		this.lastPage = (nowPage - 1);
		this.nowPage = nowPage;
		this.nextPage = (nowPage + 1);
		this.totalCount = totalCount;
		this.totalPage = (totalCount % length == 0 ? totalCount / length : totalCount / length + 1);
	}

	public int getLastPage() {
		return this.lastPage;
	}

	public int getNowPage() {
		return this.nowPage;
	}

	public int getNextPage() {
		return this.nextPage;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public List getList() {
		return this.list;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}