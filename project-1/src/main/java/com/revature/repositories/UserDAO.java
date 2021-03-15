package com.revature.repositories;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	public boolean update(User u);
	public List<User> findAll();
	public List<User> findAllEmp();
}
