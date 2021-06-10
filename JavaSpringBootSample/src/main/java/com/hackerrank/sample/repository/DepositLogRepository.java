package com.hackerrank.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.sample.model.DepositLog;

@Repository("depositLogRepository")
public interface DepositLogRepository extends JpaRepository<DepositLog, String> {

	List<DepositLog> findAllByDateCreatedBetween(Long from, Long to);

}
