<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include  file="/WEB-INF/views/common/head.jsp"%>
<!--end breadcrumbs-->
<script type="text/javascript">

function Refresh(page)
{
	$.ajax({  
		type:'post',      
		url:'<%=request.getContextPath() %>/search/MoviesCateSearch/refresh.html',  
		data:'page='+page+'&id='+'${id }',
		cache:false,  
		dataType:'html',  
		success:function(data){
			$("#searchResult").empty();
            $("#searchResult").append(data);
		}  
		});	
}
function MySpace(videoId)
{
	$.ajax({  
		type:'post',      
		url:'<%=request.getContextPath() %>/indexSpace/save.html',  
		data:'videoId='+videoId,
		cache:false,  
		dataType:'json',  
		success:function(data){
			if(data.code==1000007)
			{
				window.location.href="<%=request.getContextPath()%>/user/login";
			}else if(data.code==0)
			{
				alert('收藏成功');
			}else
			{
				alert(data.msg);
			}
		}  
		});	
}
</script>
            <section class="category-content">
                <div class="row">
                    <!-- left side content area -->
                    <div class="large-8 columns">
                        <section class="content content-with-sidebar">
                            <!-- newest video -->
                            <div class="main-heading removeMargin">
                                <div class="row secBg padding-14 removeBorderBottom">
                                    <div class="medium-8 small-12 columns">
                                        <div class="head-title">
                                            <i class="fa fa-search"></i>
                                            <h4>"${catename }"的搜索结果</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row secBg">
                                <div class="large-12 columns">
                                    <div class="row column head-text clearfix">
                                        <p class="pull-left">搜索结果 :<span>${pageBean.totalRows } 个视频被找到</span></p>
                                        <div class="grid-system pull-right show-for-large">
                                            <a class="secondary-button grid-default" href="#"><i class="fa fa-th"></i></a>
                                            <a class="secondary-button grid-medium" href="#"><i class="fa fa-th-large"></i></a>
                                            <a class="secondary-button current list" href="#"><i class="fa fa-th-list"></i></a>
                                        </div>
                                    </div>
                                    <div class="tabs-content" data-tabs-content="newVideos">
                                        <div class="tabs-panel is-active" id="new-all">
                                            <div class="row list-group">
                                            <div id="searchResult">
                                            <c:forEach items="${pageBean.result }" var="result">
                                                <div class="item large-4 medium-6 columns list">
                                                    <div class="post thumb-border">
                                                        <div class="post-thumb">
															<img src="${result.imageName }" onerror="http://placehold.it/370x220" alt="new video">
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
                                                                <a href="<%=request.getContextPath() %>/play/video/${result.id }.html" class="secondary-button"><i class="fa fa-play-circle"></i>观看视频</a>
                                                                <a href="javacript:void(0);" class="secondary-button" onclick="MySpace('${result.id }')"><i class="fa fa-folder-open"></i>收藏视频</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                </c:forEach>
          										
                                                
                                    <div class="pagination">
                                        <c:forEach items="${pageBean.pageCodes }" var="code">
                                        <a href="javacript:void(0);" class="page-numbers" onclick="Refresh('${code }');">${code }</a>
                                        </c:forEach>
                                    </div>
                                    
							
							
                                       
                                   
                                    </div>
                                    </div>
                                </div>
                                
                            </div>
                                                                  
                        </section>
                        <!-- ad Section -->
                       <!-- End ad Section -->
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

                                <!-- categories -->
                                <div class="large-12 medium-7 medium-centered columns">
                                    <div class="widgetBox">
                                        <div class="widgetTitle">
                                            <h5>categories</h5>
                                        </div>
                                        <div class="widgetContent">
                                            <ul class="accordion" data-accordion>
                                                <li class="accordion-item is-active" data-accordion-item>
                                                    <a href="#" class="accordion-title">Entertainment</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Musics <span>(8)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Animations <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Dramas <span>(5)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">Technology</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">Fashion &amp; Beauty</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">sports &amp; recreation</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">Automotive</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">foods &amp; drinks</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">Peopls</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">Nature</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">Transportationy</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">places &amp; landmarks</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">Travel</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">Animals</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li class="accordion-item" data-accordion-item>
                                                    <a href="#" class="accordion-title">Historicals &amp; Architectural</a>
                                                    <div class="accordion-content" data-tab-content>
                                                        <ul>
                                                            <li class="clearfix">
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Movies <span>(10)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Trailers <span>(3)</span></a>
                                                            </li>
                                                            <li>
                                                                <i class="fa fa-play-circle-o"></i>
                                                                <a href="#">Comedy <span>(6)</span></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
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

                                <!-- slide video -->
                                <div class="large-12 medium-7 medium-centered columns">
                                    <section class="widgetBox">
                                        <div class="row">
                                            <div class="large-12 columns">
                                                <div class="column row">
                                                    <div class="heading category-heading clearfix">
                                                        <div class="cat-head pull-left">
                                                            <h4>slide videos</h4>
                                                        </div>
                                                        <div class="sidebar-video-nav">
                                                            <div class="navText pull-right">
                                                                <a class="prev secondary-button"><i class="fa fa-angle-left"></i></a>
                                                                <a class="next secondary-button"><i class="fa fa-angle-right"></i></a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- slide Videos-->
                                                <div id="owl-demo-video" class="owl-carousel carousel" data-car-length="1" data-items="1" data-loop="true" data-nav="false" data-autoplay="true" data-autoplay-timeout="3000" data-dots="false">
                                                    <div class="item item-video thumb-border">
                                                        <figure class="premium-img">
                                                            <img src="http://placehold.it/300x190" alt="carousel">
                                                            <a href="#" class="hover-posts">
                                                                <span><i class="fa fa-play"></i></span>
                                                            </a>
                                                        </figure>
                                                        <div class="video-des">
                                                            <h6><a href="#">There are many variations of passage.</a></h6>
                                                            <div class="post-stats clearfix">
                                                                <p class="pull-left">
                                                                    <i class="fa fa-user"></i>
                                                                    <span><a href="#">admin</a></span>
                                                                </p>
                                                                <p class="pull-left">
                                                                    <i class="fa fa-clock-o"></i>
                                                                    <span>5 January 16</span>
                                                                </p>
                                                                <p class="pull-left">
                                                                    <i class="fa fa-eye"></i>
                                                                    <span>1,862K</span>
                                                                </p>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="item item-video thumb-border">
                                                        <figure class="premium-img">
                                                            <img src="http://placehold.it/300x190" alt="carousel">
                                                            <a href="#" class="hover-posts">
                                                                <span><i class="fa fa-play"></i></span>
                                                            </a>
                                                        </figure>
                                                        <div class="video-des">
                                                            <h6><a href="#">There are many variations of passage.</a></h6>
                                                            <div class="post-stats clearfix">
                                                                <p class="pull-left">
                                                                    <i class="fa fa-user"></i>
                                                                    <span><a href="#">admin</a></span>
                                                                </p>
                                                                <p class="pull-left">
                                                                    <i class="fa fa-clock-o"></i>
                                                                    <span>5 January 16</span>
                                                                </p>
                                                                <p class="pull-left">
                                                                    <i class="fa fa-eye"></i>
                                                                    <span>1,862K</span>
                                                                </p>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="item item-video thumb-border">
                                                        <figure class="premium-img">
                                                            <img src="http://placehold.it/300x190" alt="carousel">
                                                            <a href="#" class="hover-posts">
                                                                <span><i class="fa fa-play"></i></span>
                                                            </a>
                                                        </figure>
                                                        <div class="video-des">
                                                            <h6><a href="#">There are many variations of passage.</a></h6>
                                                            <div class="post-stats clearfix">
                                                                <p class="pull-left">
                                                                    <i class="fa fa-user"></i>
                                                                    <span><a href="#">admin</a></span>
                                                                </p>
                                                                <p class="pull-left">
                                                                    <i class="fa fa-clock-o"></i>
                                                                    <span>5 January 16</span>
                                                                </p>
                                                                <p class="pull-left">
                                                                    <i class="fa fa-eye"></i>
                                                                    <span>1,862K</span>
                                                                </p>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div><!-- end carousel -->
                                            </div>
                                        </div>
                                    </section><!-- End Category -->
                                </div><!-- End slide video -->

                                <!-- ad banner widget -->
                                <div class="large-12 medium-7 medium-centered columns">
                                    <div class="widgetBox">
                                        <div class="widgetTitle">
                                            <h5>Recent post videos</h5>
                                        </div>
                                        <div class="widgetContent">
                                            <div class="advBanner text-center">
                                                <a href="#"><img src="images/sideradv.png" alt="sidebar adv"></a>
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

                                <!--news letter-->
                                <div class="large-12 medium-7 medium-centered columns">
                                    <div class="widgetBox">
                                        <div class="newsLetter">
                                            <h3>Newsletter Singup</h3>
                                            <p>Subscribe to get exclusive videos</p>
                                            <form method="post">
                                                <div class="input-group">
                                                    <input class="input-group-field" type="email" placeholder="Enter your email addres">
                                                    <div class="input-group-button">
                                                        <input type="submit" class="button" value="Signup">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div><!--End news letter-->

                                <!-- twitter -->
                                <div class="large-12 medium-7 medium-centered columns">
                                    <div class="widgetBox">
                                        <div class="widgetTitle">
                                            <h5>Recent Tweets</h5>
                                        </div>
                                        <div class="widgetContent">
                                            <div class="owl-carousel carousel twitter-carousel" data-car-length="1" data-items="1" data-loop="true" data-nav="false" data-autoplay="true" data-autoplay-timeout="4000" data-dots="true">
                                                <div class="item twitter-item">
                                                    <i class="fa fa-twitter-square"></i>
                                                    <span>
                                                        <a target="_blank" title="Follow envato_help" href="http://twitter.com/envato_help">@envato_help</a> Ok Just One question is that Your account? <a target="_blank" title="https://t.co/jtUNeYAFSV
