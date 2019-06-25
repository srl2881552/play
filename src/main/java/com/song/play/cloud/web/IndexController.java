package com.song.play.cloud.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.entity.Desktop;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.entity.VideoCategory;
import com.song.play.cloud.service.UserApi;
import com.song.play.cloud.service.VideoApi;
import com.song.play.cloud.tools.JsonUtil;






@Controller
public class IndexController {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private VideoApi videoApi;
	@RequestMapping("/")
	public String view(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		Map<String, List<Desktop>> map=new LinkedHashMap<String, List<Desktop>>();
		String json=redisTemplate.opsForValue().get("com.song.play.desktop");
		HashOperations<String,String,String> operations=redisTemplate.opsForHash();
		if(json==null||"".equals(json)||"{}".equals(json))
		{
			List<Desktop> vos=videoApi.selectAll();
			
			for(Desktop vo:vos)
			{
				String num=operations.get("com.song.play.click", vo.getId().toString());
				vo.setChick(num==null?0L:Long.valueOf(num));
				if(map.get(vo.getCateName())!=null)
				{
					if(map.get(vo.getCateName()).size()<20)
					{
						map.get(vo.getCateName()).add(vo);
					}
				}else{
					List<Desktop> list=new ArrayList<Desktop>();
					list.add(vo);
					map.put(vo.getCateName(), list);
				}
			}
			json=JsonUtil.writeValueAsString(map);
			redisTemplate.opsForValue().set("com.song.play.desktop", json);
		}else{
			map=JsonUtil.readValue(json,Map.class);
			
		}
		List<VideoCategory> videoCategoryVos=videoApi.getVideoAll();
		List<VideoCategory> liveCategoryVos=videoApi.getLiveAll();

		Video video=videoApi.getByNewsVideo().get(0);
		model.addAttribute("map", map);
		model.addAttribute("id", 1);
		model.addAttribute("video", video);
		model.addAttribute("videoCategoryVos", videoCategoryVos);
		model.addAttribute("liveCategoryVos", liveCategoryVos);
		return "desktop";
	}
}
