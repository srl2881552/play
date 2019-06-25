<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include  file="/WEB-INF/views/common/ahead.jsp"%>
<div style="margin: 10px;">
	<div class="easyui-panel" style="height: 270px; padding: 10px;" data-options="collapsible:true" title="用户管理">
		<form id="formQuery">
			<table cellpadding="4">
				<tr>
					<td>用户昵称：</td>
					<td width="200"><input class="easyui-textbox" type="text" data-options="width:160" id="nick_name"/></td>
					
				</tr>
				
				<tr>
					<td>消费时间：</td>
					<td><input class="easyui-datebox" data-options="editable:false,width:160" id="startTime"/></td>
					<td>到</td>
					<td><input class="easyui-datebox" data-options="editable:false,width:160" id="endTime"/></td>
					<td><a href="javascript:reloadData()" class="easyui-linkbutton" iconCls="icon-search" >查询</a></td>
				</tr>
				
				<tr>
					
				</tr>
			</table>
		</form>
	</div>
	</div>
	<div class="easyui-panel" style="width: 100%;height: 100% " data-options="collapsible:true" title="用户管理">
		<div id="search">
		    <a href="javascript:openAdd()" class="easyui-splitbutton" iconCls="icon-add">添加</a>
		    <!-- <a href="javascript:enable()" class="easyui-linkbutton" iconCls="icon-no">无效</a> -->
		</div>
		<table class="easyui-datagrid" id="grid" data-options="pagination:true,singleSelect:false,toolbar:'#bar',loader:loadData">
			<thead>
				<tr>
					<th data-options="field:'id'">编码</th>
					<th data-options="field:'nick_name'">标题</th>
					<th data-options="field:'mobile'">消费时间</th>
					<th data-options="field:'email'">总金额</th>
					<th data-options="field:'status'">消费者</th>
					<th data-options="field:'create_at'">消费说明</th>
					<th data-options="field:'address'">类别</th>
					<th data-options="field:'province'">类别说明</th>
					<th data-options="field:'city'">订单编号</th>
					<th data-options="field:'id_card'">消费者</th>
					<th data-options="field:'rank'">消费说明</th>
					<th data-options="field:'birthday'">类别</th>
					<th data-options="field:'currency_total'">类别说明</th>
					<th data-options="field:'egg_total'">订单编号</th>
					<th data-options="field:'user_type'">类别</th>
					
					<th data-options="field:'_operate',width:160,align:'center',formatter:formatOperate">操作</th>
				</tr>
			</thead>
		</table>
	</div>
<div id="showDetil" class="easyui-dialog" title="消费明细"
	data-options="
			width:1000,height:480,
        closed: true,
        modal:true,
        buttons: [
        	{
                text:'取消',
                iconCls:'icon-cancel',
                handler:function(){
                	$('#showDetil').dialog('close');
                }
            }
		]">
	<form id=formDetil method="post">
    	<input type="hidden" name="pay_id" id="pay_id"/>
	<table class="easyui-datagrid" id="gridDetail" data-options="pagination:true,singleSelect:false,toolbar:'#bar',loader:loadDetilData">
			<thead>
				<tr>
					<th data-options="field:'id'">编码</th>
					<th data-options="field:'pay_id'">消费单号</th>
					<th data-options="field:'product_name'">商品名称</th>
					<th data-options="field:'qty'">数量</th>
					<th data-options="field:'price',formatter:formatDouble">单价</th>
				</tr>
			</thead>
		</table>
		</form>
