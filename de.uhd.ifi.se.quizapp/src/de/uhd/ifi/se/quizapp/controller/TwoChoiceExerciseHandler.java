package de.uhd.ifi.se.quizapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import de.uhd.ifi.se.quizapp.model.Information;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceDataManager;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceResult;

public class TwoChoiceExerciseHandler extends ExerciseHandler {

	TwoChoiceDataManager dataManager;

	public TwoChoiceExerciseHandler(int type) {
		this.type = type;
		this.dataManager = new TwoChoiceDataManager();
	}

	/**
	 * Parses the parameters of the request object to boolean statements
	 */
	protected static ArrayList<BooleanStatement> parametersToBooleanStatements(HttpServletRequest request) {
		ArrayList<BooleanStatement> booleanStatements = new ArrayList<BooleanStatement>();

		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String parameterValue = request.getParameterValues(parameterName)[0];
			if (parameterName.contains("input")) {
				booleanStatements.add(new BooleanStatement(parameterValue));
			} else if (parameterName.contains("radio")) {
				if (parameterValue.equals("on")) {
					booleanStatements.get(booleanStatements.size() - 1).setCorrect(true);
				}
			}
		}
		return booleanStatements;
	}

	@Override
	protected HttpServletRequest handleCreationInChain(HttpServletRequest request) {
		int difficulty = Integer.parseInt(request.getParameter("difficulty"));
		String selectedInformation = request.getParameter("information");
		int informationId = Integer.parseInt(selectedInformation.split(" ")[0]);

		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.setDifficulty(difficulty);
		exercise.setDescription(request.getParameter("description"));
		exercise.setInformationId(informationId);
		exercise.setBooleanStatements(parametersToBooleanStatements(request));

		String message = "";

		try {
			dataManager.insertExercise(exercise);
			message = "Die Richtig/Falsch Aufgabe wurde erfolgreich erstellt.";
		} catch (ClassNotFoundException | SQLException e) {
			message = "Es ist ein Fehler aufgetreten";
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		return request;
	}

	@Override
	protected HttpServletRequest handleEditingInChain(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			TwoChoiceExercise exercise = dataManager.getExercise(id);
			request.setAttribute("exercise", exercise);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return request;
	}

	@Override
	protected HttpServletRequest handleUpdatingInChain(HttpServletRequest request) {
		String message = "";
		int id = Integer.parseInt(request.getParameter("id"));
		int difficulty = Integer.parseInt(request.getParameter("difficulty"));
		String selectedInformation = request.getParameter("information");
		int informationId = Integer.parseInt(selectedInformation.split(" ")[0]);

		TwoChoiceExercise exercise = new TwoChoiceExercise();
		try {
			exercise = dataManager.getExercise(id);
		} catch (ClassNotFoundException | SQLException e) {
			message = "Die Aufgabe konnte nicht gefunden werden.";
			e.printStackTrace();
		}
		exercise.setDifficulty(difficulty);
		exercise.setDescription(request.getParameter("description"));
		exercise.setInformationId(informationId);
		exercise.setBooleanStatements(parametersToBooleanStatements(request));

		try {
			dataManager.updateExercise(exercise);
			message = "Die Richtig/Falsch Aufgabe wurde erfolgreich erstellt.";
		} catch (ClassNotFoundException | SQLException e) {
			message = "Es ist ein Fehler aufgetreten";
			e.printStackTrace();
		}

		request.setAttribute("message", message);
		return request;
	}

	@Override
	protected HttpServletRequest handleFilteringInChain(HttpServletRequest request) {
		int difficulty = Integer.parseInt(request.getParameter("difficulty"));

		try {
			List<TwoChoiceExercise> exercises = dataManager.getExercises(difficulty);
			request.setAttribute("exercises", exercises);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	@Override
	protected HttpServletRequest handleSolvingInChain(HttpServletRequest request) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));

			TwoChoiceExercise exercise = dataManager.getExercise(id);
			Information information = dataManager.getInformation(exercise.getInformationId());

			if (exercise != null && information != null) {
				request.setAttribute("exercise", exercise);
				request.setAttribute("information", information);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return request;
	}

	@Override
	protected HttpServletRequest handleCheckingInChain(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		TwoChoiceExercise exercise = null;

		try {
			exercise = dataManager.getExercise(id);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		TwoChoiceResult result = new TwoChoiceResult();
		result.setExercise(exercise);
		result.setBooleanStatements(parametersToBooleanStatements(request));

		// try {
		// dataManager.insertResult(result);
		// } catch (ClassNotFoundException | SQLException e) {
		// e.printStackTrace();
		// }

		request.setAttribute("result", result);
		request.setAttribute("exercise", exercise);

		return request;
	}

}
