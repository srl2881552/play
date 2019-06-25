<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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