package com.hackerrank.sample.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "report")
public class Report extends BaseModel implements Serializable {

	private static final long serialVersionUID = -8757175965304341720L;

	private Long start;
	private Long end;

	private Integer newAccounts;
	private Integer minBalanceAccounts;
	private Integer maxDepositInAccountCount;

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public Integer getMinBalanceAccounts() {
		return minBalanceAccounts;
	}

	public void setMinBalanceAccounts(Integer minBalanceAccounts) {
		this.minBalanceAccounts = minBalanceAccounts;
	}

	public Integer getNewAccounts() {
		return newAccounts;
	}

	public void setNewAccounts(Integer newAccounts) {
		this.newAccounts = newAccounts;
	}

	public int getMaxDepositInAccountCount() {
		return maxDepositInAccountCount;
	}

	public void setMaxDepositInAccountCount(int maxDepositInAccountCount) {
		this.maxDepositInAccountCount = maxDepositInAccountCount;
	}

}
