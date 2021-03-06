<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include  file="/WEB-INF/views/common/head.jsp"%>
<!--breadcrumbs-->
            <!--end breadcrumbs-->
            <!-- full width Video -->
<script type="text/javascript">
$(document).ready(function(){ 
	
	var vid="${video.id}";
	$.ajax({  
			type:'post',      
			url:'<%=path %>/click/save.html',  
			data:'video_id='+vid,
			cache:false,  
			dataType:'json',  
			success:function(data){
				
			}  
			});	
	
	
	
});

function sendMag(videoId)
{
	var commentText=$("#commentText").val();
	if(commentText==null||commentText=="")
	{
		alert("请输入评论内容");
		return;
	}
	$.ajax({  
		type:'post',      
		url:'<%=path %>/comment/save.html',  
		data:'videoId='+videoId+'&content='+commentText,
		cache:false,  
		dataType:'text',  
		success:function(data){
			if(data=='ok')
			{
				$("#commentText").val("");
				$.ajax({  
					type:'post',      
					url:'<%=path %>/comment/list.html',  
					data:'videoId='+videoId+'&page=1',
					cache:false,  
					dataType:'html',  
					success:function(data){
						$("#commentResult").empty();
			            $("#commentResult").append(data);
					}  
					});
			}
		}  
		});
}
function Refresh(page,videoId)
{
	$.ajax({  
		type:'post',      
		url:'<%=path %>/comment/list.html',  
		data:'videoId='+videoId+'&page='+page,
		cache:false,  
		dataType:'html',  
		success:function(data){
			$("#commentResult").empty();
            $("#commentResult").append(data);
		}  
		});
}
</script>
            <section class="verticalSlider">
                <div class="row">
                    <div class="large-12 columns">
                        <div class="thumb-slider">
                        <div class="image 1" style="width: 100%;height: 100%;">
                           <div id="video" style="width: 100%;height: 100%; z-index: 0"></div>
         
					<script type="text/javascript">
					var videoObject = {
							container: '#video',//“#”代表容器的ID，“.”或“”代表容器的class
							variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
							loaded:'loadedHandler',//监听播放器加载成功
							autoplay:false,
							poster:'${video.imageName}',
							seek:${t},
							live:false,
							video:'${video.rtmp}'//视频地址
						};
						var player=new ckplayer(videoObject);
						function loadedHandler(){//播放器加载后会调用该函数
							player.addListener('time', timeHandler); //监听播放时间,addListener是监听函数，需要传递二个参数，'time'是监听属性，这里是监听时间，timeHandler是监听接受的函数
						}
						function timeHandler(t){
							$.ajax({  
								type:'post',      
								url:'<%=path %>/video/time.html',  
								data:'videoId=${video.id}&t='+t,
								cache:false,  
								dataType:'json',  
								success:function(data){
									
								}  
								});	
						}
					</script>
                        </div>
                        </div>
                    </div>
                </div>
            </section>
            <div class="row">
                <!-- left side content area -->
                <div class="large-8 columns">
                    <!-- single post stats -->
                    <section class="SinglePostStats">
                        <!-- newest video -->
                        <div class="row secBg">
                            <div class="large-12 columns">
                                <div class="media-object stack-for-small">
                                    <div class="media-object-section">
                                        <div class="author-img-sec">
                                            <div class="thumbnail author-single-post">
                                                <a href="#"><img src= "${user.imageName }" onerror="http://placehold.it/80x80" alt="post"></a>
                                            </div>
                                            <p class="text-center"><a href="#">${user.nickName }</a></p>
                                        </div>
                                    </div>
                                    <div class="media-object-section object-second">
                                        <div class="author-des clearfix">
                                            <div class="post-title">
                                                <h4>${video.title }</h4>
                                                <p>
                                                    <span><i class="fa fa-clock-o"></i><fmt:formatDate value="${video.createAt }" pattern="yyyy-MM-dd  HH:mm:ss" /></span>
                                                    <span><i class="fa fa-eye"></i>${num}</span>
                                                    <!-- <span><i class="fa fa-thumbs-o-up"></i>1,862</span>
                                                    <span><i class="fa fa-thumbs-o-down"></i>180</span>
                                                    <span><i class="fa fa-commenting"></i>8</span> -->
                                                </p>
                                            </div>
                                            <div class="subscribe">
                                                <form method="post">
                                                    <button type="submit" name="subscribe">收藏</button>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="social-share">
                                            <div class="post-like-btn clearfix">
                                                <form method="post">
                                                    <button type="submit" name="fav"><i class="fa fa-heart"></i>Add to</button>
                                                </form>
                                                <a href="#" class="secondary-button"><i class="fa fa-thumbs-o-up"></i></a>
                                                <a href="#" class="secondary-button"><i class="fa fa-thumbs-o-down"></i></a>

                                                <div class="float-right easy-share">
                                                    <!-- Total -->
                                                    <button data-easyshare-button="total">
                                                        <span>Total</span>
                                                    </button>
                                                    <span data-easyshare-total-count>0</span>

                                                    <!-- Facebook -->
                                                    <button data-easyshare-button="facebook">
                                                        <span class="fa fa-facebook"></span>
                                                        <span>Share</span>
                                                    </button>
                                                    <span data-easyshare-button-count="facebook">0</span>

                                                    <!-- Twitter -->
                                                    <button data-easyshare-button="twitter" data-easyshare-tweet-text="">
                                                        <span class="fa fa-twitter"></span>
                                                        <span>Tweet</span>
                                                    </button>
                                                    <span data-easyshare-button-count="twitter">0</span>

                                                    <!-- Google+ -->
                                                    <button data-easyshare-button="google">
                                                        <span class="fa fa-google-plus"></span>
                                                        <span>+1</span>
                                                    </button>
                                                    <span data-easyshare-button-count="google">0</span>

                                                    <div data-easyshare-loader>Loading...</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section><!-- End single post stats -->

                    <!-- single post description -->
                    <section class="singlePostDescription">
                        <div class="row secBg">
                            <div class="large-12 columns">
                                <div class="heading">
                                    <h5>Description</h5>
                                </div>
                                <div class="description showmore_one">
                                    <p>${video.about }</p>

                                    
                                    <div class="categories">
                                        <button><i class="fa fa-folder"></i>Categories</button>
                                        <a href="#" class="inner-btn">entertainment</a>
                                        <a href="#" class="inner-btn">comedy</a>
                                    </div>
                                    <div class="tags">
                                        <button><i class="fa fa-tags"></i>Tags</button>
                                        <a href="#" class="inner-btn">3D Videos</a>
                                        <a href="#" class="inner-btn">Videos</a>
                                        <a href="#" class="inner-btn">HD</a>
                                        <a href="#" class="inner-btn">Movies</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section><!-- End single post description -->

                    <!-- related Posts -->
                    <section class="content content-with-sidebar related">
                        <div class="row secBg">
                            <div class="large-12 columns">
                                <div class="main-heading borderBottom">
                                    <div class="row padding-14">
                                        <div class="medium-12 small-12 columns">
                                            <div class="head-title">
                                                <i class="fa fa-film"></i>
                                                <h4>相关视频</h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row list-group">
                                <c:forEach items="${conforms }" var="result">
                                    <div class="item large-4 columns end group-item-grid-default">
                                        <div class="post thumb-border">
                                            <div class="post-thumb">
                                            	<img src="${result.imageName }" onerror="http://placehold.it/370x220" alt="landing">
                                                
                                                <a href="<%=request.getContextPath() %>/play/video/${result.id }.html" class="hover-posts">
                                                    <span><i class="fa fa-play"></i>观看视频</span>
                                                </a>
                                                <div class="video-stats clearfix">
                                                    <div class="thumb-stats pull-left">
                                                        <h6>HD</h6>
                                                    </div>
                                                    <div class="thumb-stats pull-left">
                                                        <i class="fa fa-heart"></i>
                                                        <span>${result.chick }</span>
                                                    </div>
                                                    <div class="thumb-stats pull-right">
                                                        <span>${result.videoLength }</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="post-des">
                                                <h6><a href="<%=request.getContextPath() %>/play/video/${result.id }.html">${result.title }</a></h6>
                                                <div class="post-stats clearfix">
                                                    <p class="pull-left">
                                                        <i class="fa fa-user"></i>
                                                        <span><a href="javascript:void(0)">${result.nickName }</a></span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-clock-o"></i>
                                                        <span><fmt:formatDate value="${result.createAt }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                                                    </p>
                                                    <p class="pull-left">
                                                        <i class="fa fa-eye"></i>
                                                        <span>${result.chick }</span>
                                                    </p>
                                                </div>
                                                <div class="post-summary">
                                                    <p>${result.about }</p>
                                                </div>
                                                <div class="post-button">
                                                    <a href="#" class="secondary-button"><i class="fa fa-play-circle"></i>watch video</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
