<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HEAD id=Head1>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>登录——进销存管理系统</title>
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体
}

TD {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体
}
</STYLE>

<META content="MSHTML 6.00.6000.16809" name=GENERATOR>
</HEAD>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script>

	
	function resetValue() {
		$('#userName').val("");
		$('#password').val("");
		$("#cw").text("");
	}
	
	function checkLogin() {
		var username = $('#userName').val();
		var password = $('#password').val();
		if(username == null || username == "") {
			$("#cw").text("请输入用户名！");
			return;
		} else if(password == null || password == "") {
			$("#cw").text("请输入密码！");
			return;
		} else {
			$('#condition').submit();
		}
	}
</script>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
	<TBODY>
		<TR>
			<TD style="HEIGHT: 105px"><IMG
				src="${pageContext.request.contextPath}/images/login_1.gif" border=0></TD>
		</TR>
		<TR>
			<TD background=${pageContext.request.contextPath}/images/login_2.jpg
				height=300>
				<TABLE height=300 cellPadding=0 width=900 border=0>
					<TBODY>
						<TR>
							<TD colSpan=2 height=35></TD>
						</TR>
						<TR>
							<TD width=360></TD>
							<TD>
								<form id="condition" name="condition" method="post" action="${pageContext.request.contextPath}/user/login.do">
									<TABLE cellSpacing=0 cellPadding=2 border=0>
										<TBODY>
											<TR>
												<TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
												<TD style="HEIGHT: 28px" width=150><INPUT id="userName"
													style="WIDTH: 130px" name="userName"
													value="${user.userName}"></TD>
											</TR>
											<TR>
												<TD style="HEIGHT: 28px">登录密码：</TD>
												<TD style="HEIGHT: 28px"><INPUT id=password
													style="WIDTH: 130px" type=password name="password"
													value="${user.password}"></TD>
											</TR>
											<TR>
												<TD style="HEIGHT: 18px"></TD>
												<TD style="HEIGHT: 18px"></TD>
												<TD style="HEIGHT: 18px"></TD>
											</TR>
											<TR>
												<TD align="left"><image id=btn
														style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
														src="${pageContext.request.contextPath}/images/login_button.gif"
														onclick="javascript:checkLogin();return false;">
													<td align="center"><image
															style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
															src="${pageContext.request.contextPath}/images/reset.jpg"
															onclick="resetValue();return false;"></td>
													<TD style="HEIGHT: 28px">&nbsp;</TD>
											</TR>
											<tr height="10">
												<td width="40%" align="center" colspan="2"><font
													color="red" size="5" id="cw" name="cw"></font></td>
											</tr>
											<tr>
												<td></td>
											</tr>
											</TD>
											</TR>
										</TBODY>
									</TABLE>
								</form>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
			</TD>
		</TR>
		<TR>
			<TD><IMG
				src="${pageContext.request.contextPath}/images/login_3.jpg" border=0></TD>
		</TR>
	</TBODY>
</TABLE>
</DIV>
</BODY>
</HTML>
<script type="text/javascript">
	if('${errorMsg}' != '') {
		$("#cw").text('${errorMsg}');
	}
</script>
