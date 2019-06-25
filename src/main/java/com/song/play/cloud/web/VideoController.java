package com.song.play.cloud.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.song.play.cloud.base.AbstractBaseController;
import com.song.play.cloud.entity.PageBean;
import com.song.play.cloud.entity.Search;
import com.song.play.cloud.entity.SearchPageBean;
import com.song.play.cloud.entity.Users;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.entity.VideoCategory;
import com.song.play.cloud.service.SearchApi;
import com.song.play.cloud.service.UserApi;
import com.song.play.cloud.service.VideoApi;
import com.song.play.cloud.tools.FileTools;
import com.song.play.cloud.tools.FmpegThread;
import com.song.play.cloud.tools.PKUtils;
import com.song.play.cloud.tools.Tools;


@Controller
public class VideoController extends AbstractBaseController{

	@Autowired
	private VideoApi videoApi;
	@Autowired
	private UserApi userApi;
//	@Autowired
//	private VideoCategoryService videoCategoryService;
	@Autowired
	private SearchApi searchApi;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
//	
//	@RequestMapping("live/{id}.html")
//	public String live(HttpServletRequest request,HttpServletResponse response,@PathVariable long id,Model model)
//	{
//		
//		model.addAttribute("liveUrl", "rtmp://39.108.11.81/live/test");
//		
//		return "index/live/live";
//	}
	@RequestMapping("live/all.html")
	public String liveAll(HttpServletRequest request,HttpServletResponse response,Integer curPage,Integer pageSize,Model model)
	{
		int pageNum=curPage==null?1:curPage;
		int size=pageSize==null?20:pageSize;
		SearchPageBean<Search> pageBean=searchApi.searchLiveAll("create_at", pageNum, size);
		List<Search> group=searchApi.searchGroup("create_at", "1", pageBean.getTotalRows());
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("group", group);
		return "index/live/all";
	}
	@RequestMapping("live/all/refresh.html") 
	public String liveAllRefresh(HttpServletRequest request,HttpServletResponse response,Integer curPage,Integer pageSize,Model model)
	{
		int pageNum=curPage==null?1:curPage;
		int size=pageSize==null?20:pageSize;
		
		SearchPageBean<Search> pageBean=searchApi.searchLiveAll("create_at", pageNum, size);
		List<Search> group=searchApi.searchGroup("create_at", "1", pageBean.getTotalRows());
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("group", group);
		return "index/live/results";
	}
//	@RequestMapping("video/{id}.html")
//	public String video(HttpServletRequest request,HttpServletResponse response,@PathVariable Long id,Model model)
//	{
//		System.out.println("11111111");
//		model.addAttribute("liveUrl", "rtmp://39.108.11.81/live/test");
//		return "index/video/video";
//	}
	@RequestMapping("video/time.html")
	public void time(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		
		Users users=this.getCurrentUser(request);
		String userId="";
		if(users==null)
		{
			userId="b5b61784323d40f6a5734653725919b7";
		}else{
			userId=users.getId();
		}
		String videoId=request.getParameter("videoId");
		String t=request.getParameter("t");
		System.out.println(userId);
		System.out.println(videoId);
		System.out.println(t);
		redisTemplate.opsForHash().put(userId, videoId, t);
		this.write(response, "success");
	}
	@RequestMapping("video/all.html") 
	public String videoAll(HttpServletRequest request,HttpServletResponse response,Integer curPage,Integer pageSize,Model model)
	{
		int pageNum=curPage==null?1:curPage;
		int size=pageSize==null?20:pageSize;
		SearchPageBean<Search> pageBean=searchApi.searchVideoAll("create_at", pageNum, size);
		List<Search> group=searchApi.searchGroup("create_at", "2", pageBean.getTotalRows());
//		List<Search> group=new ArrayList<Search>();
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("group", group);
		return "index/video/all";
	}
	@RequestMapping("video/all/refresh.html") 
	public String videoAllRefresh(HttpServletRequest request,HttpServletResponse response,Integer curPage,Integer pageSize,Model model)
	{
		int pageNum=curPage==null?1:curPage;
		int size=pageSize==null?20:pageSize;
		
		SearchPageBean<Search> pageBean=searchApi.searchVideoAll("create_at", pageNum, size);
		List<Search> group=searchApi.searchGroup("create_at", "2", pageBean.getTotalRows());
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("group", group);
		return "index/video/results";
	}

