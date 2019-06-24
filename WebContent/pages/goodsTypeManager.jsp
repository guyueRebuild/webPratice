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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons.js"></script>
<script type="text/javascript">
	
	var url;
	
	$(function() {
		$("#createTime").val(getCurrentDateTime);
	});
	
	//搜索按钮
	function searchGoodsType() {
		$("#dg").datagrid('load',{
			"type":$("#typeText").val()
		});
	}
	
	//打开添加对话框
	function openAddGoodsTypeDialog() {
		$("#dlg").dialog("open").dialog("setTitle","添加商品类型");
		url="${pageContext.request.contextPath}/goodsType/addType.do";
	}
	
	//打开修改对话框
	function openUpdateGoodsTypeDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length != 1) {
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var editRow = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","修改商品类型");
		$("#goodsTypeForm").form("load",editRow);
		url="${pageContext.request.contextPath}/goodsType/updateType.do?typeNo=" + editRow.typeNo;
	}
	
	//关闭按钮
	function closeGoodsTypeDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
	
	//重置按钮
	function resetValue() {
		$("#type").val("");
	}
	
	//保存按钮
	function saveGoodsType() {
		$("#goodsTypeForm").form("submit", {
			url:url,
			onSubmit:function() {
				return $(this).form("validate");
			},
			success:function(result) {
				var result = eval("(" + result + ")");
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
	
	//删除商品类别
	function deleteGoodsType() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length == 0) {
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var selectedIds = [];
		for(var i = 0;i < selectedRows.length;i++) {
			selectedIds.push(selectedRows[i].typeNo);
		}
		var ids = selectedIds.join(",");
		$.messager.confirm("系统提示","您确定要删除这<font color='red'>" + selectedRows.length + "</font>条数据吗？",function(r) {
			if(r) {
				$.post("${pageContext.request.contextPath}/goodsType/deleteType.do",{ids:ids},function(result) {
					if(result.success) {
						$.messager.alert("系统提示","删除成功！");
						$("#dg").datagrid("reload");
					} else {
						$.messager.alert("系统提示","数据删除失败，请联系管理员！");
					}
				},"json");
			}
		});
	}
</script>
<title>商品类别管理</title>
</head>
<body style="margin:1px">

	<table id="dg" title="商品类别管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
			url="${pageContext.request.contextPath}/goodsType/list.do" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="typeNo" width="150" align="center">商品类型编号</th>
				<th field="type" width="150" align="center">商品类型名称</th>
				<th field="createTime" width="150" align="center">创建时间</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openAddGoodsTypeDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openUpdateGoodsTypeDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteGoodsType()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;商品类型名称：
			<input type="text" id="typeText" size="20" onkeydown="if(event.keyCode == 13) searchGoodsType()" />
			<a href="javascript:searchGoodsType()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:360px;height:200px;padding:10px 20px" closed="true" buttons="#dlg_buttons">
		<form id="goodsTypeForm" method="post">
			<table cellspacing="15px">
				<tr>
					<td>类型名称：</td>
					<td>
						<input type="text" id="type" name="type" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>创建时间：</td>
					<td>
						<input type="text" id="createTime" name="createTime" class="easyui-validatebox" readonly="readonly" required="true" />&nbsp;<font color="red">*</font>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg_buttons">
		<a href="javascript:saveGoodsType()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-reset">重置</a>
		<a href="javascript:closeGoodsTypeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>