<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<jsp:useBean id="dataManager" scope="request"
	class="de.uhd.ifi.se.quizapp.model.DataManager" />
<h3>Richtig/Falsch Aufgabe erstellen</h3>

<script>
	inittinymce();
	function createForm(cols, rows) {
		var form = "";
		for (var i = 0; i < rows; i++) {
			form += "<div class='group'>";
			for (var j = 0; j < cols; j++) {
				form += "<input type='text' name='input"+i+j+"' /><fieldset><legend>Richtig</legend><input name='radio"+i+j+"' type='radio' /></fieldset>";
			}
			form += "</div><br>";
		}
		form += "<input type='hidden' name='dimensioncol' value='"+cols+"' /><input type='hidden' name='dimensionrow' value='"+rows+"' />";
		return form;
	}
</script>

<input id="cols" type="number" min="1" max="4"
	onchange="updateCol(this.value);updateRow(1);"
	placeholder="Anzahl von Aussagen" required />

<form id="createExerciseForm" action="Administrator" method="post"
	accept-charset="UTF-8">
	<textarea rows="2" style='width: 98%;' name='description'
		placeholder='Beschreibung' required>Sind die Aussagen richtig oder falsch?</textarea>
	<div id="formcontainer"></div>
	<label for="difficulty">Schwierigkeit auswählen</label><br> <input
		type="number" min="1" max="3" name="difficulty" value="1"><br>
	<label for="name">Text auswählen:</label><br> <select
		name="information" size="1">
		<%
			ArrayList<Information> informationList = dataManager.getInformation();
			for (Information eachInformation : informationList) {
		%>
		<option>
			<%
				out.print(eachInformation.getID() + " " + eachInformation.getName());
			%>
		</option>
		<%
			}
		%>
	</select> 
	<input type='hidden' name='type' value='<%=ExerciseHandler.TWOCHOICE%>' />
	<input type="submit" name="createExercise" value="Speichern" /><br>
</form>