package com.song.play.cloud.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("platform")
public class PlatformController {

	
	@RequestMapping("blog")
	public String blog(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		return "index/platform/blog";
	}
	@RequestMapping("registerlive")
	public String registerlive(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		return "index/platform/registerlive";
	}
	@RequestMapping("aboutme")
	public String aboutme(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		return "index/platform/aboutme";
	}
	@RequestMapping("about")
	public String about(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		return "index/platform/about";
	}
}
