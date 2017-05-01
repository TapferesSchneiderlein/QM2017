<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Aufgabe l&ouml;sen</title>
<link rel="stylesheet" type="text/css" href="../css/main.css">
<meta name=viewport content="width=device-width, initial-scale=1" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<style>
#shadow {
	background: rgba(0, 0, 0, 0.5);
	display: none;
	position: fixed;
	height: 100%;
	width: 100%;
	left: 0;
	top: 0;
}

#aufgabenform {
	z-index: 999;
}
</style>
</head>
<body>
	<jsp:useBean id="information"
		class="de.uhd.ifi.se.quizapp.model.Information" scope="request" />
	<div id="shadow"></div>
	<div id="aufgabentable">
		<div id="textbox">
			<%
				out.println(information.getName());
				out.println(information.getText());
			%>
		</div>

		<%
			if (request.getAttribute("exercise") != null) {
				int type = Integer.parseInt(request.getParameter("type"));
				out.print("<br/><b>Alle Aufgaben vom Typ '"
						+ (type == ExerciseHandler.TWOCHOICE ? "Richtig/Falsch" : "Satzteile")
						+ "' mit Schwierigkeitsgrad '" + request.getParameter("difficulty") + "':</b>");

				if (type == ExerciseHandler.TWOCHOICE) {
		%>
		<jsp:include page="../twoChoiceExercise/solveTwoChoiceExercise.jsp"></jsp:include>
		<%
			} else if (type == ExerciseHandler.SENTENCEPART) {
		%>
		<jsp:include
			page="../sentencePartExercise/solveSentencePartExercise.jsp"></jsp:include>
		<%
			}
			}
		%>
	</div>
	<script>
		function goBack() {
			window.history.back();
		}

		buttons = new Array();
		results = new Array();

		$(".part").click(function() {
			$("#shadow").show();
			var text = $(this).text();
			var elem = $(this)
			$(".group").addClass("highlighted");
			$(".output").click(function() {
				if (text != "") {
					var res = $(this);
					$(this).val(text).attr("disabled", false);
					elem.hide();
					$(".group").removeClass("highlighted");
					text = "";
					$("#shadow").hide();
					buttons.push(elem);
					results.push(res);
				}
			});

		});

		$("#undo").click(function() {
			var elem = buttons.pop();
			var res = results.pop();
			elem.show();
			res.val("").attr("disabled", false);
			;
		});

		$("#reset").click(function() {
			$(".part").show();
			$(".output").val("").attr("disabled", false);
			buttons = new Array();
			results = new Array();
		});
	</script>
</body>
</html>