package com.song.play.cloud.web;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.song.play.cloud.base.AbstractBaseController;


@Controller
@RequestMapping("movies")
public class MoviesController extends AbstractBaseController{

//	@Autowired
//	private VideoService videoService;
//	@Autowired
//	private SearchService searchService;
//	@Autowired
//	private VideoCategoryService videoCategoryService;
//	@Autowired
//	private UsersService usersService;
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
//	
//	@RequestMapping("view.html")
//	public String view(HttpServletRequest request,HttpServletResponse response, Model model)
//	{
//		List<VideoCategory> vos = this.videoCategoryService.getVideoAll();
//	    model.addAttribute("vos", vos);
//	    return "admin/movies/view";
//	}
//	@RequestMapping({"list"})
//	  public void list(HttpServletRequest request, HttpServletResponse response, Model model, Video video, Integer page, Integer rows)
//	  {
//	    if (page == null) {
//	      page = Integer.valueOf(1);
//	    }
//	    Page<Video> pageEntity=new Page<Video>(page, rows, "create_at");
//	    pageEntity.setAsc(false);
////	    PageBean<LiveCategoryVo> pageBean = new PageBean<LiveCategoryVo>();
//	    SelectWrapper wrapper=new SelectWrapper();
//	    wrapper.eq("type", 2);
//	    if(!StringUtils.isEmpty(video.getTitle()))
//	    {
//	    	wrapper.like("title", video.getTitle());
//	    }
//	    if(!StringUtils.isEmpty(video.getStartTime()))
//	    {
//	    	wrapper.ge("create_at", video.getStartTime());
//	    }
//	    if(!StringUtils.isEmpty(video.getEndTime()))
//	    {
//	    	wrapper.le("create_at", video.getStartTime());
//	    }
//	    pageEntity=videoService.selectPage(wrapper, pageEntity);
//	   
//	    Map<String, Object> map = new HashMap();
//	    
//	    map.put("total", Long.valueOf(pageEntity.getTotal()));
//	    if (pageEntity.getTotal() >= 0L) {
//	      map.put("rows", pageEntity.getRecords());
//	    }
//	    write(response, map);
//	  }
//	  
//	  @RequestMapping({"enable"})
//	  public void enable(HttpServletRequest request, HttpServletResponse response, Model model, String id, Integer status)
//	  {
//	    Search searchVo = new Search();
//	    Video vo=videoService.selectList(SelectWrapper.instance().eq("id", id)).get(0);
//	    vo.setStatus(status);
//	    this.videoService.update(vo);
//	    searchVo.setId(vo.getId());
//	    searchVo.setTitle(vo.getTitle());
//	    searchVo.setAbout(vo.getAbout());
//	    searchVo.setCateId(vo.getCateId());
//	    searchVo.setCateName(vo.getCateName());
//	    //searchVo.setChick(Long.valueOf(0L));
//	    searchVo.setCreateAt(vo.getCreateAt());
//	    
//
//	    searchVo.setImageName(vo.getImageName());
//	    searchVo.setMessage(vo.getMessage()==null?"":vo.getMessage());
//	    searchVo.setStatus(vo.getStatus());
//	    searchVo.setTopImage(vo.getTopImage()==null?"":vo.getTopImage());
//	    searchVo.setUserId(vo.getUserId());
//	    searchVo.setNickName(usersService.get(vo.getUserId()).getNickName());
//	    searchVo.setVideoPath(vo.getRtmp());
//	    searchVo.setVideoStream(vo.getStream()==null?"":vo.getStream());
//	    Map<String, Object> map = new HashMap<String, Object>();
//	    this.searchService.updateIndex(searchVo);
//	    map.put("msg", "");
//	    write(response, map);
//	  }
////	@RequestMapping("search.html")
////	public String search(HttpServletRequest request,HttpServletResponse response,String searchDoc,String sort,String search,String cate_name,Integer curPage,Integer pageSize,Model model)
////	{
////		SortField sortField = new SortField(sort, SortField.Type.LONG, true);
////	    int pageNum = curPage == null ? 1 : curPage.intValue();
////	    int size = pageSize == null ? 10 : pageSize.intValue();
////	    com.song.play.tools.PageBean<SearchVo> pageBean = this.searchService.searchCate(searchDoc, sortField, search, pageNum, size);
////	    
////	    String cateJson = (String)this.redisTemplate.opsForValue().get("com.song.movies.cate");
////	    List<MoviesCategoryVo> categoryVos = new ArrayList<MoviesCategoryVo>();
////	    if ((cateJson != null) && (!"".equals(cateJson)))
////	    {
////	      categoryVos = JsonUtil.readValue(cateJson, List.class, MoviesCategoryVo.class);
////	    }
////	    else
////	    {
////	      categoryVos = this.moviesCategoryService.selectAll();
////	      this.redisTemplate.opsForValue().set("com.song.movies.cate", JsonUtil.writeValueAsString(categoryVos));
////	    }
////	    model.addAttribute("pageBean", pageBean);
////	    model.addAttribute("cate_name", cate_name);
////	    model.addAttribute("categoryVos", categoryVos);
////	    return "index/search";
////	}
//	@RequestMapping("save.html")
//	public String save(HttpServletRequest request,
//			@RequestParam MultipartFile video,
//			@RequestParam MultipartFile image, HttpServletResponse response) {
//		String title = request.getParameter("title");
//		String tag = request.getParameter("tag");
//		String about = request.getParameter("about");
//		String userId = request.getParameter("userId") == null ? "b34e935f948848d2b5da25a17245f55f"
//				: request.getParameter("userId");
//		String cate_id = request.getParameter("cate_id") == null ? "150286703048488111"
//				: request.getParameter("cate_id");
//		String id = PKUtils.getUUID();
//		String video_path = FileTools.getVideoProperties().getProperty("video.web.path") + id;
//		String image_name = FileTools.getVideoProperties().getProperty("image.web.path") + id;
//		
//
//		try {
//			// Connection conn=ScpTools.getConnection("192.168.0.100", 22,
//			// "root", "as2881552");
//			Video vo = new Video();
//			Search searchVo = new Search();
//			String endFix = image.getOriginalFilename().substring(
//					image.getOriginalFilename().lastIndexOf("."),
//					image.getOriginalFilename().length());
//			File tempFile = new File(FileTools.getVideoProperties().getProperty("video.temp.path") + video.getOriginalFilename());
//			File newFile = new File(FileTools.getVideoProperties().getProperty("video.local.path") + id + ".mp4");
//			video.transferTo(tempFile);
//			File imageFile=new File(FileTools.getVideoProperties().getProperty("image.local.path")+id+endFix);
//			image.transferTo(imageFile);
//			// 视频转换
//			vo.setId(id);
//			searchVo.setId(id);
//			vo.setTitle(title);
//			searchVo.setTitle(title);
//			searchVo.setAbout(about);
//			searchVo.setTag(tag);
//			searchVo.setCateId(cate_id);
//			String cateName = videoCategoryService.selectList(SelectWrapper.instance().eq("id", cate_id)).get(0).getCateName();
//			searchVo.setCateName(cateName);
//			vo.setCateName(cateName);
//			searchVo.setChick(0L);
//			searchVo.setCreateAt(new Date());
//			vo.setAbout(about);
//			vo.setTag(tag);
//			vo.setCreateAt(new Date());
//			vo.setStatus(2);
//			vo.setType(2);
//			
//			video_path += ".mp4";
//			image_name += image.getOriginalFilename().substring(
//					image.getOriginalFilename().lastIndexOf("."),
//					image.getOriginalFilename().length());
//			vo.setImageName(image_name);
//			vo.setCateId(cate_id);
//			vo.setUserId(userId);
//			vo.setRtmp(video_path);
//			searchVo.setImageName(image_name);
//			searchVo.setMessage("");
//			searchVo.setStatus(1);
//			searchVo.setTopImage("");
//			searchVo.setUserId(userId);
//			searchVo.setNickName(usersService.selectList(SelectWrapper.instance().eq("id", userId)).get(0).getNickName());
//			searchVo.setVideoPath(video_path);
//			searchVo.setVideoStream("");
//			searchVo.setType(2);
//			vo.setVideoLength(Tools.getVideoLength(tempFile));
//			searchVo.setVideoLength(vo.getVideoLength());
//			videoService.save(vo);
//			new Thread(new FmpegThread(videoService,
//					tempFile.getAbsolutePath(), newFile.getAbsolutePath(), vo,
//					searchService, tempFile, searchVo, redisTemplate)).start();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "redirect:/movies/view.html";
//
//	}
//	public static byte[] toByteArray(InputStream input) throws IOException {
//	    ByteArrayOutputStream output = new ByteArrayOutputStream();
//	    byte[] buffer = new byte[4096];
//	    int n = 0;
//	    while (-1 != (n = input.read(buffer))) {
//	        output.write(buffer, 0, n);
//	    }
//	    return output.toByteArray();
//	}
//	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
//		VideoService videoService=(VideoService)context.getBean("videoService");
//		SearchService searchService=(SearchService)context.getBean("searchService");
//		VideoCategoryService videoCategoryService=(VideoCategoryService)context.getBean("videoCategoryService");
//		UsersService usersService=(UsersService)context.getBean("usersService");
//		RedisTemplate<String, String> redisTemplate=(RedisTemplate)context.getBean("redisTemplate");
//		java.text.SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		File dir=new File("/Users/renlongsong/Downloads/1-200");
//		File[] files=dir.listFiles();
//		File img=new File("/Users/renlongsong/Downloads/0005018302191499_b.jpg");
//		for(File file:files)
//		{
//			String title=file.getName().substring(0, file.getName().lastIndexOf("."));
//			String about=file.getName().substring(0, file.getName().lastIndexOf("."));
//			String cate_id="d8a29822d39d4038a12af941ac8374de";
//			String id=PKUtils.getUUID();
//			String video_path=FileTools.getVideoProperties().getProperty("video.web.path")+id;
//			String image_name=FileTools.getVideoProperties().getProperty("image.web.path")+id;
//			
//			
//			try {
//				//Connection conn=ScpTools.getConnection("192.168.0.104", 22, "root", "as2881552");
//				Video vo=new Video();
//				Search searchVo=new Search();
//				vo.setId(id);
//				searchVo.setId(id);
//				vo.setTitle(title);
//				searchVo.setTitle(title);
//				searchVo.setAbout(about);
//				searchVo.setCateId(cate_id);
//				searchVo.setCateName(videoCategoryService.selectList(SelectWrapper.instance().eq("id", cate_id)).get(0).getCateName());
//				searchVo.setChick(0L);
//				searchVo.setCreateAt(new Date());
//				searchVo.setType(2);
//				vo.setAbout(about);
//				vo.setCreateAt(new Date());
//				vo.setStatus(1);
//				video_path+=file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
//				image_name+=img.getName().substring(img.getName().lastIndexOf("."), img.getName().length());
//				vo.setImageName(image_name);
//				vo.setCateId(cate_id);
//				vo.setUserId("b34e935f948848d2b5da25a17245f55f");
//				vo.setCateName(videoCategoryService.selectList(SelectWrapper.instance().eq("id", cate_id)).get(0).getCateName());
//				vo.setType(2);
//				vo.setRtmp(video_path);
//				vo.setMessage("");
//				vo.setTopImage("");
//				vo.setChick(0L);
//				vo.setVideoLength(Tools.getVideoLength(file));
//				searchVo.setImageName(image_name);
//				searchVo.setMessage("");
//				searchVo.setStatus(1);
//				searchVo.setTopImage("");
//				searchVo.setUserId("b34e935f948848d2b5da25a17245f55f");
//				searchVo.setNickName(usersService.selectList(SelectWrapper.instance().eq("id", "b34e935f948848d2b5da25a17245f55f")).get(0).getNickName());
//				searchVo.setVideoPath(video_path);
//				searchVo.setVideoStream("");
//				searchVo.setVideoLength(vo.getVideoLength());
//				//ScpTools.uploadFile(toByteArray(new FileInputStream(file)), id+file.getName().substring(file.getName().lastIndexOf("."), file.getName().length()), "/home/nginx/vod", conn);
//				//ScpTools.uploadFile(file.getAbsolutePath(), id+file.getName().substring(file.getName().lastIndexOf("."), file.getName().length()), "/home/nginx/vod", conn);
//				//ScpTools.uploadFile(toByteArray(new FileInputStream(img)), id+img.getName().substring(img.getName().lastIndexOf("."), img.getName().length()), "/home/nginx/html/images", conn);
//				videoService.save(vo);
//				//searchService.saveIndex("movies", searchVo);
//				//conn.close();
//	            String json=redisTemplate.opsForValue().get("com.song.play.desktop");
//	            Map<String, List<LinkedHashMap<String, Object>>> map=JsonUtil.readValue(json, Map.class);
//	            LinkedHashMap<String, Object> desktop=new LinkedHashMap<String, Object>(); 
//	            List<LinkedHashMap<String, Object>> vos=map.get(searchVo.getCateName());
//	            if(vos==null)
//	            {
//	                    vos=new ArrayList<LinkedHashMap<String,Object>>();
//	            }
//	            desktop.put("id", searchVo.getId());
//	            desktop.put("userId", searchVo.getUserId());
//	            desktop.put("nickName", searchVo.getNickName());
//	            desktop.put("title", searchVo.getTitle());
//	            desktop.put("about", searchVo.getAbout());
//	            desktop.put("cateId", searchVo.getCateId());
//	            desktop.put("cateName", searchVo.getCateName());
//	            desktop.put("rtmp", searchVo.getVideoPath());
//	            desktop.put("stream", searchVo.getVideoStream());
//	            desktop.put("status", searchVo.getStatus());
//	            desktop.put("message", searchVo.getMessage());
//	            desktop.put("createAt", format.format(searchVo.getCreateAt()));
//	            desktop.put("topImage", searchVo.getTopImage());
//	            desktop.put("imageName", searchVo.getImageName());
//	            desktop.put("type", searchVo.getType());
//	            desktop.put("chick", 0L);
//	            desktop.put("videoLength", searchVo.getVideoLength());
//	            if(vos.size()>=20)
//	            {
//	            	vos.remove(0);
//	            	vos.add(desktop);
//	            }else{
//	            	vos.add(desktop);
//	            }
//	           // System.out.println(map);
//	            map.put(searchVo.getCateName(), vos);
//	            redisTemplate.opsForValue().set("com.song.play.desktop", JsonUtil.writeValueAsString(map));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		File videoFile=new File("/Users/renlongsong/Downloads/temp/"+id+file.getName().substring(file.getName().lastIndexOf("."), file.getName().length()));
//		File imageFile=new File("/Users/renlongsong/Downloads/image/"+id+img.getName().substring(img.getName().lastIndexOf("."), img.getName().length()));
//		file.renameTo(videoFile);
//		file.delete();
//		try {
//			Files.copy(img.toPath(), imageFile.toPath());
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//	}
//	}
}
