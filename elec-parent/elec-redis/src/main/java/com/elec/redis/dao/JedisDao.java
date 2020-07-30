package com.elec.redis.dao;

public interface JedisDao {
	/**
	 * 判段key是否存在
	 */
	Boolean exists(String key);
	/**
	 * 刪除
	 * @param key
	 * @return
	 */
	Long del(String key);
	/**
	 * 设置
	 * @param key
	 * @param value
	 * @return
	 */
	String set(String key,String value);
	
	/**
	 * 取值
	 * @param key
	 * @return
	 */
	String get(String key);
	
	/**
	 * 设置redis过期时间
	 * @param key
	 * @param seconds
	 * @return
	 */
	Long expire(String key,int seconds);
	
	
	

}
