<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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