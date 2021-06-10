package com.hackerrank.sample.service;

import javax.transaction.Transactional;

import com.hackerrank.sample.model.Report;


public interface ReportGenerationService {

	@Transactional
	public Report findBetween(Long from, Long to);
	
	@Transactional
	public  Report getMinBalanceAccounts(Long from, Long to);
	
	@Transactional
	public Report getMaxDepositAccounts(Long from, Long to);
}
