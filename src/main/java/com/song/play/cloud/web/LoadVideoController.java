package com.song.play.cloud.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.song.play.cloud.base.AbstractBaseController;


@Controller
@RequestMapping("loadVideo")
public class LoadVideoController extends AbstractBaseController{

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
//	@RequestMapping("load.html")
//	@ResponseBody
//	public String load(HttpServletRequest request,HttpServletResponse response,Model model)
//	{
//		java.text.SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		File dir=new File("E:/init");
//		File[] files=dir.listFiles();
//		File img=new File("e:/image/test2.png");
//		for(File file:files)
//		{
//			String title=file.getName().substring(0, file.getName().lastIndexOf("."));
//			String about=file.getName().substring(0, file.getName().lastIndexOf("."));
//			String cate_id="d236c80253594e96be737ec8d7a8245b";
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
//				searchVo.setImageName(image_name);
//				searchVo.setMessage("");
//				searchVo.setStatus(1);
//				searchVo.setTopImage("");
//				searchVo.setUserId("b34e935f948848d2b5da25a17245f55f");
//				searchVo.setNickName(usersService.selectList(SelectWrapper.instance().eq("id", "b34e935f948848d2b5da25a17245f55f")).get(0).getNickName());
//				searchVo.setVideoPath(video_path);
//				searchVo.setVideoStream("");
//				//ScpTools.uploadFile(toByteArray(new FileInputStream(file)), id+file.getName().substring(file.getName().lastIndexOf("."), file.getName().length()), "/home/nginx/vod", conn);
//				//ScpTools.uploadFile(file.getAbsolutePath(), id+file.getName().substring(file.getName().lastIndexOf("."), file.getName().length()), "/home/nginx/vod", conn);
//				//ScpTools.uploadFile(toByteArray(new FileInputStream(img)), id+img.getName().substring(img.getName().lastIndexOf("."), img.getName().length()), "/home/nginx/html/images", conn);
//				videoService.save(vo);
//				//searchService.saveIndex("movies", searchVo);
//				//conn.close();
//				searchService.saveIndex(searchVo);
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
//		File videoFile=new File(FileTools.getVideoProperties().getProperty("video.local.path")+id+file.getName().substring(file.getName().lastIndexOf("."), file.getName().length()));
//		File imageFile=new File(FileTools.getVideoProperties().getProperty("image.local.path")+id+img.getName().substring(img.getName().lastIndexOf("."), img.getName().length()));
//		file.renameTo(videoFile);
//		file.delete();
//		img.renameTo(imageFile);
//	}
//		return "success";
//	}
}
