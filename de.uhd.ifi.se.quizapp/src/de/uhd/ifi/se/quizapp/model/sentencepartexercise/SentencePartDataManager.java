package de.uhd.ifi.se.quizapp.model.sentencepartexercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import de.uhd.ifi.se.quizapp.model.DataManager;

/** Sentence part data manager */
public class SentencePartDataManager extends DataManager {

	public SentencePartDataManager() {
		super();
	}

	/*
	 * Retrieves all two choice exercises from database.
	 */
	public List<SentencePartExercise> getExercises() throws ClassNotFoundException, SQLException {
		List<SentencePartExercise> exercises = new ArrayList<SentencePartExercise>();
		ResultSet resultSet;

		String sql = "SELECT * FROM exercise WHERE type = 2";
		Statement stmt = this.getConnection().createStatement();

		resultSet = stmt.executeQuery(sql);

		while (resultSet.next()) {
			ArrayList<Sentence> sentences = contentToSentences(resultSet.getString(3), resultSet.getInt(5),
					resultSet.getInt(6));
			SentencePartExercise exercise = new SentencePartExercise(resultSet.getInt(1), resultSet.getInt(2),
					StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), sentences);
			exercises.add(exercise);
		}
		stmt.close();
		return exercises;
	}

	/*
	 * Parses the content of the exercise separated by "|" to sentences.
	 */
	public static ArrayList<Sentence> contentToSentences(String content, int numberOfSentenceParts,
			int numberOfSentences) {
		ArrayList<String> contentParts = new ArrayList<String>(Arrays.asList(content.split("\\|")));

		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		for (int i = 0; i < numberOfSentences; i++) {
			List<String> sentenceParts = new ArrayList<String>();
			for (int j = 0; j < numberOfSentenceParts; j++) {
				String sentencePart = contentParts.get(i * numberOfSentenceParts + j);
				sentenceParts.add(StringEscapeUtils.unescapeHtml(sentencePart));
			}
			sentences.add(new Sentence(sentenceParts));
		}
		return sentences;
	}

	/*
	 * Returns a String with sentences and sentence parts separated by "|" (to
	 * be stored in the database).
	 */
	public static String sentencesToString(ArrayList<Sentence> sentences) {
		StringBuffer contentBuffer = new StringBuffer();
		for (Sentence sentence : sentences) {
			for (String sentencePart : sentence.getSentenceParts()) {
				contentBuffer.append(sentencePart + "|");
			}
		}
		return contentBuffer.toString();
	}

	/*
	 * Retrieves a sentence-part-exercise from database by id.
	 */
	public SentencePartExercise getExercise(int exerciseId) throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;

		String sql = "SELECT * FROM exercise WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exerciseId);
		resultSet = stmt.executeQuery();

		SentencePartExercise exercise;

		ArrayList<Sentence> sentences = contentToSentences(resultSet.getString(3), resultSet.getInt(5),
				resultSet.getInt(6));
		exercise = new SentencePartExercise(resultSet.getInt(1), resultSet.getInt(2),
				StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), sentences);

		stmt.close();
		resultSet.close();

		return exercise;
	}

	/*
	 * Inserts an object of class SentencePartExercise into database.
	 */
	public void insertExercise(SentencePartExercise exercise) throws SQLException, ClassNotFoundException {
		ArrayList<Sentence> sentences = exercise.getSentences();
		String content = sentencesToString(sentences);

		String sql = "INSERT INTO exercise (difficulty, content, description, width, height, information_id, type) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exercise.getDifficulty());
		stmt.setString(2, StringEscapeUtils.escapeHtml(content));
		stmt.setString(3, exercise.getDescription());
		stmt.setLong(4, sentences.get(0).getNumberOfSentenceParts());
		stmt.setLong(5, sentences.size());
		stmt.setInt(6, exercise.getInformationId());
		stmt.setInt(7, 2);

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Insertion of sentence-part-exercise was successful.");
		} else {
			System.out.println("Insertion of sentence-part-exercise failed.");
		}
	}

	/*
	 * Update a sentence-part-exercise in database.
	 */
	public void updateExercise(SentencePartExercise exercise) throws SQLException, ClassNotFoundException {
		ArrayList<Sentence> sentences = exercise.getSentences();
		String content = sentencesToString(sentences);

		String sql = "UPDATE exercise SET difficulty = ?, content = ?, description = ?,"
				+ "width = ?, height = ?, information_id = ? WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, exercise.getDifficulty());
		stmt.setString(2, StringEscapeUtils.escapeHtml(content));
		stmt.setString(3, exercise.getDescription());
		stmt.setLong(4, sentences.get(0).getNumberOfSentenceParts());
		stmt.setLong(5, sentences.size());
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
	 * Retrieves sentence-part-exercises from database by difficulty.
	 */
	public List<SentencePartExercise> getExercises(int difficulty) throws ClassNotFoundException, SQLException {
		List<SentencePartExercise> exercises = new ArrayList<SentencePartExercise>();

		String sql = "SELECT * FROM exercise, information WHERE information.id = exercise.information_id AND exercise.difficulty = ? "
				+ "AND exercise.type = ?";

		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, difficulty);
		stmt.setInt(2, 2);

		ResultSet resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			ArrayList<Sentence> sentences = contentToSentences(resultSet.getString(3), resultSet.getInt(5),
					resultSet.getInt(6));
			SentencePartExercise exercise = new SentencePartExercise(resultSet.getInt(1), resultSet.getInt(2),
					StringEscapeUtils.unescapeHtml(resultSet.getString(4)), resultSet.getInt(7), sentences);
			exercises.add(exercise);
		}

		stmt.close();
		resultSet.close();

		return exercises;
	}

}
