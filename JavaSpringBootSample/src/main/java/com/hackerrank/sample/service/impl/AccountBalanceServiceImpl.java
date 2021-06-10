package com.hackerrank.sample.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.model.AccountBalance;
import com.hackerrank.sample.model.DepositLog;
import com.hackerrank.sample.repository.AccountBalanceRepository;
import com.hackerrank.sample.repository.DepositLogRepository;
import com.hackerrank.sample.service.AccountBalanceService;
import com.hackerrank.sample.utils.Validator;

@Service("accountBalanceService")
public class AccountBalanceServiceImpl implements AccountBalanceService {
	@Autowired
	private AccountBalanceRepository accountBalanceRepository;
	
	@Autowired
	private DepositLogRepository depositLogRepository;

	@Autowired
	private Validator validator;
	
	@Override
	public void deleteAllAccountBalances() {
		accountBalanceRepository.deleteAllInBatch();
	}

	@Override
	public void deleteAccountBalanceById(String id) {
		accountBalanceRepository.deleteById(id);
	}

	@Override
	public void createAccountBalance(AccountBalance AccountBalance) {
		AccountBalance existingAccountBalance = accountBalanceRepository.findOne(AccountBalance.getId());

		if (existingAccountBalance != null) {
			throw new BadResourceRequestException("AccountBalance with same id exists.");
		}

		accountBalanceRepository.save(AccountBalance);
	}

	@Override
	public AccountBalance getAccountBalanceById(String id) {
		AccountBalance AccountBalance = accountBalanceRepository.findOne(id);

		if (AccountBalance == null) {
			throw new NoSuchResourceFoundException("No AccountBalance with given id found.");
		}

		return AccountBalance;
	}

	@Override
	public List<AccountBalance> getAllAccountBalances() {
		return accountBalanceRepository.findAll();
	}

	@Override
	public AccountBalance deposit(String accountId, String inAmount) {
		BigDecimal amount = new BigDecimal(inAmount);
		AccountBalance accountBalance = getAccountBalanceById(accountId);

		// TODO Auto-generated method stub
		validator.deposit(accountBalance, amount);
		
		BigDecimal oldBalance = new BigDecimal(accountBalance.getBalance());
		BigDecimal newBalance = oldBalance.add(amount);
				
		accountBalance.setBalance(newBalance.toString());
		
		accountBalanceRepository.save(accountBalance);
		
		DepositLog depositLog = new DepositLog();
		depositLog.setAccountId(accountBalance.getId());
		depositLog.setDeposit(amount.toString());
		depositLogRepository.save(depositLog);
		
		return accountBalance;
	}

	@Override
	public AccountBalance withdraw(String accountId, String inAmount) {
		BigDecimal amount = new BigDecimal(inAmount);
		AccountBalance accountBalance = getAccountBalanceById(accountId);

		// TODO Auto-generated method stub
		validator.withdraw(accountBalance, amount);
		
		BigDecimal oldBalance = new BigDecimal(accountBalance.getBalance());
		BigDecimal newBalance = oldBalance.subtract(amount);
				
		accountBalance.setBalance(newBalance.toString());
		
		accountBalanceRepository.save(accountBalance);
		return accountBalance;
	}

}
