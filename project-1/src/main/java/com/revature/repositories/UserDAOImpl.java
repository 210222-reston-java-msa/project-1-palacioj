package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO{
	private static Logger log = Logger.getLogger(UserDAOImpl.class);
	
	@Override
	public boolean update(User u) {
		PreparedStatement stmt = null;
		
		try {
			Connection conn = ConnectionUtil.getConnection();
					
			String sql = "UPDATE ers_users SET ers_first_name = ?, ers_last_name = ?, ers_user_email = ?, ers_password = ? WHERE ers_users_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getFirstName());
			stmt.setString(2, u.getLastName());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getPassword());
			stmt.setLong(5, u.getId());
			if (!stmt.execute()) {
				return false;
			}
			
		} catch(SQLException ex) {
			log.warn("Unable to update user", ex);
			return false;
		}
		
		return true;
	}

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<User>();

		try {
			Connection conn = ConnectionUtil.getConnection();
					
			String sql = "SELECT * FROM ers_users";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ers_users_id");
				String username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("ers_first_name");
				String lastName = rs.getString("ers_last_name");	
				String email = rs.getString("user_email");
				int role_id = rs.getInt("user_role_id");

				User u = new User(id, username, password, firstName, lastName, email, role_id);
				list.add(u);
			}
			
		} catch(SQLException ex) {
			log.warn("Unable to retieve all users", ex);
		}
		
		return list;
	}

	@Override
	public List<User> findAllEmp() {
		List<User> list = new ArrayList<User>();

		try {
			Connection conn = ConnectionUtil.getConnection();
					
			String sql = "SELECT * FROM ers_users WHERE user_role_id = 1";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ers_users_id");
				String username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("ers_first_name");
				String lastName = rs.getString("ers_last_name");	
				String email = rs.getString("user_email");
				int role_id = rs.getInt("user_role_id");

				User u = new User(id, username, password, firstName, lastName, email, role_id);
				list.add(u);
			}
			
		} catch(SQLException ex) {
			log.warn("Unable to retieve all users", ex);
		}
		
		return list;
	}
}
