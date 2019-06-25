<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	request.setAttribute("url", request.getServletPath());
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>龙龙直播互动平台-专业的在线视频直播平台</title>
    <link rel="icon" type="image/x-icon" href="<%=request.getContextPath() %>/images/favicon.icon" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/app.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/theme.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/layerslider/css/layerslider.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/responsive.css">
</head>
<body>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ckplayer/ckplayer.js"></script>
<script src="<%=request.getContextPath() %>/bower_components/jquery/dist/jquery.js"></script>

<div class="off-canvas-wrapper">
    <div class="off-canvas-wrapper-inner" data-off-canvas-wrapper>
        <!--header-->
        <div class="off-canvas position-left light-off-menu dark-off-menu" id="offCanvas" data-off-canvas>
            <div class="off-menu-close">
                <h3>Menu</h3>
                <span data-toggle="offCanvas"><i class="fa fa-times"></i></span>
            </div>
            <ul class="vertical menu off-menu" data-responsive-menu="drilldown">
                <li class="has-submenu">
                    <a href="<%=request.getContextPath()%>">首页</a>
                    
                </li>
                <li class="has-submenu" data-dropdown-menu="example1">
                    <a href="<%=request.getContextPath() %>/liveCategories/categories.html">直播分类</a>
                    
                </li>
                <li class="has-submenu" data-dropdown-menu="example1">
                    <a href="<%=request.getContextPath() %>/videoCategories/categories.html">视频分类</a>
                    
                </li>
                <li><a href="<%=request.getContextPath() %>/live/all.html">全部直播</a></li>
                <li><a href="<%=request.getContextPath() %>/video/all.html">全部视频</a></li>
               <!--  <li>
                    <a href="<%=request.getContextPath() %>/live/hot.html">热门直播</a>
                    
                </li> -->
               <!--  <li>
                    <a href="<%=request.getContextPath() %>/video/hot.html">热门视频</a>
                    
                </li> -->
                <!-- <li><a href="<%=request.getContextPath() %>/mine/anchor.html">热门主播</a></li> -->
                <li><a href="<%=request.getContextPath() %>/mine/space.html">我的空间</a></li>
                <li><a href="<%=request.getContextPath() %>/mine/follow.html">我的收藏</a></li>
                <li><a href="<%=request.getContextPath() %>/mine/history.html">观看历史</a></li>
                <li><a href="<%=request.getContextPath() %>/content/view.html">资料库(暂未开放)</a></li>
            </ul>
            <div class="responsive-search">
                <form method="post" action="<%=request.getContextPath()%>/search/multipleSearch">
                    <div class="input-group">
                        <input class="input-group-field" name="search" type="text" placeholder="搜索您的视频">
                        <div class="input-group-button">
                            <button type="submit" name="search"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="off-social">
                <h6>Get Socialize</h6>
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-google-plus"></i></a>
                <a href="#"><i class="fa fa-instagram"></i></a>
                <a href="#"><i class="fa fa-vimeo"></i></a>
                <a href="#"><i class="fa fa-youtube"></i></a>
            </div>
            <div class="top-button">
                <ul class="menu">
                    <li>
                        <a href="<%=request.getContextPath() %>/video/showUpload.html">视频上传</a>
                    </li>
                    <li class="dropdown-login">
                        <a href="<%=request.getContextPath() %>/user/login.html">登录/注册</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="off-canvas-content" data-off-canvas-content>
            <header>
                <!-- Top -->
                <section id="top" class="topBar topBarBlack show-for-large">
                    <div class="row">
                        <div class="medium-6 columns">
                            <div class="topBarMenu">
                                <ul class="menu">
                                    <li>
                                        <a href="<%=request.getContextPath()%>"><i class="fa fa-home"></i>首页</a>
                                    </li>
                                    <li>
                                        <a href="<%=request.getContextPath() %>/platform/registerlive.html"><i class="fa fa-th-large"></i>我要直播</a>
                                    </li>
                                    <li>
                                        <a href="<%=request.getContextPath() %>/platform/aboutme.html"><i class="fa fa-user"></i>介绍</a>
                                    </li>
                                    <li>
                                        <a href="<%=request.getContextPath() %>/platform/blog.html"><i class="fa fa-pencil-square-o"></i>博客</a>
                                    </li>
                                    <li>
                                        <a href="<%=request.getContextPath() %>/platform/about.html"><i class="fa fa-envelope"></i>联系我们</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="medium-6 columns">
                            <div class="top-button">
                                <div class="socialLinks float-right">
                                    <a href="#"><i class="fa fa-facebook-f"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-google-plus"></i></a>
                                    <a href="#"><i class="fa fa-instagram"></i></a>
                                    <a href="#"><i class="fa fa-vimeo"></i></a>
                                    <a href="#"><i class="fa fa-youtube"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </section><!-- End Top -->
                <!--Navber-->
                <section id="navBar">
                    <div class="middleNav show-for-large">
                        <div class="row">
                            <div class="large-3 columns">
                                <div class="logo">
                                    <a href="index.html"><img src="<%=request.getContextPath() %>/images/logo.png" alt="betube"></a>
                                </div>
                            </div>
                            <div class="large-6 columns">
                                <div class="topCenterAdv text-center">
                                    <img src="<%=request.getContextPath() %>/images/header-top-img.png" alt="betube">
                                </div>
                            </div>
                            <div class="large-3 columns">
                                <div class="search-btns float-right">
                                    <ul class="menu">
                                        <li class="upl-btn">
                                            <a href="<%=request.getContextPath() %>/video/showUpload.html">视频上传</a>
                                        </li>
                                        <li class="login">
                                            <a href="<%=request.getContextPath() %>/user/login.html">登录/注册</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <nav class="sticky-container navBlack navFull-v2" data-sticky-container>
                        <div class="sticky topnav" data-sticky data-top-anchor="navBar" data-btm-anchor="footer-bottom:bottom" data-margin-top="0" data-margin-bottom="0" style="width: 100%; background: #444;" data-sticky-on="large">
                            <div class="row">
                                <div class="large-12 columns">
                                    <div class="title-bar title-bar-dark" data-responsive-toggle="beNav" data-hide-for="large">
                                        <button class="menu-icon" type="button" data-toggle="offCanvas"></button>
                                        <div class="title-bar-title"><img src="<%=request.getContextPath() %>/images/logo-small.png" alt="logo"></div>
                                    </div>

                                    <div class="top-bar show-for-large topbar-light-dark" id="beNav" style="width: 100%;">
                                        <div class="top-bar-left">
                                            <ul class="menu vertical medium-horizontal" data-responsive-menu="drilldown medium-dropdown">
                                                <li class="has-submenu active" data-dropdown-menu="example">
                                                    <a href="<%=request.getContextPath()%>">首页</a>
                                                    
                                                </li>
                                                
                                                <li><a href="<%=request.getContextPath() %>/liveCategories/categories.html">直播分类</a></li>
                                                <li><a href="<%=request.getContextPath() %>/videoCategories/categories.html">视频分类</a></li>
                                                <li><a href="<%=request.getContextPath() %>/live/all.html">全部直播</a></li>
                                                <li><a href="<%=request.getContextPath() %>/video/all.html">全部视频</a></li>
                                               <!--  <li><a href="<%=request.getContextPath() %>/live/hot.html">热门直播</a></li>
                                                <li><a href="<%=request.getContextPath() %>/video/hot.html">热门视频</a></li>
                                                <li><a href="<%=request.getContextPath() %>/mine/anchor.html">热门主播</a></li> -->
                								<li><a href="<%=request.getContextPath() %>/mine/space.html">我的空间</a></li>
                								<li><a href="<%=request.getContextPath() %>/mine/follow.html">我的收藏</a></li>
                								<li><a href="<%=request.getContextPath() %>/mine/history.html">观看历史</a></li>
                								<li><a href="<%=request.getContextPath() %>/content/view.html">资料库(暂未开放)</a></li>
                                            </ul>
                                        </div>
                                        <div class="top-bar-right search-btn">
                                            <ul class="menu">
                                                <li class="search">
                                                    <i class="fa fa-search"></i>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="search-bar" class="clearfix search-bar-light search-bar-dark">
                                <form method="post" action="<%=request.getContextPath()%>/search/multipleSearch">
                                    <div class="search-input float-left">
                                        <input type="search" name="search" placeholder="在这里搜索您的视频" value=""/>
                                    </div>
                                    <div class="search-btn float-right text-right">
                                        <button class="button" name="search" type="submit">搜索</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </nav>
                </section>
            </header><!-- End Header -->