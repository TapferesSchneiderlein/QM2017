package de.uhd.ifi.se.quizapp.model.twochoiceexercise;

/** Statements with two options */
public class BooleanStatement {
	/** Statement */
	private String statement;
	/** Is answer correct */
	private boolean correct;

	public BooleanStatement() {
	}

	public BooleanStatement(String statement) {
		this.statement = statement;
		this.correct = false;
	}

	public BooleanStatement(String statement, boolean isCorrect) {
		this(statement);
		this.correct = isCorrect;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean isCorrect) {
		this.correct = isCorrect;
	}

	public String toString() {
		StringBuffer contentBuffer = new StringBuffer();

		contentBuffer.append(this.getStatement() + ":");
		if (this.isCorrect()) {
			contentBuffer.append("true");
		} else {
			contentBuffer.append("");
		}
		return contentBuffer.toString();
	}

}
