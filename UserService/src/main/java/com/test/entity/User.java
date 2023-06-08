package com.test.entity;

import java.util.List;

public class User {

	private String id;
	private String name;
	private String email;
	private String about;
	private List<Rating> ratings;

	public User() {
	}

	public User(String id, String name, String email, String about, List<Rating> ratings) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.about = about;
		this.ratings = ratings;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", about=" + about + ", ratings=" + ratings
				+ "]";
	}

}
