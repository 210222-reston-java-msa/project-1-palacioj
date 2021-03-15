package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.ReimbursementDAOImpl;

public class ReimbursementService {
	public static ReimbursementDAO rDao = new ReimbursementDAOImpl();
	
	public static boolean insert(Reimbursement r) {
		return rDao.insert(r);
	}
	
	public static boolean update(Reimbursement r) {
		return rDao.update(r);
	}
	
	public static List<Reimbursement> listAll() {
		return rDao.findAll();
	}
	
	public static List<Reimbursement> listAllPending() {
		return rDao.findAllPending();
	}
	
	public static List<Reimbursement> listAllResolved() {
		return rDao.findAllResolved();
	}

	public static List<Reimbursement> listUserReimb(int user_id) {
		List<Reimbursement> all = listAll();
		List<Reimbursement> userReimbursementList = new ArrayList<Reimbursement>();
		
		for(Reimbursement r: all) {
			if (r.getId() == user_id) {
				userReimbursementList.add(r);
			}
		}
		
		return userReimbursementList;
	}
	
	public static List<Reimbursement> listUserPending(int user_id) {
		List<Reimbursement> all = listAllPending();
		List<Reimbursement> userReimbursementList = new ArrayList<Reimbursement>();
		
		for(Reimbursement r: all) {
			if (r.getId() == user_id) {
				userReimbursementList.add(r);
			}
		}
		
		return userReimbursementList;
	}
	
	public List<Reimbursement> listUserResolved(int user_id) {
		List<Reimbursement> all = listAllResolved();
		List<Reimbursement> userReimbursementList = new ArrayList<Reimbursement>();
		
		for(Reimbursement r: all) {
			if (r.getId() == user_id) {
				userReimbursementList.add(r);
			}
		}
		
		return userReimbursementList;		
	}
}
