package com.bestaone.aiwan.auth.service.impl;

import com.bestaone.aiwan.auth.domain.Account;
import com.bestaone.aiwan.auth.mapper.AccountMapper;
import com.bestaone.aiwan.auth.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountMapper mapper;

    @Override
    public Account save(Account account) {
        if(account.getId()!=null){
            mapper.update(account);
        } else {
            account.setId(System.currentTimeMillis());
            mapper.insert(account);
        }
        return account;
    }

    @Override
    public Account findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return mapper.findAll();
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

}
