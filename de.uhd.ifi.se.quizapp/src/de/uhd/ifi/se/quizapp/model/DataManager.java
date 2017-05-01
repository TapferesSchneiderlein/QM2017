package de.uhd.ifi.se.quizapp.model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.lang.*;

/** Data Manager class */
public class DataManager {

	/** Database name */
	private String dbName = "org.sqlite.JDBC";
	/** Database path */
    private Path dbPath = Paths.get(new File("").getAbsolutePath(), "WebContent", "db", "heieducation.sqlite");
    /** Database URL */
	private String dbURL = "jdbc:sqlite:" + dbPath.toString();
	/** Database Username */
	private String dbUserName = "";
	/** Database Password */
	private String dbPassword = "";

	/** Connection */
	private Connection conn = null;

	public DataManager() {
		this.conn = initConnection();
	}

	public String getDbName() {
		return this.dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbURL() {
		return this.dbURL;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public String getDbUserName() {
		return this.dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbPassword() {
		return this.dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public Connection initConnection() {
		try {
			Class.forName(this.getDbName());
			this.conn = DriverManager.getConnection(this.getDbURL());
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Could not connect to DB: " + e.getMessage());
		}
		return this.conn;
	}

	public Connection getConnection() throws ClassNotFoundException {
		return this.conn;
	}

	public void putConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("SQL Error: Putting Connection failed.");
			}
		}
	}

	/*
	 * Inserts an object of class Information into database.
	 */
	public boolean insertInformation(Information information) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO information (name, text) VALUES (?, ?)";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setString(1, StringEscapeUtils.escapeHtml(information.getName()));
		stmt.setString(2, StringEscapeUtils.escapeHtml(information.getText()));

		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			return true;
		} else {
			System.err.println("Insertion of information to database failed.");
			return false;
		}
	}

	/*
	 * Retrieves all information from database.
	 */
	public ArrayList<Information> getInformation() throws ClassNotFoundException, SQLException {
		ArrayList<Information> informationList = new ArrayList<Information>();
		ResultSet resultSet;

		String sql = "SELECT * FROM information";
		Statement stmt = this.getConnection().createStatement();

		resultSet = stmt.executeQuery(sql);

		while (resultSet.next()) {
			informationList.add(
					new Information(resultSet.getInt("id"), StringEscapeUtils.unescapeHtml(resultSet.getString("name")),
							StringEscapeUtils.unescapeHtml(resultSet.getString("text"))));
		}
		stmt.close();
		return informationList;
	}

	/*
	 * Retrieves a single information from database by id.
	 */
	public Information getInformation(int informationId) throws ClassNotFoundException, SQLException {
		ResultSet resultSet = null;

		String sql = "SELECT * FROM information WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, informationId);
		resultSet = stmt.executeQuery();

		Information information = new Information(resultSet.getInt("id"),
				StringEscapeUtils.unescapeHtml(resultSet.getString("name")),
				StringEscapeUtils.unescapeHtml(resultSet.getString("text")));

		stmt.close();
		resultSet.close();

		return information;
	}

	/*
	 * Update existing information in database.
	 */
	public boolean updateInformation(Information information) throws SQLException, ClassNotFoundException {
		int informationId = information.getInformationId();
		String name = StringEscapeUtils.escapeHtml(information.getName());
		String text = StringEscapeUtils.escapeHtml(information.getText());

		String sql = "UPDATE information SET text = ?, name = ? WHERE id = ?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setString(1, text);
		stmt.setString(2, name);
		stmt.setInt(3, informationId);

		int status = stmt.executeUpdate();
		stmt.close();

		if (status == 1) {
			System.out.println("Update of information was successful.");
			return true;
		} else {
			System.err.println("Update of information to database failed.");
			return false;
		}
	}

	/*
	 * Deletes an information in database by id.
	 */
	public boolean deleteInformation(int informationId) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM information WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, informationId);
		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Deletion of information was successful.");
			return true;
		} else {
			System.err.println("Deletion of information failed.");
			return false;
		}
	}

	/*
	 * Deletes an information in database.
	 */
	public boolean deleteInformation(Information information) throws SQLException, ClassNotFoundException {
		return this.deleteInformation(information.getInformationId());
	}

	/*
	 * Deletes an exercise by id in database.
	 */
	public boolean deleteExercise(int informationId) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM exercise WHERE id = ?";
		PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		stmt.setInt(1, informationId);
		int status = stmt.executeUpdate();
		stmt.close();
		if (status == 1) {
			System.out.println("Deletion of exercise was successful.");
			return true;
		} else {
			System.out.println("Deletion of exercise was not successful.");
			return false;
		}
	}

	/*
	 * Creates a new database.
	 */
	public void createDatabase() throws ClassNotFoundException {
		// load the sqlite-JDBC driver using the current class loader
		try {
			Class.forName(this.getDbName());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			// create a database connection
			Statement statement = this.getConnection().createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("CREATE TABLE exercise (id integer PRIMARY KEY AUTOINCREMENT,"
					+ "difficulty integer NOT NULL, content text, description varchar(256),"
					+ "width integer NOT NULL, height integer NOT NULL,"
					+ "information_id integer NOT NULL, type integer NOT NULL);");
			statement.executeUpdate("CREATE TABLE information (id integer PRIMARY KEY AUTOINCREMENT,"
					+ "text text, name varchar(256));");
			statement.executeUpdate("CREATE TABLE result (id integer PRIMARY KEY AUTOINCREMENT,"
					+ "exercise_id integer NOT NULL, content text," + "student_id integer DEFAULT NULL)");
			statement.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
