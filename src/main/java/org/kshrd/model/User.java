package org.kshrd.model;

public class User {
	
	private String fullName;
	private String address; 
	private String userName;
	private String password;
	private String confirm_password;
	private String email;
	
	public User(){}

	public User(String fullName, String address, String userName, String password, String confirm_password, String email) {
		super();
		this.fullName = fullName;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.confirm_password = confirm_password;
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	

}
