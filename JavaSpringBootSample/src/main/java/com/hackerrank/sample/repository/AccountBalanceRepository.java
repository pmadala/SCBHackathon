package com.hackerrank.sample.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.sample.model.AccountBalance;

@Repository("accountBalanceRepository")
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, String> {
    @Transactional
    String deleteById(String id);
    
    @Transactional
    AccountBalance findFirstByOrderByBalanceAsc();
    
    List<AccountBalance> findAllByBalance(String balance);
}
	