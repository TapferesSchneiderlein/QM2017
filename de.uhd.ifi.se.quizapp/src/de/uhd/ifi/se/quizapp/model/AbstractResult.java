package de.uhd.ifi.se.quizapp.model;

/** Abstract class for result handling */
public abstract class AbstractResult {
	/** Exercise */
	private AbstractExercise exercise;

	public AbstractResult() {

	}

	public AbstractExercise getExercise() {
		return exercise;
	}

	public void setExercise(AbstractExercise exercise) {
		this.exercise = exercise;
	}

	public float getPercentage() {
		return 0;
	}
}