	@RequestMapping("video/refreshTime.html") 
	@ResponseBody
	public void refreshTime(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		int currentPage=1;
		PageBean<Video> videos=videoApi.selectListPage(currentPage, 200);
		int totalPage=videos.getTotalPage();
		while(currentPage<=totalPage)
		{
			videos=videoApi.selectListPage(currentPage, 200);
			if(videos.getResult()!=null)
			{
				for(Video video:videos.getResult())
				{
					video.setVideoLength(Tools.getVideoLength(new File(FileTools.getVideoProperties().getProperty("video.local.path")+video.getId()+".mp4")));
					videoApi.update(video);
				}
			}
			currentPage++;
		}
		

		this.write(response, "success");
	}
	@RequestMapping("video/showUpload.html") 
	public String showUpload(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		Users users=this.getCurrentUser(request);
		if(users==null)
		{
			return "redirect:/user/login";
		}
		List<VideoCategory> categories=videoApi.getVideoAll();
		Users user=userApi.getUsersById(users.getId());
		System.out.println("11111111");
		model.addAttribute("cates", categories);
		model.addAttribute("user", user);
		return "index/video/upload";
	}
	@RequestMapping("video/upload") 
	public String upload(HttpServletRequest request,HttpServletResponse response,Video video,@RequestParam(value = "imgUpload") MultipartFile imgUpload,@RequestParam(value = "videoUpload") MultipartFile videoUpload,Model model)
	{
		Users users=this.getCurrentUser(request);
		if(users==null)
		{
			return "redirect:/user/login";
		}
		String tag = request.getParameter("tag");
		Users user=userApi.getUsersById(users.getId());
		VideoCategory videoCategory=videoApi.getCateById(video.getCateId());
		String id=PKUtils.getUUID();
		String video_path = FileTools.getVideoProperties().getProperty("video.web.path") + id;
		String image_name = FileTools.getVideoProperties().getProperty("image.web.path") + id;
		

		try {
			// Connection conn=ScpTools.getConnection("192.168.0.100", 22,
			// "root", "as2881552");
			Video vo = new Video();
			Search searchVo = new Search();
			String endFix = imgUpload.getOriginalFilename().substring(
					imgUpload.getOriginalFilename().lastIndexOf("."),
					imgUpload.getOriginalFilename().length());
			File tempFile = new File(FileTools.getVideoProperties().getProperty("video.temp.path") + videoUpload.getOriginalFilename());
			File newFile = new File(FileTools.getVideoProperties().getProperty("video.local.path") + id + ".mp4");
			videoUpload.transferTo(tempFile);
			File imageFile=new File(FileTools.getVideoProperties().getProperty("image.local.path")+id+endFix);
			imgUpload.transferTo(imageFile);
			// 视频转换
			video.setId(id);
			searchVo.setId(id);
			
			searchVo.setTitle(video.getTitle());
			searchVo.setAbout(video.getAbout());
			searchVo.setTag(tag);
			searchVo.setCateId(video.getCateId());
			String cateName = videoCategory.getCateName();
			searchVo.setCateName(cateName);
			video.setCateName(cateName);
			searchVo.setChick(0L);
			searchVo.setCreateAt(new Date());
			video.setTag(tag);
			video.setCreateAt(new Date());
			video.setStatus(2);
			video.setType(2);
			
			video_path += ".mp4";
			image_name += imgUpload.getOriginalFilename().substring(
					imgUpload.getOriginalFilename().lastIndexOf("."),
					imgUpload.getOriginalFilename().length());
			video.setImageName(image_name);
			
			video.setUserId(users.getId());
			video.setRtmp(video_path);
			searchVo.setImageName(image_name);
			searchVo.setMessage("");
			searchVo.setStatus(1);
			searchVo.setTopImage("");
			searchVo.setUserId(users.getId());
			searchVo.setNickName(user.getNickName());
			searchVo.setVideoPath(video_path);
			searchVo.setVideoStream("");
			searchVo.setType(2);
			video.setVideoLength(Tools.getVideoLength(tempFile));
			searchVo.setVideoLength(video.getVideoLength()); 
			videoApi.save(video);
			new Thread(new FmpegThread(videoApi,
					tempFile.getAbsolutePath(), newFile.getAbsolutePath(), video,
					searchApi, tempFile, searchVo, redisTemplate)).start();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(video);
		System.out.println(imgUpload);
		System.out.println(videoUpload);
		return "redirect:/mine/space.html";
		
	}
}
