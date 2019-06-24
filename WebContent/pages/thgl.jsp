<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	
	var url;
	
	//打开添加对话框
	function openAddSalesInformationDialog() {
		$("#dlg").dialog("open").dialog("setTitle","添加退货信息");
		url="${pageContext.request.contextPath}/salesReturn/addSalesReturn.do";
	}
	
	//打开修改对话框
	function openUpdateSalesInformationDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length != 1) {
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var editRow = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","修改退货信息");
		$("#salesReturnInformationForm").form("load",editRow);
		url="${pageContext.request.contextPath}/salesReturn/updateSalesReturn.do?sNo=" + editRow.sNo;
	}
	
	
	//关闭按钮
	function closeSalesInformationDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
	
	//重置按钮
	function resetValue() {
		$("#goodsNo").combobox("setText","");
		$("#srQuantity").val("");
		$("#srPrice").val("");
		$("#srDate").val("");
		$("#clientNo").combobox("setText","");
		$("#srRemark").val("");
	}
	
	//搜索按钮
	function searchSalesInformation() {
		$("#dg").datagrid('load',{
			"handler":$("#salesNameText").val()
		});
	}
	
	//保存按钮
	function saveSalesReturnInformation() {
		$("#salesReturnInformationForm").form("submit",{
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
	
	
	//删除商品信息
	function deleteSalesInformation() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length == 0) {
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var selectedIds = [];
		for(var i = 0;i < selectedRows.length;i++) {
			selectedIds.push(selectedRows[i].goodsNo);
		}
		var ids = selectedIds.join(",");
		$.messager.confirm("系统提示","您确定要删除这<font color='red'>" + selectedRows.length + "</font>条数据吗？",function(r) {
			if(r) {
				$.post("${pageContext.request.contextPath}/salesReturn/deleteSalesReturn.do",{ids:ids},function(result){
					if(result.success) {
						$.messager.alert("系统提示","删除成功！");
						$("#dg").datagrid("reload");
					} else {
						$.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
					}
				},"json");
			}
		});
	}
</script>


<title>销售退货管理</title>
</head>
<body style="margin:1px">
	
	<table id="dg" title="销售退货管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
			url="${pageContext.request.contextPath}/salesReturn/salesReturnList.do" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="sNo" width="150" align="center">销售退货单号</th>
				<th field="goodsNo" width="150" align="center" hidden="true">商品编号</th>
				<th field="goodsName" width="150" align="center">商品名称</th>
				<th field="storageNo" width="150" align="center" hidden="true">仓库编号</th>
				<th field="storageName" width="150" align="center">仓库名称</th>
				<th field="srQuantity" width="150" align="center">退货数量</th>
				<th field="srPrice" width="150" align="center">退货单价</th>
				<th field="srDate" width="250" align="center">退货日期</th>
				<th field="handler" width="150" align="center">经手人</th>
				<th field="clientNo" width="150" align="center" hidden="true">客户编号</th>
				<th field="client" width="150" align="center">客户名称</th>
				<th field="srRemark" width="200" align="center">备注</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openAddSalesInformationDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openUpdateSalesInformationDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteSalesInformation()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;经手人：
			<input type="text" id="salesNameText" size="20" onkeydown="if(event.keyCode == 13) searchSalesInformation()" />
			<a href="javascript:searchSalesInformation()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:620px;height:390px;padding:10px 20px" closed="true" buttons="#dlg_buttons">
		<form id="salesReturnInformationForm" method="post">
			<table cellspacing="15px">
				<tr>
					<td>商品名称：</td>
					<td>
						<input id="goodsNo" name="goodsNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'goodsNo',textField:'goodsName',
						url:'${pageContext.request.contextPath}/sales/goodsName.do'"/>&nbsp;<font color="red">*</font>
					</td>
					<td>退货数量：</td>
					<td>
						<input type="text" id="srQuantity" name="srQuantity" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>退货单价：</td>
					<td>
						<input type="text" id="srPrice" name="srPrice" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
					<td>退货日期：</td>
					<td>
						<input id="srDate" name="srDate" class="easyui-datetimebox" data-options="required:true,showSeconds:true"
						 style="width: 145px" />&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>经手人：</td>
					<td>
						<input type="text" id="handler" name="handler" class="easyui-validatebox" required="true" value="${currentUser.userName}" readonly="readonly" />&nbsp;<font color="red">*</font>
					</td>
					<td>客户名称：</td>
					<td>
						<input id="clientNo" name="clientNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'clientNo',textField:'client',
						url:'${pageContext.request.contextPath}/sales/client.do'"/>&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>仓库名称：</td>
					<td>
						<input type="text" id="storageNo" name="storageNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'storageNo',textField:'storageName',
						url:'${pageContext.request.contextPath}/storage/nameList.do'" />&nbsp;<font color="red">*</font>
					</td>	
				</tr>
				<tr>
					<td>&nbsp;备注：</td>
					<td colspan="3">
						<textarea type="text" id="srRemark" name="srRemark" rows="5" cols="46"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg_buttons">
		<a href="javascript:saveSalesReturnInformation()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-reset">重置</a>
		<a href="javascript:closeSalesInformationDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>