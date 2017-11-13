package com.yance520.itpower.mapper.api;

import com.yance520.itpower.mapper.base.BaseMapper;
import com.yance520.itpower.model.api.TokenApi;

/**
 * 用户Token
 * 
 */
public interface TokenApiMapper extends BaseMapper<TokenApi> {

    TokenApi queryByUserName(String userName);

    TokenApi queryByToken(String token);

    int deleteByUserName(String userName);

    int deleteByToken(String token);
	
}
