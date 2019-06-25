package com.song.play.cloud.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.song.play.cloud.base.AbstractBaseController;
import com.song.play.cloud.entity.Comment;
import com.song.play.cloud.entity.PageBean;
import com.song.play.cloud.entity.Users;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.service.UserApi;
import com.song.play.cloud.service.VideoApi;
import com.song.play.cloud.tools.PKUtils;
import com.song.play.cloud.tools.PageTools;

@Controller
@RequestMapping("comment")
public class CommentController extends AbstractBaseController{

	@Autowired
	private UserApi userApi;
//	@Autowired
//	private CommentService commentService;
	@Autowired
	private VideoApi videoApi;
//	
	@RequestMapping("save.html")
	@ResponseBody
	public String save(HttpServletRequest request,HttpServletResponse response,Comment comment)
	{
		Users users=this.getCurrentUser(request);
		if(users==null)
		{
			return "redirect:/user/login";
		}
		comment.setContent(comment.getContent());
		Video video=videoApi.getVideoById(comment.getVideoId());
		comment.setId(PKUtils.getUUID());
		comment.setStatus(1);
		comment.setUserId(users.getId());
		comment.setCreateAt(new Date());
		comment.setNickName(users.getNickName());
		comment.setTitle(video.getTitle());
		userApi.commentSave(comment);
		return "ok";
	}
	@RequestMapping("list.html")
	public String list(HttpServletRequest request,HttpServletResponse response,Integer page,String videoId,Model model)
	{
		Users users=this.getCurrentUser(request);
		if(users==null)
		{
			return "redirect:/user/login";
		}
		
		if(page==null)
		{
			page=1;
		}
		Video video=videoApi.getVideoById(videoId);
		

		PageBean<Comment> page2=userApi.selectListPage(videoId, Integer.valueOf(page), 10);
		
	    PageTools tools=new PageTools(Integer.valueOf(page),page2.getTotalPage());
		tools.reflashPageCode();
		model.addAttribute("pageCodes", tools.pageCodes);
		model.addAttribute("page2", page2);
		model.addAttribute("video", video);
		return "index/comment/list";
	}
}
