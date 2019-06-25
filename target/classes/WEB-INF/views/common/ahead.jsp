<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>龙龙直播管理平台</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/admin/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/admin/css/demo.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/admin/css/easyui.css">
<script type="text/javascript" src="<%=path %>/admin/js/jquery.min.js"></script>

<script type="text/javascript" src="<%=path %>/admin/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/admin/js/jquery.easyui.mobile.js"></script>
<script type="text/javascript" src="<%=path %>/admin/js/search.js"></script>
<script type="text/javascript" src="<%=path %>/admin/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/admin/js/highcharts.js"></script>
<script type="text/javascript" src="<%=path %>/admin/js/exporting.js"></script>
</head>
<body class="easyui-layout">
