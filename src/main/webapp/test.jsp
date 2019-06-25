<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>

	<head>
		<meta charset="UTF-8">
		<title>ckplayer</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/ckplayer/ckplayer.js"></script>
		
	</head>

	<body>
		<div id="video"  style="width: 100%;height: 100%"></div>
         
					<script type="text/javascript">
					var videoObject = {
							container: '#video',//“#”代表容器的ID，“.”或“”代表容器的class
							variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
							autoplay:true,//自动播放
							live:true,//直播视频形式
							video:'rtmp://39.108.11.81/live/test'//视频地址
						};
					var player=new ckplayer(videoObject);
					</script>
	</body>

</html>