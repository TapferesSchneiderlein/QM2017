package de.uhd.ifi.se.quizapp.model;

/** Information Class */
public class Information {
	/** Information ID */
	private int informationId;
	/** Name */
	private String name;
	/** Text */
	private String text;

	public Information() {

	}

	public Information(String name, String text) {
		this.name = name;
		this.text = text;
	}

	public Information(int informationId, String name, String text) {
		this.informationId = informationId;
		this.name = name;
		this.text = text;
	}

	public int getID() {
		return this.informationId;
	}

	public void setId(int informationId) {
		this.informationId = informationId;
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