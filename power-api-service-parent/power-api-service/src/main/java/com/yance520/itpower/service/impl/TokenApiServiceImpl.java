package com.yance520.itpower.service.impl;

import com.yance520.itpower.mapper.api.TokenApiMapper;
import com.yance520.itpower.model.api.TokenApi;
import com.yance520.itpower.service.TokenApiService;
import org.springframework.beans.factory.annotation.Autowired;


public class TokenApiServiceImpl implements TokenApiService {
    @Autowired
    private TokenApiMapper tokenApiMapper;

    @Override
    public TokenApi queryByUserName(String userName) {
        return tokenApiMapper.queryByUserName(userName);
    }

    @Override
    public TokenApi queryByToken(String token) {
        return tokenApiMapper.queryByToken(token);
    }

    @Override
    public void save(TokenApi token) {
        tokenApiMapper.save(token);
    }

    @Override
    public void update(TokenApi token) {
        tokenApiMapper.update(token);
    }

    @Override
    public int deleteByUserName(String userName) {
        return tokenApiMapper.deleteByUserName(userName);
    }

    @Override
    public int deleteByToken(String token) {
        return tokenApiMapper.deleteByToken(token);
    }
}
