package com.hackerrank.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.sample.model.Report;

@Repository("reportingRepository")
public interface ReportingRepository extends JpaRepository<Report, String> {
	
}
