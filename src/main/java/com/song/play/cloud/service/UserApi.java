package com.song.play.cloud.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.song.play.cloud.entity.Click;
import com.song.play.cloud.entity.Comment;
import com.song.play.cloud.entity.PageBean;
import com.song.play.cloud.entity.Users;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.hystrix.SearchHystrix;
import com.song.play.cloud.hystrix.UserHystrix;
import com.song.play.cloud.security.FeignUser;



@FeignClient(name = "play-user-api",configuration=FeignUser.class,fallback = UserHystrix.class)
public interface UserApi {
	@RequestMapping(value = "/user/save",method = RequestMethod.GET)
    public Integer save(@RequestBody Users users);
	@RequestMapping(value = "/user/update",method = RequestMethod.GET)
	public Integer update(@RequestBody Users users);
	@RequestMapping(value = "/user/delete",method = RequestMethod.GET)
	public Integer delete(@RequestParam(value = "id", required = true) String id);
	@RequestMapping(value = "/user/getUsersById",method = RequestMethod.GET)
	public Users getUsersById(@RequestParam(value = "id", required = true) String id);
	@RequestMapping(value = "/user/login",method = RequestMethod.GET)
	public List<Users> login(@RequestParam(value = "login", required = true) String login,@RequestParam(value = "password", required = true) String password);
	@RequestMapping(value = "/user/findUserByLogin",method = RequestMethod.GET)
	public List<Users> findUserByLogin(@RequestParam(value = "login", required = true) String login);
	@RequestMapping(value = "/click/selectCount",method = RequestMethod.GET)
	public Integer clickselectCount(@RequestParam(value = "videoId") String videoId,@RequestParam(value = "userId") String userId);
	@RequestMapping(value = "/click/save",method = RequestMethod.GET)
	public Integer saveClick(@RequestBody Click click);
	
	
	@RequestMapping(value ="/comment/save",method = RequestMethod.GET)
	public Integer commentSave(@RequestBody Comment comment);
	
	@RequestMapping(value ="/comment/update",method = RequestMethod.GET)
	public Integer commentUpdate(@RequestBody Comment comment);
		
	@RequestMapping(value ="/comment/selectCount",method = RequestMethod.GET)
	public Integer commentselectCount(@RequestParam(value = "videoId") String videoId);
	
	@RequestMapping(value ="/comment/selectListPage",method = RequestMethod.GET)
	public PageBean<Comment> selectListPage(@RequestParam(value = "videoId") String videoId,@RequestParam(value = "pageNo") Integer pageNo,@RequestParam(value = "pageSize") Integer pageSize);
	
	@RequestMapping(value ="/user/findMySpaceListPage",method = RequestMethod.GET)
	public PageBean<Video> findMySpaceListPage(@RequestParam(value = "userId") String userId,@RequestParam(value = "pageNo") Integer pageNo,@RequestParam(value = "pageSize") Integer pageSize);
	
	@RequestMapping(value ="/user/findMyFollowListPage",method = RequestMethod.GET)
	public PageBean<Video> findMyFollowListPage(@RequestParam(value = "userId") String userId,@RequestParam(value = "pageNo") Integer pageNo,@RequestParam(value = "pageSize") Integer pageSize);
	
	@RequestMapping(value ="/click/findMyHistoryListPage",method = RequestMethod.GET)
	public PageBean<Video> findMyHistoryListPage(@RequestParam(value = "userId") String userId,@RequestParam(value = "pageNo") Integer pageNo,@RequestParam(value = "pageSize") Integer pageSize);
}
