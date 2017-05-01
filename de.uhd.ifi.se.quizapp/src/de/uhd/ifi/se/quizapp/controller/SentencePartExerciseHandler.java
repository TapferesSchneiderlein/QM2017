package de.uhd.ifi.se.quizapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import de.uhd.ifi.se.quizapp.model.Information;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.Sentence;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartDataManager;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult;

/** Handler for exercises with sentences */
public class SentencePartExerciseHandler extends AbstractExerciseHandler {
	/** Data Manager */
	SentencePartDataManager dataManager;

	public SentencePartExerciseHandler(int type) {
		this.type = type;
		this.dataManager = new SentencePartDataManager();
	}

	/**
	 * Parses the parameters of the request object to sentences
	 */
	protected static ArrayList<Sentence> parametersToSentences(HttpServletRequest request, int numberOfSentenceParts) {
		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		Enumeration<String> parameterList = request.getParameterNames();
		int i = 0;
		while (parameterList.hasMoreElements()) {
			String parameterName = parameterList.nextElement();
			String parameterValue = request.getParameterValues(parameterName)[0];
			if (parameterName.contains("input")) {
				if (i == 0 || i % numberOfSentenceParts == 0) {
					Sentence sentence = new Sentence();
					sentence.addSentencePart(parameterValue);
					sentences.add(sentence);
				} else {
					sentences.get(sentences.size() - 1).addSentencePart(parameterValue);
				}
				i++;
			}
		}
		return sentences;
	}

	@Override
	protected HttpServletRequest handleCreationInChain(HttpServletRequest request) {
		int difficulty = Integer.parseInt(request.getParameter("difficulty"));
		String selectedInformation = request.getParameter("information");
		int informationId = Integer.parseInt(selectedInformation.split(" ")[0]);
		int numberOfSentenceParts = Integer.parseInt(request.getParameter("numberOfSentenceParts"));

		SentencePartExercise exercise = new SentencePartExercise();
		exercise.setDifficulty(difficulty);
		exercise.setDescription(request.getParameter("description"));
		exercise.setInformationId(informationId);

		exercise.setSentences(parametersToSentences(request, numberOfSentenceParts));
		String message = "";

		try {
			this.dataManager.insertExercise(exercise);
			message = "Die Satzverbindungsaufgabe wurde erfolgreich erstellt.";
		} catch (ClassNotFoundException | SQLException e) {
			message = "Es ist ein Fehler aufgetreten";
			e.printStackTrace();
		}

		request.setAttribute("message", message);
		return request;
	}

	@Override
	protected HttpServletRequest handleEditingInChain(HttpServletRequest request) {
		int exerciseId = Integer.parseInt(request.getParameter("id"));
		try {
			SentencePartExercise exercise = dataManager.getExercise(exerciseId);
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
		int exerciseId = Integer.parseInt(request.getParameter("id"));
		int difficulty = Integer.parseInt(request.getParameter("difficulty"));
		String selectedInformation = request.getParameter("information");
		int informationId = Integer.parseInt(selectedInformation.split(" ")[0]);
		int numberOfSentenceParts = Integer.parseInt(request.getParameter("numberOfSentenceParts"));

		SentencePartExercise exercise = new SentencePartExercise();
		try {
			exercise = dataManager.getExercise(exerciseId);
		} catch (ClassNotFoundException | SQLException e) {
			message = "Die Aufgabe konnte nicht gefunden werden.";
			e.printStackTrace();
		}
		exercise.setDifficulty(difficulty);
		exercise.setDescription(request.getParameter("description"));
		exercise.setInformationId(informationId);
		exercise.setSentences(parametersToSentences(request, numberOfSentenceParts));

		try {
			dataManager.updateExercise(exercise);
			message = "Die Satzverbindungsaufgabe wurde erfolgreich aktualisiert.";
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
			List<SentencePartExercise> exercises = dataManager.getExercises(difficulty);
			request.setAttribute("exercises", exercises);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	@Override
	protected HttpServletRequest handleSolvingInChain(HttpServletRequest request) {
		try {
			int exerciseId = Integer.parseInt(request.getParameter("id"));

			SentencePartExercise exercise = dataManager.getExercise(exerciseId);
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
		int exerciseId = Integer.parseInt(request.getParameter("id"));
		SentencePartExercise exercise = null;

		try {
			exercise = dataManager.getExercise(exerciseId);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int numberOfSentenceParts = 1;
		try {
			numberOfSentenceParts = exercise.getSentences().get(0).getNumberOfSentenceParts();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}		

		SentencePartResult result = new SentencePartResult();
		result.setExercise(exercise);
		result.setSentences(parametersToSentences(request, numberOfSentenceParts));

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
