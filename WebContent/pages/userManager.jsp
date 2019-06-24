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

	//设置指派时间
	$(function() {
		$("#userDate").val(getCurrentDateTime());
	});
	
	//搜索用户信息
	function searchUser() {
		$("#dg").datagrid('load',{
			"userName":$("#userText").val()
		});
	}
	
	//打开添加用户对话框
	function openUserAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle","添加用户信息");
		$("#userDate").val(getCurrentDateTime());
		url = "${pageContext.request.contextPath}/user/saveUser.do";
	}
	
	//打开修改用户对话框
	function openUserUpdateDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		//一次只能编辑一条数据
		if(selectedRows.length != 1) {
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var editRow = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","修改用户信息");
		$("#userForm").form("load",editRow);
		url="${pageContext.request.contextPath}/user/saveUser.do?userNo=" + editRow.userNo;
	}
	
	//保存操作
	function saveUser() {
		$("#userForm").form("submit",{
			url:url,
			onSubmit:function() {
				return $(this).form("validate");
			},
			success:function(result) {
				var result = eval('(' +result + ')');	//eval()函数用于将后台传到前台的JSON字符串转化为JSON对象才能使用
				if(result.success) {
					$.messager.alert("系统提示","保存成功！");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else {
					$.messager.alert("系统提示","保存失败！");
					return;
				}
			}
		});
	}
	
	//重置
	function resetValue() {
		$("#userName").val("");
		$("#password").val("");
		$("#nickName").val("");
		$("#userEmail").val("");
		$("#userPhone").val("");
	}
	
	//关闭操作
	function closeDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
	
	//删除用户
	function deleteUser() {
		var selectedRows = $("#dg").datagrid("getSelections");		//获取选择的数据
		if(selectedRows.length == 0) {
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		
		var selectedIds = [];
		for(var i = 0;i < selectedRows.length;i++) {
			selectedIds.push(selectedRows[i].userNo);
		}
		
		var ids = selectedIds.join(",");
		$.messager.confirm("系统提示","您确定要删除这<font color=red>" + selectedRows.length + "</font>条数据吗？",function(r) {
			if(r) {
				$.post("${pageContext.request.contextPath}/user/deleteUser.do",{ids:ids},function(result) {
					if(result.success) {
						$.messager.alert("系统提示","删除成功！");
						$("#dg").datagrid("reload");
					}else {
						$.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
					}
				},"json");
			}
		});
	}
</script>
<title>用户管理</title>
</head>
<body style="margin: 1px">
<!-- fitColumns属性设置为true，可以自动扩展或收缩列的大小以适应网格宽度和防止水平滚动条
     pagination属性用于添加分页组件
     rownumbers属性用于定义是否显示列号
     fit属性用于设置是否可以让表格自动缩放以适应父容器
     toolbar属性用于添加工具条
 -->
<table id="dg" class="easyui-datagrid" title="用户管理" fitColumns="true"
       pagination="true" rownumbers="true" fit="true" toolbar="#tb" url="${pageContext.request.contextPath}/user/userList.do">
    <thead>
        <tr>
            <th field="cb" checkbox="true" align="center"></th>
            <th field="userNo" width="50" align="center">编号</th>
            <th field="userName" width="50" align="center">用户名</th>
            <th field="password" width="50" align="center">密码</th>
            <th field="nickName" width="50" align="center">昵称</th>
            <th field="userPhone" width="50" align="center">联系电话</th>
            <th field="userEmail" width="150" align="center">电子邮箱</th>
            <th field="userDate" width="150" align="center">注册日期</th>
        </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <!-- plain属性设置为true时表示显示普通效果 -->
        <a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openUserUpdateDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        <!-- onkeydown事件表达当键盘某个按键被按下时所执行的操作
             event.keyCode==13表示当按下回车键时
        -->
        &nbsp;用户名：&nbsp;<input type="text" id="userText" size="20" onkeydown="if(event.keyCode==13) searchUser()" />
        <a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 620px;height: 230px;padding: 10px 20px" closed="true" buttons="#dlg_buttons">
	<form id="userForm" method="post">
		<table cellspacing="8px">
			<tr>
				<td>用户名：</td>
				<td>
					<!-- easyui-validatebox表示文本框为验证框
						 required属性是配合验证框所使用的
						 值为true时表示必需的，值为false时表示非必需的
					 -->
					<input type="text" id="userName" name="userName" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>密码：</td>
				<td>
					<input type="password" id="password" name="password" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td>昵称：</td>
				<td>
					<input type="text" id="nickName" name="nickName" class="easyui-validatebox"/>
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>电子邮箱：</td>
				<td>
					<!-- validType="email"表示验证方式为邮箱验证 -->
					<input type="text" id="userEmail" name="userEmail" class="easyui-validatebox" validType="email" required="true"/>&nbsp;<font color="red">*</font>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td>联系电话：</td>
				<td>
					<input type="text" id="userPhone" name="userPhone" class="easyui-validatebox"/>
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>注册日期：</td>
				<td>
					<input type="text" id="userDate" name="userDate" class="easyui-validatebox" readonly="readonly"/>
				</td>
			</tr>
		</table>
	</form>
</div>

<div id="dlg_buttons">
	<a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-reset">重置</a>
	<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>