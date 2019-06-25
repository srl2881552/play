package com.song.play.cloud.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("liveCategories")
public class LiveCategoriesController {

//	@Autowired
//	private VideoCategoryService videoCategoryService;
//	@Autowired
//	private VideoService videoService;
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
//	@RequestMapping("categories")
//	public String Categories(HttpServletRequest request,HttpServletResponse response,Model model)
//	{
//		List<VideoCategory> videoCategories=videoCategoryService.getLiveAll();
//		model.addAttribute("cates", videoCategories);
//		return "index/live/live-categories";
//	}
}
