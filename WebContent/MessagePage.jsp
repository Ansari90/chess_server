<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>MessagePage</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
function submitform() {
	document.loginRedirect.submit();
}
</script>
</head>
<body>
<h1>${requestScope.messageHeading}</h1><br/>
${requestScope.messageBody}<br/>
<form name="loginRedirect" action="${pageContext.request.contextPath}/LoginPage.jsp" method="get">
Click <a href="javascript:submitform()">here</a> to go to the login page.
</form>
</body>
</html>