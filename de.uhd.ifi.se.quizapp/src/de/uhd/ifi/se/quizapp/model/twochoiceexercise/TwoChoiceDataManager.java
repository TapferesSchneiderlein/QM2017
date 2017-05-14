package de.uhd.ifi.se.quizapp.model.twochoiceexercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import de.uhd.ifi.se.quizapp.model.DataManager;

public class TwoChoiceDataManager extends DataManager {

	public TwoChoiceDataManager() {

	}

	/*
	 * Retrieves all two choice exercises from database.
	 */
	public List<TwoChoiceExercise> getExercises() throws ClassNotFoundException, SQLException {
		List<TwoChoiceExercise> exercises = new ArrayList<TwoChoiceExercise>();
		ResultSet resultSet;

		String sql = "SELECT * FROM exercise WHERE type = 1";
		Statement stmt = this.getConnection().createStatement();

		resultSet = stmt.executeQuery(sql);

		while (resultSet.next()) {
			ArrayList<BooleanStatement> booleanStatements = contentToBooleanStatements(resultSet.getString(3));
			TwoChoiceExercise exercise = new TwoChoiceExercise(resultSet.getInt(1), resultSet.getInt(2),
					StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), booleanStatements);

			exercises.add(exercise);
		}
		stmt.close();
		return exercises;
	}

	/*
	 * Parses the content of the exercise separated by "|" to BooleanStatements.
	 */
	public static ArrayList<BooleanStatement> contentToBooleanStatements(String content) {
		ArrayList<String> contentParts = new ArrayList<String>(Arrays.asList(content.split("\\|")));

		ArrayList<BooleanStatement> booleanStatements = new ArrayList<BooleanStatement>();

		for (int i = 0; i < contentParts.size(); i++) {
			if (!contentParts.get(i).equals("on") && !contentParts.get(i).equals("")) {
				BooleanStatement statement = new BooleanStatement(StringEscapeUtils.unescapeHtml(contentParts.get(i)));
				if (i + 1 < contentParts.size() && contentParts.get(i + 1).equals("on")) {
					statement.setCorrect(true);
				}
				booleanStatements.add(statement);
			}
		}
		return booleanStatements;
	}

	/*
	 * Returns a String with boolean statements parts separated by "|". True
	 * statements are followed by "on" (to be stored in the database).
	 */
	public static String booleanStatementsToString(ArrayList<BooleanStatement> booleanStatements) {
		StringBuffer contentBuffer = new StringBuffer();

		for (BooleanStatement booleanStatement : booleanStatements) {
			// parts separated by "|" since it is rarely used in a
			// sentence
			contentBuffer.append(booleanStatement.getStatement() + "|");
			if (booleanStatement.isCorrect()) {
				contentBuffer.append("on|");
			} else {
				contentBuffer.append("|");
			}
		}
		return contentBuffer.toString();
	}

	/*
	 * Retrieves a two-choice-exercise from database by id.
	 */
	public TwoChoiceExercise getExercise(int id) throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;

		String sql = "SELECT * FROM exercise WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, id);
		resultSet = stmt.executeQuery();

		TwoChoiceExercise exercise;

		ArrayList<BooleanStatement> booleanStatements = contentToBooleanStatements(resultSet.getString(3));
		exercise = new TwoChoiceExercise(resultSet.getInt(1), resultSet.getInt(2),
				StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), booleanStatements);

		stmt.close();
		resultSet.close();

		return exercise;
	}

	/*
	 * Inserts an object of class TwoChoiceExercise into database.
	 */
	public void insertExercise(TwoChoiceExercise exercise) throws SQLException, ClassNotFoundException {
		String content = booleanStatementsToString(exercise.getBooleanStatements());

		String sql = "INSERT INTO exercise (difficulty, content, description, width, height, information_id, type) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exercise.getDifficulty());
		stmt.setString(2, StringEscapeUtils.escapeHtml(content));
		stmt.setString(3, exercise.getDescription());
		stmt.setLong(4, 0);
		stmt.setLong(5, 0);
		stmt.setInt(6, exercise.getInformationId());
		stmt.setInt(7, 1);

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Insertion of exercise was successful.");
		} else {
			System.out.println("Insertion of exercise failed.");
		}
	}

	/*
	 * Update a two-choice-exercise in database.
	 */
	public void updateExercise(TwoChoiceExercise exercise) throws SQLException, ClassNotFoundException {
		String content = booleanStatementsToString(exercise.getBooleanStatements());

		String sql = "UPDATE exercise SET difficulty = ?, content = ?, description = ?,"
				+ "width = ?, height = ?, information_id = ? WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exercise.getDifficulty());
		stmt.setString(2, StringEscapeUtils.escapeHtml(content));
		stmt.setString(3, exercise.getDescription());
		stmt.setLong(4, 0);
		stmt.setLong(5, 0);
		stmt.setInt(6, exercise.getInformationId());
		stmt.setInt(7, exercise.getId());

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Update of sentence-part-exercise was successful.");
		} else {
			System.out.println("Update of sentence-part-exercise failed.");
		}
	}

	/*
	 * Retrieves two-choice-exercises from database by difficulty.
	 */
	public List<TwoChoiceExercise> getExercises(int difficulty) throws ClassNotFoundException, SQLException {
		List<TwoChoiceExercise> exercises = new ArrayList<TwoChoiceExercise>();

		String sql = "SELECT * FROM exercise, information WHERE information.id = exercise.information_id AND exercise.difficulty = ? "
				+ "AND exercise.type = ?";

		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, difficulty);
		stmt.setInt(2, 1);

		ResultSet resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			ArrayList<BooleanStatement> booleanStatements = contentToBooleanStatements(resultSet.getString(3));
			TwoChoiceExercise exercise = new TwoChoiceExercise(resultSet.getInt(1), resultSet.getInt(2),
					StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), booleanStatements);

			exercises.add(exercise);
		}
		stmt.close();
		resultSet.close();

		return exercises;
	}

}
