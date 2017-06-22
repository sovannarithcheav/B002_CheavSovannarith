package org.kshrd.model;

public class Student {
	
	private int id;
	private String name;
	private String gender;
	
	public Student(int id, String name, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
	}
	public Student() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

}
