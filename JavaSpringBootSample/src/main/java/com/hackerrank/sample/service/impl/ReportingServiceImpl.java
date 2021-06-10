package com.hackerrank.sample.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.service.ReportGenerationService;
import com.hackerrank.sample.service.ReportingService;

@Service("reportingService")
public class ReportingServiceImpl implements ReportingService {
	
	
	@Autowired
	ReportGenerationService reportGenerationService;

	public int getNewAccountCount(Date from, Date to) {
		return reportGenerationService.findBetween(from.getTime(), to.getTime()).getNewAccounts();
	}

	public int getMinBalanceAccountCount(Date from, Date to) {
		return reportGenerationService.getMinBalanceAccounts(from.getTime(), to.getTime()).getMinBalanceAccounts();
	}
	
	public int getMaxDepositAccountCount(Date from, Date to) {
		return reportGenerationService.getMaxDepositAccounts(from.getTime(), to.getTime()).getMaxDepositInAccountCount();
	}

}
