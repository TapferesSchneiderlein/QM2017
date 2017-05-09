package de.uhd.ifi.se.quizapp.model.twochoiceexercise;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TwoChoiceResultTest {
	private TwoChoiceResult result;
	
	@Before
	public void setUp() throws Exception {
		// Mock-Daten für Ergebnis generieren
		ArrayList<BooleanStatement> statements = new ArrayList<BooleanStatement>();
		
		for (int i=1;i<=10;i++) {
			boolean correct = (int)(Math.random() * 10) % 2 == 0; 
			statements.add(new BooleanStatement("Statement " + i, correct));
		}
		
		this.result = new TwoChoiceResult();
		result.setBooleanStatements(statements);
	}
	
	@Test
	public void testGetPercentage() {
		// Testfall 1: Berechnung klappt, Prozentwert liegt im validen Bereich
		float percentage = result.getPercentage();
		assertTrue( ( percentage <= 1.0 && percentage >= 0.0 ) || percentage == -1.0 );
		
		// Testfall 2: Liste mit Ergebnissen ist leer
		result.setBooleanStatements(null);
		percentage = result.getPercentage();
		assertTrue(percentage == -1);
	}

}
