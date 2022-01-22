package org.nisvarthafoundation.nisvarthaportal.businessservices.NFUIDisplayBusinessService.domain.model.valueobjects;

public class LoginUser {
	
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
	
	
	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}


	private String userName;
	private String password;
	
	

}
