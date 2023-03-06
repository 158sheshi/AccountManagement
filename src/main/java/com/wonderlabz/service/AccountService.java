package com.wonderlabz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonderlabz.entity.BankAccountEntity;
import com.wonderlabz.pojo.Account;
import com.wonderlabz.pojo.AccountType;
import com.wonderlabz.repositories.BankAccountRepository;

@Service
public class AccountService {

	@Autowired
	private BankAccountRepository accountRepository;

	@Autowired
	private TransactionHistoryService historyService;

	public Account depositAmount(Account account) {
		BankAccountEntity bankAccount = accountRepository.getById(account.getId());
		String message = "Account with number: " + bankAccount.getAccountNumber() + ", credited with balance "
				+ account.getAmount();
		bankAccount.setAmount(bankAccount.getAmount() + account.getAmount());
		accountRepository.save(bankAccount);
		historyService.addHistoryLog(message);

		return account;
	}

	public Account withDrawAmount(Account account) {
		BankAccountEntity bankAccount = accountRepository.getById(account.getId());
		String message = "Account with number: " + bankAccount.getAccountNumber() + ", debited with balance "
				+ account.getAmount();
		if (AccountType.SAVINGS.equals(account.getType()) && (bankAccount.getAmount() - account.getAmount() >= 1000)) {
			bankAccount.setAmount(bankAccount.getAmount() - account.getAmount());
			accountRepository.save(bankAccount);
			historyService.addHistoryLog(message);

		} else if (AccountType.CURRENT.equals(account.getType())
				&& (bankAccount.getAmount() - account.getAmount() >= -100000)) {
			bankAccount.setAmount(bankAccount.getAmount() - account.getAmount());
			accountRepository.save(bankAccount);
			historyService.addHistoryLog(message);
		} else {
			historyService.addHistoryLog("Unable to debit amount from Account with number: " + bankAccount.getAccountNumber() + ", for amount "
					+ account.getAmount());
		}
		return account;
	}

	public Account createAccount(Account account) {
		BankAccountEntity bankAccount = generateBankAccountEntity(account);
		long accountNumber = generateAccountNumber();
		bankAccount.setAccountNumber(accountNumber);
		accountRepository.save(bankAccount);
		account.setId(bankAccount.getAccountNumber());

		String message = "Account with number: " + bankAccount.getAccountNumber() + ", created with balance "
				+ bankAccount.getAmount();
		historyService.addHistoryLog(message);

		return account;
	}

	private BankAccountEntity generateBankAccountEntity(Account account) {
		BankAccountEntity bankAccount = new BankAccountEntity();
		bankAccount.setAmount(account.getAmount());
		bankAccount.setType(account.getType().toString());

		return bankAccount;
	}

	private long generateAccountNumber() {
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return number;
	}

}
