<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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