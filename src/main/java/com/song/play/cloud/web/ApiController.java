package com.song.play.cloud.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.song.dop.framework.annotation.Register;
import org.song.dop.framework.annotation.RegisterController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.song.play.cloud.entity.Search;
import com.song.play.cloud.entity.SearchPageBean;
import com.song.play.cloud.entity.Users;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.entity.VideoCategory;
import com.song.play.cloud.service.SearchApi;
import com.song.play.cloud.service.UserApi;
import com.song.play.cloud.service.VideoApi;
import com.song.play.cloud.tools.PKUtils;
import com.song.play.cloud.tools.PrintUtils;
import com.song.play.cloud.tools.ResultCodeEnum;
import com.song.play.cloud.tools.SearchTools;
import com.song.play.cloud.tools.ServiceResult;

@RegisterController
@Controller
@RequestMapping("api")
public class ApiController {

//	@Autowired
//	private VideoCategoryService videoCategoryService;
	@Autowired
	private SearchApi searchApi;
//	@Autowired
//	private UserSpaceService userSpaceService;
	@Autowired
	private VideoApi videoApi;
	@Autowired
	private UserApi userApi;
//	@Autowired
//	private ClickService clickService;
//	@Autowired
//	private UserAccountService userAccountService;
//	@Autowired
//	private GoodsRecordService goodsRecordService;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Register("song.play.video.get.livecate")
	public void getLiveCate(HttpServletRequest request, HttpServletResponse response) {
		List<VideoCategory> liveCategoryVos=videoApi.getLiveAll();
		ServiceResult result=new ServiceResult();
		result.setResult(liveCategoryVos);
		System.out.println("liveCategoryVos");
		PrintUtils.printJson(request,response, result);
	}
	
