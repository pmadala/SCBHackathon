package com.hackerrank.sample.service;

import java.util.List;

import com.hackerrank.sample.model.AccountBalance;

public interface AccountBalanceService {
    
	void createAccountBalance(AccountBalance accountBalance);

	AccountBalance getAccountBalanceById(String id);
	
	List<AccountBalance> getAllAccountBalances();

	void deleteAllAccountBalances();

    void deleteAccountBalanceById(String id);

	AccountBalance deposit(String accountId, String amount);

	AccountBalance withdraw(String accountId, String amount);
}
