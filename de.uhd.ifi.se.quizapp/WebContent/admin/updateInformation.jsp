<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="info" scope="request"
	class="de.uhd.ifi.se.quizapp.model.Information" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Information bearbeiten</title>
</head>

<body>
	<%	
	String text = info.getText();
	String name = info.getName();
	int id = info.getID();
	%>
	<form id="aufgabenform" action="Administrator" method="post"
		accept-charset="UTF-8">
		<input type="text" style="width: 98%;" name="name" value="<%=name%>" placeholder="Titel" required /><br> <label for="text"></label><br>
		<textarea name="text" rows="10" style="width:98%;"><%=text%></textarea>
		<br> <input type="hidden" name="id" value="<%=id%>"> <input
			type="submit" name="updateInformation" value="Speichern" /><br>
	</form>
	<script>
		inittinymce();
	</script>

	<jsp:include page="showInformation.jsp"></jsp:include>

</body>
</html>