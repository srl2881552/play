package com.song.play.cloud.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.base.AbstractBaseController;



@Controller
@RequestMapping("tagManager")
public class VideoTagManagerController extends AbstractBaseController{

//	@Autowired
//	private VideoTagService videoTagService;
//	@RequestMapping("view.html")
//	public String view(HttpServletRequest request,HttpServletResponse response,Model model)
//	{
//		return "admin/tag/view";
//	}
//	@RequestMapping("list.html")
//	public void list(HttpServletRequest request,HttpServletResponse response,Model model,VideoTag videoTag,String startTime,String endTime,Integer page, Integer rows)
//	{
//		if (page == null) {
//		      page = Integer.valueOf(1);
//		    }
//		Page<VideoTag> pageEntity=new Page<VideoTag>(page, rows, "create_at");
//		pageEntity.setAsc(false);
//		SelectWrapper wrapper=new SelectWrapper();
//	    if(!StringUtils.isEmpty(videoTag.getTagText()))
//	    {
//	    	wrapper.like("tag_text", videoTag.getTagText());
//	    }
//	    if(!StringUtils.isEmpty(startTime))
//	    {
//	    	wrapper.ge("create_at", startTime);
//	    }
//	    if(!StringUtils.isEmpty(endTime))
//	    {
//	    	wrapper.le("create_at", endTime);
//	    }
//	    pageEntity=videoTagService.selectPage(wrapper, pageEntity);
//	    Map<String, Object> map = new HashMap<String, Object>();
//	    
//	    map.put("total", Long.valueOf(pageEntity.getTotal()));
//	    if (pageEntity.getTotal() >= 0L) {
//	      map.put("rows", pageEntity.getRecords());
//	    }
//	    write(response, map);
//	}
//	@RequestMapping({"save"})
//	  public void save(HttpServletRequest request, HttpServletResponse response, Model model, VideoTag videoTag, Integer page, Integer rows)
//	  {
//	    String id = PKUtils.getUUID();
//	    videoTag.setCreateAt(new Date());
//	    videoTag.setId(id);
//	    videoTag.setStatus(1);
//	    this.videoTagService.save(videoTag);
//	    Map<String, Object> map = new HashMap<String, Object>();
//	    map.put("msg", "");
//	    write(response, map);
//	  }
}
