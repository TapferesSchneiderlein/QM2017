<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Ihre L&ouml;sung</title>
<link rel="stylesheet" type="text/css" href="../css/main.css">
<meta name=viewport content="width=device-width, initial-scale=1" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<%
		if (request.getAttribute("exercise") != null) {
			int type = Integer.parseInt(request.getParameter("type"));
			if (type == ExerciseHandler.TWOCHOICE) {
	%>
	<jsp:include page="../twoChoiceExercise/showTwoChoiceResult.jsp"></jsp:include>
	<%
		} else if (type == ExerciseHandler.SENTENCEPART) {
	%>
	<jsp:include page="../sentencePartExercise/showSentencePartResult.jsp"></jsp:include>
	<%
		} else {
				out.println("Keine Aufgabe verfügbar");
			}
		}
	%>
	<a href="filterExercises.jsp"><button>N&auml;chste Aufgabe</button></a>
</body>
</html>