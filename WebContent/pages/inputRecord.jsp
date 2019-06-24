<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入库记录</title>

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
	function openAddGoodsInformationDialog() {
		$("#dlg").dialog("open").dialog("setTitle","添加入库信息");
		url="${pageContext.request.contextPath}/stockIn/addStockIn.do";
	}
	
	//打开修改对话框
	function openUpdateGoodsInformationDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if(selectedRows.length != 1) {
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var editRow = selectedRows[0];
		var forward = editRow.storageNo;
		$("#dlg").dialog("open").dialog("setTitle","修改入库信息");
		$("#goodsInformationForm").form("load",editRow);
		url="${pageContext.request.contextPath}/stockIn/updateStockIn.do?stockInNo=" + editRow.stockInNo + "&forward=" + forward;


	}
	
	//删除
	function deleteGoodsInformation(){
		var selectedRows=$("#dg").datagrid("getSelections");
		 if(selectedRows.length==0){
			 $.messager.alert("系统提示","请选择要删除的数据！");
			 return;
		 }
		 var strIds=[];
		 for(var i=0;i<selectedRows.length;i++){
			 strIds.push(selectedRows[i].stockInNo);
		 }
		 var ids=strIds.join(",");
		 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/stockIn/deleteByStockInNo.do",{ids:ids},function(result){
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
	
	//关闭按钮
	function closeGoodsInformationDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
	
	//重置按钮
	function resetValue() {
		$("#storageNo").val("");
		$("#goodsNo").val("");
		$("#siQuantity").val("");
		$("#siDate").val("");
		$("#handler").val("");
		$("#siRemark").val("");
	}
	
	//搜索按钮
	function searchGoodsInformation() {
		$("#dg").datagrid('load',{
			"handler":$("#goodsNameText").val()
		});
	}
	
	//保存按钮
	function saveGoodsInformation() {
		$("#goodsInformationForm").form("submit",{
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
</script>

</head>
<body style="margin: 1px">

	<table id="dg" title="入库记录" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true"
		url="${pageContext.request.contextPath}/stockIn/stockInList.do"
		fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="stockInNo" width="150" align="center">入库单号</th>
				<th field="storageNo" width="150" align="center">仓库编号</th>
				<th field="goodsNo" width="150" align="center">商品编号</th>
				<th field="siQuantity" width="150" align="center">入库数量</th>
				<th field="siDate" width="150" align="center">入库日期</th>
				<th field="handler" width="150" align="center">经手人</th>
				<th field="siRemark" width="200" align="center">备注</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<div>
			<a href="javascript:openAddGoodsInformationDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openUpdateGoodsInformationDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteGoodsInformation()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			
		</div>
		<div>
			&nbsp;经手人：
			<input type="text" id="goodsNameText" size="20" onkeydown="if(event.keyCode == 13) searchGoodsInformation()" />
			<a href="javascript:searchGoodsInformation()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:600px;height:320px;padding:10px 20px" closed="true" buttons="#dlg_buttons">
		<form id="goodsInformationForm" method="post">
			<table cellspacing="10px">
				<tr>
					<td>仓库编号：</td>
					<td>
						<input type="text" id="storageNo" name="storageNo" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
					<td>商品编号：</td>
					<td>
						<input type="text" id="goodsNo" name="goodsNo" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td>入库数量：</td>
					<td>
						<input type="text" id="siQuantity" name="siQuantity" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
					<td>入库日期：</td>
					<td>
						<!-- <input type="text" id="siDate" name="siDate" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
						 -->
						 <input class="easyui-datetimebox" id="siDate" name="siDate"
    			data-options="required:true,showSeconds:true"  style="width:150px">
					</td>
				</tr>
				
				<tr>
					<td>经手人：</td>
					<td>
						<input type="text" id="handler" name="handler" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font>
					</td>
				</tr>
	
				<tr>
					<td>&nbsp;备注：</td>
					<td colspan="3">
						<textarea type="text" id="siRemark" name="siRemark" rows="5" cols="46"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg_buttons">
		<a href="javascript:saveGoodsInformation()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-reset">重置</a>
		<a href="javascript:closeGoodsInformationDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
</body>
</html>