<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>ChessBoard</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
fromOrTo = "From";

function enterSelection(rank, file) {

	if(fromOrTo == "From") {
		document.getElementById("fromRank").value = rank;
		document.getElementById("fromFile").value = file;
	}
	else {
		document.getElementById("toRank").value = rank;
		document.getElementById("toFile").value = file;
	}

	document.getElementById("testPara").innerHTML = (fromOrTo + " " + rank + file);
	fromOrTo = (fromOrTo == "From")?"To":"From";
}
</script>
</head>
<body>
<h1>${sessionScope.gameMessage}</h1>
	<table border="1">
<tr><td>8</td><td>${sessionScope.s57}</td><td>${sessionScope.s58}</td><td>${sessionScope.s59}</td>
<td>${sessionScope.s60}</td><td>${sessionScope.s61}</td><td>${sessionScope.s62}</td>
<td>${sessionScope.s63}</td><td>${sessionScope.s64}</td></tr>
<tr><td>7</td><td>${sessionScope.s49}</td><td>${sessionScope.s50}</td><td>${sessionScope.s51}</td>
<td>${sessionScope.s52}</td><td>${sessionScope.s53}</td><td>${sessionScope.s54}</td>
<td>${sessionScope.s55}</td><td>${sessionScope.s56}</td></tr>
<tr><td>6</td><td>${sessionScope.s41}</td><td>${sessionScope.s42}</td><td>${sessionScope.s43}</td>
<td>${sessionScope.s44}</td><td>${sessionScope.s45}</td><td>${sessionScope.s46}</td>
<td>${sessionScope.s47}</td><td>${sessionScope.s48}</td></tr>
<tr><td>5</td><td>${sessionScope.s33}</td><td>${sessionScope.s34}</td><td>${sessionScope.s35}</td>
<td>${sessionScope.s36}</td><td>${sessionScope.s37}</td><td>${sessionScope.s38}</td>
<td>${sessionScope.s39}</td><td>${sessionScope.s40}</td></tr>
<tr><td>4</td><td>${sessionScope.s25}</td><td>${sessionScope.s26}</td><td>${sessionScope.s27}</td>
<td>${sessionScope.s28}</td><td>${sessionScope.s29}</td><td>${sessionScope.s30}</td>
<td>${sessionScope.s31}</td><td>${sessionScope.s32}</td></tr>
<tr><td>3</td><td>${sessionScope.s17}</td><td>${sessionScope.s18}</td><td>${sessionScope.s19}</td>
<td>${sessionScope.s20}</td><td>${sessionScope.s21}</td><td>${sessionScope.s22}</td>
<td>${sessionScope.s23}</td><td>${sessionScope.s24}</td></tr>
<tr><td>2</td><td>${sessionScope.s9}</td><td>${sessionScope.s10}</td><td>${sessionScope.s11}</td>
<td>${sessionScope.s12}</td><td>${sessionScope.s13}</td><td>${sessionScope.s14}</td>
<td>${sessionScope.s15}</td><td>${sessionScope.s16}</td></tr>
<tr><td>1</td><td>${sessionScope.s1}</td><td>${sessionScope.s2}</td><td>${sessionScope.s3}</td>
<td>${sessionScope.s4}</td><td>${sessionScope.s5}</td><td>${sessionScope.s6}</td>
<td>${sessionScope.s7}</td><td>${sessionScope.s8}</td></tr>
<tr><td>*</td><td>a</td><td>b</td><td>c</td><td>d</td><td>e</td><td>f</td><td>g</td><td>h</td></tr>
</table>
<form name="refresh" action="${pageContext.request.contextPath}/ChessServlet" method="get"><input type="submit" value="Refresh"></form>
<p id="testPara"></p>
<br/><h3>${requestScope.moveMessage}</h3>
<form name="nextMove" action="${pageContext.request.contextPath}/ChessServlet" method="post">
<table>
<tr><td></td><td>Rank</td><td>File</td></tr>
<tr><td>From:</td><td><input id="fromRank" name="fromRank" type="text"></td><td><input id="fromFile" name="fromFile" type="text"></td></tr>
<tr><td>To:</td><td><input id="toRank" name="toRank" type="text"></td><td><input id="toFile" name="toFile" type="text"></td><tr/>
</table>
<input type="submit" value="Make Move">
</form>
<form name="gameEnd" action="${pageContext.request.contextPath}/EndGameServlet" method="post">
To end game and view moves made: <input type="submit" value="End Game">
</form>
<form name="previousMatches" action="${pageContext.request.contextPath}/EndGameServlet" method="get">
To view previous matches: <input type="submit" value="Previous Matches">
</form>
</body>
</html>