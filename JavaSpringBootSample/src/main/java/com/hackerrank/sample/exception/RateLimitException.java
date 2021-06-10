package com.hackerrank.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class RateLimitException extends RuntimeException {

	private static final long serialVersionUID = 2971576721206282789L;

	public RateLimitException(String msg) {
        super(msg);
    }
}
