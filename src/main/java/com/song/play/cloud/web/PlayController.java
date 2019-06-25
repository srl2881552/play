package com.song.play.cloud.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.base.AbstractBaseController;
import com.song.play.cloud.entity.Comment;
import com.song.play.cloud.entity.PageBean;
import com.song.play.cloud.entity.Search;
import com.song.play.cloud.entity.Users;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.entity.VideoCategory;
import com.song.play.cloud.service.SearchApi;
import com.song.play.cloud.service.UserApi;
import com.song.play.cloud.service.VideoApi;
import com.song.play.cloud.tools.PageTools;



@Controller
@RequestMapping("play")
public class PlayController extends AbstractBaseController{

	@Autowired
	private VideoApi videoApi;
	@Autowired
	private UserApi userApi;
//	@Autowired
//	private VideoCategoryService videoCategoryService;
	@Autowired
	private SearchApi searchApi;
//	@Autowired
//	private CommentService commentService;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	private static Logger logger=Logger.getLogger(PlayController.class);
	@RequestMapping("video/{id}.html")
	public String live(HttpServletRequest request,HttpServletResponse response,@PathVariable String id,Model model)
	{
		Users user=this.getCurrentUser(request);
		String userId="";
		if(user==null)
		{
			userId="b5b61784323d40f6a5734653725919b7";
		}else{
			userId=user.getId();
		}
		
		Video video=videoApi.getVideoById(id);
		Users users=userApi.getUsersById(userId);
		List<VideoCategory> videoCates=videoApi.getVideoAll();
		List<VideoCategory> liveCategoryVos=videoApi.getLiveAll();
		HashOperations<String,String,String> operations=redisTemplate.opsForHash();
		String num=operations.get("com.song.play.click", video.getId());
		
		int commentCount=userApi.commentselectCount(id);
		if(num==null||"".equals(num))
		{
			num="0";
		}

		PageBean<Comment> page2=userApi.selectListPage(id, Integer.valueOf(1), 10);
		
	    PageTools tools=new PageTools(Integer.valueOf(1),page2.getTotalPage());
		tools.reflashPageCode();
		List<Search> conforms=searchApi.searchConform("create_at", video.getCateId());
		Object t=redisTemplate.opsForHash().get(userId, id);
		if(t==null)
		{
			t="0.000000";
		}
		model.addAttribute("videoCates", videoCates);
		model.addAttribute("Count", commentCount);
		model.addAttribute("pageCodes", tools.pageCodes);
		model.addAttribute("liveCategoryVos", liveCategoryVos);
		model.addAttribute("conforms", conforms);
		model.addAttribute("video", video);
		model.addAttribute("user", users);
		model.addAttribute("num", num);
		model.addAttribute("page2", page2);
		model.addAttribute("t", t);
		if(video.getType()==1)
		{
			return "index/live/live";
		}else{
			if(video.getRtmp().endsWith("flv")||video.getRtmp().endsWith("FLV"))
			{
				return "index/flv";
				
			}else{
				return "index/video/video";
			}
		}
	}
	
}
