package com.revature.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO{
	private static Logger log = Logger.getLogger(ReimbursementDAOImpl.class);

	@Override
	public boolean insert(Reimbursement r) {

		PreparedStatement stmt = null;
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id)" +
					"VALUES (?, ?, ?, ?, ? ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, r.getAmount());
			stmt.setTimestamp(2, new java.sql.Timestamp(r.getTime_submitted().getTime()));	// this needs to set to date
			stmt.setString(3, r.getDescription());
			stmt.setInt(4, r.getAuthor_id());
			stmt.setInt(5, r.getStatus_id());
			stmt.setInt(6, r.getType_id());

			if (!stmt.execute()) {
				return false;
			}
			
		} catch(SQLException ex) {
			log.warn("Unable to insert user", ex);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean update(Reimbursement r) {
		PreparedStatement stmt = null;
		
		try {
			Connection conn = ConnectionUtil.getConnection();
					
			String sql = "UPDATE ers_reimbursement SET reimb_resolved = ?, reimb_resolver = ?, reimb_status_id = ? WHERE reimb_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setTimestamp(1, new java.sql.Timestamp(r.getTime_resolved().getTime()));
			stmt.setInt(2, r.getResolver_id());
			stmt.setInt(3, r.getStatus_id());
			stmt.setInt(4, r.getId());
			if (!stmt.execute()) {
				return false;
			}
			
		} catch(SQLException ex) {
			log.warn("Unable to update reimbursement", ex);
			return false;
		}
		
		return true;
	}

	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> list = new ArrayList<Reimbursement>();

		try {
			Connection conn = ConnectionUtil.getConnection();
					
			String sql = "SELECT * FROM ers_reimbursement";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("reimb_id");
				int amount = rs.getInt("reimb_amount");
				Date time_submitted = rs.getTimestamp("reimb_submitted");
				Date time_resolved = rs.getTimestamp("reimb_resolved");
				String description = rs.getString("reimb_description");
				//File receipt = rs.getBinaryStream("reimb_receipt");
				int author_id = rs.getInt("reimb_author");
				int resolver_id = rs.getInt("reimb_resolver");
				int status_id = rs.getInt("reimb_status_id");
				int type_id = rs.getInt("reimb_type_id");
				
				Reimbursement r = new Reimbursement(id, amount, time_submitted, time_resolved, description,
					 author_id, resolver_id, status_id, type_id);
				list.add(r);
			}
			
		} catch(SQLException ex) {
			log.warn("Unable to retieve all reimbursements", ex);
		}
		
		return list;
	}

	@Override
	public List<Reimbursement> findAllPending() {
		List<Reimbursement> list = new ArrayList<Reimbursement>();

		try {
			Connection conn = ConnectionUtil.getConnection();
					
			String sql = "SELECT * FROM ers_reimbursement where reimb_status_id = 1";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("reimb_id");
				int amount = rs.getInt("reimb_amount");
				Date time_submitted = rs.getTimestamp("reimb_submitted");
				Date time_resolved = rs.getTimestamp("reimb_resolved");
				String description = rs.getString("reimb_description");
				//File receipt = rs.getBinaryStream("reimb_receipt");
				int author_id = rs.getInt("reimb_author");
				int resolver_id = rs.getInt("reimb_resolver");
				int status_id = rs.getInt("reimb_status_id");
				int type_id = rs.getInt("reimb_type_id");
				
				Reimbursement r = new Reimbursement(id, amount, time_submitted, time_resolved, description,
					 author_id, resolver_id, status_id, type_id);
				list.add(r);
			}
			
		} catch(SQLException ex) {
			log.warn("Unable to retieve all pending reimbursements", ex);
		}
		
		return list;
	}

	@Override
	public List<Reimbursement> findAllResolved() {
		List<Reimbursement> list = new ArrayList<Reimbursement>();

		try {
			Connection conn = ConnectionUtil.getConnection();
					
			String sql = "SELECT * FROM ers_reimbursement where reimb_status_id != 1";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("reimb_id");
				int amount = rs.getInt("reimb_amount");
				Date time_submitted = rs.getTimestamp("reimb_submitted");
				Date time_resolved = rs.getTimestamp("reimb_resolved");
				String description = rs.getString("reimb_description");
				//File receipt = rs.getBinaryStream("reimb_receipt");
				int author_id = rs.getInt("reimb_author");
				int resolver_id = rs.getInt("reimb_resolver");
				int status_id = rs.getInt("reimb_status_id");
				int type_id = rs.getInt("reimb_type_id");
				
				Reimbursement r = new Reimbursement(id, amount, time_submitted, time_resolved, description,
					 author_id, resolver_id, status_id, type_id);
				list.add(r);
			}
			
		} catch(SQLException ex) {
			log.warn("Unable to retieve all resolved reimbursements", ex);
		}
		
		return list;
	}

}
