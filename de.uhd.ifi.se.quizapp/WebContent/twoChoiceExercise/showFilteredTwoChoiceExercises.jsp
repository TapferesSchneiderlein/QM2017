<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise"%>
<jsp:useBean id="dataManager"
	class="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceDataManager"></jsp:useBean>

<table id="aufgabentable">
	<%
	@SuppressWarnings("unchecked")
	ArrayList<TwoChoiceExercise> exercises = (ArrayList<TwoChoiceExercise>)request.getAttribute("exercises");
		for (TwoChoiceExercise exercise : exercises) {
			Information information = dataManager.getInformation(exercise.getInformationId());
	%>
	<tr>
		<td data-th='Schwierigkeit'><%=exercise.getDifficulty()%></td>
		<td data-th='Name'><%=information.getName()%></td>
		<td data-th='Name'><%=information.getText()%></td>
		<td><form action="Student" method="post">
				<input type="hidden" name="id" value='<%=exercise.getId()%>'>
				<input type="hidden" name="type" value='1'>
				<button type="submit" name="solveExercise">Aufgabe
					l&ouml;sen</button>
			</form></td>
	</tr>
	<%
		}
	%>
</table>