If" href="https://t.co/jtUNeYAFSVIf">https://t.co/jtUNeYAFSV If</a> not then please bloc this ASAP.</span>
                                                </div>
                                                <div class="item twitter-item">
                                                    <i class="fa fa-twitter-square"></i>
                                                    <span>
                                                        <a target="_blank" title="Follow envato_help" href="http://twitter.com/envato_help">@envato_help</a> Ok Just One question is that Your account? <a target="_blank" title="https://t.co/jtUNeYAFSV
If" href="https://t.co/jtUNeYAFSVIf">https://t.co/jtUNeYAFSV If</a> not then please bloc this ASAP.</span>
                                                </div>
                                                <div class="item twitter-item">
                                                    <i class="fa fa-twitter-square"></i>
                                                    <span>
                                                        <a target="_blank" title="Follow envato_help" href="http://twitter.com/envato_help">@envato_help</a> Ok Just One question is that Your account? <a target="_blank" title="https://t.co/jtUNeYAFSV
If" href="https://t.co/jtUNeYAFSVIf">https://t.co/jtUNeYAFSV If</a> not then please bloc this ASAP.</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div><!-- End Twitter -->

                            </div>
                        </aside>
                    </div><!-- end sidebar -->
                </div>
            </section><!-- End Category Content-->
            <!-- footer -->
           
 
<%@ include  file="/WEB-INF/views/common/foot.jsp"%>