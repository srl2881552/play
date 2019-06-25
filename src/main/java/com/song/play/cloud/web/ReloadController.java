package com.song.play.cloud.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.base.AbstractBaseController;

@Controller
@RequestMapping("reload")
public class ReloadController extends AbstractBaseController{

//	@Autowired
//	private SearchService searchService;
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
//	@Autowired
//	private VideoCategoryService videoCategoryService;
//	@Autowired
//	private VideoService videoService;
//	
//	@RequestMapping("search")
//	public void search(HttpServletRequest request,HttpServletResponse response,String searchDoc)
//	{
//		searchService.refresh();
//		this.write(response, "success");
//	}
}
