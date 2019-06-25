package com.song.play.cloud.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.base.AbstractBaseController;

@Controller
@RequestMapping("goods")
public class GoodsController extends AbstractBaseController{

//	@Autowired
//	private GoodsService goodsService;
//	@RequestMapping("view.html")
//	public String view(HttpServletRequest request,HttpServletResponse response)
//	{
//		return "admin/goods/view";
//	}
}
