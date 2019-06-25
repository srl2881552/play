package com.song.play.cloud.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.base.AbstractBaseController;


@Controller
@RequestMapping("userMag")
public class UserManagerController extends AbstractBaseController{

//	@Autowired
//	private UsersService usersService;
//	
//	@RequestMapping("view.html")
//	public String view(HttpServletRequest request,HttpServletResponse response)
//	{
//		return "admin/user/view";
//	}
//	@RequestMapping("list")
//	public void list(HttpServletRequest request,HttpServletResponse response,Users userVo,Integer page,Integer rows)
//	{
//		if(page==null)
//		{
//			page=1;
//		}
//		Page<Users> pageEntity=new Page<Users>(page, rows, "create_at");
//	    pageEntity.setAsc(false);
////	    PageBean<LiveCategoryVo> pageBean = new PageBean<LiveCategoryVo>();
//	    SelectWrapper wrapper=new SelectWrapper();
//	    if(!StringUtils.isEmpty(userVo.getLogin()))
//	    {
//	    	wrapper.like("login", userVo.getLogin());
//	    }
//	    
//	    pageEntity=usersService.selectPage(wrapper, pageEntity);
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		long totalCount = pageEntity.getTotal();
//		map.put("total", totalCount);
//		if (totalCount >=0) {
//			map.put("rows", pageEntity.getRecords());
//		}
//		this.write(response, map);
//	}
}
