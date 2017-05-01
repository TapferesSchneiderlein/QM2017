package de.uhd.ifi.se.quizapp.model.sentencepartexercise;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.AbstractResult;

/** Result of sentence exercise */
public class SentencePartResult extends AbstractResult {
	/** List of sentences */
	private ArrayList<Sentence> sentences;

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
