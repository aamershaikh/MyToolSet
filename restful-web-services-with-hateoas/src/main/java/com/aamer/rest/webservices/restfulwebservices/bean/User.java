package com.aamer.rest.webservices.restfulwebservices.bean;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {
	
	private int userid;
	
	@Size(min=2, message="Name should be minimum of 2 characters")		// to validate name contains minimum 2 characters
	private String name;
	
	@Past(message="Birthdate should be in past") 					// to validate date is always in past
	private Date bithdate;
	
	public User() {
	
	}
	
	public User(int userid, String name, Date bithdate) {
		this.userid = userid;
		this.name = name;
		this.bithdate = bithdate;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBithdate() {
		return bithdate;
	}

	public void setBithdate(Date bithdate) {
		this.bithdate = bithdate;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", bithdate=" + bithdate + "]";
	}
	

}
