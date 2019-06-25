package com.song.play.cloud.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.base.AbstractBaseController;




@Controller
@RequestMapping("content")
public class ContentController extends AbstractBaseController{

//	@Autowired
//	private SearchService searchService;
//	@RequestMapping("view.html")
//	public String view(HttpServletRequest request,HttpServletResponse response)
//	{
//		Users users=this.getCurrentUser(request);
//		if(users==null)
//		{
//			return "redirect:/user/login";
//		}
//		return "index/content/view";
//	}
}
