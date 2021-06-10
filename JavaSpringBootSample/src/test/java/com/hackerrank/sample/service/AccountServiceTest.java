
package com.hackerrank.sample.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackerrank.sample.BaseTest;
import com.hackerrank.sample.model.Account;

public class AccountServiceTest extends BaseTest {

	@Autowired
	private AccountService accountService;

	@Before
	public void cleanStart() {
		accountService.deleteAllAccounts();
		
		Account account1 = new Account();
		account1.setId("id1");
		account1.setName("account 1");
		account1.setCountry("country 1");
		account1.setCurrency("dummy 1");
		account1.setCustomer("customer 1");
		account1.setFingerprint("dummy 1");
		account1.setLast4("5678");
		account1.setStatus("NEW");
		account1.setType("SAVINGS");

		accountService.createAccount(account1);

		Account account2 = new Account();
		account2.setId("id2");
		account2.setName("account 2");
		account2.setCountry("country 2");
		account2.setCurrency("dummy 2");
		account2.setCustomer("customer 2");
		account2.setFingerprint("dummy 2");
		account2.setLast4("1234");
		account2.setStatus("NEW");
		account2.setType("SAVINGS");

		accountService.createAccount(account2);

		Account account3 = new Account();
		account3.setId("id3");
		account3.setName("account 3");
		account3.setCountry("country 3");
		account3.setCurrency("dummy 3");
		account3.setCustomer("customer 3");
		account3.setFingerprint("dummy 3");
		account3.setLast4("4356");
		account3.setStatus("NEW");
		account3.setType("SAVINGS");

		accountService.createAccount(account3);
	}

	@Test
	public void createAccount() {
		List<Account> allAccounts = accountService.getAllAccounts();

		Assert.assertEquals(allAccounts.size(), 3);
	}

	@Test
	public void getAccountById() {
		Account account = accountService.getAccountById("id1");
		Assert.assertEquals(account.getId(), "id1");
	}

	@Test
	public void getAllAccounts() {
		List<Account> allAccounts = accountService.getAllAccounts();
		Assert.assertEquals(allAccounts.size(), 3);
	}

	@Test
	public void updateAccount() {
		String country = "India";

		Account account = accountService.getAccountById("id1");
		account.setCountry(country);
		
		accountService.updateAccount(account);
		
		Account savedAccount = accountService.getAccountById("id1");
		
		Assert.assertEquals(savedAccount.getCountry(), country);
	}

	@Test
	public void updateAccountFailure() {

		Account account = accountService.getAccountById("id1");
		String oldName = account.getName(), newName = "TestUser";

		account.setName(newName);
		
		accountService.updateAccount(account);
		
		Account savedAccount = accountService.getAccountById("id1");
		
		Assert.assertEquals(savedAccount.getName(), oldName);
	}

	@Test
	public void deleteAccountById() {
		accountService.deleteAccountById("id1");
		List<Account> allAccounts = accountService.getAllAccounts();
		
		Assert.assertEquals(allAccounts.size(), 2);
	}

	@Test
	public void deleteAllAccounts() {
		accountService.deleteAllAccounts();
		List<Account> allAccounts = accountService.getAllAccounts();
		
		Assert.assertEquals(allAccounts.size(), 0);		
	}


}
