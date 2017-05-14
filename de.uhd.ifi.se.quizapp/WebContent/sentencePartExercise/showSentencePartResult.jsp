<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.sentencepartexercise.Sentence"%>
<jsp:useBean id="result"
	class="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult"
	scope="request" />
<jsp:useBean id="exercise"
	class="de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise"
	scope="request" />
<%
	ArrayList<Sentence> exerciseSentences = exercise.getSentences();
	ArrayList<Sentence> resultSentences = result.getSentences();
	boolean isCorrect = false;
	int numberOfSentenceParts = exerciseSentences.get(1).getNumberOfSentenceParts();

	for (int i = 0; i < exerciseSentences.size(); i++) {
		isCorrect = false;
		out.print("<div class='group'>" + resultSentences.get(i).toString() + "</div>");
		for (int k = 0; k < numberOfSentenceParts; k++) {
			for (int j = 0; j < resultSentences.size(); j++) {
				if (resultSentences.get(i).getSentenceParts().get(k)
						.equals(exerciseSentences.get(j).getSentenceParts().get(k))) {
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