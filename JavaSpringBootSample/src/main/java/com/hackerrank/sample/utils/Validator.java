package com.hackerrank.sample.utils;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hackerrank.sample.exception.AccountBalanceValidationException;
import com.hackerrank.sample.model.AccountBalance;

@Component
public class Validator {

	@Value("${account.dPercentage}")
	private String dPercentage;

	@Value("${account.wPercentage}")
	private String wPercentage;

	public void deposit(AccountBalance accountBalance, BigDecimal amount) {
		BigDecimal balance = new BigDecimal(accountBalance.getBalance());
		if (balance.intValue() != 0) {
			if (balance.multiply(new BigDecimal(dPercentage)).divide(new BigDecimal(100)).compareTo(amount) == -1)
				throw new AccountBalanceValidationException("No validation set for now, so unable to process");
			else
				return;
		} else {
			return;
		}
	}

	public void withdraw(AccountBalance accountBalance, BigDecimal amount) {
		BigDecimal balance = new BigDecimal(accountBalance.getBalance());
		System.out.println(balance);
		System.out.println(wPercentage);
		if (balance.multiply(new BigDecimal(wPercentage)).divide(new BigDecimal(100)).compareTo(amount) == -1)
			throw new AccountBalanceValidationException(
					balance.multiply(new BigDecimal(wPercentage)).divide(new BigDecimal(100)) + " should be less than "
							+ amount);
		else
			return;
	}

}
