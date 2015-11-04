package com.example.learningsqliteadvance.model;

import java.io.Serializable;

import android.graphics.Bitmap;

@SuppressWarnings("serial")
public class Author implements Serializable {
	int id;
	String code;
	String name;
	Bitmap image;

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public Author(int id, String code, String name, Bitmap image) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
