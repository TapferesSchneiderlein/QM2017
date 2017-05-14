package de.uhd.ifi.se.quizapp.model.twochoiceexercise;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.Result;

public class TwoChoiceResult extends Result {

	private ArrayList<BooleanStatement> booleanStatements;

	public TwoChoiceResult() {
		this.booleanStatements = new ArrayList<BooleanStatement>();
	}

	public ArrayList<BooleanStatement> getBooleanStatements() {
		return booleanStatements;
	}

	public void setBooleanStatements(ArrayList<BooleanStatement> booleanStatements) {
		this.booleanStatements = booleanStatements;
	}
	
	public String toString() {
		return this.booleanStatements.toString();
	}

}
