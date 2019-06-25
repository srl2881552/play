package com.song.play.cloud.hystrix;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.song.play.cloud.entity.Click;
import com.song.play.cloud.entity.Comment;
import com.song.play.cloud.entity.PageBean;
import com.song.play.cloud.entity.Users;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.service.UserApi;

@Component
public class UserHystrix implements UserApi{

	@Override
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public Integer save(Users users) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public Integer update(Users users) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public Integer delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@RequestMapping(value = "/user/getUsersById", method = RequestMethod.POST)
	public Users getUsersById(String id) {
		// TODO Auto-generated method stub
		return new Users();
	}

	@Override
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public List<Users> login(String login, String password) {
		// TODO Auto-generated method stub
		return new ArrayList<Users>();
	}

	@Override
	@RequestMapping(value = "/user/findUserByLogin", method = RequestMethod.POST)
	public List<Users> findUserByLogin(String login) {
		// TODO Auto-generated method stub
		return new ArrayList<Users>();
	}

	@Override
	@RequestMapping(value = "/click/selectCount", method = RequestMethod.POST)
	public Integer clickselectCount(String videoId, String userId) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	@RequestMapping(value = "/click/save", method = RequestMethod.POST)
	public Integer saveClick(Click click) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@RequestMapping(value = "/comment/save", method = RequestMethod.POST)
	public Integer commentSave(@RequestBody Comment comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@RequestMapping(value = "/comment/update", method = RequestMethod.POST)
	public Integer commentUpdate(@RequestBody Comment comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@RequestMapping(value = "/comment/selectCount", method = RequestMethod.POST)
	public Integer commentselectCount(@RequestParam("videoId") String videoId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@RequestMapping(value = "/comment/selectListPage", method = RequestMethod.POST)
	public PageBean<Comment> selectListPage(
			@RequestParam("videId") String videoId,
			@RequestParam("pageNo") Integer pageNo,
			@RequestParam("pageSize") Integer pageSize) {
		// TODO Auto-generated method stub
		return new PageBean<Comment>();
	}

	@Override
	@RequestMapping("/user/findMySpaceListPage")
	public PageBean<Video> findMySpaceListPage(
			@RequestParam("userId") String userId,
			@RequestParam("pageNo") Integer pageNo,
			@RequestParam("pageSize") Integer pageSize) {
		// TODO Auto-generated method stub
		return new PageBean<Video>();
	}

	@Override
	@RequestMapping("/user/findMyFollowListPage")
	public PageBean<Video> findMyFollowListPage(
			@RequestParam("userId") String userId,
			@RequestParam("pageNo") Integer pageNo,
			@RequestParam("pageSize") Integer pageSize) {
		// TODO Auto-generated method stub
		return new PageBean<Video>();
	}

	@Override
	@RequestMapping(value = "/click/findMyHistoryListPage", method = RequestMethod.POST)
	public PageBean<Video> findMyHistoryListPage(@RequestParam String userId,
			@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		// TODO Auto-generated method stub
		return new PageBean<Video>();
	}

	

}
