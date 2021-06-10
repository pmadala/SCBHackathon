package com.hackerrank.sample.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackerrank.sample.BaseTest;
import com.hackerrank.sample.exception.AccountBalanceValidationException;
import com.hackerrank.sample.model.Account;
import com.hackerrank.sample.model.AccountBalance;

public class AccountBalanceServiceTest extends BaseTest {

	@Autowired
	private AccountBalanceService accountBalanceService;
	
	@Autowired
	private AccountService accountService;

	@Before
	public void cleanStart() {
		
		accountService.deleteAllAccounts();
		
		for (int i = 0; i < 3; i++) {
			Account account1 = new Account();
			
			account1.setId("id"+i);
			account1.setName("account"+i);
			account1.setCountry("country"+i);
			account1.setCurrency("dummy"+i);
			account1.setCustomer("customer"+i);
			account1.setFingerprint("dummy"+i);
			account1.setLast4("5678");
			account1.setStatus("NEW");
			account1.setType("SAVINGS");
			
			accountService.createAccount(account1);
		}
		
	}
	
	@Test
	public void createAccountBalance() {
		AccountBalance accountBalance = new AccountBalance();
		accountBalance.setId("id34");
		accountBalance.setBalance("8900");
		accountBalance.setAccount("id34");
		accountBalance.setLast4("9090");
		accountBalanceService.createAccountBalance(accountBalance);
		
		AccountBalance actualAccountBalance = accountBalanceService.getAccountBalanceById("id34");
		Assert.assertEquals("id34", actualAccountBalance.getId());
	}

	@Test
	public void getAccountBalanceById() {
		AccountBalance accountBalance = accountBalanceService.getAccountBalanceById("id1");
		Assert.assertEquals("id1", accountBalance.getId());
	}
	
	@Test
	public void getAllAccountBalances(){
		List<AccountBalance> accountBalances = accountBalanceService.getAllAccountBalances();
		Assert.assertEquals(accountBalances.size(), 3);
	}

	@Test
	public void deleteAllAccountBalances() {
		accountBalanceService.deleteAllAccountBalances();
		List<AccountBalance> accountBalances = accountBalanceService.getAllAccountBalances();
		Assert.assertEquals(accountBalances.size(), 0);
	}

	@Test
	public void deleteAccountBalanceById() {
		accountBalanceService.deleteAccountBalanceById("id1");
		List<AccountBalance> accountBalances = accountBalanceService.getAllAccountBalances();
		Assert.assertEquals(accountBalances.size(), 2);
	}

	@Test
	public void deposit() {
		AccountBalance accountBalance = accountBalanceService.getAccountBalanceById("id1");
		Assert.assertEquals("0", accountBalance.getBalance());
		accountBalanceService.deposit("id1", "100");
		
		accountBalance = accountBalanceService.getAccountBalanceById("id1");
		Assert.assertEquals("100", accountBalance.getBalance());
	}

	@Test
	public void withdraw() {
		AccountBalance accountBalance = accountBalanceService.getAccountBalanceById("id1");
		Assert.assertEquals("0", accountBalance.getBalance());
		accountBalanceService.deposit("id1", "1000");
		accountBalanceService.withdraw("id1", "100");
		
		accountBalance = accountBalanceService.getAccountBalanceById("id1");
		Assert.assertEquals("900", accountBalance.getBalance());
	}
	
	
	@Test
	public void withdrawFailedForwPercetage10() {
		AccountBalance accountBalance = accountBalanceService.getAccountBalanceById("id1");
		Assert.assertEquals("0", accountBalance.getBalance());
		accountBalanceService.deposit("id1", "1000");
		try {
			accountBalanceService.withdraw("id1", "700");
		} catch (AccountBalanceValidationException ex) {
			Assert.assertTrue(true);
			return;
		}
		Assert.assertTrue(false);
	}
	
	
	@Test
	public void depositeFailedFordPercetage20() {
		AccountBalance accountBalance = accountBalanceService.getAccountBalanceById("id1");
		Assert.assertEquals("0", accountBalance.getBalance());
		accountBalanceService.deposit("id1", "1000");
		try {
			accountBalanceService.deposit("id1", "1000");
		} catch (AccountBalanceValidationException ex) {
			Assert.assertTrue(true);
			return;
		}
		Assert.assertTrue(false);
	}


}
