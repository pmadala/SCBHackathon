package com.hackerrank.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchResourceFoundException extends RuntimeException {

	private static final long serialVersionUID = 6474686089976998994L;

	public NoSuchResourceFoundException(String msg) {
        super(msg);
    }
}