	@Register("song.play.video.get.videocate")
	public void getVideoCate(HttpServletRequest request, HttpServletResponse response) {
		List<VideoCategory> liveCategoryVos=videoApi.getVideoAll();
		ServiceResult result=new ServiceResult();
		result.setResult(liveCategoryVos);
		System.out.println("liveCategoryVos");
		PrintUtils.printJson(request,response, result);
	}
	@Register("song.play.home.get.banner")
	public void getBanner(HttpServletRequest request, HttpServletResponse response) {

		SearchPageBean<Search> pageBean=searchApi.searchVideoAll("create_at", 1, 20);
		List<Search> group=searchApi.searchGroup("create_at", "2", pageBean.getTotalRows());
		ServiceResult result=new ServiceResult();
		result.setResult(group);
		System.out.println("group");
		PrintUtils.printJson(request,response, result);
	}
	@Register("song.play.home.get.advert")
	public void getAdvert(HttpServletRequest request, HttpServletResponse response) {
		
		ServiceResult result=new ServiceResult();
		result.setResult("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1134199377,3075523333&fm=26&gp=0.jpg");
		PrintUtils.printJson(request,response, result);
	}	
	@Register("song.play.home.hot.list")
	public void getHotList(HttpServletRequest request, HttpServletResponse response) {
		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
		ServiceResult result=new ServiceResult();
		SearchPageBean<Search> pageBean=searchApi.searchVideoAll("create_at",Integer.valueOf(pageNo),Integer.valueOf(pageSize));
		result.setResult(pageBean);
		PrintUtils.printJson(request,response, result);
	}	
	@Register("song.play.video.get.list")
	public void getVideoList(HttpServletRequest request, HttpServletResponse response) {
		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
		ServiceResult result=new ServiceResult();
		SearchPageBean<Search> pageBean=searchApi.searchVideoAll("create_at",Integer.valueOf(pageNo),Integer.valueOf(pageSize));
		result.setResult(pageBean);
		PrintUtils.printJson(request,response, result);
	}
	@Register("song.play.live.get.list")
	public void getLiveList(HttpServletRequest request, HttpServletResponse response) {
		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
		ServiceResult result=new ServiceResult();
		SearchPageBean<Search> pageBean=searchApi.searchLiveAll("create_at",Integer.valueOf(pageNo),Integer.valueOf(pageSize));
		result.setResult(pageBean);
		PrintUtils.printJson(request,response, result);
	}
	@Register("song.play.cate.get.list")
	public void getCateList(HttpServletRequest request, HttpServletResponse response) {
		ServiceResult result=new ServiceResult();
		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
		String search=request.getParameter("cateId");
		if(StringUtils.isEmpty(search))
		{
			result.setCode(-1);
			result.setMsg("请选择分类搜索");
			PrintUtils.printJson(request,response, result);
			return;
		}
		SearchPageBean<Search> pageBean=searchApi.searchVideoCate("create_at", search, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		result.setResult(pageBean);
		PrintUtils.printJson(request,response, result);
	}	
	@Register("song.play.multiple.get.list")
	public void getMultipleList(HttpServletRequest request, HttpServletResponse response) {
		ServiceResult result=new ServiceResult();
		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
		String search=request.getParameter("wd");
		System.out.println(search);
		if(StringUtils.isEmpty(search))
		{
			result.setCode(-1);
			result.setMsg("请输入关键字搜索");
			PrintUtils.printJson(request,response, result);
			return;
		}
//		PageBean<Search> pageBean=searchService.searchVideoMultiple(sortField, search, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		SearchPageBean<Search> pageBean=searchApi.searchMultiple("create_at", SearchTools.StringFilter(search), Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		result.setResult(pageBean);
		PrintUtils.printJson(request,response, result);
	}
	@Register("song.play.video.play")
	public void getVideoPlay(HttpServletRequest request, HttpServletResponse response) {
		ServiceResult result=new ServiceResult();
		String videoId=request.getParameter("id");
		Video video=videoApi.getVideoById(videoId);
		result.setResult(video);
		PrintUtils.printJson(request,response, result);
	}
	@Register("song.play.reg.fast.login")
	@RequestMapping("test")
	public void fastLogin(HttpServletRequest request, HttpServletResponse response) {
		ServiceResult result=new ServiceResult();
		String login=request.getHeader("login")==null?request.getParameter("login"):request.getHeader("login");
		String password=request.getHeader("password")==null?request.getParameter("password"):request.getHeader("password");
		List<Users> users=userApi.login(login,password);
		if(users.size()>0)
		{
			result=new ServiceResult(ResultCodeEnum.Success);
			result.setResult(users.get(0));
			PrintUtils.printJson(request, response, result);
		}else{
			Users userVo=new Users();
			userVo.setId(PKUtils.getUUID());
			userVo.setCreateAt(new Date());
			userVo.setStatus(1);
			userVo.setUserType(1);
			userVo.setMobile(login);
			userVo.setLogin(login);
			userVo.setPassword(password);
			userApi.save(userVo);
			result=new ServiceResult(ResultCodeEnum.Success);
			result.setResult(userVo);
			PrintUtils.printJson(request, response, result);
		}
	}
	
//	@Register("song.play.mine.get.space")
//	public void getMinespace(HttpServletRequest request, HttpServletResponse response) {
//		ServiceResult result=new ServiceResult();
//		
//		Mine mine=new Mine();
//		String userId=request.getParameter("uid");
//		Users users=usersService.selectList(new SelectWrapper().eq("id", userId)).get(0);
//		Video video=new Video();
//		video.setUserId(userId);
//		Page<Video> pageEntity=new Page<Video>(Integer.valueOf(1), Integer.valueOf(20), "create_at");
//	    pageEntity.setAsc(false);
//	    SelectWrapper wrapper=new SelectWrapper();
//	    wrapper.eq("user_id", userId);
//	    pageEntity=videoService.selectPage(wrapper, pageEntity);
//	    mine.setSpace(Long.valueOf(pageEntity.getTotal()));
//	    Page<Click> history=new Page<Click>(Integer.valueOf(1), Integer.valueOf(20), "create_at");
//		PageBeanMaps<List<Map<String, Object>>> beanMaps=clickService.findMyHistory(userId, history);
//	    mine.setHistory(Long.valueOf(beanMaps.getTotalRows()));
//	    Page<UserSpace> follow=new Page<UserSpace>(Integer.valueOf(1), Integer.valueOf(20), "create_at");
//		PageBeanMaps<List<Map<String, Object>>> followMaps=userSpaceService.findMySpacePage(userId, follow);
//	    mine.setFollow(Long.valueOf(followMaps.getTotalRows()));
//	    SelectWrapper accountWrapper=new SelectWrapper();
//	    accountWrapper.eq("user_id", userId);
//	    int account=userAccountService.selectCount(wrapper);
//	    mine.setAccount(Long.valueOf(account));
//	    SelectWrapper goodsWrapper=new SelectWrapper();
//	    goodsWrapper.eq("give_id", userId);
//	    int goods=goodsRecordService.selectCount(goodsWrapper);
//	    mine.setGoods(Long.valueOf(goods));
//	    mine.setNickName(users.getNickName());
//	    mine.setImageName(users.getImageName());
//		result.setResult(mine);
//		PrintUtils.printJson(request,response, result);
//	}
//	@Register("song.play.get.myspace")
//	public void getMyspace(HttpServletRequest request, HttpServletResponse response) {
//		ServiceResult result=new ServiceResult();
//		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
//		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
//		String userId=request.getParameter("uid");
//		Video video=new Video();
//		video.setUserId(userId);
//		Page<Video> pageEntity=new Page<Video>(Integer.valueOf(pageNo), Integer.valueOf(pageSize), "create_at");
//	    pageEntity.setAsc(false);
////	    PageBean<LiveCategoryVo> pageBean = new PageBean<LiveCategoryVo>();
//	    SelectWrapper wrapper=new SelectWrapper();
//	    wrapper.eq("user_id", userId);
//	    
//	    pageEntity=videoService.selectPage(wrapper, pageEntity);
//	    int pageSum=pageEntity.getTotal()%Integer.valueOf(pageSize)==0?pageEntity.getTotal()/Integer.valueOf(pageSize):pageEntity.getTotal()/Integer.valueOf(pageSize)+1;
//	    pageEntity.setSize(pageSum);
//		result.setResult(pageEntity);
//		PrintUtils.printJson(request,response, result);
//	}
//	@Register("song.play.mine.get.history")
//	public void getMinehistory(HttpServletRequest request, HttpServletResponse response) {
//		ServiceResult result=new ServiceResult();
//		java.text.SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String userId=request.getParameter("uid");
//		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
//		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
//		Page<Click> pageEntity=new Page<Click>(Integer.valueOf(pageNo), Integer.valueOf(pageSize), "create_at");
//		   
//	    PageBeanMaps<List<Map<String, Object>>> beanMaps=clickService.findMyHistory(userId, pageEntity);
//	    List<Map<String, Object>> lists=beanMaps.getResult();
//	    for(Map<String, Object> map:lists)
//	    {
//	    	Object object=map.get("create_at");
//	    	Date date=(Date)object;
//	    	map.put("create_at", format.format(date));
//	    }
//	    beanMaps.setResult(lists);
//		result.setResult(beanMaps);
//		PrintUtils.printJson(request,response, result);
//	}
//	@Register("song.play.mine.get.follow")
//	public void getMinefollow(HttpServletRequest request, HttpServletResponse response) {
//		ServiceResult result=new ServiceResult();
//		java.text.SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String userId=request.getParameter("uid");
//		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
//		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
//		Page<UserSpace> pageEntity=new Page<UserSpace>(Integer.valueOf(pageNo), Integer.valueOf(pageSize), "create_at");
//		   
//	    PageBeanMaps<List<Map<String, Object>>> beanMaps=userSpaceService.findMySpacePage(userId, pageEntity);
//	    List<Map<String, Object>> lists=beanMaps.getResult();
//	    for(Map<String, Object> map:lists)
//	    {
//	    	Object object=map.get("create_at");
//	    	Date date=(Date)object;
//	    	map.put("create_at", format.format(date));
//	    }
//	    beanMaps.setResult(lists);
//	    result.setResult(beanMaps);
//		PrintUtils.printJson(request,response, result);
//	}
//	@Register("song.play.mine.get.account")
//	public void getMineaccount(HttpServletRequest request, HttpServletResponse response) {
//		ServiceResult result=new ServiceResult();
//		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
//		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
//		String search=request.getParameter("cateId");
//		if(StringUtils.isEmpty(search))
//		{
//			result.setCode(-1);
//			result.setMsg("请选择分类搜索");
//			PrintUtils.printJson(request,response, result);
//			return;
//		}
//		SortField sortField=new SortField("create_at", Type.LONG, true);
//		PageBean<Search> pageBean=searchService.searchVideoCate(sortField, search, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
//		result.setResult(pageBean);
//		PrintUtils.printJson(request,response, result);
//	}
//	@Register("song.play.mine.get.goods")
//	public void getMinegoods(HttpServletRequest request, HttpServletResponse response) {
//		ServiceResult result=new ServiceResult();
//		String pageNo=request.getParameter("pageNo")==null?"1":request.getParameter("pageNo");
//		String pageSize=request.getParameter("pageSize")==null?"20":request.getParameter("pageSize");
//		String search=request.getParameter("cateId");
//		if(StringUtils.isEmpty(search))
//		{
//			result.setCode(-1);
//			result.setMsg("请选择分类搜索");
//			PrintUtils.printJson(request,response, result);
//			return;
//		}
//		SortField sortField=new SortField("create_at", Type.LONG, true);
//		PageBean<Search> pageBean=searchService.searchVideoCate(sortField, search, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
//		result.setResult(pageBean);
//		PrintUtils.printJson(request,response, result);
//	}
//	public static void main(String[] args) {
//		String json="{\"code\":0,\"msg\":\"系统正常\",\"result\":{\"address\":\"\",\"birthday\":\"\",\"cardImage\":\"\",\"city\":\"\",\"cradImageName\":\"\",\"createAt\":\"2019-04-08 11:59:28\",\"email\":\"\",\"id\":\"523f0ef0e5d54cc29f0efb8ab87880ba\",\"idCard\":\"\",\"imageName\":\"\",\"levelId\":\"\",\"login\":\"1111\",\"mobile\":\"1111\",\"nickName\":\"\",\"password\":\"b59c67bf196a4758191e42f76670ceba\",\"province\":\"\",\"status\":1,\"topImage\":\"\",\"userType\":1}}";
//		JSONObject result=JSONObject.parseObject(json);
//		Users user=(Users)JsonUtil.readValue(result.getString("result"), Users.class);
//		System.out.println(user.getLogin());
//	}
}
