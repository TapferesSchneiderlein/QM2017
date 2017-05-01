<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement"%>
<jsp:useBean id="result"
	class="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceResult"
	scope="request" />
<jsp:useBean id="exercise"
	class="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise"
	scope="request" />

<%
	ArrayList<BooleanStatement> exerciseStatements = exercise.getBooleanStatements();
	ArrayList<BooleanStatement> resultStatements = result.getBooleanStatements();
	boolean isCorrect = false;

	for (BooleanStatement resultStatement : resultStatements) {
		isCorrect = false;
		out.print("<div class='group'>" + resultStatement.getStatement() + "</div>");
		for (BooleanStatement exerciseStatement : exerciseStatements) {
			if (resultStatement.getStatement().equals(exerciseStatement.getStatement())) {
				if (resultStatement.isCorrect() == exerciseStatement.isCorrect()) {
					isCorrect = true;
				}
			}
		}
		if (isCorrect) {
			out.println("<img class='correct' src='../img/right.png' />");
		} else {
			out.print("<img class='correct' src='../img/wrong.png' />");
		}
	}
%>