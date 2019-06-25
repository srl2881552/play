package com.song.play.cloud.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.base.AbstractBaseController;



@Controller
@RequestMapping("liveCategory")
public class LiveCategoryController extends AbstractBaseController{

//	@Autowired
//	private VideoCategoryService videoCategoryService;
//	@RequestMapping("view.html")
//	public String view(HttpServletRequest request,HttpServletResponse response)
//	{
//		return "admin/liveCategory/view";
//	}
//	@RequestMapping({"list"})
//	  public void list(HttpServletRequest request, HttpServletResponse response, Model model, VideoCategory moviesCategoryVo, Integer page, Integer rows)
//	  {
//	    if (page == null) {
//	      page = Integer.valueOf(1);
//	    }
////	    PageBean<LiveCategoryVo> pageBean = new PageBean<LiveCategoryVo>();
//	    Page<VideoCategory> pageEntity=new Page<VideoCategory>(page, rows, "create_at");
//	    pageEntity.setAsc(false);
////	    PageBean<LiveCategoryVo> pageBean = new PageBean<LiveCategoryVo>();
//	    SelectWrapper wrapper=new SelectWrapper();
//	    if(!StringUtils.isEmpty(moviesCategoryVo.getCateName()))
//	    {
//	    	wrapper.like("cate_name", moviesCategoryVo.getCateName());
//	    }
//	    if(!StringUtils.isEmpty(moviesCategoryVo.getStartTime()))
//	    {
//	    	wrapper.ge("create_at", moviesCategoryVo.getStartTime());
//	    }
//	    if(!StringUtils.isEmpty(moviesCategoryVo.getEndTime()))
//	    {
//	    	wrapper.le("create_at", moviesCategoryVo.getStartTime());
//	    }
//	    pageEntity=videoCategoryService.selectPage(wrapper, pageEntity);
////	    pageBean.setPage(page.intValue());
////	    pageBean.setPageSize(rows.intValue());
////	    pageBean.setParameter(moviesCategoryVo);
////	    pageBean = this.liveCategoryService.selectListPage(pageBean);
//	    
//	    Map<String, Object> map = new HashMap<String, Object>();
//	    
//	    map.put("total", Long.valueOf(pageEntity.getTotal()));
//	    if (pageEntity.getTotal() >= 0L) {
//	      map.put("rows", pageEntity.getRecords());
//	    }
//	    write(response, map);
//	  }
//	@RequestMapping({"save"})
//	  public void save(HttpServletRequest request, HttpServletResponse response, Model model, VideoCategory liveCategoryVo, Integer page, Integer rows)
//	  {
//	    String id = PKUtils.getUUID();
//	    liveCategoryVo.setCreateAt(new Date());
//	    liveCategoryVo.setId(id);
//	    this.videoCategoryService.save(liveCategoryVo);
//	    Map<String, Object> map = new HashMap<String, Object>();
//	    map.put("msg", "");
//	    write(response, map);
//	  }
}
