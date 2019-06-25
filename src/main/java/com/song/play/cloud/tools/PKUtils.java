package com.song.play.cloud.tools;

import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

public class PKUtils {

	private static int suffixLen = 5;

	/**
	 * @date：2010-12-15
	 * @Description：生成主键
	 * @return 主键
	 */
	public synchronized static String getPrimaryKey () {
		String currentTimeMillisStr = new Long(System.currentTimeMillis()).toString();
		String randomStr = RandomStringUtils.randomNumeric(suffixLen);
		
		return currentTimeMillisStr + randomStr;
	}
	
	/**
	 * @date：2010-12-15
	 * @Description：生成主键
	 * @return 主键
	 */
	public synchronized static String getUUID() {
		
		return UUID.randomUUID().toString().replace("-", "");	
	}


	public synchronized static Long getLongPrimaryKey () {
		String currentTimeMillisStr = new Long(System.currentTimeMillis()).toString();
		String randomStr = RandomStringUtils.randomNumeric(suffixLen);
		String temp = currentTimeMillisStr + randomStr;
		
		return Long.parseLong(temp);
	}
	
	
	public static void main (String[] args) {
		
		System.out.println("*******"+getLongPrimaryKey());//140296949984432231
	}
}
