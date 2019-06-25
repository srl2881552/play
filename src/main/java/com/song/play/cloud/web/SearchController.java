package com.song.play.cloud.web;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;










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
import com.song.play.cloud.tools.SearchTools;
import com.song.play.cloud.tools.Tools;



@Controller
@RequestMapping("search")
public class SearchController extends AbstractBaseController{

	@Autowired
	private SearchApi searchApi;
////	@Autowired
////	private CloudSolrClient cloudSolrClient;
	@Autowired
	private VideoApi videoApi;
	@Autowired
	private UserApi userApi;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@RequestMapping("refresh")
	@ResponseBody
	public void refresh(HttpServletRequest request,HttpServletResponse response,String searchDoc)
	{
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int currentPage=1;
				searchApi.removeAll();
				HashOperations<String,String,String> operations=redisTemplate.opsForHash();
				PageBean<Video> videos=videoApi.selectListPage(currentPage, 200);
				int totalPage=videos.getTotalPage();
				while(currentPage<=totalPage)
				{
					videos=videoApi.selectListPage(currentPage, 200);
					if(videos.getResult()!=null)
					{
						for(Video vo:videos.getResult())
						{
							Search search=new Search();
							search.setId(vo.getId());
							search.setAbout(vo.getAbout());
							search.setCateId(vo.getCateId());
							String num=operations.get("com.song.play.click", vo.getId());
							if(num==null||"".equals(num))
							{
								search.setChick(0L);
							}else{
								search.setChick(Long.valueOf(num));
							}
							
							search.setCreateAt(vo.getCreateAt());
							search.setTag(vo.getTag());
							search.setImageName(vo.getImageName());
							search.setMessage(vo.getMessage()==null?"":vo.getMessage());
							search.setStatus(vo.getStatus());
							search.setTitle(vo.getTitle());
							search.setTopImage(vo.getTopImage()==null?"":vo.getTopImage());
							search.setUserId(vo.getUserId());
							Users user=userApi.getUsersById(vo.getUserId());
							search.setNickName(user==null?"":user.getNickName());
							VideoCategory categoryVo=videoApi.getCateById(vo.getCateId());
							search.setCateName(categoryVo==null?"":categoryVo.getCateName());
							search.setVideoPath(vo.getRtmp());
							search.setVideoStream(vo.getStream()==null?"":vo.getStream());
							search.setType(vo.getType());
							search.setVideoLength(vo.getVideoLength()==null?"":vo.getVideoLength());

							searchApi.saveIndex(search);
						}
					}
					currentPage++;
				}
			}
		});
		
		this.write(response, "success");
	}
	@RequestMapping("MoviesCateSearch/{id}.html")
	public String MoviesCate(HttpServletRequest request,HttpServletResponse response,@PathVariable String id,Integer curPage,Integer pageSize,Model model)
	{
		int pageNum=curPage==null?1:curPage;
		int size=pageSize==null?20:pageSize;
		SearchPageBean<Search> pageBean=searchApi.searchVideoCate("create_at", id, pageNum, size);
		
		VideoCategory categoryVo=videoApi.getCateById(id);
		//model.addAttribute("map", map);
		model.addAttribute("id", id);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("catename", categoryVo.getCateName());
		return "index/search/videoCatesearch";
	}
	@RequestMapping("multipleSearch")
	public String multipleSearch(HttpServletRequest request,HttpServletResponse response,String searchDoc,String search,Integer curPage,Integer pageSize,Model model)
	{
		int pageNum=curPage==null?1:curPage;
		int size=pageSize==null?20:pageSize;
		
		SearchPageBean<Search> pageBean=searchApi.searchMultiple("create_at", SearchTools.StringFilter(search), pageNum, size);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("catename", SearchTools.StringFilter(search));
		return "index/search/search-results";
	}
	@RequestMapping("multipleSearch/refresh")
	public String multipleSearchRefresh(HttpServletRequest request,HttpServletResponse response,String search,Integer curPage,Integer pageSize,Model model)
	{
		
		int pageNum=curPage==null?1:curPage;
		int size=pageSize==null?20:pageSize;
		SearchPageBean<Search> pageBean=searchApi.searchMultiple("create_at", SearchTools.StringFilter(search), pageNum, size);
		
		
		//model.addAttribute("map", map);

		model.addAttribute("pageBean", pageBean);
		model.addAttribute("catename", SearchTools.StringFilter(search));
		return "index/search/results";
	}
	@RequestMapping("MoviesCateSearch/refresh.html")
	public String refresh(HttpServletRequest request,HttpServletResponse response,String id,Integer page,Integer pageSize,Model model)
	{
		
		int pageNum=page==null?1:page;
		int size=pageSize==null?20:pageSize;
		SearchPageBean<Search> pageBean=searchApi.searchVideoCate("create_at", id, pageNum, size);
		VideoCategory categoryVo=videoApi.getCateById(id);
		model.addAttribute("id", id);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("catename", categoryVo.getCateName());
		return "index/search/results";
	}
	@RequestMapping("LiveCateSearch/{id}.html")
	public String LiveCateSearch(HttpServletRequest request,HttpServletResponse response,@PathVariable String id,Integer curPage,Integer pageSize,Model model)
	{
		
		int pageNum=curPage==null?1:curPage;
		int size=pageSize==null?20:pageSize;
		SearchPageBean<Search> pageBean=searchApi.searchLiveCate("create_at", id, pageNum, size);
		
		VideoCategory categoryVo=videoApi.getCateById(id);
		//model.addAttribute("map", map);
		model.addAttribute("id", id);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("catename", categoryVo.getCateName());
		return "index/search/liveCatesearch";
	}
	
	@RequestMapping("LiveCateSearch/refresh.html")
	public String liverefresh(HttpServletRequest request,HttpServletResponse response,String id,Integer curPage,Integer pageSize,Model model)
	{
		int pageNum=curPage==null?1:curPage;
		int size=pageSize==null?20:pageSize;
		SearchPageBean<Search> pageBean=searchApi.searchLiveCate("create_at", id, pageNum, size);
		
		VideoCategory categoryVo=videoApi.getCateById(id);
		//model.addAttribute("map", map);
		model.addAttribute("id", id);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("catename", categoryVo.getCateName());
		
		return "index/search/results";
	}
}
