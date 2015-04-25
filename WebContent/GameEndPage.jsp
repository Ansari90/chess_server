<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>End Game Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h2>Move History</h2><br/>
<table><c:forEach items="${requestScope.moves}" var="theMove">
<tr><td>${theMove}</td></tr></c:forEach></table><br/>
<form name="oneMoreGame" action="${pageContext.request.contextPath}/LoginPage.jsp" method="post">
To begin another game, log back in: <input type="submit" value="Restart">
</form>
</body>
</html>