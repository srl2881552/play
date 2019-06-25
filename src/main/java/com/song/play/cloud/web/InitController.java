package com.song.play.cloud.web;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("init")
public class InitController {

//	private static Logger logger=Logger.getLogger(InitController.class);
//	@Autowired
//	private VideoService videoService;
//	@Autowired
//	private VideoCategoryService videoCategoryService;
//	@RequestMapping("neo4j")
//	@ResponseBody
//	public String neo4j(HttpServletRequest request,HttpServletResponse response)
//	{
//		Connection conn=Neo4JManager.getConnection();
//		Statement stmt=Neo4JManager.create(conn);
//		Neo4JManager.execute(stmt, "MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r");
//		Neo4JManager.close(stmt);
//		List<VideoCategory> categories=videoCategoryService.getVideoAll();
//		stmt=Neo4JManager.create(conn);
//		Neo4JManager.execute(stmt, "CREATE (所有分类:分类{title:\"所有分类\",id:\"1\"})");
//		Neo4JManager.close(stmt);
//		for(VideoCategory category:categories)
//		{
//			stmt=Neo4JManager.create(conn);
//			Neo4JManager.execute(stmt, "CREATE ("+category.getCateName()+":分类{title:\""+category.getCateName()+"\",cate_id:\""+category.getId()+"\"})");
//			Neo4JManager.execute(stmt, "match (n:分类),(n1:分类) where n.title = \"所有分类\" and n1.title = \""+category.getCateName()+"\" create (n1)-[r:属于]->(n);");
//		}
//		Neo4JManager.close(stmt);
//		logger.info("初始化分类结束");
//		SelectWrapper wrapper=new SelectWrapper();
//		wrapper.eq("type", 2);
//		wrapper.eq("status", 1);
//		List<Video> videos=videoService.selectList(wrapper);
//		for(Video video:videos)
//		{
//			stmt=Neo4JManager.create(conn);
//			Neo4JManager.execute(stmt, "CREATE ("+video.getTitle()+":视频{title:\""+video.getTitle()+"\",video_id:\""+video.getId()+"\",about:\""+video.getAbout()+"\",tag:\""+video.getTag()+"\"})");
//			Neo4JManager.execute(stmt, "match (n:分类),(n1:视频) where n.title = \""+video.getCateName()+"\" and n1.title = \""+video.getTitle()+"\" create (n1)-[r:属于]->(n)");
//		}
//		Neo4JManager.close(stmt);
//		logger.info("初始化视频结束");
//		Neo4JManager.close(conn);
//		return "SUCCESS";
//	}
}
