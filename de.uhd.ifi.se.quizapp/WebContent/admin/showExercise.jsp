<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<%
	int type = Integer.parseInt(request.getParameter("type"));
	if (type == ExerciseHandler.TWOCHOICE) {
%>
<jsp:include page="../twoChoiceExercise/showTwoChoiceExercises.jsp"></jsp:include>
<%
	} else if (type == ExerciseHandler.SENTENCEPART) {
%>
<jsp:include page="../sentencePartExercise/showSentencePartExercises.jsp"></jsp:include>
<%
	}
%>