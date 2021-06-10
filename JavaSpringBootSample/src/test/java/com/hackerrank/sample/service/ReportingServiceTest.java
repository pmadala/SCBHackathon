package com.hackerrank.sample.service;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackerrank.sample.BaseTest;
import com.hackerrank.sample.model.Account;

import org.junit.Assert;

public class ReportingServiceTest extends BaseTest {

	@Autowired
	private AccountBalanceService accountBalanceService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ReportingService reportingService;

	@Before
	public void cleanStart() {

		accountService.deleteAllAccounts();

		for (int i = 0; i < 3; i++) {
			Account account1 = new Account();

			account1.setId("id" + i);
			account1.setName("account" + i);
			account1.setCountry("country" + i);
			account1.setCurrency("dummy" + i);
			account1.setCustomer("customer" + i);
			account1.setFingerprint("dummy" + i);
			account1.setLast4("5678");
			account1.setStatus("NEW");
			account1.setType("SAVINGS");

			accountService.createAccount(account1);
			accountBalanceService.deposit("id" + i, "1000");
			accountBalanceService.withdraw("id" + i, "100");
		}

	}

	@Test
	public void getNewAccountCount() {
		Date now = new Date();
		Date from = new Date(now.getTime() - 24 * 3600 * 1000);

		int numberOfNewAccount = reportingService.getNewAccountCount(from, now);
		Assert.assertEquals(3, numberOfNewAccount);
	}

	@Test
	public void getMinBalanceAccountCount() {
		accountBalanceService.withdraw("id" + 2, "90");
		accountBalanceService.withdraw("id" + 1, "90");
		
		Date now = new Date();
		Date from = new Date(now.getTime() - 24 * 3600 * 1000);

		int numberOfMinBalanceAccount = reportingService.getMinBalanceAccountCount(from, now);
		Assert.assertEquals(2, numberOfMinBalanceAccount);
	}

	@Test
	public void getMaxDepositAccountCount() {
		accountBalanceService.deposit("id" + 2, "100");
		accountBalanceService.deposit("id" + 1, "100");
		
		Date now = new Date();
		Date from = new Date(now.getTime() - 24 * 3600 * 1000);

		int numberOfMaxBalanceAccount = reportingService.getMaxDepositAccountCount(from, now);
		Assert.assertEquals(6, numberOfMaxBalanceAccount);
	}
}
