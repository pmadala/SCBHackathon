package com.hackerrank.sample.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.sample.service.ReportingService;

@RestController("/api/reporting")
public class ReportingController {
	@Autowired
	private ReportingService reportingService;

	/*
	 * @RequestMapping(value = "/all", method = RequestMethod.GET)
	 * 
	 * @ResponseStatus(HttpStatus.OK) public Report generateReport() { return
	 * reportingService.generateReport(); }
	 */

	@RequestMapping(value = "/new_accounts", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Integer getNewAccountCount(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy")
 Date from, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy")
 Date to) {
		return reportingService.getNewAccountCount(from, to);
	}

	@RequestMapping(value = "/min_balance_accounts", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Integer getMinBalanceAccountCount(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy")
	 Date from, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy")
	 Date to) {
		return reportingService.getMinBalanceAccountCount(from, to);
	}

	@RequestMapping(value = "/max_deposit_accounts", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Integer getMaxDepositAccountCount(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy")
	 Date from, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy")
	 Date to) {
		return reportingService.getMaxDepositAccountCount(from, to);
	}
	
}
