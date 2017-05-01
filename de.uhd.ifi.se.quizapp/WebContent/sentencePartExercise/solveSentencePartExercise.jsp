<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.Sentence"%>
<jsp:useBean id="exercise"
	class="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise"
	scope="request">
</jsp:useBean>
<div id='description'><%=exercise.getDescription()%></div>
<button onclick="goBack()">Zurück</button>
<%
	ArrayList<Sentence> sentences = exercise.getSentences();
	for (Sentence sentence : sentences) {
		List<String> sentenceParts = sentence.getSentenceParts();
		Collections.shuffle(sentenceParts);
%>
<form id="aufgabenform" action=Student method="post">
	<%
		out.print("<p>");
			for (String sentencePart : sentenceParts) {
				out.print("<div class='part'>" + sentencePart + "</div> **** ");
			}
			out.print("</p>");
		}
		String form = "";
		int numberOfSentenceParts = sentences.get(0).getSentenceParts().size();

		for (int i = 0; i < sentences.size(); i++) {
			form += "<div class='group'>";
			for (int j = 0; j < numberOfSentenceParts; j++) {
				form += "<input class='output' name='input" + i + j + "'readonly='readonly'  required='required'>";
			}
			form += "</div>";
		}
		out.print(form);
	%>
	<input type='hidden' name='id' value='<%=exercise.getId()%>' /> <input
		type='hidden' name='type' value='2' /> <input type='checkbox'
		name='ready' required /> <input type='submit' name='checkResult'
		value='Senden' />
</form>