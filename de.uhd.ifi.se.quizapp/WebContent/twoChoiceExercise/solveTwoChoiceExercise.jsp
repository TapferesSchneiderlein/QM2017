<%@page import="java.util.ArrayList"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement"%>
<jsp:useBean id="exercise"
	class="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise"
	scope="request">
</jsp:useBean>
<div id='description'><%=exercise.getDescription()%></div>
<button onclick="goBack()">Zurück</button>

<form id="aufgabenform" action=Student method="post">
	<div class='group'>
		<%
			ArrayList<BooleanStatement> booleanStatements = exercise.getBooleanStatements();
			int type = 1;
			int id = exercise.getId();

			String form = "";
			int i = 0;
			for (BooleanStatement statement : booleanStatements) {
				form += "<input type='text' name='input" + i + "' value='" + statement.getStatement() + "' readonly  />";
				form += "<fieldset><legend>Richtig</legend><input name='radio" + i + "' type='radio'  />";
				form += "<legend>Falsch</legend><input name='radio" + i + "' type='radio' value=''  /></fieldset>";
				i++;
			}
			out.print(form);
		%>
	</div>
	<input type='hidden' name='id' value='<%=exercise.getId()%>' /> <input
		type='hidden' name='type' value='1' /><input
		type='checkbox' name='ready' required /> <input type='submit'
		name='checkResult' value='Senden' />
</form>