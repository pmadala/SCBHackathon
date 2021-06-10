package com.hackerrank.sample.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.sample.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	@Transactional
	String deleteById(String id);

	@Transactional
	User findByUsername(String username);

	List<User> findAllByDateCreatedBetween(Long start, Long end);
}
