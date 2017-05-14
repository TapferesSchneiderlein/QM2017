package de.uhd.ifi.se.quizapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uhd.ifi.se.quizapp.controller.SentencePartExerciseHandler;
import de.uhd.ifi.se.quizapp.controller.TwoChoiceExerciseHandler;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/Student")
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	TwoChoiceExerciseHandler exerciseHandler;
	SentencePartExerciseHandler sentencePartExerciseHandler;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
		super();
		exerciseHandler = new TwoChoiceExerciseHandler(ExerciseHandler.TWOCHOICE);
		sentencePartExerciseHandler = new SentencePartExerciseHandler(ExerciseHandler.SENTENCEPART);
		exerciseHandler.setSuccessor(sentencePartExerciseHandler);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		RequestDispatcher dispatcher = null;

		if (request.getParameter("filterExercises") != null) {
			request = exerciseHandler.handleFiltering(request);
			dispatcher = request.getRequestDispatcher("/student/index.jsp?p=filterExercises");
		} else if (request.getParameter("solveExercise") != null) {
			request = exerciseHandler.handleSolving(request);
			dispatcher = request.getRequestDispatcher("/student/index.jsp?p=solveExercise");
		} else if (request.getParameter("checkResult") != null) {
			request = exerciseHandler.handleChecking(request);
			dispatcher = request.getRequestDispatcher("/student/index.jsp?p=showResult");
		}

		// No request
		else {
			out.println("No request.");
		}

		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

}
