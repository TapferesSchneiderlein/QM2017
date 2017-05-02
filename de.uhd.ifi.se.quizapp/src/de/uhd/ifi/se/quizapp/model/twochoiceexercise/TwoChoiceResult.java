package de.uhd.ifi.se.quizapp.model.twochoiceexercise;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.AbstractResult;

/**  */
public class TwoChoiceResult extends AbstractResult {
	/** List of boolean statements */
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