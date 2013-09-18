<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset = "UTF-8">
	<link rel = "stylesheet" type = "text/css" href = "login.css">
	<title>Welcome</title>
</head>
<body>
<section class = "sec">
	<p1>Welcome</p1><br>

	<%= request.getAttribute("name") %><span>さん、ようこそ！！</span>

	<form method = "post" action = "http://localhost:8080/Login/edit_date">
		<input type = "text" name = "id" /><span>ID</span><br>
		<input type = "text" name = "name" /><span>名前</span><br>
		<input type = "text" name = "password" /><span>パスワード</span><br>

		<input type = "submit" value = "削除" />
		<input type = "submit" value = "追加" />
		<input type = "submit" value = "更新" />
	</form>
</section>
</body>
</html>
