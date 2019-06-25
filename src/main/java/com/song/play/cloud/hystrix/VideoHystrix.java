package com.song.play.cloud.hystrix;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.song.play.cloud.entity.Desktop;
import com.song.play.cloud.entity.PageBean;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.entity.VideoCategory;
import com.song.play.cloud.service.VideoApi;

@Component
public class VideoHystrix implements VideoApi{

	@Override
	@RequestMapping(value = "/video/save", method = RequestMethod.POST)
	public Integer save(@RequestBody Video video) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@RequestMapping(value = "/video/update", method = RequestMethod.POST)
	public Integer update(@RequestBody Video video) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@RequestMapping(value = "/video/delete", method = RequestMethod.POST)
	public Integer delete(@RequestParam(value = "id", required = true) String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@RequestMapping(value = "/video/getVideoById", method = RequestMethod.POST)
	public Video getVideoById(@RequestParam(value = "id", required = true) String id) {
		// TODO Auto-generated method stub
		return new Video();
	}

	@Override
	@RequestMapping(value = "/videoCategory/getVideoAll", method = RequestMethod.POST)
	public List<VideoCategory> getVideoAll() {
		// TODO Auto-generated method stub
		return new ArrayList<VideoCategory>();
	}

	@Override
	@RequestMapping(value = "/videoCategory/getLiveAll", method = RequestMethod.POST)
	public List<VideoCategory> getLiveAll() {
		// TODO Auto-generated method stub
		return new ArrayList<VideoCategory>();
	}

	@Override
	@RequestMapping(value = "/desktop/selectAll", method = RequestMethod.POST)
	public List<Desktop> selectAll() {
		// TODO Auto-generated method stub
		return new ArrayList<Desktop>();
	}

	@Override
	@RequestMapping(value = "/video/getByNewsVideo", method = RequestMethod.POST)
	public List<Video> getByNewsVideo() {
		// TODO Auto-generated method stub
		System.out.println("VideoHystrix=============getByNewsVideo");
		return new ArrayList<Video>();
	}

	@Override
	@RequestMapping(value = "/desktop/refresh", method = RequestMethod.POST)
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value = "/video/selectListPage", method = RequestMethod.POST)
	public PageBean<Video> selectListPage(
			@RequestParam(value = "pageNo", required = true) Integer pageNo,
			@RequestParam(value = "pageSize", required = true) Integer pageSize) {
		// TODO Auto-generated method stub
		return new PageBean<Video>();
	}

	@Override
	@RequestMapping("/videoCategory/getCateById")
	public VideoCategory getCateById(
			@RequestParam(value = "id", required = true) String id) {
		// TODO Auto-generated method stub
		return new VideoCategory();
	}

	

}
