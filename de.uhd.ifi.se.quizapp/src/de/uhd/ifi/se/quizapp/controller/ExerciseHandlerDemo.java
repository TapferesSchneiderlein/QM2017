package de.uhd.ifi.se.quizapp.controller;

import de.uhd.ifi.se.quizapp.controller.SentencePartExerciseHandler;
import de.uhd.ifi.se.quizapp.controller.TwoChoiceExerciseHandler;

public class ExerciseHandlerDemo {

	public static void main(String[] args) {
		TwoChoiceExerciseHandler twoChoiceExerciseHandler = new TwoChoiceExerciseHandler(ExerciseHandler.TWOCHOICE);
		SentencePartExerciseHandler sentencePartExerciseHandler = new SentencePartExerciseHandler(ExerciseHandler.SENTENCEPART);
		
		twoChoiceExerciseHandler.setSuccessor(sentencePartExerciseHandler);
		//twoChoiceExerciseHandler.chooseHandler(1);
	}

}
