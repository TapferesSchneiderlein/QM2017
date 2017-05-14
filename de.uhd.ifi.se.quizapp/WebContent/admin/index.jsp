<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name=viewport content="width=device-width, initial-scale=1" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/main.css">
<title>Administrator/Innen Zugang</title>
<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script>
	function inittinymce() {
		tinymce.init({
			selector : 'textarea',
			plugins : "image colorpicker textcolor media  emoticons link"
		});
	}
</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div id="wrapper">
		<h1>Administrator/Innen Zugang</h1>
		<h4>W&auml;hlen Sie eine Aufgabe + Information aus, die Sie erstellen
			m&ouml;chten.</h4>
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