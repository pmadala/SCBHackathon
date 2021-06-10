package com.hackerrank.sample.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.model.AccountBalance;
import com.hackerrank.sample.model.DepositLog;
import com.hackerrank.sample.model.Report;
import com.hackerrank.sample.repository.AccountBalanceRepository;
import com.hackerrank.sample.repository.AccountRepository;
import com.hackerrank.sample.repository.DepositLogRepository;
import com.hackerrank.sample.repository.ReportingRepository;
import com.hackerrank.sample.service.ReportGenerationService;

@Service("reportGenerationService")
public class ReportGenerationServiceImpl implements ReportGenerationService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private DepositLogRepository depositLogRepository;

	@Autowired
	private AccountBalanceRepository accountBalanceRepository;

	@Autowired
	ReportingRepository reportingRepository;
	
	@Transactional
	public Report findBetween(Long from, Long to) {
		Report report = new Report();
		report.setStart(from);
		report.setEnd(to);
		report.setNewAccounts(accountRepository.findAllByDateCreatedBetween(from, to).size());
		report.setMinBalanceAccounts(0);
		report.setMaxDepositInAccountCount(0);
		report = reportingRepository.save(report);
		return report;
	}

	public  Report getMinBalanceAccounts(Long from, Long to) {
		Report report = new Report();
		report.setEnd(to);
		report.setStart(from);
		report.setNewAccounts(0);
		report.setMaxDepositInAccountCount(0);
		
		AccountBalance accountBalance = (AccountBalance) accountBalanceRepository.findFirstByOrderByBalanceAsc();
		String balance = "0";
		if (accountBalance != null ) {
			balance = accountBalance.getBalance();
		}
	
		report.setMinBalanceAccounts(accountBalanceRepository.findAllByBalance(balance).size());
		report = reportingRepository.save(report);
		return report;
	}

	public Report getMaxDepositAccounts(Long from, Long to) {
		Report report = new Report();
		report.setId(UUID.randomUUID().toString());
		report.setEnd(to);
		report.setStart(from);
		report.setNewAccounts(0);
		report.setMinBalanceAccounts(0);
		
		List<DepositLog> depositesBetweenDates = depositLogRepository.findAllByDateCreatedBetween(from, to);
		
		Map<String, Integer> accountIdVsCount = new HashMap<>();
		
		for (DepositLog depositLog: depositesBetweenDates) {
			accountIdVsCount.put(depositLog.getAccountId(), accountIdVsCount.getOrDefault(depositLog.getAccountId(), 0)+1);
		}
		
		int maxDeposites = accountIdVsCount.entrySet().stream().
			max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue();
	
		report.setMaxDepositInAccountCount(maxDeposites);
		report = reportingRepository.save(report);
		return report;
	}
}
