package com.song.play.cloud.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.song.play.cloud.base.AbstractBaseController;


@Controller
@RequestMapping("live")
public class LiveController extends AbstractBaseController{

//	@Autowired
//	private VideoService videoService;
//	@Autowired
//	private VideoCategoryService videoCategoryService;
//	@Autowired
//	private UsersService usersService;
//	@Autowired
//	private SearchService searchService;
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
//	@RequestMapping("view.html")
//	public String view(HttpServletRequest request,HttpServletResponse response,Model model)
//	{
//		List<VideoCategory> vos = this.videoCategoryService.selectList(SelectWrapper.instance().eq("type", 1));
//	    model.addAttribute("vos", vos);
//		return "admin/live/view";
//	}
//	@RequestMapping({"list"})
//	  public void list(HttpServletRequest request, HttpServletResponse response, Model model, Video liveVo, Integer page, Integer rows)
//	  {
//	    if (page == null) {
//	      page = Integer.valueOf(1);
//	    }
//	    Page<Video> pageEntity=new Page<Video>(page, rows, "create_at");
//	    pageEntity.setAsc(false);
////	    PageBean<LiveCategoryVo> pageBean = new PageBean<LiveCategoryVo>();
//	    SelectWrapper wrapper=new SelectWrapper();
//	    wrapper.eq("type", 1);
//	    if(!StringUtils.isEmpty(liveVo.getTitle()))
//	    {
//	    	wrapper.like("title", liveVo.getTitle());
//	    }
//	    if(!StringUtils.isEmpty(liveVo.getStartTime()))
//	    {
//	    	wrapper.ge("create_at", liveVo.getStartTime());
//	    }
//	    if(!StringUtils.isEmpty(liveVo.getEndTime()))
//	    {
//	    	wrapper.le("create_at", liveVo.getStartTime());
//	    }
//	    pageEntity=videoService.selectPage(wrapper, pageEntity);
//	    
//	    
//	    List<Video> moviesVos = pageEntity.getRecords();
//	    for (Video vo : moviesVos) {
//	      vo.setCateName(videoCategoryService.selectList(SelectWrapper.instance().eq("id", vo.getCateId())).get(0).getCateName());
//	    }
//	    Map<String, Object> map = new HashMap();
//	    
//	    map.put("total", Long.valueOf(pageEntity.getTotal()));
//	    if (pageEntity.getTotal() >= 0L) {
//	      map.put("rows", pageEntity.getRecords());
//	    }
//	    write(response, map);
//	  }
//	@RequestMapping("save.html")
//	public String save(HttpServletRequest request,@RequestParam MultipartFile image,HttpServletResponse response)
//	{
//		java.text.SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String title=request.getParameter("title");
//		String about=request.getParameter("about");
//		String userId = request.getParameter("userId") == null ? "b34e935f948848d2b5da25a17245f55f"
//				: request.getParameter("userId");
//		String rtmp=request.getParameter("rtmp");
//		String stream=request.getParameter("stream");
//		String cate_id=request.getParameter("cate_id")==null?"150286703048488111":request.getParameter("cate_id");
//		String id=PKUtils.getUUID();
//		String image_name=FileTools.getVideoProperties().getProperty("image.web.path") + id;
//		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
//		List<MultipartFile> multipartFiles=multiRequest.getFiles("file");
//		
//		try {
//			//Connection conn=ScpTools.getConnection("192.168.0.100", 22, "root", "as2881552");
//			Video vo=new Video();
//			Search searchVo=new Search();
//			String endFix = image.getOriginalFilename().substring(
//					image.getOriginalFilename().lastIndexOf("."),
//					image.getOriginalFilename().length());
//			File imageFile=new File(FileTools.getVideoProperties().getProperty("image.local.path")+id+endFix);
//			image.transferTo(imageFile);
//			// 视频转换
//			vo.setId(id);
//			searchVo.setId(id);
//			vo.setTitle(title);
//			searchVo.setTitle(title);
//			searchVo.setAbout(about);
//			searchVo.setCateId(cate_id);
//			String cateName = videoCategoryService.selectList(SelectWrapper.instance().eq("id", cate_id)).get(0).getCateName();
//			searchVo.setCateName(cateName);
//			vo.setCateName(cateName);
//			searchVo.setChick(0L);
//			searchVo.setCreateAt(new Date());
//			vo.setAbout(about);
//			vo.setCreateAt(new Date());
//			vo.setStatus(1);
//			vo.setType(1);
//			image_name += image.getOriginalFilename().substring(
//					image.getOriginalFilename().lastIndexOf("."),
//					image.getOriginalFilename().length());
//			vo.setImageName(image_name);
//			vo.setCateId(cate_id);
//			vo.setUserId(userId);
//			vo.setRtmp(rtmp);
//			vo.setStream(stream);
//			searchVo.setImageName(image_name);
//			searchVo.setMessage("");
//			searchVo.setStatus(1);
//			searchVo.setTopImage("");
//			searchVo.setUserId(userId);
//			searchVo.setNickName(usersService.selectList(SelectWrapper.instance().eq("id", userId)).get(0).getNickName());
//			searchVo.setVideoStream(stream);
//			searchVo.setVideoPath(rtmp);
//			searchVo.setType(1);
//			vo.setVideoLength("199:99:99");
//			searchVo.setVideoLength(vo.getVideoLength());
//			videoService.save(vo);
//			searchService.saveIndex(searchVo);
//            String json=redisTemplate.opsForValue().get("com.song.play.desktop");
//            Map<String, List<LinkedHashMap<String, Object>>> map=JsonUtil.readValue(json, Map.class);
//            LinkedHashMap<String, Object> desktop=new LinkedHashMap<String, Object>(); 
//            List<LinkedHashMap<String, Object>> vos=map.get(searchVo.getCateName());
//            if(vos==null)
//            {
//                    vos=new ArrayList<LinkedHashMap<String,Object>>();
//            }
//            desktop.put("id", searchVo.getId());
//            desktop.put("userId", searchVo.getUserId());
//            desktop.put("nickName", searchVo.getNickName());
//            desktop.put("title", searchVo.getTitle());
//            desktop.put("about", searchVo.getAbout());
//            desktop.put("cateId", searchVo.getCateId());
//            desktop.put("cateName", searchVo.getCateName());
//            desktop.put("rtmp", searchVo.getVideoPath());
//            desktop.put("stream", searchVo.getVideoStream());
//            desktop.put("status", searchVo.getStatus());
//            desktop.put("message", searchVo.getMessage());
//            desktop.put("createAt", format.format(searchVo.getCreateAt()));
//            desktop.put("topImage", searchVo.getTopImage());
//            desktop.put("imageName", searchVo.getImageName());
//            desktop.put("type", searchVo.getType());
//            desktop.put("chick", 0L);
//            if(vos.size()>=20)
//            {
//            	vos.remove(0);
//            	vos.add(desktop);
//            }else{
//            	vos.add(desktop);
//            }
//            System.out.println(map);
//            map.put(searchVo.getCateName(), vos);
//            redisTemplate.opsForValue().set("com.song.play.desktop", JsonUtil.writeValueAsString(map));
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "redirect:/live/view.html";
//		
//	}
//	@RequestMapping({"enable"})
//	  public void enable(HttpServletRequest request, HttpServletResponse response, Model model, String id, Integer status)
//	  {
//	    
//	    Search searchVo = new Search();
//	    Video vo=this.videoService.selectList(SelectWrapper.instance().eq("id", id)).get(0);
//	    vo.setStatus(status);
//
//	    this.videoService.update(vo);
//	    searchVo.setId(vo.getId());
//	    
//	    searchVo.setTitle(vo.getTitle());
//	    searchVo.setAbout(vo.getAbout());
//	    searchVo.setCateId(vo.getCateId());
//	    searchVo.setCateName(vo.getCateName());
//	    searchVo.setCreateAt(vo.getCreateAt());
//	    
//
//	    searchVo.setImageName(vo.getImageName());
//	    searchVo.setMessage("");
//	    searchVo.setStatus(vo.getStatus());
//	    searchVo.setTopImage("");
//	    searchVo.setUserId(vo.getUserId());
//	    searchVo.setNickName(usersService.get(vo.getUserId()).getNickName());
//	    searchVo.setVideoPath(vo.getRtmp());
//	    searchVo.setVideoStream(vo.getStream());
//	    Map<String, Object> map = new HashMap<String, Object>();
//	    this.searchService.updateIndex(searchVo);
//	    map.put("msg", "");
//	    write(response, map);
//	  }
}
