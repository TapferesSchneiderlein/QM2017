<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<jsp:useBean id="dataManager" scope="request"
	class="de.uhd.ifi.se.quizapp.model.DataManager" />
<h3>Satzteilverbindungs-Aufgabe erstellen</h3>

<script>
	function createForm(cols, rows) {
		var form = "<textarea name='description' placeholder='Beschreibung'  required>Verbinde die Satzteile</textarea>";
		for (var i = 0; i < rows; i++) {
			form += "<div class='group'>";
			for (var j = 0; j < cols; j++) {
				form += "<input type='text' name='input"+i+j+"' placeholder='Satzteil "+j+"' />";
			}
			form += "</div><br>";
		}
		form += "<input type='hidden' name='numberOfSentenceParts' value='"+cols+"' />";
		return form;
	}
</script>

<input id="cols" type="number" min="1" max="4"
	onchange="updateCol(this.value);updateRow(1);inittinymce();"
	placeholder="Anzahl Satzteile" required />
<input id="rows" type="number" min="1" max="5"
	onchange="updateRow(this.value);inittinymce();"
	placeholder="Anzahl Sätze" required />

<form id="createExerciseForm" action="Administrator" method="post"
	accept-charset="UTF-8">
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
	</select> <input type='hidden' name='type'
		value='<%=ExerciseHandler.SENTENCEPART%>' /> <input type="submit"
		name="createExercise" value="Speichern" /><br>
</form>
