package com.hackerrank.sample.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.sample.annotation.RateLimit;
import com.hackerrank.sample.model.Account;
import com.hackerrank.sample.model.AccountBalance;
import com.hackerrank.sample.service.AccountBalanceService;
import com.hackerrank.sample.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountBalanceService accountBalanceService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@RateLimit(usage = 10, duration = 1, type = "MINUTE")
	public void createNewAccount(@RequestBody @Valid Account account) {
		accountService.createAccount(account);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@RateLimit(usage = 1000, duration = 5, type = "MINUTE")
	public void updateAccount(@PathVariable String id, @RequestBody Account account) {
		accountService.updateAccount(account);
	}

	@RequestMapping(value = "/{id}/deposit", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@RateLimit(usage = 100, duration = 5, type = "MINUTE")
	public AccountBalance deposit(@PathVariable String id, @RequestBody String amount) {
		return accountBalanceService.deposit(id, amount);
	}

	@RequestMapping(value = "/{id}/withdraw", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@RateLimit(usage = 100, duration = 5, type = "MINUTE")
	public AccountBalance withdraw(@PathVariable String id, @RequestBody String amount) {
		return accountBalanceService.withdraw(id, amount);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAccountById(@PathVariable String id) {
		accountService.deleteAccountById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@RateLimit(usage = 3, duration = 10, type = "SECOND")
	public Account getAccountById(@PathVariable String id) {
		return accountService.getAccountById(id);
	}
}
