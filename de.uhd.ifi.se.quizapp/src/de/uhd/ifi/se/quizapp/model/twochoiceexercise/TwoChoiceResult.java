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
	
	/**
	* The method calculates and returns the percentage of correct
	* answers for a TwoChoiceExercise.
	*
	* Input of the method:
	*	1. 	TwoChoiceExercise containing statements which can be
	*		true or false. (accessed through getExercise())
	*	2. 	TwoChoiceResult containing the solution for the
	*		exercise statements. (accessed through this)
	*
	* Output of the method:
	*	The percentage of correct answers as decimal.
	*	The percentage cannot be less than zero or greater than
	*	one (0 <= percentage <= 1).
	*	Returns zero if an exercise contains no statements.
	*	Returns -1 on invalid inputs.
	*/
	public float getPercentage() {		
		
		TwoChoiceExercise ex = (TwoChoiceExercise)getExercise();
		ArrayList<BooleanStatement> exStatements = ex.getBooleanStatements();
		
		if ( this.booleanStatements == null || exStatements == null
				|| this.booleanStatements.size() != exStatements.size()) {
			return -1;
		}
		
		if(exStatements.isEmpty()){
			return 0;
		}
		float correct = 0;
		for(int i = 0; i<exStatements.size(); i++){
			boolean answer = exStatements.get(i).isCorrect();
			boolean solution =  this.booleanStatements.get(i).isCorrect();
			if (answer == solution){
				correct++;
			}
		}
		return correct / (float)exStatements.size();		
	}
}
