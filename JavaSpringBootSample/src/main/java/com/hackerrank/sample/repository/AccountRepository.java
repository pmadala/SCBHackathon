package com.hackerrank.sample.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.sample.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, String> {
	@Transactional
	String deleteById(String id);

	List<Account> findAllByDateCreatedBetween(Long start, Long end);
}
