package com.song.play.cloud.web;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.play.cloud.base.AbstractBaseController;
import com.song.play.cloud.entity.Click;
import com.song.play.cloud.entity.Search;
import com.song.play.cloud.entity.Users;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.service.SearchApi;
import com.song.play.cloud.service.UserApi;
import com.song.play.cloud.service.VideoApi;
import com.song.play.cloud.tools.JsonUtil;
import com.song.play.cloud.tools.PKUtils;



@Controller
@RequestMapping("click")
public class ClickController extends AbstractBaseController{

	@Autowired
	private UserApi userApi;
//
	@Autowired
	private VideoApi videoApi;
	@Autowired
	private SearchApi searchApi;
//
//	@Autowired
//	private VideoCategoryService videoCategoryService;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@RequestMapping("view.html")
	public String view(HttpServletRequest request,HttpServletResponse response)
	{
		return "admin/click/view";
	}
//	
	@RequestMapping("save.html")
	public void save(HttpServletRequest request,HttpServletResponse response,String video_id)
	{
		Users users=this.getCurrentUser(request);
		String userId="";
		if(users==null)
		{
			userId="b5b61784323d40f6a5734653725919b7";
		}else{
			userId=users.getId();
		}
		Click vo=new Click();
		vo.setId(PKUtils.getUUID());
		vo.setCreateAt(new Date());
		vo.setUserId(userId);
		vo.setVideoId(video_id);
		String videoId=video_id;
		int count=userApi.clickselectCount(videoId,userId);
		if(count==0)
		{
			userApi.saveClick(vo);
		}
		//.put("com.song.play.click", video_id, value);
		HashOperations<String,String,String> operations=redisTemplate.opsForHash();
		String num=operations.get("com.song.play.click", video_id.toString());
		if(num==null||"".equals(num))
		{
			operations.put("com.song.play.click", video_id.toString(), "1");
		}else{
			Long number=Long.valueOf(num)+1;
			operations.put("com.song.play.click", video_id.toString(), number.toString());
		}
		String json=redisTemplate.opsForValue().get("com.song.play.desktop");
		Map<String, List<LinkedHashMap<String, Object>>> map=JsonUtil.readValue(json,Map.class);
		for (Map.Entry<String, List<LinkedHashMap<String, Object>>> entry : map.entrySet()) {  
			List<LinkedHashMap<String, Object>> vos=entry.getValue();
			for(LinkedHashMap<String, Object> vo2:vos)
			{
				if(operations.get("com.song.play.click",vo2.get("id").toString())!=null)
				{
					vo2.put("chick", operations.get("com.song.play.click",vo2.get("id").toString()));
				}else{
					vo2.put("chick", 0);
				}
			}
		}
		redisTemplate.opsForValue().set("com.song.play.desktop", JsonUtil.writeValueAsString(map));
		Search searchVo=new Search();
		Video moviesVo=videoApi.getVideoById(video_id);
		if(moviesVo!=null)
		{
			searchVo.setId(moviesVo.getId());
		    
		    searchVo.setTitle(moviesVo.getTitle());
		    searchVo.setAbout(moviesVo.getAbout());
		    searchVo.setCateId(moviesVo.getCateId());
		    searchVo.setCateName(videoApi.getCateById(moviesVo.getCateId()).getCateName());
		    searchVo.setChick(Long.valueOf(operations.get("com.song.play.click",video_id.toString())));
		    searchVo.setCreateAt(moviesVo.getCreateAt());
		    searchVo.setImageName(moviesVo.getImageName());
		    searchVo.setMessage("");
		    searchVo.setStatus(moviesVo.getStatus());
		    searchVo.setType(moviesVo.getType());
		    searchVo.setTopImage("");
		    searchVo.setTag(moviesVo.getTag());
		    searchVo.setUserId(moviesVo.getUserId());
		    searchVo.setNickName(userApi.getUsersById(vo.getUserId()).getNickName());
		    searchVo.setVideoPath(moviesVo.getRtmp());
		    searchVo.setVideoStream("");
		    searchVo.setVideoLength(moviesVo.getVideoLength());
		    searchApi.updateIndex(searchVo);
		}
		this.write(response, vo);
	}
//	public static void main(String[] args) {
//		System.out.println(PKUtils.getUUID());
//	}
}
