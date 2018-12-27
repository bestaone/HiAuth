package com.bestaone.aiwan.user.service;

import com.bestaone.aiwan.user.domain.Account;

import java.util.List;

public interface AccountService {

    Account save(Account account);

    Account findById(Long id);

    List<Account> findAll();

    void delete(Long id);

}
