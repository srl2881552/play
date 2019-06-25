/**
 * 
 */
package com.song.play.cloud.tools;

import java.util.Arrays;
import java.util.List;


public final class CommonConstant {
	
	/** common constant */
	public static final Integer MAX_AGE_ONE_MONTH = 30*24*60;//Cookie's lifecycle
	public static final String COOKIE_PATH = "/";//The default general path for Cookie
	/** 失效时间30*60 */
	public static final int OUT_TIME_1800 = 1800;


	/**
	 * COOKIE_AGE_ONE_YEAR 登录完成 5年过期≈(永不过期).
	 */
	public static final Integer SESSION_AGE_ONE_YEAR=157680000;
	public static final Integer SESSION_AGE_30D=2160000;//30天
	public static final Integer HISTORY_URL_AGE_30M=1800;//30分钟

	/** under bar **/
	public static final String UNDER_BAR = "_";

	
	/**.
	 * SESSION_ID
	 */
    public static final String SESSION_ID = "user_session_id";
    public static final String HISTORY_SESSION_ID = "o2opc:commonConstant:history_session_id";
    public static final String COOKIE_ID = "cookie_id";
    public static final String COOKIE_HISTORY_ID = "cookie_history_id";
    
    public static final String SEARCH_HISTORY_ID = "search_history_id";
    
    public static final String USER_ID_BI = "user_id_bi";
	/**.
	 * redis的有限日期
	 */
	public static final Integer REDISDAGE = 1296000;//15天
	/**
	 * COOKIE_AGE_ONE_YEAR 登录完成永不过期.
	 */
	public static final int SESSION_AGE_FOREVER=0;
	
	
	/**
	 * 登录回跳历史路劲
	 */
	public static final String URLLD="successUrl";
	
	/**
	 * 图片验证码
	 */
	public static final String KAPTCHA_SESSION_KEY ="kapt";
	
	

	
	
}
