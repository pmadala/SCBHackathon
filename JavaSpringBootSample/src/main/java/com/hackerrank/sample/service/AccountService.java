package com.hackerrank.sample.service;

import java.util.List;

import com.hackerrank.sample.model.Account;

public interface AccountService {
    
	void createAccount(Account account);

	Account getAccountById(String id);
	
	List<Account> getAllAccounts();

	void updateAccount(Account account);

	void deleteAllAccounts();
	
	void deleteAccountById(String id);
}
