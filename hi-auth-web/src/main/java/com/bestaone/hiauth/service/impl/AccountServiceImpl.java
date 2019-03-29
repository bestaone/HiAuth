package com.bestaone.hiauth.service.impl;

import com.bestaone.hiauth.core.mapper.BaseMapper;
import com.bestaone.hiauth.core.service.BaseServiceImpl;
import com.bestaone.hiauth.domain.Account;
import com.bestaone.hiauth.mapper.AccountMapper;
import com.bestaone.hiauth.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, Long> implements AccountService {

    @Resource
    AccountMapper mapper;

    @Override
    public BaseMapper<Account, Long> getMapper() {
        return mapper;
    }

}
