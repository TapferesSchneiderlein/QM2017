package de.uhd.ifi.se.quizapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uhd.ifi.se.quizapp.controller.SentencePartExerciseHandler;
import de.uhd.ifi.se.quizapp.controller.TwoChoiceExerciseHandler;
import de.uhd.ifi.se.quizapp.model.*;

@WebServlet("/Administrator")
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataManager dataManager;

	TwoChoiceExerciseHandler exerciseHandler;
	SentencePartExerciseHandler sentencePartExerciseHandler;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorServlet() {
		super();
		exerciseHandler = new TwoChoiceExerciseHandler(ExerciseHandler.TWOCHOICE);
		sentencePartExerciseHandler = new SentencePartExerciseHandler(ExerciseHandler.SENTENCEPART);
		exerciseHandler.setSuccessor(sentencePartExerciseHandler);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		// Connect to database
		dataManager = new DataManager();

		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = null;

		if (request.getParameter("createExercise") != null) {
			request = exerciseHandler.handleCreation(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createExercise");
		}

		else if (request.getParameter("createInformation") != null) {
			request = this.handleCreateInformationRequest(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createInformation");
		}

		else if (request.getParameter("deleteExercise") != null) {
			request = exerciseHandler.handleDeletion(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createExercise");
		}

		else if (request.getParameter("deleteInformation") != null) {
			request = this.handleDeleteInformationRequest(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createInformation");
		}

		else if (request.getParameter("editExercise") != null) {
			//request = exerciseHandler.handleEditing(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=updateExercise");
		}

		else if (request.getParameter("updateExercise") != null) {
			request = exerciseHandler.handleUpdating(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createExercise");
		}

		else if (request.getParameter("editInformation") != null) {
			request = this.handleEditInformationRequest(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=updateInformation");
		}

		else if (request.getParameter("updateInformation") != null) {
			request = this.handleUpdateInformationRequest(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createInformation");
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
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public HttpServletRequest handleCreateInformationRequest(HttpServletRequest request) {
		String text = request.getParameter("text");
		String name = request.getParameter("name");

		Information info = new Information(name, text);

		// Insert new information into database
		try {
			dataManager.insertInformation(info);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}

		String message = "Information erfolgreich erstellt";
		request.setAttribute("message", message);
		return request;
	}

	public HttpServletRequest handleDeleteInformationRequest(HttpServletRequest request) {
		int id = 0;
		if (request.getParameter("id") != null)
			id = Integer.parseInt(request.getParameter("id"));

		try {
			dataManager.deleteInformation(id);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}

		String message = "Information erfolgreich geloescht";
		request.setAttribute("message", message);
		return request;
	}

	public HttpServletRequest handleEditInformationRequest(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Information info = new Information();
		try {
			info = dataManager.getInformation(id);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		request.setAttribute("info", info);
		return request;
	}

	public HttpServletRequest handleUpdateInformationRequest(HttpServletRequest request) {
		String text = request.getParameter("text");
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));

		Information info = new Information(id, name, text);

		// Update information in database
		try {
			dataManager.updateInformation(info);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}

		String message = "Information erfolgreich aktualisiert";
		request.setAttribute("message", message);
		return request;
	}

}
