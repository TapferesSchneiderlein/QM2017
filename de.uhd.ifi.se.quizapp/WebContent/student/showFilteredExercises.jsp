<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<%
	if (request.getAttribute("exercises") != null) {
		int type = Integer.parseInt(request.getParameter("type"));
		out.print("<br/><b>Alle Aufgaben vom Typ '"
				+ (type == ExerciseHandler.TWOCHOICE ? "Richtig/Falsch" : "Satzteile")
				+ "' mit Schwierigkeitsgrad '" + request.getParameter("difficulty") + "':</b>");

		if (type == ExerciseHandler.TWOCHOICE) {
%>
<jsp:include
	page="../twoChoiceExercise/showFilteredTwoChoiceExercises.jsp"></jsp:include>
<%
	} else if (type == ExerciseHandler.SENTENCEPART) {
%>
<jsp:include
	page="../sentencePartExercise/showFilteredSentencePartExercises.jsp"></jsp:include>
<%
	}
	}
%>