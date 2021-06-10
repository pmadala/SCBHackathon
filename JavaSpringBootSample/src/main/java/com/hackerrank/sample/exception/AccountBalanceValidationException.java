package com.hackerrank.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountBalanceValidationException extends RuntimeException {

	private static final long serialVersionUID = -5730693311269332626L;

	public AccountBalanceValidationException(String msg) {
        super(msg);
    }
}
