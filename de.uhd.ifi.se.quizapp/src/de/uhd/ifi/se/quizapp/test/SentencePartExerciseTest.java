package de.uhd.ifi.se.quizapp.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.*;

public class SentencePartExerciseTest {

	@Test
	public void testSentenceInExercise() {
		Sentence sentence = new Sentence();
		sentence.addSentencePart("Obst und Gemüse");
		sentence.addSentencePart("sind sehr gesund");
		
		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		sentences.add(sentence);
		
		SentencePartExercise exercise = new SentencePartExercise();
		exercise.setSentences(sentences);
		
		assertTrue(exercise.getSentences().contains(sentence));		
	}
}
