package com.song.play.cloud.tools;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import com.song.play.cloud.entity.Search;
import com.song.play.cloud.entity.Video;
import com.song.play.cloud.service.SearchApi;
import com.song.play.cloud.service.VideoApi;

public class FmpegThread implements Runnable{

	private VideoApi videoApi;
	private String inputFile;
	private String outputFile;
	private Video video;
	private SearchApi searchApi;
	private File sourceFile;
	private Search search;
	private RedisTemplate<String, String> redisTemplate;
	public FmpegThread(VideoApi videoApi,String inputFile,String outputFile,Video video,SearchApi searchApi,File sourceFile,Search search,RedisTemplate<String, String> redisTemplate )
	{
		this.videoApi=videoApi;
		this.inputFile=inputFile;
		this.outputFile=outputFile;
		this.video=video;
		this.searchApi=searchApi;
		this.sourceFile=sourceFile;
		this.redisTemplate=redisTemplate;
		this.search=search;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String path=FileTools.getProperties().getProperty("ffmpeg");
		ConvertSingleVideo convertVideo=new ConvertSingleVideo(path);
		java.text.SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean flag=convertVideo.convert(inputFile, outputFile);
		if(flag)
		{
			Video vo=videoApi.getVideoById(video.getId());
			vo.setStatus(1);
			videoApi.update(vo);
			searchApi.saveIndex(search);
			sourceFile.delete();
			String json=redisTemplate.opsForValue().get("com.song.play.desktop");
			if(json==null||"".equals(json))
			{
				Map<String, List<LinkedHashMap<String, Object>>> map=new HashMap<String,List<LinkedHashMap<String,Object>>>();
				LinkedHashMap<String, Object> desktop=new LinkedHashMap<String, Object>(); 
				List<LinkedHashMap<String, Object>> vos=map.get(search.getCateName());
	            if(vos==null)
	            {
	                    vos=new ArrayList<LinkedHashMap<String,Object>>();
	            }
	            desktop.put("id", search.getId());
	            desktop.put("userId", search.getUserId());
	            desktop.put("nickName", search.getNickName());
	            desktop.put("title", search.getTitle());
	            desktop.put("about", search.getAbout());
	            desktop.put("cateId", search.getCateId());
	            desktop.put("cateName", search.getCateName());
	            desktop.put("rtmp", search.getVideoPath());
	            desktop.put("stream", search.getVideoStream());
	            desktop.put("status", search.getStatus());
	            desktop.put("message", search.getMessage());
	            desktop.put("createAt", format.format(search.getCreateAt()));
	            desktop.put("topImage", search.getTopImage());
	            desktop.put("imageName", search.getImageName());
	            desktop.put("type", search.getType());
	            desktop.put("chick", 0L);
	            desktop.put("videoLength", search.getVideoLength());
	            if(vos.size()>=20)
	            {
	            	vos.remove(0);
	            	vos.add(desktop);
	            }else{
	            	vos.add(desktop);
	            }
	            System.out.println(map);
	            map.put(search.getCateName(), vos);
	            redisTemplate.opsForValue().set("com.song.play.desktop", JsonUtil.writeValueAsString(map));
			}else{
				Map<String, List<LinkedHashMap<String, Object>>> map=JsonUtil.readValue(json, Map.class);
	            LinkedHashMap<String, Object> desktop=new LinkedHashMap<String, Object>(); 
	            List<LinkedHashMap<String, Object>> vos=map.get(search.getCateName());
	            if(vos==null)
	            {
	                    vos=new ArrayList<LinkedHashMap<String,Object>>();
	            }
	            desktop.put("id", search.getId());
	            desktop.put("userId", search.getUserId());
	            desktop.put("nickName", search.getNickName());
	            desktop.put("title", search.getTitle());
	            desktop.put("about", search.getAbout());
	            desktop.put("cateId", search.getCateId());
	            desktop.put("cateName", search.getCateName());
	            desktop.put("rtmp", search.getVideoPath());
	            desktop.put("stream", search.getVideoStream());
	            desktop.put("status", search.getStatus());
	            desktop.put("message", search.getMessage());
	            desktop.put("createAt", format.format(search.getCreateAt()));
	            desktop.put("topImage", search.getTopImage());
	            desktop.put("imageName", search.getImageName());
	            desktop.put("type", search.getType());
	            desktop.put("chick", 0L);
	            desktop.put("videoLength", search.getVideoLength());
	            if(vos.size()>=20)
	            {
	            	vos.remove(0);
	            	vos.add(desktop);
	            }else{
	            	vos.add(desktop);
	            }
	            System.out.println(map);
	            map.put(search.getCateName(), vos);
	            redisTemplate.opsForValue().set("com.song.play.desktop", JsonUtil.writeValueAsString(map));
			}
            
		}
	}

}
