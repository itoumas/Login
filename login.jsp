<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset = "UTF-8">
	<link rel = "stylesheet" type = "text/css" href = "login.css">
	<title>ログイン</title>
</head>
<body>
<section class = "sec">
	<div class = "text">ログイン</div><br>

	<form method = "post" action = "http://localhost:8080/Login/login_servlet">

		<input type = "text" name = "id" /><span>ID</span><br>
		<input type = "text" name = "password" /><span>Pass</span><br>
		<input type = "submit" value = "Login" />

	</form>


</section>
</body>
</html>