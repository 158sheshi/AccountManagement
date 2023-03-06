package com.wonderlabz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wonderlabz.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer>{

}
