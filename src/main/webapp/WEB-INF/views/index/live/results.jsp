<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
                                                                <p class="pull-rigth">
                                                                    <i class="fa fa-eye"></i>
                                                                    <span>${result.videoLength }</span>
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