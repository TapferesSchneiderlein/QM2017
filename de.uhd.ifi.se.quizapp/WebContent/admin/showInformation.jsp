<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<jsp:useBean id="dataManager"
	class="de.uhd.ifi.se.quizapp.model.DataManager"></jsp:useBean>
<table id="aufgabentable">
	<%
		ArrayList<Information> informationList = dataManager.getInformation();
		for (Information information : informationList) {
	%>
	<tr>
		<td data-th="ID"><%=information.getID()%></td>
		<td data-th="NAME"><%=information.getName()%></td>
		<td data-th="TEXT"><%=information.getText()%></td>
		<td>
			<form action="Administrator" method="post">
				<input type="hidden" name="id" value='<%=information.getID()%>'>
				<input type="submit" name="deleteInformation" value="Löschen"
					onclick="return confirm('Diesen Eintrag löschen?')"> <input
					type="submit" name="editInformation" value="Bearbeiten">
			</form>
		</td>
	</tr>
	<%
		}
	%>
</table>
