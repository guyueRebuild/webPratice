<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
	//搜索按钮
	function searchGoodsInformation() {
		$("#dg").datagrid('load', {
			"storageNo" : $("#goodsNameText").val()
		});
	}
</script>

<title>仓库查询</title>
</head>
<body style="margin: 1px">
	<table id="dg" title="仓库查询" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true"
		url="${pageContext.request.contextPath}/inventorySearch/inventorySearchList.do"
		fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				
				<th field="goodsName" width="150" align="center">商品名称</th>
				<th field="quantity" width="150" align="center">商品数量</th>
			</tr>
		</thead>

		<div id="tb">
			<div>
				&nbsp;仓库编号： <input type="text" id="goodsNameText" size="20"
					onkeydown="if(event.keyCode == 13) searchGoodsInformation()" /> <a
					href="javascript:searchGoodsInformation()"
					class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
			</div>
		</div>

	</table>
</body>
</html>