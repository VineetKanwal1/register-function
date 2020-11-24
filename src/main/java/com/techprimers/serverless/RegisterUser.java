package com.techprimers.serverless;

public class RegisterUser {

	private String userId;
	private String password;
	private String address;

	public RegisterUser() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String userpassword) {
		this.password = userpassword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
