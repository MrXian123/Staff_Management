<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
	</head>

	<body class="body">
		<div class="divon">
			<div class="text"><img src="<%=basePath%>image/logo.png" style="margin-top: -20px;float: left;" />员工管理系统</div>
			<div class="login">
				<div class="lleft">
					<div class="llleft">
						<img src="<%=basePath%>image/three.png" style="width: 100%; height: 100%;" />
					</div>
				</div>
				<form>
					<div class="lright">
						<input class="toptext" placeholder="用户名" id="name" name="name" type="text" style="width: 200px; height: 25px" />
						<input class="toptextone" placeholder="密码"  id="password"  name="password" type="password" style="width: 200px; height: 25px" />
						<div>
							<input class="button  button1" id="login" type="button" value="登录" onclick="commit()" style="width: 70px; height: 28px" /> 
							<input class="button1" id="cancel" type="button" value="取消" style="width: 70px; height: 28px" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script>
		function commit() {
			$.post("/GxaProject/admin_login", {
				"name": $("#name").val(),
				"password": $("#password").val()
			}, function(data) {
				if(data == "true"){
					window.location.href = "<%=basePath%>index.html";
				}else{
					alert("登录失败！用户名或密码错误。");
				}
			});
		}
	</script>

</html>