</c:forEach>
                                    
                                </div>
                            </div>
                        </div>
                    </section><!--end related posts-->
                    <!-- Comments -->
                                        <section class="content comments">
                        <div class="row secBg">
                            <div class="large-12 columns">
                                <div class="main-heading borderBottom">
                                    <div class="row padding-14">
                                        <div class="medium-12 small-12 columns">
                                            <div class="head-title">
                                                <i class="fa fa-comments"></i>
                                                <h4>评论 <span></span></h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="comment-box thumb-border">
                                    <div class="media-object stack-for-small">
                                        <div class="media-object-section comment-img text-center">
                                            
                                            <h6><a href="#">${user.nickName }</a></h6>
                                        </div>
                                        <div class="media-object-section comment-textarea">
                                            
                                                <textarea name="commentText" placeholder="在这里添加评论" id="commentText"></textarea>
                                                <input type="submit" name="submit" value="评论" onclick="sendMag('${video.id}');">
                                            
                                        </div>
                                    </div>
                                </div>
                                <div id="commentResult">
								<c:forEach items="${page2.result }" var="result">
                                

                                <!-- main comment -->
                                <div class="main-comment showmore_one">
                                   

                                    <div class="media-object stack-for-small">
                                        <div class="media-object-section comment-img text-center">
                                            
                                        </div>
                                        <div class="media-object-section comment-desc">
                                            <div class="comment-title">
                                                <span class="name"><a href="#">${result.nickName }</a> 说:</span>
                                                <span class="time float-right"><i class="fa fa-clock-o"></i><fmt:formatDate value="${result.createAt }" pattern="yyyy-MM-dd  HH:mm:ss" /></span>
                                            </div>
                                            <div class="comment-text">
                                                <p>${result.content }</p>
                                            </div>
                                            

                                        </div>
                                    </div>

                                    
                                </div><!-- End main comment -->
                                
