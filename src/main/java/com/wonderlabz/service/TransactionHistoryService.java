package com.wonderlabz.service;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonderlabz.entity.TransactionHistory;
import com.wonderlabz.repositories.TransactionHistoryRepository;

@Service
public class TransactionHistoryService {
	
	@Autowired
	private TransactionHistoryRepository historyRepository;

	public void addHistoryLog(String message) {
		TransactionHistory transactionHistory = transactionHistoryBuild(message);
		historyRepository.save(transactionHistory);
	}

	private TransactionHistory transactionHistoryBuild(String message) {
		TransactionHistory transactionHistory = new TransactionHistory();
		Calendar calendar = Calendar.getInstance();
		transactionHistory.setCreateDate(new Timestamp(calendar.getTimeInMillis()));
		transactionHistory.setMessage(message);
		return transactionHistory;
	}

}
