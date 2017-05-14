<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<nav>
	<ul>
		<li><a href="index.jsp?p=createInformation" >Informationen verwalten</a></li><!--
		  --><li><a href="index.jsp?p=createExercise&type=<%=ExerciseHandler.TWOCHOICE%>">Richtig/Falsch-Aufgaben verwalten</a></li><!--
		--><li><a href="index.jsp?p=createExercise&type=<%=ExerciseHandler.SENTENCEPART%>">Satzteilverbindungs-Aufgaben verwalten</a></li><!-- 
	--></ul>
</nav>