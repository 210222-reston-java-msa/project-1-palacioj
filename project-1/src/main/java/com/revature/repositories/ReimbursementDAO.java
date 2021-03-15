package com.revature.repositories;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDAO {
	public boolean insert(Reimbursement r);
	public boolean update(Reimbursement r);
	
	public List<Reimbursement> findAll();
	public List<Reimbursement> findAllPending();
	public List<Reimbursement> findAllResolved();
}
