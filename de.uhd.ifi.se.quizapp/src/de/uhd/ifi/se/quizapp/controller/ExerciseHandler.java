package de.uhd.ifi.se.quizapp.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import de.uhd.ifi.se.quizapp.model.DataManager;

public abstract class ExerciseHandler {
	
	DataManager dataManager;

	public static final int TWOCHOICE = 1;
	public static final int SENTENCEPART = 2;

	protected int type;

	// next element in chain of responsibility
	protected ExerciseHandler successor;

	public ExerciseHandler getSuccessor() {
		return this.successor;
	}

	public void setSuccessor(ExerciseHandler successor) {
		this.successor = successor;
	}
	
	public HttpServletRequest handleCreation(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleCreationInChain(request);
		}
		if (this.successor != null) {
			return this.successor.handleCreationInChain(request);
		}
		return null;
	}
	
	abstract protected HttpServletRequest handleCreationInChain(HttpServletRequest request);
	
	public HttpServletRequest handleDeletion(HttpServletRequest request) {
		this.dataManager = new DataManager();
		int id = 1;
		if (request.getParameter("id") != null)
			id = Integer.parseInt(request.getParameter("id"));

		try {
			dataManager.deleteExercise(id);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}

		request.setAttribute("message", "Die Aufgabe wurde erfolgreich geloescht.");
		return request;
	}
	
	/**
	 * TODO: Compare performance if this method is used instead of only using the jsp page
	 */
	public HttpServletRequest handleEditing(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleEditingInChain(request);
		}
		if (this.successor != null) {
			return this.successor.handleEditingInChain(request);
		}
		return null;
	}
	
	abstract protected HttpServletRequest handleEditingInChain(HttpServletRequest request);
	
	public HttpServletRequest handleUpdating(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleUpdatingInChain(request);
		}
		if (this.successor != null) {
			return this.successor.handleUpdatingInChain(request);
		}
		return null;
	}
	
	abstract protected HttpServletRequest handleUpdatingInChain(HttpServletRequest request);
	
	public HttpServletRequest handleFiltering(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleFilteringInChain(request);
		}
		if (this.successor != null) {
			return this.successor.handleFilteringInChain(request);
		}
		return null;
	}
	
	abstract protected HttpServletRequest handleFilteringInChain(HttpServletRequest request);
	
	public HttpServletRequest handleSolving(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleSolvingInChain(request);
		}
		if (this.successor != null) {
			return this.successor.handleSolvingInChain(request);
		}
		return null;
	}
	
	abstract protected HttpServletRequest handleSolvingInChain(HttpServletRequest request);
	
	public HttpServletRequest handleChecking(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleCheckingInChain(request);
		}
		if (this.successor != null) {
			return this.successor.handleCheckingInChain(request);
		}
		return null;
	}
	
	abstract protected HttpServletRequest handleCheckingInChain(HttpServletRequest request);
	
}
