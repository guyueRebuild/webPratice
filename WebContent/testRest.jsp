<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" >
$(document).ready(function(){
	  $("button").click(function(){
	    $.get("/testRest/client.do",function(data,status){
	      alert("数据：" + data + "\n状态：" + status);
	    });
	  });
	});
</script>
</head>
<body>
<button>向页面发送 HTTP GET 请求，然后获得返回的结果</button>
</body>
</html>