package com.song.play.cloud.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.entity.Search;
import com.song.play.cloud.entity.SearchPageBean;
import com.song.play.cloud.entity.VideoCategory;
import com.song.play.cloud.service.SearchApi;
import com.song.play.cloud.service.VideoApi;



@Controller
@RequestMapping("videoCategories")
public class VideoCategoriesController {

	
	@Autowired
	private VideoApi videoApi;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private SearchApi searchApi;
	@RequestMapping("categories.html")
	public String Categories(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		List<VideoCategory> categories=videoApi.getVideoAll();
		SearchPageBean<Search> pageBean=searchApi.searchVideoAll("create_at", 1, 20);
		List<Search> group=searchApi.searchGroup("create_at", "2", pageBean.getTotalRows());
		model.addAttribute("group", group);
		model.addAttribute("cates", categories);
		return "index/video/video-categories";
	}
}
