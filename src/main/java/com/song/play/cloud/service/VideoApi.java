package com.song.play.cloud.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.song.play.cloud.entity.Desktop;
import com.song.play.cloud.entity.PageBean;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.entity.VideoCategory;
import com.song.play.cloud.hystrix.VideoHystrix;
import com.song.play.cloud.security.FeignUser;
import com.song.play.cloud.security.FeignVideo;

@FeignClient(name = "play-video-api",configuration=FeignVideo.class,fallback = VideoHystrix.class)
public interface VideoApi {

	@RequestMapping(value = "/video/save",method = RequestMethod.GET)
    public Integer save(@RequestBody Video video);
	@RequestMapping(value = "/video/update",method = RequestMethod.GET)
	public Integer update(@RequestBody Video video);
	@RequestMapping(value = "/video/delete",method = RequestMethod.GET)
	public Integer delete(@RequestParam(value = "id", required = true) String id);
	@RequestMapping(value = "/video/getVideoById",method = RequestMethod.GET)
	public Video getVideoById(@RequestParam(value = "id", required = true) String id);
	@RequestMapping(value = "/videoCategory/getVideoAll",method = RequestMethod.GET)
	public List<VideoCategory> getVideoAll();
	@RequestMapping(value = "/videoCategory/getLiveAll",method = RequestMethod.GET)
	public List<VideoCategory> getLiveAll();
	@RequestMapping(value = "/desktop/selectAll",method = RequestMethod.GET)
	public List<Desktop> selectAll();
	@RequestMapping(value ="/video/getByNewsVideo",method = RequestMethod.GET)
	public List<Video> getByNewsVideo();
	@RequestMapping(value ="/desktop/refresh",method = RequestMethod.GET )
	public void refresh();
	@RequestMapping(value ="/video/selectListPage",method = RequestMethod.GET)
	public PageBean<Video> selectListPage(@RequestParam(value = "pageNo", required = true) Integer pageNo,@RequestParam(value = "pageSize", required = true) Integer pageSize);
	@RequestMapping(value = "/videoCategory/getCateById",method = RequestMethod.GET)
	public VideoCategory getCateById(@RequestParam(value = "id", required = true) String id);
}
