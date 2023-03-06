package com.wonderlabz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wonderlabz.entity.BankAccountEntity;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long>{

}
