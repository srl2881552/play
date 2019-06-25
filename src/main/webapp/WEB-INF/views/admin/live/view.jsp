<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include  file="/WEB-INF/views/common/ahead.jsp"%>
<!--  <div style="margin: 10px;">
 <div class="easyui-panel" style="height: 100%; width: 100%" data-options="collapsible:true" title="记事本">
		<form id="formQuery">
			<table cellpadding="4">
				<tr>
					<td>标题：</td>
					<td width="200"><input class="easyui-textbox" type="text" data-options="width:160" id="title"/></td>
					<td>内容：</td>
					<td width="200"><input class="easyui-textbox" type="text" data-options="width:160" id="comment"/></td>
					
					<td>创建人：</td>
					<td width="200"><input class="easyui-textbox" type="text" data-options="width:160" id="create_by"/></td>
					<td><a href="javascript:reloadData()" class="easyui-linkbutton" iconCls="icon-search" >查询</a></td>
				</tr>
				
			</table>
		</form>
	</div>
	</div> -->
	<!-- <div data-options="region:'center'" style="width:100%;height:100px;"> -->
	<div class="easyui-panel" style="width: 100%;height: 100% " data-options="collapsible:true" title="直播管理">
		<div id="search">
		    <a href="javascript:openAdd()" class="easyui-splitbutton" iconCls="icon-add">添加直播间</a>
		    <!-- <a href="javascript:enable()" class="easyui-linkbutton" iconCls="icon-no">无效</a> -->
		</div>
		<table class="easyui-datagrid" id="grid" data-options="pagination:true,rownumbers:true,singleSelect:true,toolbar:'#bar',loader:loadData">
			<thead>
				<tr>
					<!-- <th data-options="field:'_operate',width:80,align:'center',formatter:formatOperate">操作</th> -->
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id'">编码</th>
					<th data-options="field:'title'">标题</th>
					<th data-options="field:'about'">介绍</th>
					<th data-options="field:'cateId'">类型编号</th>
					<th data-options="field:'cateName'">类型名称</th>
					<th data-options="field:'status',formatter:formatStatus">状态</th>
					<th data-options="field:'createAt'">创建时间</th>
					<th data-options="field:'imageName'">影片头图</th>
					<th data-options="field:'rtmp'">rtmp地址</th>
					<th data-options="field:'stream'">直播流地址</th>
					<th data-options="field:'_operate',width:80,align:'center',formatter:formatOperate">操作</th>
				</tr>
			</thead>
		</table>
	</div>
<div id="divAddEdit" class="easyui-dialog" title="导入账本"
	data-options="
	width:1000,
        closed: true,
        modal:true,
        buttons: [
        	{
	            text:'确定',
	            iconCls:'icon-ok',
	            handler:function(){
	            	onOk();
            	}
            },{
                text:'取消',
                iconCls:'icon-cancel',
                handler:function(){
                	$('#divAddEdit').dialog('close');
                }
            }
		]">
	<form id=formAddEdit method="post" enctype="multipart/form-data" action="<%=path%>/live/save.html">
		<table>
			
			<tr>
				<td><font color="red">* </font>标题:</td>
				<td><input class="easyui-textbox" data-options="required:true" name="title" id="title"/></td>
				
			</tr>
			<tr>
				<td><font color="red">* </font>介绍:</td>
				<td><input class="easyui-textbox" data-options="multiline:true" style="height:400px;width:300px" name="about" id="about"/></td>
				
			</tr>
			<tr>
				<td><font color="red">* </font>类型:</td>
				<td><select id="cate_id" name="cate_id" class="easyui-combobox" data-options="required:true,panelHeight:'auto',editable:false" style="width: 135px;">
						<c:forEach items="${vos }" var="t">
							<option value="${t.id }">${t.cateName }</option>
						</c:forEach>
						</select></td>
			</tr>
			<tr>
				<td><font color="red">* </font>地址:</td>
				<td><input class="easyui-textbox" data-options="required:true" name="rtmp" id="rtmp"/></td>
				
			</tr>
			<tr>
				<td><font color="red">* </font>视频流:</td>
				<td><input class="easyui-textbox" data-options="required:true" name="stream" id="stream"/></td>
				
			</tr>
			<tr>
				<td><font color="red">* </font>直播图片:</td>
				<td><input class="easyui-filebox"  data-options="prompt:'上传视频图片'" name="image" id="image" style="width:100%"></td>
				
			</tr>
		</table>
    </form>
</div>

<div id="divAddSelcet" class="easyui-dialog" title="查看记事本"
	data-options="
	width:1000,
        closed: true,
        modal:true,
        buttons: [
        	{
                text:'取消',
                iconCls:'icon-cancel',
                handler:function(){
                	$('#divAddSelcet').dialog('close');
                }
            }
		]">
	<form id=formAddSelcet method="post">
    	<input type="hidden" name="id" id="id"/>
		<table>
			
			
			<tr>
				<td><font color="red">* </font>标题:</td>
				<td><input class="easyui-textbox" data-options="required:true" style="width:800px" name="title" id="title"/></td>
				
			</tr>
			<tr>
				<td><font color="red">* </font>内容:</td>
				
				<td>
				<textarea rows="10" cols="155" id="comment" class=""></textarea>
				</td>
				
			</tr>
			
			
			<tr>
				<td><font color="red">* </font>类型:</td>
				<td><select id="no_type" name="no_type" class="easyui-combobox" data-options="required:true,panelHeight:'auto',editable:false" style="width: 135px;">
						<c:forEach items="${types }" var="t">
							<option value="${t.code }">${t.value}</option>
						</c:forEach>
						</select></td>
			</tr>
		</table>
    </form>
