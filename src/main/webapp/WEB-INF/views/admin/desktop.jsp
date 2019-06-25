<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include  file="/WEB-INF/views/common/ahead.jsp"%>

	<script>
		function OpenTab(title,url){
			$("#tabs").tabs('add',{
		        title: title,
		        content: '<iframe style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="' + url + '"></iframe>',
		        closable: true
		    });
		}
		
	</script>
	
	<div class="easyui-layout" style="width:100%;height:100%">
		<div data-options="region:'north'" style="height:50px"></div>
		<div data-options="region:'south',split:true" style="height:50px;"></div>
		
		<div data-options="region:'west',split:true" title="导航" style="width:20%;">
			<div class="easyui-accordion" data-options="fit:false,border:false">
				<div title="分类管理">
					<a href="javascript:OpenTab('分类管理', '<%=path%>/liveCategory/view.html')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-blank'" style="width:100%;text-align:left;">分类管理</a>
				</div>
				<div title="直播管理">
					<a href="javascript:OpenTab('直播间', '<%=path%>/live/view.html')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-blank'" style="width:100%;text-align:left;">直播间</a>
					
				</div>
				<div title="视频管理">
					<a href="javascript:OpenTab('视频', '<%=path%>/movies/view.html')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-blank'" style="width:100%;text-align:left;">视频</a>
					
				</div>
				<div title="标签管理">
					<a href="javascript:OpenTab('标签管理', '<%=path%>/tagManager/view.html')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-blank'" style="width:100%;text-align:left;">标签管理</a>
					
				</div>
				<div title="礼物管理">
					<a href="javascript:OpenTab('礼物管理', '<%=path%>/goods/view.html')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-blank'" style="width:100%;text-align:left;">礼物管理</a>
					<a href="javascript:OpenTab('赠送管理', '<%=path%>/goodsRecord/view.html')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-blank'" style="width:100%;text-align:left;">赠送管理</a>
				</div>	
				<div title="用户管理">
					<a href="javascript:OpenTab('用户管理', '<%=path%>/user/view.html')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-blank'" style="width:100%;text-align:left;">用户管理</a>
					<a href="javascript:OpenTab('用户等级管理', '<%=path%>/userSpace/view.html')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-blank'" style="width:100%;text-align:left;">用户等级管理</a>
				</div>
				
				</div>
			</div>
		
		<div data-options="region:'center',title:'',iconCls:'icon-ok'">
			
		<div id="tabs" class="easyui-tabs" style="width:100%;height:100%">
		<div title="首页" style="padding:10px" data-options="iconCls:'icon-help',closable:true">
			<p style="font-size:14px"></p>
			<div>
			<div id="column" style="min-width:100%;height:10%"></div>
			<div id="line" style="min-width:100%;height:10%"></div>
			</div>
		</div>
	</div>
		</div>
	</div>

<%@ include  file="/WEB-INF/views/common/afoot.jsp"%>