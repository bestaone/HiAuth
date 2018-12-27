package com.bestaone.aiwan.user.service.impl;

import com.bestaone.aiwan.user.domain.Account;
import com.bestaone.aiwan.user.mapper.AccountMapper;
import com.bestaone.aiwan.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account save(Account account) {
        if(account.getId()!=null){
            accountMapper.update(account);
        } else {
            account.setId(System.currentTimeMillis());
            accountMapper.insert(account);
        }
        return account;
    }

    @Override
    public Account findById(Long id) {
        return accountMapper.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    @Override
    public void delete(Long id) {
        accountMapper.delete(id);
    }

}
