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
	function openAddPurchasing_ReturnInformationDialog() {
		$("#dlg").dialog("open").dialog("setTitle","添加采购信息");
		url="${pageContext.request.contextPath}/purchasing_Return/addPurchasing_Return.do";
	}
	
	//打开修改对话框
	function openUpdatePurchasing_ReturnInformationDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length != 1) {
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var editRow = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","修改采购退货信息");
		$("#purchasing_ReturnInformationForm").form("load",editRow);
		url="${pageContext.request.contextPath}/purchasing_Return/updatePurchasing_Return.do?prNo=" + editRow.prNo;
	}
	
	//关闭按钮
	function closePurchasing_ReturnInformationDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
	
	//重置按钮
	function resetValue() {
		$("#goodsNo").combobox("setText","");
		$("#prQuantity").val("");
		$("#prprice").val("");
		$("#prDate").val("");
		$("#prRemark").val("");
	}
	
	//搜索按钮
	function searchPurchasing_ReturnInformation() {
		$("#dg").datagrid('load',{
			"goodsName":$("#purchasing_ReturnNameText").val()
		});
	}
	
	//保存按钮
	function savePurchasing_ReturnInformation() {
		$("#purchasing_ReturnInformationForm").form("submit",{
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
	 function deletePurchasing_ReturnInformation(){
		 var selectedRows=$("#dg").datagrid("getSelections");
		 if(selectedRows.length==0){
			 $.messager.alert("系统提示","请选择要删除的数据！");
			 return;
		 }
		 var strIds=[];
		 for(var i=0;i<selectedRows.length;i++){
			 strIds.push(selectedRows[i].prNo);
		 }
		 var ids=strIds.join(",");
		 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/purchasing_Return/deletePurchasing_Return.do",{ids:ids},function(result){
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
<title>采购退货管理</title>
</head>
<body style="margin:1px">
	
	<table id="dg" title="采购退货管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
			url="${pageContext.request.contextPath}/purchasing_Return/purchasing_ReturnList.do" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="prNo" width="150" align="center">采购退货单号</th>
				<th field="goodsNo" width="150" align="center" hidden="ture">商品编号</th>
				<th field="goodsName" width="150" align="center">商品名称</th>
				<th field="storageNo" width="150" align="center" hidden="ture">仓库编号</th>
				<th field="storageName" width="150" align="center">仓库名称</th>
				<th field="prQuantity" width="150" align="center">退货数量</th>
				<th field="prPrice" width="200" align="center">退货单价</th>
				<th field="prDate" width="200" align="center">退货日期</th>
				<th field="handler" width="150" align="center">经手人</th>
				<th field="prRemark" width="200" align="center">备注</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openAddPurchasing_ReturnInformationDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openUpdatePurchasing_ReturnInformationDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deletePurchasing_ReturnInformation()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;商品名称：
			<input type="text" id="purchasing_ReturnNameText" size="20" onkeydown="if(event.keyCode == 13) searchPurchasing_ReturnInformation()" />
			<a href="javascript:searchPurchasing_ReturnInformation()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:600px;height:350px;padding:10px 20px" closed="true" buttons="#dlg_buttons">
		<form id="purchasing_ReturnInformationForm" method="post">
			<table cellspacing="15px">
				<tr>
					<td>商品编号：</td>
					<td>
						<input type="text" id="goodsNo" name="goodsNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'goodsNo',textField:'goodsName',
						url:'${pageContext.request.contextPath}/goods/goodsNameList.do'" />&nbsp;<font color="red">*</font>
					</td>
					<td>退货数量：</td>
					<td>
						<input type="text" id="prQuantity" name="prQuantity" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>退货单价：</td>
					<td>
						<input type="text" id="prPrice" name="prPrice" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
					<td>退货日期：</td>
					<td>
					<input class="easyui-datetimebox" id="prDate"
						name="prDate" data-options="required:true,showSeconds:true"
						 style="width: 145px">&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>经手人：</td>
					<td>
						<input type="text" id="handler" name="handler" class="easyui-validatebox" required="true" value="${currentUser.userName}" readonly="readonly" />&nbsp;<font color="red">*</font>
					</td>	
					<td>仓库名称：</td>
					<td>
						<input type="text" id="storageNo" name="storageNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'storageNo',textField:'storageName',
						url:'${pageContext.request.contextPath}/storage/nameList.do'" />&nbsp;<font color="red">*</font>
					</td>				
				</tr>
				<tr>				
					<td>备注：</td>
					<td colspan="3">
						<textarea type="text" id="prRemark" name="prRemark" rows="5" cols="46"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg_buttons">
		<a href="javascript:savePurchasing_ReturnInformation()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-reset">重置</a>
		<a href="javascript:closePurchasing_ReturnInformationDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>