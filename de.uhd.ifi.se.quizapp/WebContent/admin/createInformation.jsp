<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informationen eintragen</title>
</head>
<body>
	<h1>Informationen eintragen</h1>
	<form id="createInformationForm" action="Administrator" method="post" accept-charset="UTF-8">
			<input type="text" style="width:98%;" name="name" placeholder="Titel" required /><br>
			<label for="text"></label><br>
	 		<textarea name="text" rows="10" style="width:98%;"></textarea><br>
		<input type="submit" name="createInformation" value="Speichern" /><br>
	</form>
	<script>inittinymce();</script>
	
	<jsp:include page="showInformation.jsp"></jsp:include>
</body>
</html>