package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class UserService {
	public static UserDAO uDao = new UserDAOImpl();
	
	public static boolean update(User u) {
		return uDao.update(u);
	}
	
	public static List<User> findAll() {
		return uDao.findAll();
	}
	
	public static List<User> findAllEmp() {
		return uDao.findAllEmp();
	}
	
	public static User findByUser(String username) {
		List<User> all = findAll();
		for(User u: all) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
	
	public static User confirmLogin(String username, String password) {
		User u = findByUser(username);
		
		if(u == null) {
			return null;
		}

		if (u.getPassword().equals(password)){
			return u;
		}
		
		return null;
	}

}