</div>

<script type="text/javascript">
function formatOperate(value, row) {
	var rows=getRowById(row.id);
	var oper="";
	if(rows.status==1)
	{
		oper = "<a href='javascript:openEdit(\""+rows.id+"\",\"0\")' class='easyui-linkbutton'>无效</a>";
	}else{
		oper = "<a href='javascript:openEdit(\""+rows.id+"\",\"1\")' class='easyui-linkbutton'>有效</a>";
	}
		return oper;
}
function openSelect(id)
{
	var row=getRowById(id);
	var form = $("#formAddSelcet");
	//form.find("#userWays").combobox("setValue", row.userWays);
	form.find("#id").val(row.id);
	form.find("#title").textbox("setValue",row.title);
	form.find("#comment").val(row.comment);
	
	$("#divAddSelcet").dialog("open");
}
//添加弹出
function openAdd() {
	setAddData();
	$("#divAddEdit").dialog("open");
}
function openDelete(id)
{
	var row=getRowById(id);
	ddsy.msg.confirm("确认修改当前数据吗？", function(ok){  
		if (ok) {	
			invoke("enable", {id:row.id}, function(result) {
				if(result.msg == ""){
					$.messager.alert('提示','操作成功','info', function(){
						$("#divAddEdit").dialog("close");
						reloadData();
		      	    });
				}else{
					ddsy.msg.info(result.msg);
				}
			});
		}
	});
}
function setAddData(){
	var form = $("#formAddEdit");
	form.find("#id").val("");
	form.find("#title").textbox("setValue", "");
	form.find("#comment").val("");
	
}
function formatStatus(value, row) {
	var oper = "";
	if(value=="1")
	{
		oper = "<span>有效</span>";
	}else{
		oper = "<span>无效</span>";
	}
		return oper;
}
//ajax添加或者编辑
function onOk() {
	var form = $("#formAddEdit");
	form.submit();
}
function formatDouble(value, row) {
	var oper = "<span>"+value+"</span>";
		return oper;
}
function getEditFormData() {
	var form = $("#formAddEdit");
	
	var data = {};
	data.title=form.find("#title").val();
	data.about=form.find("#about").val();
	data.rtmp=form.find("#rtmp").val();
	data.stream=form.find("#stream").val();
	data.cate_id=form.find("#cate_id").combobox('getValue');
	data.id=form.find("#id").val();
	return data;
}

//无效
	  
//点击查询 刷新表格
function reloadData() {
	$("#grid").datagrid('reload'); 
}

//获取查询数据
function getQueryData() {
	var form = $("#formQuery");
	var data = {};
	//data.title = $.trim(form.find("#title").textbox("getValue"));
	//data.comment = $.trim(form.find("#comment").val());
	//data.create_by = $.trim(form.find("#create_by").textbox("getValue"));
	return data;
}

//加载数据
function loadData(param,success,error) {
	invoke("list", $.extend(getQueryData(), param), function(result) {
		var rowlength = result.rows.length;
		success(result);
	});
}

//调用方法
function invoke(act, param, callback) {
	ddsy.core.invoke("<%=path%>/live/" + act + ".html", param, callback);
}

//获取选择ids
function getSelectIds() {
	var ids = "";
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length > 0) {
		for (var i = 0; i < rows.length; i++) {
			if(rows[i].status == '1'){
				ids += rows[i].id + ",";
			}
		}
	}
	return ids;
}

//显示编辑
function openEdit(id,status) {
	invoke("enable", {id:id,status:status}, function(result) {
		if(result.msg == ""){
			$.messager.alert('提示','操作成功','info', function(){
				//$("#divAddEdit").dialog("close");
				reloadData();
      	    });
		}else{
			ddsy.msg.info(result.msg);
		}
	});
}
function enable() {
	var ids = getSelectIds();
	if (ids.length > 0) {
		ids = ids.substring(0,ids.length - 1);
		ddsy.msg.confirm("确认修改当前数据吗？", function(ok){  
			if (ok) {	
				invoke("enable", {id:ids}, function(result) {
					if(result.msg == ""){
						$.messager.alert('提示','操作成功','info', function(){
							$("#divAddEdit").dialog("close");
							reloadData();
			      	    });
					}else{
						ddsy.msg.info(result.msg);
					}
				});
			}
		});
	} else {
		ddsy.msg.alert("请选择有效数据");
	}
}
//根据id获取记录
function getRowById(id) {
	var rows = $("#grid").datagrid("getRows");
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].id == id) {
			return rows[i];
		}
	}
	return null;
}
</script>
<%@ include  file="/WEB-INF/views/common/afoot.jsp"%>