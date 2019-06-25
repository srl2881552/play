package com.song.play.cloud.web;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.song.play.cloud.base.AbstractBaseController;

@Controller
@RequestMapping("admin")
public class CommentManagerController extends AbstractBaseController{

//	@Autowired
//	private UsersService usersService;
//	@Autowired
//	private CommentService commentService;
//	@Autowired
//	private VideoService videoService;
//	
//	@RequestMapping("comment/view.html")
//	public String view(HttpServletRequest request,HttpServletResponse response,Comment comment)
//	{
//		 return "admin/comment/view";
//	}
//	@RequestMapping("comment/list.html")
//	public void list(HttpServletRequest request,HttpServletResponse response,Integer page,Comment comment, Integer rows,Model model)
//	{
//		Users user = (Users) request.getSession().getAttribute("user");
//	    if (page == null) {
//	      page = Integer.valueOf(1);
//	    }
//	    Page<Comment> pageEntity=new Page<Comment>(page, rows, "create_at");
//	    pageEntity.setAsc(false);
////	    PageBean<LiveCategoryVo> pageBean = new PageBean<LiveCategoryVo>();
//	    SelectWrapper wrapper=new SelectWrapper();
//	    
//	    
//	   
//	    if(!StringUtils.isEmpty(comment.getContent()))
//	    {
//	    	wrapper.like("content", comment.getContent());
//	    }
//	    if(!StringUtils.isEmpty(comment.getTitle()))
//	    {
//	    	wrapper.like("title", comment.getTitle());
//	    }
//	    if(!StringUtils.isEmpty(comment.getNickName()))
//	    {
//	    	wrapper.like("nick_name", comment.getNickName());
//	    }
//	    pageEntity=commentService.selectPage(wrapper, pageEntity);
//	   
//	    Map<String, Object> map = new HashMap();
//	    
//	    map.put("total", Long.valueOf(pageEntity.getTotal()));
//	    if (pageEntity.getTotal() >= 0L) {
//	      map.put("rows", pageEntity.getRecords());
//	    }
//	    write(response, map);
//	}
}
