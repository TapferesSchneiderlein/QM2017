package de.uhd.ifi.se.quizapp.controller;

import de.uhd.ifi.se.quizapp.controller.SentencePartExerciseHandler;
import de.uhd.ifi.se.quizapp.controller.TwoChoiceExerciseHandler;

/** Demo class for the exercise handler */
public class ExerciseHandlerDemo {

	public static void main(String[] args) {
		TwoChoiceExerciseHandler twoChoiceExerciseHandler = new TwoChoiceExerciseHandler(AbstractExerciseHandler.TWOCHOICE);
		SentencePartExerciseHandler sentencePartExerciseHandler = new SentencePartExerciseHandler(AbstractExerciseHandler.SENTENCEPART);
		
		twoChoiceExerciseHandler.setSuccessor(sentencePartExerciseHandler);
		//twoChoiceExerciseHandler.chooseHandler(1);
	}

}
