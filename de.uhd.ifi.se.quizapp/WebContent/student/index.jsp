<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Sch&uuml;ler/Innen Zugang</title>
<link rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<body>
	<nav>
		<ul>
			<li><a href="index.jsp?p=filterExercises">Aufgaben finden</a></li>
		</ul>
	</nav>
	<div id="wrapper">
	<%
		if (request.getParameter("p") != null) {
			String p = request.getParameter("p");
	%>
	<jsp:include page='<%=p + ".jsp"%>' />
	<%
		}
	%>
	</div>
</body>
</html>