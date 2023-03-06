package com.wonderlabz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wonderlabz.pojo.Account;
import com.wonderlabz.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/createAccount")
	public Account createAccount(@RequestBody Account account) {
		account = accountService.createAccount(account);
		return account;
	}
	
	@PostMapping("/withdrawAmount")
	public Account withdrawAmount(@RequestBody Account account) {
		account = accountService.withDrawAmount(account);
		return account;
	}
	
	@PostMapping("/depositAmount")
	public Account depositAmount(@RequestBody Account account) {
		account = accountService.depositAmount(account);
		return account;
	}
	
}
