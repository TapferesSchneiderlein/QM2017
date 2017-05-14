<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise"%>
<%@page import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<jsp:useBean id="dataManager"
	class="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceDataManager"></jsp:useBean>
<table id="aufgabentable">
	<%
		String exerciseView = "";

		List<TwoChoiceExercise> twoChoiceExercises = dataManager.getExercises();
		for (TwoChoiceExercise exercise : twoChoiceExercises) {
			ArrayList<BooleanStatement> booleanStatements = exercise.getBooleanStatements();			
			for (BooleanStatement statement : booleanStatements) {
				exerciseView += "<div class='group'>" + statement.getStatement();
				if (statement.isCorrect()) {
					exerciseView += "<input name='radio"
							+ "' type='checkbox' checked  disabled/>";
				} else {
					exerciseView += "<input name='radio"
							+ "' type='checkbox' disabled/>";
				}
				exerciseView += "</div>";
			}
	%>
	<tr>
		<td data-th='Schwierigkeitsgrad'><%=exercise.getDifficulty()%></td>
		<td data-th='Aufgabe'><%=exerciseView%></td>
		<td data-th='Beschreibung'><%=exercise.getDescription()%></td>
		<td data-th='text'><%=dataManager.getInformation(exercise.getInformationId()).getName()%></td>
		<td>
			<form action="Administrator" method="post">
				<input type="hidden" name="id" value='<%=exercise.getId()%>'>
				<input type="hidden" name="type" value='<%=ExerciseHandler.TWOCHOICE%>'>
				<input type="submit" name="deleteExercise" value="Löschen"
					onclick="return confirm('Diesen Eintrag löschen?')"> <input
					type="submit" name="editExercise" value="Bearbeiten">
			</form>
		</td>
	</tr>
	<%
		exerciseView = "";
		}
	%>
</table>