package de.uhd.ifi.se.quizapp.model;

public class Information {
	private int id;
	private String name;
	private String text;

	public Information() {

	}

	public Information(String name, String text) {
		this.name = name;
		this.text = text;
	}

	public Information(int id, String name, String text) {
		this.id = id;
		this.name = name;
		this.text = text;
	}

	public int getID() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}