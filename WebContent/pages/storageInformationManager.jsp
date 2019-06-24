<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	
	var url;
	
	//打开添加对话框
	function openAddStorageInformationDialog() {
		$("#dlg").dialog("open").dialog("setTitle","添加仓库信息");
		url="${pageContext.request.contextPath}/storage/addStorage.do";
	}
	
	//打开修改对话框
	function openUpdateStorageInformationDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length != 1) {
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var editRow = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","修改仓库信息");
		$("#storageInformationForm").form("load",editRow);
		url="${pageContext.request.contextPath}/storage/updateStorage.do?storageNo=" + editRow.storageNo;
	}
	
	//关闭按钮
	function closeStorageInformationDialog() {
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
	function searchStorageInformation() {
		$("#dg").datagrid('load',{
			"client":$("#clientText").val()
		});
	}
	
	//保存按钮
	function saveStorageInformation() {
		$("#storageInformationForm").form("submit",{
			url:url,
			onSubmit:function() {
				return $(this).form("validate");
			},
			success:function(result) {
				var result = eval('(' + result + ')');
				if(result.success) {
					$.messager.alert("系统提示","保存成功！");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				} else {
					$.messager.alert("系统提示","保存失败！");
					return;
				}
			}
		});
	}
	//删除按钮
	 function deleteStorageInformation(){
		 var selectedRows=$("#dg").datagrid("getSelections");
		 if(selectedRows.length==0){
			 $.messager.alert("系统提示","请选择要删除的数据！");
			 return;
		 }
		 var strIds=[];
		 for(var i=0;i<selectedRows.length;i++){
			 strIds.push(selectedRows[i].storageNo);
		 }
		 var ids=strIds.join(",");
		 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/storage/deleteStorage.do",{ids:ids},function(result){
					if(result.success){
						 $.messager.alert("系统提示","数据已成功删除！");
						 $("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
					}
				},"json");
			} 
		 });
	 }
</script>
<title>仓库信息管理</title>
</head>
<body style="margin:1px">
	
	<table id="dg" title="仓库信息管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
			url="${pageContext.request.contextPath}/storage/storageList.do" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="storageNo" width="150" align="center">仓库编号</th>
				<th field="storageName" width="150" align="center">仓库名称</th>
				<th field="storageAddress" width="150" align="center">仓库地址</th>
				<th field="capacity" width="150" align="center">仓库容量</th>
				<th field="cordon" width="150" align="center">容量警戒线</th>
				<th field="currentStorage" width="150" align="center">当前容量</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openAddStorageInformationDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openUpdateStorageInformationDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteStorageInformation()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;仓库名称：
			<input type="text" id="storageNameText" size="20" onkeydown="if(event.keyCode == 13) searchStorageInformation()" />
			<a href="javascript:searchStorageInformation()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:600px;height:250px;padding:10px 20px" closed="true" buttons="#dlg_buttons">
		<form id="storageInformationForm" method="post">
			<table cellspacing="15px">
				<tr>
					<td>仓库名称：</td>
					<td>
						<input type="text" id="storageName" name="storageName" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
					<td>当前容量：</td>
					<td>
						<input type="text" id="currentStorage" name="currentStorage" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>仓库容量：</td>
					<td>
						<input type="text" id="capacity" name="capacity" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
					<td>容量警戒线：</td>
					<td>
						<input type="text" id="cordon" name="cordon" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>仓库地址：</td>
					<td colspan="3">
						<textarea type="text" id="storageAddress" name="storageAddress" rows="1" cols="48"></textarea>&nbsp;<font color="red">*</font>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg_buttons">
		<a href="javascript:saveStorageInformation()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-reset">重置</a>
		<a href="javascript:closeStorageInformationDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>