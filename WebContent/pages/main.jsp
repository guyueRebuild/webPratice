<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页——进销存管理系统</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var user={userName:'hjhv',password:'111'};	
	var userStr=JSON.stringify(user);
	$.ajax({
		url: 'http://localhost:8080/JXCMS2/testUser/login',
		type:'post',
		headers: {
	        Accept: "application/json; charset=utf-8"
	    },
	    contentType: "application/json;charset=UTF-8",
		data:userStr,
		dataType: 'json',
		success: function(res) {
				console.log(res)
			},
		error: function(err) {
			console.log(err)
			}
		
	})
	
		$.ajax({
		url: 'http://localhost:8080/JXCMS2/testUser/test',
		type:'get',
		dataType: 'json',
		headers: {
	        Accept: "application/json; charset=utf-8"
	    },
	    contentType: "application/json;charset=UTF-8",
		success: function(res) {
				console.log(res)
			},
		error: function(err) {
			console.log(err)
			}
		
	})
	function openTab(text,url,iconCls) {
		if($("#tabs").tabs("exists",text)) {
			$("#tabs").tabs("select",text);
		} else {
			var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/pages/" + url + "'></iframe>";
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content
			});
		}
	}
	
	function logout(){
		$.messager.confirm("系统提示","您确定要退出系统吗？",function(r){
			if(r){
				window.location.href='${pageContext.request.contextPath}/user/logout.do';
			} 
		 });
	}
</script>
</head>
<body class="easyui-layout">

	<!-- 顶部布局 -->
	<div region="north" style="height:85px;background-color:#E0ECFF">
		<table style="padding:5px" width="100%">
			<tr>
				<td width="50%">
					<img alt="logoicon" src="${pageContext.request.contextPath}/images/bglogoicon.png">
					<img alt="logo" src="${pageContext.request.contextPath}/images/LOGO.png">
				</td>
				<td valign="bottom" align="right" width="50%">
					<font size="3">&nbsp;&nbsp;<strong>欢迎：${currentUser.userName}</strong></font>
				</td>
			</tr>
		</table>
	</div>
	<!-- 中间布局 -->
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">		<!-- fit属性设置自适应 -->
			<div title="首页" data-options="iconCls:'icon-home'"  style="background-color:#E5F7F7">
				<div align="center" style="padding-top: 100px">
				<img alt="logo" src="${pageContext.request.contextPath}/images/bglogoicon.png">
					<font color="#29C1E8" size="10">欢迎使用进销存管理系统</font>
				</div>
			</div>
		</div>
	</div>
	<!-- 导航栏布局 -->
	<div region="west" style="width:200px" title="导航栏菜单" spilt="true">		<!-- spilt属性设置导航栏是否可收起 -->
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="资料信息管理" data-options="selected:true,iconCls:'icon-fwgd'" style="padding:10px">
				<a href="javascript:openTab('商品信息管理','goodsInfomationManager.jsp','icon-yxjhgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-yxjhgl'" style="width:150px">商品信息管理</a>
				<a href="javascript:openTab('商品类别管理','goodsTypeManager.jsp','icon-khkfjh')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khkfjh'" style="width:150px">商品类别管理</a>
				<a href="javascript:openTab('供应商信息管理','providerInformationManager.jsp','icon-sjzdlbgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-sjzdlbgl'" style="width:150px">供应商信息管理</a>
				<a href="javascript:openTab('仓库信息管理','storageInformationManager.jsp','icon-home')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-home'" style="width:150px">仓库信息管理</a>
				<a href="javascript:openTab('客户信息管理','clientInformationManager.jsp','icon-khxxgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khxxgl'" style="width:150px">客户信息管理</a>
			</div>
			<div title="库存管理" data-options="selected:true,iconCls:'icon-ckgl'" style="padding:10px">
				<a href="javascript:openTab('出库记录','outputRecord.jsp','icon-ckjl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ckjl'" style="width:150px">出库记录</a>
				<a href="javascript:openTab('入库记录','inputRecord.jsp','icon-rkjl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-rkjl'" style="width:150px">入库记录</a>
				<a href="javascript:openTab('库存查询','inventorySearch.jsp','icon-kccx')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-kccx'" style="width:150px">库存查询</a>
			</div>
			<div title="采购管理" data-options="selected:true,iconCls:'icon-kccx'" style="padding:10px">
				<a href="javascript:openTab('进货管理','purchasingManager.jsp','icon-jhgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-jhgl'" style="width:150px">进货管理</a>
				<a href="javascript:openTab('采购退货管理','purchasing_ReturnManager.jsp','icon-thgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-thgl'" style="width:150px">采购退货管理</a>
			</div>
			<div title="销售管理" data-options="selected:true,iconCls:'icon-xsgl'" style="padding:10px">
				<a href="javascript:openTab('销售管理','saleManager.jsp','icon-sale')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-sale'" style="width:150px">销售管理</a>
				<a href="javascript:openTab('销售退货管理','thgl.jsp','icon-thgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-thgl'" style="width:150px">销售退货管理</a>
			</div>
			<div title="系统管理" data-options="selected:true,iconCls:'icon-item'" style="padding:10px">
				<a href="javascript:openTab('用户管理','userManager.jsp','icon-khgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khgl'" style="width:150px">用户管理</a>
				<a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width:150px">安全退出</a>
			</div>
		</div>
	</div>
	<!-- 底部布局 -->
	<div region="south">
		<table style="color:blue" align="center">
			<tr>
				<td style="padding-left:60px">&copy;</td>
				<td>广东工业大学</td>
				<td style="padding-left:60px">东软Java EE实训</td>
				<td style="padding-left:60px">iTeam</td>
			</tr>
		</table>
	</div>
</body>
</html>