</div>
<div id="divAddEdit" class="easyui-dialog" title="添加消费记录"
	data-options="
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
	<form id=formAddEdit method="post">
    	<input type="hidden" name="id" id="id"/>
		<table>
			
			
			<tr>
				<td><font color="red">* </font>标题:</td>
				<td><input class="easyui-textbox" data-options="required:true" name="title" id="title"/></td>
				
			</tr>
			<tr>
				<td><font color="red">* </font>消费时间:</td>
				<td><input class="easyui-datetimebox" data-options="required:true,editable:false" name="pay_at" id="pay_at"/></td>
			</tr>
			
			<tr>
				<td><font color="red">* </font>消费说明:</td>
				<td><input class="easyui-textbox" data-options="multiline:true" style="height:200px" name="comment" id="comment"/></td>
			</tr>
			<tr>
				<td><font color="red">* </font>类别:</td>
				<td><select id="type" name="type" class="easyui-combobox" data-options="required:true,panelHeight:'auto',editable:false" style="width: 135px;">
						<c:forEach items="${types }" var="t">
							<option value="${t.code }">${t.value }</option>
						</c:forEach>
						</select></td>
			</tr>
		</table>
    </form>
</div>
<div id="divAddDetil" class="easyui-dialog" title="添加消费记录"
	data-options="
        closed: true,
        modal:true,
        buttons: [
        	{
	            text:'确定',
	            iconCls:'icon-ok',
	            handler:function(){
	            	onDetilOk();
            	}
            },{
                text:'取消',
                iconCls:'icon-cancel',
                handler:function(){
                	$('divAddDetil').dialog('close');
                }
            }
		]">
	<form id=formAddDetil method="post">
    	<input type="hidden" name="id" id="id"/>
		<table>
			
			
			<tr>
				<td><font color="red">* </font>商品名称:</td>
				<td><input class="easyui-textbox" data-options="required:true" name="product_name" id="product_name"/></td>
				
			</tr>
			<tr>
				<td><font color="red">* </font>数量:</td>
				<td><input class="easyui-textbox" data-options="required:true" name="qty" id="qty"/></td>
				
			</tr>
			<tr>
				<td><font color="red">* </font>单价:</td>
				<td><input class="easyui-textbox" data-options="required:true" name="price" id="price"/></td>
				
			</tr>
			
			<tr>
				<td><font color="red">* </font>消费单号:</td>
				<td><input class="easyui-textbox" data-options="required:true" name="pay_id" id="pay_id"/></td>
			</tr>
		</table>
    </form>
</div>
<script type="text/javascript">
function loadDetilData(param,success,error) {
	var paramData = getDetailQueryData();
	if(paramData.pay_id){
		Detilinvoke("queryDetil", $.extend(paramData, param), function(result) {
			success(result);
		});
	}else{
		success({total:0,rows:[]});
	}
}
function reloadDetailData() {
	$("#gridDetail").datagrid('reload'); 
}
function getDetailQueryData(row) {
	var form = $("#formDetil");
	var data = {};
	data.pay_id = form.find("#pay_id").val();
	return data;
}
function getQueryData() {
	var form = $("#formQuery");
	var data = {};
	data.nick_name=form.find("#nick_name").val();
	
	data.startTime=form.find("#startTime").datebox('getValue');
	data.endTime=form.find("#endTime").datebox('getValue');
	return data;
}
// 加载市下拉框


