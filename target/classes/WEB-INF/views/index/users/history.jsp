<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include  file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript">
 function followRefresh(page)
 {
	 $.ajax({  
			type:'post',      
			url:'<%=request.getContextPath() %>/mine/history/refresh.html',  
			data:'pageNo='+page,
			cache:false,  
			dataType:'html',  
			success:function(data){
				$("#followResult").empty();
	            $("#followResult").append(data);
			}  
			});	
 }
</script>

            <!-- profile top section -->
            <section class="topProfile">
                <div class="main-text text-center">
                    <div class="row">
                        <div class="large-12 columns">
                            <h3>龙龙直播互动平台-专业的在线视频直播平台</h3>
                            
                        </div>
                    </div>
                </div>
                <div class="profile-stats">
                    <div class="row secBg">
                        <div class="large-12 columns">
                            <div class="profile-author-img">
                                <img src="${user.imageName }" onerror="http://placehold.it/120x110" alt="profile author img">
                            </div>
                            <div class="profile-subscribe">
                               <!-- <span><i class="fa fa-users"></i>6</span>
                                 <button type="submit" name="subscribe">subscribe</button> -->
                            </div>
                            
                            <div class="clearfix">
                                <div class="profile-author-name float-left">
                                    <h4>${user.nickName }</h4>
                                    <p>加入时间 : <span><fmt:formatDate value="${user.createAt }" pattern="yyyy-MM-dd HH:mm:ss" /></span></p>
                                </div>
                                <div class="profile-author-stats float-right">
                                    <ul class="menu">
                                        <li>
                                            <div class="icon float-left">
                                                <i class="fa fa-video-camera"></i>
                                            </div>
                                            <div class="li-text float-left">
                                                <p class="number-text">36</p>
                                                <span>Videos</span>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="icon float-left">
                                                <i class="fa fa-heart"></i>
                                            </div>
                                            <div class="li-text float-left">
                                                <p class="number-text">50</p>
                                                <span>favorites</span>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="icon float-left">
                                                <i class="fa fa-users"></i>
                                            </div>
                                            <div class="li-text float-left">
                                                <p class="number-text">6</p>
                                                <span>followers</span>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="icon float-left">
                                                <i class="fa fa-comments-o"></i>
                                            </div>
                                            <div class="li-text float-left">
                                                <p class="number-text">26</p>
                                                <span>comments</span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section><!-- End profile top section -->
            <div class="row">
                <!-- left sidebar -->
                <div class="large-4 columns">
                    
                </div><!-- end sidebar -->
                <!-- right side content area -->
                <div class="large-8 columns profile-inner">
                    <!-- single post description -->
                    <section class="profile-videos">
                        <div class="row secBg">
                            <div class="large-12 columns">
                                <div class="heading">
                                    <i class="fa fa-video-camera"></i>
                                    <h4>观看历史</h4>
                                </div>
                                <div id="followResult">
                                <c:forEach items="${pageBean.result }" var="result">
                                <div class="profile-video">
                                    <div class="media-object stack-for-small">
                                        <div class="media-object-section media-img-content">
                                            <div class="video-img">
                                                <img src="${result.imageName }" onerror="http://placehold.it/170x150" alt="video thumbnail">
                                            </div>
                                        </div>
                                        <div class="media-object-section media-video-content">
                                            <div class="video-content">
                                                <h5><a href="<%=request.getContextPath() %>/play/video/${result.id }.html">${result.title }</a></h5>
                                                <p>${result.about }</p>
                                            </div>
                                            <div class="video-detail clearfix">
                                                <div class="video-stats">
                                                    
                                                    <span><i class="fa fa-clock-o"></i><fmt:formatDate value="${result.createAt }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                                                    <span><i class="fa fa-clock-o"></i>已观看到${result.historyTime }</span>
                                                </div>
                                                <div class="video-btns">
                                                    <form method="post">
                                                        <button type="submit" name="unfav"><i class="fa fa-heart-o"></i>取消收藏</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </c:forEach>
                                <div class="pagination">
                                        <c:forEach items="${pageCodes }" var="code">
                                        <a href="javacript:void(0);" class="page-numbers" onclick="followRefresh('${code }');">${code }</a>
                                        </c:forEach>
                                    </div>
                               </div>
                            </div>
                        </div>
                    </section><!-- End single post description -->
                </div><!-- end left side content area -->
            </div>

            
     
            <!-- footer -->
            
<%@ include  file="/WEB-INF/views/common/foot.jsp"%>