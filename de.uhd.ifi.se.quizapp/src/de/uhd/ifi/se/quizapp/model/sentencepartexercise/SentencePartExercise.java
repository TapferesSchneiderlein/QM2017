package de.uhd.ifi.se.quizapp.model.sentencepartexercise;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.AbstractExercise;

/** Exercise with sentences */
public class SentencePartExercise extends AbstractExercise {
	/** List of sentences */
	private ArrayList<Sentence> sentences;

	public SentencePartExercise() {

	}

	public SentencePartExercise(int exerciseId, int difficulty, String description, int informationId,
			ArrayList<Sentence> sentences) {
		super(exerciseId, difficulty, description, informationId);
		this.sentences = sentences;
	}

	public ArrayList<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(ArrayList<Sentence> sentences) {
		this.sentences = sentences;
	}
	
	public String toString() {
		return this.sentences.toString();
	}

}