//添加弹出
function openAdd() {
	setAddData();
	$("#divAddEdit").dialog("open");
}
function setAddData()
{
	var form = $("#formAddEdit");
	form.find("#title").textbox("setValue","");
	form.find("#id").val("");
	form.find("#pay_at").datetimebox("setValue","");
	form.find("#comment").textbox("setValue","");
}
function openAddDetail(id) {
	//setAddData();
	var row=getRowById(id);
	var form=$("#formAddDetil");
	form.find("#pay_id").textbox("setValue",row.id);
	$("#divAddDetil").dialog("open");
}
function formatOperate(value, row) {
	var oper = "<a href='javascript:openDetail(" + row.id + ")' class='easyui-linkbutton' data-option=''>详情</a> | ";
		oper+="<a href='javascript:openEdit(" + row.id + ")' class='easyui-linkbutton'>编辑</a> | ";
		oper+="<a href='javascript:openDelete(" + row.id + ")' class='easyui-linkbutton'>删除</a> |";
		oper+="<a href='javascript:openAddDetail(" + row.id + ")' class='easyui-linkbutton'>添加明细</a> ";
		return oper;
}
function openDetail(id)
{
	$("#showDetil").dialog("open");
	var row=getRowById(id);
	var form = $("#formDetil");
	form.find("#pay_id").val(row.id);
	reloadDetailData();
}
function formatDouble(value, row) {
	var oper = "<span>"+value.toFixed(2)+"</span>";
		return oper;
}
//ajax添加或者编辑
function onOk() {
	var form = $("#formAddEdit");
	
	if(form.find("#title").val() == ""){
		ddsy.msg.info("*为必填项,请填写完整");
		return false;
	}
	if (form.find('#pay_at').datetimebox("getValue")=="") {
		ddsy.msg.info("*为必填项,请填写完整");
		return false;
	}
	if (form.find('#comment').val() == "") {
		ddsy.msg.info("*为必填项,请填写完整");
		return false;
	}
	
	var method = "save";
	if(form.find("#id").val() != ""){
		method = "edit";
	}
	
	invoke(method, getEditFormData(), function(result) {
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
function onDetilOk() {
	var form = $("#formAddDetil");
	
	if(form.find("#product_name").val() == ""){
		ddsy.msg.info("*为必填项,请填写完整");
		return false;
	}
	if (form.find('#price').val()=="") {
		ddsy.msg.info("*为必填项,请填写完整");
		return false;
	}
	if (form.find('#qty').val() == "") {
		ddsy.msg.info("*为必填项,请填写完整");
		return false;
	}
	
	var method = "save";
	if(form.find("#id").val() != ""){
		method = "edit";
	}
	
	Detilinvoke(method, getDetilFormData(), function(result) {
		if(result.msg == ""){
			$.messager.alert('提示','操作成功','info', function(){
				$("#divAddDetil").dialog("close");
				reloadData();
      	    });
		}else{
			ddsy.msg.info(result.msg);
		}
	});
}
function getEditFormData() {
	var form = $("#formAddEdit");
	
	var data = {};
	data.title=form.find("#title").val();
	data.pay_at=form.find("#pay_at").datetimebox("getValue");
	//data.total_price=form.find("#total_price").val();
	data.comment=form.find("#comment").val();
	data.type=form.find("#type").combobox('getValue');
	data.id=form.find("#id").val();
	data.type_value=form.find("#type").combobox('getText');
	return data;
}
function getDetilFormData() {
	var form = $("#formAddDetil");
	
	var data = {};
	data.product_name=form.find("#product_name").val();
	data.qty=form.find("#qty").val();
	//data.total_price=form.find("#total_price").val();
	data.price=form.find("#price").val();
	data.pay_id=form.find("#pay_id").textbox('getValue');
	
	return data;
}
//无效
function enable() {
	var ids = getSelectIds();
	if (ids.length > 0) {
		ids = ids.substring(0,ids.length - 1);
		ddsy.msg.confirm("确认修改当前数据吗？", function(ok){  
			if (ok) {	
				invoke("enable", {ids:ids}, function(result) {
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
	  
//点击查询 刷新表格
function reloadData() {
	$("#grid").datagrid('reload'); 
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
	ddsy.core.invoke("<%=path%>/user/" + act + ".html", param, callback);
}
function Detilinvoke(act, param, callback) {
	ddsy.core.invoke("<%=path%>/payItem/" + act + ".html", param, callback);
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
function openEdit(id) {
	var row = getRowById(id);
	var form = $("#formAddEdit");
	form.find("#title").textbox("setValue",row.title);
	form.find("#id").val(row.id);
	form.find("#pay_at").datetimebox("setValue",row.pay_at);
	form.find("#comment").textbox("setValue",row.comment);
	$("#divAddEdit").dialog("open");
}
function openDelete(id) {
	var row = getRowById(id);

		ddsy.msg.confirm("确认修改当前数据吗？", function(ok){  
			if (ok) {	
				invoke("delete", {id:row.id}, function(result) {
					if(result.msg == ""){
						$.messager.alert('提示','操作成功','info', function(){
							reloadData();
			      	    });
					}else{
						ddsy.msg.info(result.msg);
					}
				});
			}
		});
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