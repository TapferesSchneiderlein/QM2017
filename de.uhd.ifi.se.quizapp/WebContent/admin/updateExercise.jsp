<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aktualisiere Aufgabe</title>
<script>
	inittinymce();
</script>
</head>
<body>
	<%
		int type = Integer.parseInt(request.getParameter("type"));
		if (type == ExerciseHandler.TWOCHOICE) {
	%>
	<jsp:include page="../twoChoiceExercise/updateTwoChoiceExercise.jsp"></jsp:include>
	<%
		} else if (type == ExerciseHandler.SENTENCEPART) {
	%>
	<jsp:include page="../sentencePartExercise/updateSentencePartExercise.jsp"></jsp:include>
	<%
		}
	%>
</body>
<jsp:include page="showExercise.jsp"></jsp:include>
</html>