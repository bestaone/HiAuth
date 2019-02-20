package com.bestaone.aiwan.user.service.impl;

import com.bestaone.aiwan.core.mapper.BaseMapper;
import com.bestaone.aiwan.core.service.BaseServiceImpl;
import com.bestaone.aiwan.user.domain.Account;
import com.bestaone.aiwan.user.mapper.AccountMapper;
import com.bestaone.aiwan.user.service.AccountService;
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
