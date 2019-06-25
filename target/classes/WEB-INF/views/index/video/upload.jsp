<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include  file="/WEB-INF/views/common/head.jsp"%>
<script src="<%=request.getContextPath() %>/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
function uploadTest()
{
	
	var form=$("#uploadForm");
	form.submit();
	

}
</script>
 <!-- profile top section -->
            <section class="topProfile topProfile-inner">
                <div class="main-text">
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
                            
                            <div class="profile-share">
                                
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
                    <aside class="secBg sidebar">
                        <div class="row">
                            <!-- profile overview -->
                            <div class="large-12 columns">
                                <div class="widgetBox">
                                    <div class="widgetTitle">
                                        <h5>Profile Overview</h5>
                                    </div>
                                    <div class="widgetContent">
                                        <ul class="profile-overview">
                                            <li class="clearfix"><a href="profile-about-me.html"><i class="fa fa-user"></i>about me</a></li>
                                            <li class="clearfix"><a href="profile-video.html"><i class="fa fa-video-camera"></i>Videos <span class="float-right">36</span></a></li>
                                            <li class="clearfix"><a href="profile-favorite.html"><i class="fa fa-heart"></i>Favorite Videos<span class="float-right">50</span></a></li>
                                            <li class="clearfix"><a href="profile-followers.html"><i class="fa fa-users"></i>Followers<span class="float-right">6</span></a></li>
                                            <li class="clearfix"><a href="profile-comments.html"><i class="fa fa-comments-o"></i>comments<span class="float-right">26</span></a></li>
                                            <li class="clearfix"><a href="profile-settings.html"><i class="fa fa-gears"></i>Profile Settings</a></li>
                                            <li class="clearfix"><a href="index.html"><i class="fa fa-sign-out"></i>Logout</a></li>
                                        </ul>
                                        <a href="submit-post.html" class="button"><i class="fa fa-plus-circle"></i>Submit Video</a>
                                    </div>
                                </div>
                            </div><!-- End profile overview -->
                        </div>
                    </aside>
                </div><!-- end sidebar -->
                <!-- right side content area -->
                <div class="large-8 columns profile-inner">
                    <!-- profile settings -->
                    <section class="submit-post">
                        <div class="row secBg">
                            <div class="large-12 columns">
                                <div class="heading">
                                    <i class="fa fa-pencil-square-o"></i>
                                    <h4>添加一个新的视频</h4>
                                </div>
                                  <form id="uploadForm" enctype="multipart/form-data" method="post" action="<%=request.getContextPath()%>/video/upload">
                                <div class="row">
                                    <div class="large-12 columns">

                                      
                                        
                                            <div data-abide-error class="alert callout" style="display: none;">
                                                <p><i class="fa fa-exclamation-triangle"></i>
                                                    There are some errors in your form.</p>
                                            </div>
                                            <div class="row">
                                                <div class="large-12 columns">
                                                    <label>标题
                                                        <input type="text" id="title" name="title" placeholder="输入视频的标题" required>
                                                        <span class="form-error">
                                                            Yo, you had better fill this out, it's required.
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="large-12 columns">
                                                    <label>描述
                                                        <textarea id="about" name="about"></textarea>
                                                    </label>
                                                </div>
                                                <!-- <div class="large-12 columns">
                                                    <h6 class="borderBottom">Choose Video Method:</h6>
                                                    <p><strong>Note:</strong> Please choose one of the following ways to embed the video into your post, the video is determined in the order: Video Code > Video URL > Video File.</p>
                                                </div>
                                                <div class="large-12 columns">
                                                    <div class="radio">
                                                       <input type="radio" value="check" name="videolink" id="videolink1" checked>
                                                       <label class="customLabel" for="videolink1">Video Link From Youtube/Vimeo etc..</label>
                                                       <input type="radio" value="check" name="videolink" id="videolink2">
                                                       <label class="customLabel" for="videolink2">Custom Video Upload / Put custom Video URL </label>
                                                       <input type="radio" value="check" name="videolink" id="videolink3">
                                                       <label class="customLabel" for="videolink3">Embed/Object Code</label>
                                                    </div>
                                                </div> -->
                                                
                                                <div class="large-12 columns">
                                                    <!--
                                                    <label>Put here your video url with proper extension:
                                                        <input type="url" placeholder="for example:http://yoursite.com/sample-video.mp4">
                                                    </label>
                                                    <h6>OR</h6>
                                                    -->
                                                    <div class="upload-video">
                                                        <label></label>
                                                        <label for="videoUpload" class="button">上传视频文件</label>
                                                        <input type="file" id="videoUpload" name="videoUpload" class="show-for-sr">
                                                    </div>
                                                    <p class="extraMargin">粘贴您的视频文件URL到这里。支持的视频格式：MP4、M4V、WebMV、WebM、OGV和FLV。关于跨平台和跨浏览器支持。如果你想让你的视频在所有平台和浏览器（HTML5和Flash）上都能工作，你应该为相同的视频提供各种视频格式，如果视频文件准备好了，每行输入一个网址。例如：http://yousite.com/sample-..m4v http://yousite.com/sample-..ogv推荐格式解决方案：webmv+m4v+ogv。</p>
                                                </div>
                                                
                                                <!--  
                                                <div class="large-12 columns">
                                                    <div class="post-meta">
                                                        <label>Meta Title:
                                                            <textarea placeholder="enter meta title"></textarea>
                                                        </label>
                                                        <p>IF you want to put your custom meta Title then put here otherwise your post title will be the default meta Title</p>
                                                    </div>
                                                    <div class="post-meta">
                                                        <label>Meta Description:
                                                            <textarea placeholder="enter meta Description"></textarea>
                                                        </label>
                                                        <p>IF you want to put your custom meta description then put here otherwise your post description will be the default meta description</p>
                                                    </div>
                                                    <div class="post-meta">
                                                        <label>Meta keywords:
                                                            <textarea placeholder="enter meta keywords"></textarea>
                                                        </label>
                                                        <p>IF you want to put your custom meta Keywords then put here otherwise your post Keywords will be the default meta Keywords</p>
                                                    </div>
                                                    -->
                                                    <div class="post-category">
                                                        <label>视频分类:
                                                            <select id="cateId" name="cateId">
                                                            	<c:forEach items="${cates }" var="cate">
                                                                <option value="${cate.id }">${cate.cateName }</option>
                                                                
                                                                </c:forEach>
                                                            </select>
                                                        </label>
                                                    </div>
                                                    <div class="upload-video">
                                                        <label for="imgUpload" class="button">上传图片</label>
                                                        <input type="file" id="imgUpload" name="imgUpload" class="show-for-sr">
                                                    </div>
                                                </div>
                                                <!-- 
                                                <div class="large-12 columns">
                                                    <div class="video-sprite clearfix">
                                                        <div class="video-layout">
                                                            <input type="radio" id="radio-img-1" name="radio-btns-sprite">
                                                            <label for="radio-img-1" class="fullwidth">fullwidth</label>
                                                            <span>Full-width</span>
                                                        </div>
                                                        <div class="video-layout">
                                                            <input type="radio" id="radio-img-2" name="radio-btns-sprite" checked>
                                                            <label for="radio-img-2" class="standard">standard</label>
                                                            <span>Standarad</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                 -->
                                                  
                                                <div class="large-12 columns">
                                                    <label>标签:
                                                        <input type="text" placeholder="输入视频相关的标签(多个用逗号隔开)" name="tag" id="tag"/>
                                                    </label>
                                                </div>
                                                
                                                <div class="large-12 columns">
                                                    <button class="button expanded" type="submit" name="submit" onclick="uploadTest();">现在上传</button>
                                                </div>
                                            </div>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </section><!-- End profile settings -->
                </div><!-- end left side content area -->
            </div>
			<%@ include  file="/WEB-INF/views/common/foot.jsp"%>