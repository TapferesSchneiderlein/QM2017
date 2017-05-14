<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Aufgaben finden</title>
<link rel="stylesheet" type="text/css" href="../css/main.css">
<meta name=viewport content="width=device-width, initial-scale=1" />
</head>
<body>
	<form id="filterform" action="Student" method="post">
		<label for="difficulty">Schwierigkeitsgrad</label> <select
			name="difficulty" size=3>
			<option selected>1</option>
			<option>2</option>
			<option>3</option>
		</select> <label for="type">Aufgabentyp</label> <select name="type" size=2>
			<option value="<%=ExerciseHandler.TWOCHOICE%>" selected>Richtig/Falsch</option>
			<option value="<%=ExerciseHandler.SENTENCEPART%>">Satzteile</option>
		</select> <input type="submit" name="filterExercises" value="Suchen & Anzeigen">
	</form>
	<jsp:include page="showFilteredExercises.jsp"></jsp:include>
</body>
</html>