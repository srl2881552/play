<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include  file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript">
	function spaceRefresh(code)
	{
		$.ajax({  
			type:'post',      
			url:'<%=request.getContextPath() %>/mine/space/refresh.html',  
			data:'pageNo='+code,
			cache:false,  
			dataType:'html',  
			success:function(data){
				$("#spaceResult").empty();
	            $("#spaceResult").append(data);
			}  
			});	
	}
</script>

            <!-- profile top section -->
            <section class="topProfile">
                <div class="main-text text-center">
                    <div class="row">
                        <div class="large-12 columns">
                            <h3>世界最大</h3>
                            <h1>充满活力的视频主题</h1>
                        </div>
                    </div>
                </div>
                <div class="profile-stats">
                    <div class="row secBg">
                        <div class="large-12 columns">
                            <div class="profile-author-img">
                                
                                <img src="${user.imageName }" onerror="http://placehold.it/120x110" alt="profile author img">
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
                                    <h4>我的视频</h4>
                                </div>
                                <div id="spaceResult">
                                <c:forEach items="${pageBean.result }" var="result">
                                <div class="profile-video">
                                    <div class="media-object stack-for-small">
                                        <div class="media-object-section media-img-content">
                                            <div class="video-img">
                                                <img onerror="http://placehold.it/170x150" src="${result.imageName }" alt="video thumbnail">
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
                                                    
                                                </div>
                                                <div class="video-btns">
                                                    
                                                    <a class="video-btn" href="#"><i class="fa fa-trash"></i>删除</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </c:forEach>
                                <div class="pagination">
                                        <c:forEach items="${pageCodes }" var="code">
                                        <a href="javacript:void(0);" class="page-numbers" onclick="spaceRefresh('${code }');">${code }</a>
                                        </c:forEach>
                                </div>
                                </div>
                            </div>
                        </div>
                    </section><!-- End single post description -->
                </div><!-- end left side content area -->
            </div>

           
<%@ include  file="/WEB-INF/views/common/foot.jsp"%>
