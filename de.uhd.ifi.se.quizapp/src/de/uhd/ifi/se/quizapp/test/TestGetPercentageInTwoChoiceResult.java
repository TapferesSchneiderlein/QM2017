package de.uhd.ifi.se.quizapp.test;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceResult;

public class TestGetPercentageInTwoChoiceResult {

	@Test
	public void testExerciseOneWrongStatementWrongSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", true));

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testExerciseOneWrongStatementCorrectSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));

		assertTrue(result.getPercentage() == 1);
	}

	@Test
	public void testExerciseOneCorrectStatementWrongSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testExerciseOneCorrectStatementCorrectSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", true));

		assertTrue(result.getPercentage() == 1);
	}

	@Test
	public void testExerciseTwoWrongStatementsWrongSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", false));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", true));
		result.getBooleanStatements().add(new BooleanStatement("B", true));

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testExerciseTwoWrongStatementsPartlyCorrectSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", false));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", true));

		Assert.assertEquals(0.5, result.getPercentage(), 0.001);
	}

	@Test
	public void testExerciseTwoWrongStatementsCorrectSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", false));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", false));

		assertTrue(result.getPercentage() == 1);
	}

	@Test
	public void testExerciseTwoPartlyCorrectStatementsWrongSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", true));
		result.getBooleanStatements().add(new BooleanStatement("B", false));

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testExerciseTwoPartlyCorrectStatementsPartlyCorrectSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", false));

		Assert.assertEquals(0.5, result.getPercentage(), 0.001);
	}

	@Test
	public void testExerciseTwoPartlyCorrectStatementsCorrectSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", true));

		assertTrue(result.getPercentage() == 1);
	}

	@Test
	public void testExerciseTwoCorrectStatementsWrongSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", false));

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testExerciseTwoCorrectStatementsPartlyCorrectSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", true));

		Assert.assertEquals(0.5, result.getPercentage(), 0.001);
	}

	@Test
	public void testExerciseTwoCorrectStatementsCorrectSolution() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", true));
		result.getBooleanStatements().add(new BooleanStatement("B", true));

		assertTrue(result.getPercentage() == 1);
	}

	@Test
	public void testResultStatementsNull(){
		TwoChoiceResult result = new TwoChoiceResult();
		result.setBooleanStatements(null);
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));
		result.setExercise(exercise);
		
		assertTrue(result.getPercentage() == -1);
	}
	
	@Test
	public void testExerciseStatementsNull(){
		TwoChoiceResult result = new TwoChoiceResult();
		result.getBooleanStatements().add(new BooleanStatement("A", true));
		
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.setBooleanStatements(null);
		result.setExercise(exercise);
		
		assertTrue(result.getPercentage() == -1);
	}
	
	@Test
	public void testStatementsDifferInLength(){
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", true));
		result.getBooleanStatements().add(new BooleanStatement("B", true));

		assertTrue(result.getPercentage() == -1);
	}
	
	@Test
	public void testNoStatements(){
		TwoChoiceResult result = new TwoChoiceResult();
		result.setExercise(new TwoChoiceExercise());

		assertTrue(result.getPercentage() == 0);
	}
}
