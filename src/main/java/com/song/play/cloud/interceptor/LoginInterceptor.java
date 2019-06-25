package com.song.play.cloud.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Repository
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger=Logger.getLogger(LoginInterceptor.class);
//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";

		/** 当前的URL无参数 */
		String uri = request.getRequestURI();
		String Referer=request.getHeader("referer");
		
		/** 首页URL */
		String indexURL = basePath;
		//检查请求URL是否包含敏感字符
		
//
//		
//		
//
//		/** 获取cookie */
//		Cookie cookie = CookieUtil.getCookie(request, CommonConstant.SESSION_ID);
//
//		/** 判断cookie是否为空 */
//		String sid = "";
//		if (!org.springframework.util.StringUtils.isEmpty(cookie)) {
//			sid = cookie.getValue();
//			try {
//
//				/** 在cookie不为空的情况下，获取用户并判断是否为空 */
//				UserVo userVO = null;
//				try {
//					//从redis中取
//					
//					userVO = (UserVo)JedisManager.getObject("play:login:cookie:"+sid);
//					//userVO=JsonUtil.readValue(json, UserVo.class);
//				} catch (Exception e) {
//					logger.error("get redis item failed! message:"+e);
//				}
//				if (!ValidateTool.isNull(userVO)) {
//
//					/* 更新redis的有效期 一周 */
//					JedisManager.setObject("play:login:cookie:"+sid, CommonConstant.REDISDAGE, userVO);
//				}
//			} catch (Exception e) {
//				logger.error("redis异常，message=" + e.getMessage(), e);
//				throw new Exception();
//			}
//		}
		return true;
    }

}
