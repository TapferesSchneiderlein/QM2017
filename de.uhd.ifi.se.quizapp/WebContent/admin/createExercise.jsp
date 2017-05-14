<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aufgaben erstellen</title>
<script>
	var rows = 0;
	var cols = 0;
	function updateRow(variable) {
		rows = variable;
		form = createForm(cols, rows);
		$("#formcontainer").html(form);
	}
	function updateCol(variable) {
		cols = variable;
		form = createForm(cols, rows);
		$("#formcontainer").html(form);
	}
</script>
</head>
<body>
	<h1>Aufgaben erstellen</h1>
	<%
		int type = Integer.parseInt(request.getParameter("type"));
		if (type == ExerciseHandler.TWOCHOICE) {
	%>

	<jsp:include page="../twoChoiceExercise/createTwoChoiceExercise.jsp"></jsp:include>

	<%
		} else if (type == ExerciseHandler.SENTENCEPART) {
	%>

	<jsp:include page="../sentencePartExercise/createSentencePartExercise.jsp"></jsp:include>

	<%
		} else {
			out.print("Keine Aufgabe ausgewählt");
		}
	%>

	<p>${message}</p>
	<jsp:include page="showExercise.jsp"></jsp:include>
</body>
</html>