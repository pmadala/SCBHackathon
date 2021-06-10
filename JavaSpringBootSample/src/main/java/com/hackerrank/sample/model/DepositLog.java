package com.hackerrank.sample.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "deposit_log")
public class DepositLog extends BaseModel {

	private static final long serialVersionUID = 2606681193188312078L;

	private String deposit;
	private String accountId;

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


}
