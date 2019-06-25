package com.song.play.cloud.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.base.AbstractBaseController;



@Controller
@RequestMapping("admin")
public class AdminController extends AbstractBaseController{

	@RequestMapping("desktop")
	public String desktop(HttpServletRequest request,HttpServletResponse response)
	{
		return "admin/desktop";
	}
}
