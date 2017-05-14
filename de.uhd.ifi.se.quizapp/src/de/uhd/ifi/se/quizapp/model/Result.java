package de.uhd.ifi.se.quizapp.model;

public abstract class Result {

	private Exercise exercise;

	public Result() {

	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public float getPercentage() {
		return 0;
	}
}
