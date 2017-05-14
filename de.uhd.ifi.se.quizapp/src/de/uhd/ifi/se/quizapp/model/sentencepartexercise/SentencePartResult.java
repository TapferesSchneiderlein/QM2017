package de.uhd.ifi.se.quizapp.model.sentencepartexercise;

import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.Result;

public class SentencePartResult extends Result {

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
