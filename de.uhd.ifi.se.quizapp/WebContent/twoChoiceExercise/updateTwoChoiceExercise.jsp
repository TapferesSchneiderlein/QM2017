<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<%@page import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement"%>
<%@page import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<jsp:useBean id="dataManager" scope="request"
	class="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceDataManager" />
<h1>Aufgaben bearbeiten</h1>

<%
	int id = Integer.parseInt(request.getParameter("id"));
	TwoChoiceExercise exercise = dataManager.getExercise(id);
	ArrayList<BooleanStatement> booleanStatements = exercise.getBooleanStatements();

	String form = "<div class='group'>";
	int i = 0;
	for (BooleanStatement statement : booleanStatements) {
		form += "<input type='text' name='input" + i + "' value='" + statement.getStatement() + "'  />";
		if (statement.isCorrect()) {
			form += "<fieldset><legend>Richtig</legend><input name='radio" + i
					+ "' type='checkbox' checked /></fieldset>";
		} else {
			form += "<fieldset><legend>Richtig</legend><input name='radio" + i + "' type='checkbox'/></fieldset>";
		}
		i++;
	}
	form += "</div>";
%>

<form id="updateExerciseForm" action="Administrator" method="post"
	accept-charset="UTF-8">
	<textarea name='description' rows='2' style='width: 98%;'
		placeholder='Beschreibung' required><%=exercise.getDescription()%></textarea>
	<div id="formcontainer"><%=form%></div>
	<label for="difficulty">Schwierigkeit auswählen</label><br> <input
		type="number" min="1" max="3" name="difficulty"
		value="<%=exercise.getDifficulty()%>"><br> <label
		for="name">Text auswählen:</label><br> <select name="information"
		size="1">
		<%
			ArrayList<Information> informationList = dataManager.getInformation();
			for (Information eachInformation : informationList) {
		%><option>
			<%
				out.print(eachInformation.getID() + " " + eachInformation.getName());
			%>
		</option>
		<%
			}
		%>
	</select> 
	<input type='hidden' name='type' value='<%=ExerciseHandler.TWOCHOICE %>' />
	<input type='hidden' name='id' value='<%=id%>' />
	<input type="submit" name="updateExercise" value="Speichern" /><br>
</form>