</c:forEach>
									<div class="pagination">
                                        <c:forEach items="${pageCodes }" var="code">
                                        <a href="javacript:void(0);" class="page-numbers" onclick="Refresh('${code }','${video.id}');">${code }</a>
                                        </c:forEach>
                                    </div>
                                    
</div>

                            </div>
                        </div>
                    </section><!-- End Comments -->
                </div><!-- end left side content area -->
                <!-- sidebar -->
                <div class="large-4 columns">
                    <aside class="secBg sidebar">
                        <div class="row">
                            <!-- search Widget -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox">
                                    <div class="widgetTitle">
                                        <h5>Search Videos</h5>
                                    </div>
                                    <form id="searchform" method="get" role="search">
                                        <div class="input-group">
                                            <input class="input-group-field" type="text" placeholder="Enter your keyword">
                                            <div class="input-group-button">
                                                <input type="submit" class="button" value="Submit">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div><!-- End search Widget -->

                            <!-- most view Widget -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox">
                                    <div class="widgetTitle">
                                        <h5>Most View Videos</h5>
                                    </div>
                                    <div class="widgetContent">
                                        <div class="video-box thumb-border">
                                            <div class="video-img-thumb">
                                                <img src="http://placehold.it/300x190" alt="most viewed videos">
                                                <a href="#" class="hover-posts">
                                                    <span><i class="fa fa-play"></i>Watch Video</span>
                                                </a>
                                            </div>
                                            <div class="video-box-content">
                                                <h6><a href="#">There are many variations of passage. </a></h6>
                                                <p>
                                                    <span><i class="fa fa-user"></i><a href="#">admin</a></span>
                                                    <span><i class="fa fa-clock-o"></i>5 January 16</span>
                                                    <span><i class="fa fa-eye"></i>1,862K</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="video-box thumb-border">
                                            <div class="video-img-thumb">
                                                <img src="http://placehold.it/300x190" alt="most viewed videos">
                                                <a href="#" class="hover-posts">
                                                    <span><i class="fa fa-play"></i>Watch Video</span>
                                                </a>
                                            </div>
                                            <div class="video-box-content">
                                                <h6><a href="#">There are many variations of passage. </a></h6>
                                                <p>
                                                    <span><i class="fa fa-user"></i><a href="#">admin</a></span>
                                                    <span><i class="fa fa-clock-o"></i>5 January 16</span>
                                                    <span><i class="fa fa-eye"></i>1,862K</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="video-box thumb-border">
                                            <div class="video-img-thumb">
                                                <img src="http://placehold.it/300x190" alt="most viewed videos">
                                                <a href="#" class="hover-posts">
                                                    <span><i class="fa fa-play"></i>Watch Video</span>
                                                </a>
                                            </div>
                                            <div class="video-box-content">
                                                <h6><a href="#">There are many variations of passage. </a></h6>
                                                <p>
                                                    <span><i class="fa fa-user"></i><a href="#">admin</a></span>
                                                    <span><i class="fa fa-clock-o"></i>5 January 16</span>
                                                    <span><i class="fa fa-eye"></i>1,862K</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="video-box thumb-border">
                                            <div class="video-img-thumb">
                                                <img src="http://placehold.it/300x190" alt="most viewed videos">
                                                <a href="#" class="hover-posts">
                                                    <span><i class="fa fa-play"></i>Watch Video</span>
                                                </a>
                                            </div>
                                            <div class="video-box-content">
                                                <h6><a href="#">There are many variations of passage. </a></h6>
                                                <p>
                                                    <span><i class="fa fa-user"></i><a href="#">admin</a></span>
                                                    <span><i class="fa fa-clock-o"></i>5 January 16</span>
                                                    <span><i class="fa fa-eye"></i>1,862K</span>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- end most view Widget -->

                            <!-- categories -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox clearfix">
                                    <div class="widgetTitle">
                                        <h5>Categories</h5>
                                    </div>
                                    <div class="widgetContent clearfix">
                                        <ul>
                                            <li class="cat-item"><a href="#">Entertainment &nbsp; (6)</a></li>
                                            <li class="cat-item"><a href="#">Historical &amp; Archival &nbsp;(8)</a></li>
                                            <li class="cat-item"><a href="#">Technology&nbsp;(4)</a></li>
                                            <li class="cat-item"><a href="#">People&nbsp;(3)</a></li>
                                            <li class="cat-item"><a href="#">Fashion &amp; Beauty&nbsp;(2)</a></li>
                                            <li class="cat-item"><a href="#">Nature&nbsp;(1)</a></li>
                                            <li class="cat-item"><a href="#">Automotive&nbsp;(5)</a></li>
                                            <li class="cat-item"><a href="">Foods &amp; Drinks&nbsp;(5)</a></li>
                                            <li class="cat-item"><a href="#">Foods &amp; Drinks&nbsp;(10)</a></li>
                                            <li class="cat-item"><a href="#">Animals&nbsp;(12)</a></li>
                                            <li class="cat-item"><a href="#">Sports &amp; Recreation&nbsp;(14)</a></li>
                                            <li class="cat-item"><a href="">Places &amp; Landmarks&nbsp;(16)</a></li>
                                            <li class="cat-item"><a href="">Places &amp; Landmarks&nbsp;(1)</a></li>
                                            <li class="cat-item"><a href="#">Travel&nbsp;(2)</a></li>
                                            <li class="cat-item"><a href="#">Transportation&nbsp;(3)</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <!-- social Fans Widget -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox">
                                    <div class="widgetTitle">
                                        <h5>social fans</h5>
                                    </div>
                                    <div class="widgetContent">
                                        <div class="social-links">
                                            <a class="socialButton" href="#">
                                                <i class="fa fa-facebook"></i>
                                                <span>698K</span>
                                                <span>fans</span>
                                            </a>
                                            <a class="socialButton" href="#">
                                                <i class="fa fa-twitter"></i>
                                                <span>598</span>
                                                <span>followers</span>
                                            </a>
                                            <a class="socialButton" href="#">
                                                <i class="fa fa-google-plus"></i>
                                                <span>98k</span>
                                                <span>followers</span>
                                            </a>
                                            <a class="socialButton" href="#">
                                                <i class="fa fa-youtube"></i>
                                                <span>168k</span>
                                                <span>followers</span>
                                            </a>
                                            <a class="socialButton" href="#">
                                                <i class="fa fa-vimeo"></i>
                                                <span>498</span>
                                                <span>followers</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- End social Fans Widget -->

                            <!-- ad banner widget -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox">
                                    <div class="widgetTitle">
                                        <h5>Recent post videos</h5>
                                    </div>
                                    <div class="widgetContent">
                                        <div class="advBanner text-center">
                                            <a href="#"><img src="<%=request.getContextPath() %>/images/sideradv.png" alt="sidebar adv"></a>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- end ad banner widget -->

                            <!-- Recent post videos -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox">
                                    <div class="widgetTitle">
                                        <h5>Recent post videos</h5>
                                    </div>
                                    <div class="widgetContent">
                                        <div class="media-object stack-for-small">
                                            <div class="media-object-section">
                                                <div class="recent-img">
                                                    <img src= "http://placehold.it/120x80" alt="recent">
                                                    <a href="#" class="hover-posts">
                                                        <span><i class="fa fa-play"></i></span>
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="media-object-section">
                                                <div class="media-content">
                                                    <h6><a href="#">The lorem Ipsumbeen the industry's standard.</a></h6>
                                                    <p><i class="fa fa-user"></i><span>admin</span><i class="fa fa-clock-o"></i><span>5 january 16</span></p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="media-object stack-for-small">
                                            <div class="media-object-section">
                                                <div class="recent-img">
                                                    <img src= "http://placehold.it/120x80" alt="recent">
                                                    <a href="#" class="hover-posts">
                                                        <span><i class="fa fa-play"></i></span>
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="media-object-section">
                                                <div class="media-content">
                                                    <h6><a href="#">The lorem Ipsumbeen the industry's standard.</a></h6>
                                                    <p><i class="fa fa-user"></i><span>admin</span><i class="fa fa-clock-o"></i><span>5 january 16</span></p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="media-object stack-for-small">
                                            <div class="media-object-section">
                                                <div class="recent-img">
                                                    <img src= "http://placehold.it/120x80" alt="recent">
                                                    <a href="#" class="hover-posts">
                                                        <span><i class="fa fa-play"></i></span>
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="media-object-section">
                                                <div class="media-content">
                                                    <h6><a href="#">The lorem Ipsumbeen the industry's standard.</a></h6>
                                                    <p><i class="fa fa-user"></i><span>admin</span><i class="fa fa-clock-o"></i><span>5 january 16</span></p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="media-object stack-for-small">
                                            <div class="media-object-section">
                                                <div class="recent-img">
                                                    <img src= "http://placehold.it/120x80" alt="recent">
                                                    <a href="#" class="hover-posts">
                                                        <span><i class="fa fa-play"></i></span>
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="media-object-section">
                                                <div class="media-content">
                                                    <h6><a href="#">The lorem Ipsumbeen the industry's standard.</a></h6>
                                                    <p><i class="fa fa-user"></i><span>admin</span><i class="fa fa-clock-o"></i><span>5 january 16</span></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- End Recent post videos -->

                            <!-- tags -->
                            <div class="large-12 medium-7 medium-centered columns">
                                <div class="widgetBox">
                                    <div class="widgetTitle">
                                        <h5>Tags</h5>
                                    </div>
                                    <div class="tagcloud">
                                        <a href="#">3D Videos</a>
                                        <a href="#">Videos</a>
                                        <a href="#">HD</a>
                                        <a href="#">Movies</a>
                                        <a href="#">Sports</a>
                                        <a href="#">3D</a>
                                        <a href="#">Movies</a>
                                        <a href="#">Animation</a>
                                        <a href="#">HD</a>
                                        <a href="#">Music</a>
                                        <a href="#">Recreation</a>
                                    </div>
                                </div>
                            </div><!-- End tags -->
                        </div>
                    </aside>
                </div><!-- end sidebar -->
            </div>
<%@ include  file="/WEB-INF/views/common/foot.jsp"%>