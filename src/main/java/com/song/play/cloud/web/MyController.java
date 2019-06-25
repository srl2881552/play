package com.song.play.cloud.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.base.AbstractBaseController;
import com.song.play.cloud.entity.PageBean;
import com.song.play.cloud.entity.Users;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.service.UserApi;
import com.song.play.cloud.service.VideoApi;
import com.song.play.cloud.tools.PageTools;


@Controller
@RequestMapping("mine")
public class MyController extends AbstractBaseController{

	@Autowired
	private UserApi userApi;
	@Autowired
	private VideoApi videoApi;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@RequestMapping("follow.html")
	public String follow(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		Users users=this.getCurrentUser(request);
		if(users==null)
		{
			return "redirect:/user/login";
		}
		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
//		UserSpace space=new UserSpace();
//		space.setUserId(users.getId());
		
		
	   
	    PageBean<Video> beanMaps=userApi.findMyFollowListPage(users.getId(), Integer.valueOf(pageNo), Integer.valueOf(pageSize));
	    PageTools tools=new PageTools(Integer.valueOf(pageNo),beanMaps.getTotalPage());
		tools.reflashPageCode();
	    
		
		Users user=userApi.getUsersById(users.getId());
		model.addAttribute("pageBean", beanMaps);
		model.addAttribute("user", user);
		model.addAttribute("pageCodes", tools.pageCodes);
		return "index/users/follow";
	}
	@RequestMapping("follow/refresh.html")
	public String followRefresh(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		Users users=this.getCurrentUser(request);
		if(users==null)
		{
			return "redirect:/user/login";
		}
		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
		PageBean<Video> beanMaps=userApi.findMyFollowListPage(users.getId(), Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		Users user=userApi.getUsersById(users.getId());
		model.addAttribute("pageBean", beanMaps);
		model.addAttribute("user", user);
		return "index/users/followRefresh";
	}
	@RequestMapping("space.html")
	public String space(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
		Users users=this.getCurrentUser(request);
		if(users==null)
		{
			return "redirect:/user/login";
		}

		PageBean<Video> beanMaps=userApi.findMySpaceListPage(users.getId(), Integer.valueOf(pageNo), Integer.valueOf(pageSize));
	    
	   
	    PageTools tools=new PageTools(Integer.valueOf(pageNo),beanMaps.getTotalPage());
		tools.reflashPageCode();
		model.addAttribute("pageBean", beanMaps);
		Users user=userApi.getUsersById(users.getId());
		model.addAttribute("user", user);
		model.addAttribute("pageCodes", tools.pageCodes);
		return "index/users/space";
	}
	@RequestMapping("history.html")
	public String history(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		Users users=this.getCurrentUser(request);
		if(users==null)
		{
			return "redirect:/user/login";
		}
		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");

	   
	    PageBean<Video> beanMaps=userApi.findMyHistoryListPage(users.getId(), Integer.valueOf(pageNo), Integer.valueOf(pageSize));
	    List<Video> result=beanMaps.getResult();
	    for(Video map:result)
	    {
	    	Object t=redisTemplate.opsForHash().get(users.getId(), map.getId());
	    	if(t==null)
	    	{
	    		map.setHistoryTime("0.000000秒");
	    	}else{
	    		map.setHistoryTime(t+"秒");
	    	}
	    }
	    PageTools tools=new PageTools(Integer.valueOf(pageNo),beanMaps.getTotalPage());
		tools.reflashPageCode();
	    beanMaps.setResult(result);
		model.addAttribute("pageBean", beanMaps);
		model.addAttribute("user", users);
		model.addAttribute("pageCodes", tools.pageCodes);
		return "index/users/history";
	}
	@RequestMapping("history/refresh.html")
	public String historyRefresh(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		Users users=this.getCurrentUser(request);
		if(users==null)
		{
			return "redirect:/user/login";
		}
		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
//		UserSpace space=new UserSpace();
//		space.setUserId(users.getId());
		PageBean<Video> beanMaps=userApi.findMyHistoryListPage(users.getId(), Integer.valueOf(pageNo), Integer.valueOf(pageSize));
	    List<Video> result=beanMaps.getResult();
	    for(Video map:result)
	    {
	    	Object t=redisTemplate.opsForHash().get(users.getId(), map.getId());
	    	if(t==null)
	    	{
	    		map.setHistoryTime("0.000000秒");
	    	}else{
	    		map.setHistoryTime(t+"秒");
	    	}
	    }
	    PageTools tools=new PageTools(Integer.valueOf(pageNo),beanMaps.getTotalPage());
		tools.reflashPageCode();
	    beanMaps.setResult(result);
		model.addAttribute("pageBean", beanMaps);
		model.addAttribute("user", users);
		model.addAttribute("pageCodes", tools.pageCodes);
		return "index/users/historyRefresh";
	}
	@RequestMapping("space/refresh.html")
	public String spaceRefresh(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
		Users users=this.getCurrentUser(request);
		if(users==null)
		{
			return "redirect:/user/login";
		}
		Video video=new Video();
		video.setUserId(users.getId());
		PageBean<Video> beanMaps=userApi.findMySpaceListPage(users.getId(), Integer.valueOf(pageNo), Integer.valueOf(pageSize));
	    
		   
	    PageTools tools=new PageTools(Integer.valueOf(pageNo),beanMaps.getTotalPage());
		tools.reflashPageCode();
		model.addAttribute("pageBean", beanMaps);
		Users user=userApi.getUsersById(users.getId());
		model.addAttribute("user", user);
		model.addAttribute("pageCodes", tools.pageCodes);
		return "index/users/spaceRefresh";
	}
	@RequestMapping("anchor")
	public String anchor(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		return "index/users/anchor";
	}
}
