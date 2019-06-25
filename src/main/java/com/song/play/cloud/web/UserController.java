package com.song.play.cloud.web;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.song.play.cloud.entity.Users;
import com.song.play.cloud.service.UserApi;
import com.song.play.cloud.tools.CommonConstant;
import com.song.play.cloud.tools.CookieUtil;
import com.song.play.cloud.tools.JedisManager;
import com.song.play.cloud.tools.MD5;
import com.song.play.cloud.tools.PKUtils;
import com.song.play.cloud.tools.PrintUtils;
import com.song.play.cloud.tools.ResultCodeEnum;
import com.song.play.cloud.tools.ServiceResult;
import com.song.play.cloud.tools.Tools;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserApi userApi;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@RequestMapping("login.html")
	public String login(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		return "index/users/login";
	}
	@RequestMapping("register")
	public String register(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		return "index/users/register";
	}
	@RequestMapping("doRegister")
	public void doRegister(HttpServletRequest request,HttpServletResponse response,Model model,Users userVo)
	{
		ServiceResult result=new ServiceResult();
		List<Users> users=userApi.findUserByLogin(userVo.getLogin());
		if(users.size()>0)
		{
			result=new ServiceResult(ResultCodeEnum.USER_IS_HAVE);
			PrintUtils.printJson(request, response, result);
			return;
		}
		userVo.setId(PKUtils.getUUID());
		userVo.setCreateAt(new Date());
		userVo.setStatus(1);
		userVo.setUserType(1);
		userVo.setLogin(userVo.getMobile());
		userVo.setPassword(MD5.GetMD5Code(userVo.getPassword()));
		userApi.save(userVo);
		String sid= request.getSession().getId();
		CookieUtil.setCookie(response, CommonConstant.SESSION_ID,sid,String.valueOf(CommonConstant.SESSION_AGE_ONE_YEAR),"/");
		 try {
			 JedisManager.setObject("play:login:cookie:"+sid, CommonConstant.REDISDAGE, userVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 PrintUtils.printJson(request, response, result);
	}
	@RequestMapping("doLogin")
	@ResponseBody
	public void doLogin(HttpServletRequest request,HttpServletResponse response,Model model,String login,String password)
	{
		ServiceResult result=new ServiceResult();
		List<Users> users=userApi.login(login,MD5.GetMD5Code(password));
		if(users.size()>0)
		{
			result=new ServiceResult(ResultCodeEnum.Success);
			String sid= request.getSession().getId();
			CookieUtil.setCookie(response, CommonConstant.SESSION_ID,sid,String.valueOf(CommonConstant.SESSION_AGE_ONE_YEAR),"/");
			 try {
				 JedisManager.setObject("play:login:cookie:"+sid, CommonConstant.REDISDAGE, users.get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}
			PrintUtils.printJson(request, response, result);
		}else{
			result=new ServiceResult(ResultCodeEnum.USER_ERROR);
			
			PrintUtils.printJson(request, response, result);
		}
		
	}
	public static void main(String[] args) {
		try {
			Encoder encoder = new Encoder();
			MultimediaInfo m = encoder.getInfo(new File("e:/init/ea02199a90dd452da29fe18916a94801.mp4"));
			System.out.print(Tools.getFormatLength(String.valueOf(m.getDuration()/(3600*1000))) + ":" 
			        + Tools.getFormatLength(String.valueOf(m.getDuration()%(3600*1000)/(60*1000)))+ ":" 
			        + Tools.getFormatLength(String.valueOf(m.getDuration()%(3600*1000)%(60*1000)/1000)));

			} catch (Exception e) {
			e.printStackTrace();
			}
			
	}
	
	
}
