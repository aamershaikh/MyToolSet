package com.aamer.rest.webservices.restfulwebservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.aamer.rest.webservices.restfulwebservices.bean.User;

@Component
public class UserService {
	
	private static List<User> userList = new ArrayList<>();
	private static int usercount = 4;
	
	static {
		userList.add(new User(1, "Aamer", new Date()));
		userList.add(new User(2, "Shahila", new Date()));
		userList.add(new User(3, "Areeb", new Date()));
		userList.add(new User(4, "Zainab", new Date()));
	}
	
	//getAllUsers
	public List<User> getAllUsers() {
		return userList;
	}
	
	//addUser
	public User addUser(User user) {
		user.setUserid(++usercount);
		userList.add(user);
		return user;
	}
	
	//getOneUser
	public User getOneUser(int id){
		for (User user : userList) {
			if(user.getUserid() == id) {
				return user;
			}
		}
		return null;
	}
	
	//deleteOneUser
	public User deleteOneUser(int id){
		for (User user : userList) {
			if(user.getUserid() == id) {
				userList.remove(user);
				return user;
			}
		}
		return null;
	}
	
	
	
}
