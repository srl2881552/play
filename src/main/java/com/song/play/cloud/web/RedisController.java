package com.song.play.cloud.web;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("redis")
public class RedisController {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@RequestMapping("zadd")
	@ResponseBody
	public String zadd(HttpServletRequest request,HttpServletResponse response)
	{
		String value=request.getParameter("value");
		String score=request.getParameter("score");
		redisTemplate.opsForZSet().add("plat", value, Double.valueOf(score));
		return "SUCCESS";
	}
	
	@RequestMapping("zget")
	@ResponseBody
	public String zget(HttpServletRequest request,HttpServletResponse response)
	{
		String value=request.getParameter("value");
		String score=request.getParameter("score");
		Set<TypedTuple<String>> tuples=redisTemplate.opsForZSet().rangeWithScores("plat", 0, -1);
		Iterator<TypedTuple<String>>iterator= tuples.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next().getValue());
			System.out.println(iterator.next().getScore());
		}
		return "SUCCESS";
	}
//	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
//		VideoRedis videoRedis=(VideoRedis)context.getBean("videoRedisService");
//		RedisTemplate<String, String> redisTemplate=(RedisTemplate)context.getBean("redisTemplate");
//		              redisTemplate.opsForZSet().incrementScore("plat", "song5", 2000);
////		for(int i=3;i<20;i++)
////		{
////			redisTemplate.opsForZSet().add("plat", "song"+i, Double.valueOf(100*i));
////		}
//		//由低到高
////		Set<TypedTuple<String>> tuples=redisTemplate.opsForZSet().rangeWithScores("plat", 0,100);
//		//由高到低
//		Set<TypedTuple<String>> tuples=redisTemplate.opsForZSet().rangeWithScores("plat", 0,-1);
//		Iterator<TypedTuple<String>>iterator= tuples.iterator();
//		while(iterator.hasNext())
//		{
//			TypedTuple<String> tuple=iterator.next();
//			System.out.println(tuple.getValue());
//			System.out.println(tuple.getScore());
//		}
////		RedisVideo redisVideo=new RedisVideo();
////		redisVideo.setId(1L);
////		redisVideo.setName("宋人龙");   
////		redisVideo.setCreateAt(new Date());
////		redisVideo.setAge(32);
////		videoRedis.insert(redisVideo);
////		RedisVideo video=videoRedis.selectById(1L);
////		System.out.println(video.getName());
//	}
}
