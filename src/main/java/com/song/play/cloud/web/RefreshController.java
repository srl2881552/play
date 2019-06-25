package com.song.play.cloud.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.song.play.cloud.service.VideoApi;

@Controller
@RequestMapping("refresh")
public class RefreshController {

	@Autowired
	private VideoApi videoApi;
	@RequestMapping("desktop")
	@ResponseBody
	public String desktop(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		videoApi.refresh();
		return "SUCCESS";
	}
}
