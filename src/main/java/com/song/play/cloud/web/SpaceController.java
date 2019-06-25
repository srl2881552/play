package com.song.play.cloud.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.song.play.cloud.base.AbstractBaseController;


@Controller
@RequestMapping("indexSpace")
public class SpaceController extends AbstractBaseController{

//	@Autowired
//	private UserSpaceService userSpaceService;
//	@RequestMapping("save.html")
//	@ResponseBody
//	public void save(HttpServletRequest request,HttpServletResponse response,String videoId)
//	{
//		Users users=this.getCurrentUser(request);
//		if(users==null)
//		{
//			ServiceResult result=new ServiceResult(ResultCodeEnum.USER_ERROR);
//			this.write(response, result);
//		}else{
//			UserSpace space=new UserSpace();
//			space.setSpaceType(1);//1:收藏2:关注
//			space.setCreateAt(new Date());
//			space.setId(PKUtils.getUUID());
//			space.setUserId(users.getId());
//			space.setVideoId(videoId);
//			List<UserSpace> spaces=userSpaceService.findMySpace(space);
//			if(spaces.size()==0)
//			{
//				userSpaceService.save(space);
//			}
//			
//			ServiceResult result=new ServiceResult(ResultCodeEnum.Success);
//			this.write(response, result);
//		}
//		
//		//return "admin/userSpace/view";
//	}
}
