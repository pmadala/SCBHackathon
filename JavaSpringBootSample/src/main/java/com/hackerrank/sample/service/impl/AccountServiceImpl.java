package com.hackerrank.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.model.Account;
import com.hackerrank.sample.model.AccountBalance;
import com.hackerrank.sample.repository.AccountBalanceRepository;
import com.hackerrank.sample.repository.AccountRepository;
import com.hackerrank.sample.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountBalanceRepository accountBalanceRepository;

	@Override
	public void deleteAllAccounts() {
		accountRepository.deleteAllInBatch();
	}

	@Override
	public void deleteAccountById(String id) {
		accountRepository.deleteById(id);
	}

	@Override
	public void createAccount(Account account) {
		Account existingAccount = accountRepository.findOne(account.getId());

		if (existingAccount != null) {
			throw new BadResourceRequestException("Account with same id exists.");
		}

		accountRepository.save(account);
		
		AccountBalance accountBalance = new AccountBalance();

		accountBalance.setId(account.getId());
		accountBalance.setAccount(account.getId());
		accountBalance.setBalance("0");
		accountBalance.setLast4(account.getLast4());
		
		accountBalanceRepository.save(accountBalance);
	}

	@Override
	public Account getAccountById(String id) {
		Account account = accountRepository.findOne(id);

		if (account == null) {
			throw new NoSuchResourceFoundException("No Account with given id found.");
		}

		return account;
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public void updateAccount(Account inAccount) {
		// Only editable fields are metadata, country, currency, fingerprint, status

		Account account = getAccountById(inAccount.getId());

		if (inAccount.getCountry() != null) {
			account.setCountry(inAccount.getCountry());
		}
		if (inAccount.getCurrency() != null) {
			account.setCurrency(inAccount.getCurrency());
		}
		if (inAccount.getFingerprint() != null) {
			account.setFingerprint(inAccount.getFingerprint());
		}
		if (inAccount.getStatus() != null) {
			account.setStatus(inAccount.getStatus());
		}
		
		accountRepository.save(account);

	}
}
