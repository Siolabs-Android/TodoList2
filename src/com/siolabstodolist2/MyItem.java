package com.siolabstodolist2;

import java.io.Serializable;

public class MyItem implements Serializable{

	String  category;
	String text;
	
	int id ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.text;
	}
	
}
