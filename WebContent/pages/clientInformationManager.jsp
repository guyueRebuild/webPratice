<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;

	//打开添加对话框
	function openAddClientInformationDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加客户信息");
		url = "${pageContext.request.contextPath}/client/addClient.do";
	}

	//打开修改对话框
	function openUpdateClientInformationDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var editRow = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "修改客户信息");
		$("#clientInformationForm").form("load", editRow);
		url = "${pageContext.request.contextPath}/client/updateClient.do?clientNo="
				+ editRow.clientNo;
	}

	//关闭按钮
	function closeClientInformationDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}

	//重置按钮
	function resetValue() {
		$("#client").val("");
		$("#cAddress").val("");
		$("#cEmail").val("");
		$("#cPhone").val("");
	}

	//搜索按钮
	function searchClientInformation() {
		$("#dg").datagrid('load', {
			"client" : $("#clientNameText").val()
		});
	}

	//保存按钮
	function saveClientInformation() {
		$("#clientInformationForm").form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$.messager.alert("系统提示", "保存成功！");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				} else {
					$.messager.alert("系统提示", "保存失败！");
					return;
				}
			}
		});
	}
	//删除按钮
	function deleteClientInformation() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var strIds = [];
		for (var i = 0; i < selectedRows.length; i++) {
			strIds.push(selectedRows[i].clientNo);
		}
		var ids = strIds.join(",");
		$.messager.confirm("系统提示","您确定要删除这<font color=red>" + selectedRows.length+ "</font>条数据吗？",function(r) {
			if (r) {
				$.post("${pageContext.request.contextPath}/client/deleteClient.do",{ids : ids},function(result) {
					if (result.success) {
						$.messager.alert("系统提示","数据已成功删除！");
						$("#dg").datagrid("reload");
						} else {
							$.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
						}
					}, "json");
				}
			});
	}
</script>
<title>客户信息管理</title>
</head>
<body style="margin: 1px">

	<table id="dg" title="客户信息管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
		url="${pageContext.request.contextPath}/client/clientList.do" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="clientNo" width="150" align="center">客户编号</th>
				<th field="client" width="150" align="center">客户名称</th>
				<th field="cAddress" width="150" align="center">联系地址</th>
				<th field="cEmail" width="150" align="center">联系邮箱</th>
				<th field="cPhone" width="150" align="center">联系电话</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<div>
			<a href="javascript:openAddClientInformationDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> 
			<a href="javascript:openUpdateClientInformationDialog()"class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
			<a href="javascript:deleteClientInformation()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>&nbsp;客户名称： 
			<input type="text" id="clientNameText" size="20" onkeydown="if(event.keyCode == 13) searchClientInformation()" />
			<a href="javascript:searchClientInformation()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog" style="width: 600px; height: 210px; padding: 10px 20px" closed="true" buttons="#dlg_buttons">
		<form id="clientInformationForm" method="post">
			<table cellspacing="15px">
				<tr>
					<td>客户名称：</td>
					<td><input type="text" id="client" name="client" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font></td>
					<td>联系地址：</td>
					<td><input type="text" id="cAddress" name="cAddress" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font></td>
				</tr>
				<tr></tr>
				<tr>
					<td>联系邮箱：</td>
					<td><input type="text" id="cEmail" name="cEmail" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font></td>
					<td>联系电话：</td>
					<td><input type="text" id="cPhone" name="cPhone" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg_buttons">
		<a href="javascript:saveClientInformation()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> 
		<a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-reset">重置</a> 
		<a href="javascript:closeClientInformationDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>