package de.uhd.ifi.se.quizapp.model;

/** Abstract Class for Exercises */
public abstract class AbstractExercise {
	
	/** Exercise ID */
	private int exerciseId;	
	/** Information ID */
	private int informationId; 
	/** Description */
	private String description; 
	/** Difficulty */
	private int difficulty;		

	public AbstractExercise() {
	}

	public AbstractExercise(int exerciseId) {
		this();
		this.exerciseId = exerciseId;
	}

	public AbstractExercise(int exerciseId, int difficulty, String description, int informationId) {
		this(exerciseId);
		this.difficulty = difficulty;
		this.description = description;
		this.informationId = informationId;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInformationId(int informationId) {
		this.informationId = informationId;
	}

	public int getInformationId() {
		return this.informationId;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setId(int exerciseId) {
		this.exerciseId = exerciseId;
	}
	
}
