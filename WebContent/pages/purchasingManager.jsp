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
	
	//打开添加对话框
	function openAddPurchasingInformationDialog() {
		$("#dlg").dialog("open").dialog("setTitle","添加采购信息");
		url="${pageContext.request.contextPath}/purchasing/addPurchasing.do";
	}
	
	//打开修改对话框
	function openUpdatePurchasingInformationDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length != 1) {
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var editRow = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","修改采购信息");
		$("#purchasingInformationForm").form("load",editRow);
		url="${pageContext.request.contextPath}/purchasing/updatePurchasing.do?pNo=" + editRow.pNo;
	}
	
	//关闭按钮
	function closePurchasingInformationDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
	
	//重置按钮
	function resetValue() {
		$("#goodsNo").combobox("setText","");
		$("#quantityIn").val("");
		$("#priceIn").val("");
		$("#dateIn").val("");
		$("#pRemark").val("");
	}
	
	//搜索按钮
	function searchPurchasingInformation() {
		$("#dg").datagrid('load',{
			"goodsName":$("#goodsNameText").val()
		});
	}
	
	//保存按钮
	function savePurchasingInformation() {
		$("#purchasingInformationForm").form("submit",{
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
	 function deletePurchasingInformation(){
		 var selectedRows=$("#dg").datagrid("getSelections");
		 if(selectedRows.length==0){
			 $.messager.alert("系统提示","请选择要删除的数据！");
			 return;
		 }
		 var strIds=[];
		 for(var i=0;i<selectedRows.length;i++){
			 strIds.push(selectedRows[i].pNo);
		 }
		 var ids=strIds.join(",");
		 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/purchasing/deletePurchasing.do",{ids:ids},function(result){
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
<title>进货管理</title>
</head>
<body style="margin:1px">
	
	<table id="dg" title="进货管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
			url="${pageContext.request.contextPath}/purchasing/purchasingList.do" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="pNo" width="150" align="center">采购进货单号</th>
				<th field="goodsNo" width="150" align="center" hidden="true">商品编号</th>
				<th field="goodsName" width="150" align="center">商品名称</th>
				<th field="storageNo" width="150" align="center" hidden="true">仓库编号</th>
				<th field="storageName" width="150" align="center">进货仓库</th>
				<th field="quantityIn" width="150" align="center">进货数量</th>
				<th field="priceIn" width="200" align="center">进货单价</th>
				<th field="dateIn" width="200" align="center">进货日期</th>
				<th field="handler" width="150" align="center">经手人</th>
				<th field="pRemark" width="200" align="center">备注</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openAddPurchasingInformationDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openUpdatePurchasingInformationDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deletePurchasingInformation()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;商品名称：
			<input type="text" id="goodsNameText" size="20" onkeydown="if(event.keyCode == 13) searchPurchasingInformation()" />
			<a href="javascript:searchPurchasingInformation()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:600px;height:350px;padding:10px 20px" closed="true" buttons="#dlg_buttons">
		<form id="purchasingInformationForm" method="post">
			<table cellspacing="15px">
				<tr>
					<td>商品名称：</td>
					<td>
						<input type="text" id="goodsNo" name="goodsNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'goodsNo',textField:'goodsName',
						url:'${pageContext.request.contextPath}/goods/goodsNameList.do'" />&nbsp;<font color="red">*</font>
					</td>
					<td>进货数量：</td>
					<td>
						<input type="text" id="quantityIn" name="quantityIn" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>进货单价：</td>
					<td>
						<input type="text" id="priceIn" name="priceIn" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
					<td>进货日期：</td>
					<td>
						<input class="easyui-datetimebox" id="dateIn" name="dateIn" data-options="required:true,showSeconds:true"
						 style="width: 145px" />&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>经手人：</td>
					<td>
						<input type="text" id="handler" name="handler" class="easyui-validatebox" required="true" value="${currentUser.userName}" readonly="readonly" />&nbsp;<font color="red">*</font>
					</td>
					<td>进货仓库：</td>
					<td>
						<input type="text" id="storageNo" name="storageNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'storageNo',textField:'storageName',
						url:'${pageContext.request.contextPath}/storage/nameList.do'" />&nbsp;<font color="red">*</font>
					</td>					
				</tr>
				<tr>				
					<td>备注：</td>
					<td colspan="3">
						<textarea type="text" id="pRemark" name="pRemark" rows="5" cols="46"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg_buttons">
		<a href="javascript:savePurchasingInformation()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-reset">重置</a>
		<a href="javascript:closePurchasingInformationDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>