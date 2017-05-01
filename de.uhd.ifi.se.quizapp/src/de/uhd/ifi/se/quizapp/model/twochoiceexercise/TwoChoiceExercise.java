package de.uhd.ifi.se.quizapp.model.twochoiceexercise;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.AbstractExercise;

/** Two choice exercise */
public class TwoChoiceExercise extends AbstractExercise {
	/** List oft boolean statements */
	private ArrayList<BooleanStatement> booleanStatements;

	public TwoChoiceExercise() {
		this.booleanStatements = new ArrayList<BooleanStatement>();
	}

	public TwoChoiceExercise(int exerciseId, int difficulty, String description, int informationId,
			ArrayList<BooleanStatement> booleanStatements) {
		super(exerciseId, difficulty, description, informationId);
		this.booleanStatements = booleanStatements;
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
