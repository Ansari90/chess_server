<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>LoginPage</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>AAP Enterprise Chess Server</h1><br/>
Please enter your username and password to be taken to your game:<br/>
<form name="theForm" action="${pageContext.request.contextPath}/LoginServlet" method="post">
<table>
<tr><td>Username::</td><td><input name="userName" type="text"></td></tr>
<tr><td>Password:</td><td><input name="password" type="text"></td><tr/>
</table>
<input type="submit" value="Sign In">
</form>
</body>
</html>