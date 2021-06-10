package com.hackerrank.sample.service;

import java.util.Date;

public interface ReportingService {

	int getNewAccountCount(Date from, Date to) ;

	int getMinBalanceAccountCount(Date from, Date to) ;
	
	int getMaxDepositAccountCount(Date from, Date to) ;

}
