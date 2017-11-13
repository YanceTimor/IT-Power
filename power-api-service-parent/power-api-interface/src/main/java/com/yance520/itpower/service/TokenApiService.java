package com.yance520.itpower.service;


import com.yance520.itpower.model.api.TokenApi;

/**
 * 用户Token
 * 
 */
public interface TokenApiService {

	/**
	 * 根据账号查询
	 * @param userName
	 * @return
	 */
	TokenApi queryByUserName(String userName);

	/**
	 * 根据token值查询
	 * @param token
	 * @return
	 */
	TokenApi queryByToken(String token);

	/**
	 * 保存
	 * @param token
	 */
	void save(TokenApi token);

	/**
	 * 更新
	 * @param token
	 */
	void update(TokenApi token);

	/**
	 * 根据账号删除
	 * @param userName
	 * @return
	 */
	int deleteByUserName(String userName);

	/**
	 * 根据token删除
	 * @param token
	 * @return
	 */
	int deleteByToken(String token);

}
