package com.bestaone.aiwan.auth.service;

import com.bestaone.aiwan.auth.domain.Account;

import java.util.List;

public interface AccountService {

    Account save(Account account);

    Account findById(Long id);

    List<Account> findAll();

    void delete(Long id);

}
