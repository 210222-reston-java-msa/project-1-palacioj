package com.revature.models;

import java.io.File;
import java.util.Date;

public class Reimbursement {
	private int id;
	private int amount;
	private Date time_submitted;
	private Date time_resolved;
	private String description;
	private File receipt;
	private int author_id;
	private int resolver_id;
	private int status_id;
	private int type_id;
	
	public Reimbursement() {	}

	public Reimbursement(int id, int amount, Date time_submitted, Date time_resolved, String description, File receipt,
			int author_id, int resolver_id, int status_id, int type_id) {
		super();
		this.id = id;
		this.amount = amount;
		this.time_submitted = time_submitted;
		this.time_resolved = time_resolved;
		this.description = description;
		this.receipt = receipt;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	public Reimbursement(int id, int amount, Date time_submitted, Date time_resolved, String description,
			int author_id, int resolver_id, int status_id, int type_id) {
		super();
		this.id = id;
		this.amount = amount;
		this.time_submitted = time_submitted;
		this.time_resolved = time_resolved;
		this.description = description;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}
	
	public Reimbursement(int amount, Date time_submitted, String description, File receipt, int author_id, int status_id,
			int type_id) {
		super();
		this.amount = amount;
		this.time_submitted = time_submitted;
		this.description = description;
		this.receipt = receipt;
		this.author_id = author_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}
	
	public Reimbursement(int amount, Date time_submitted, String description, int author_id, int status_id,
			int type_id) {
		super();
		this.amount = amount;
		this.time_submitted = time_submitted;
		this.description = description;
		this.author_id = author_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getTime_submitted() {
		return time_submitted;
	}

	public void setTime_submitted(Date time_submitted) {
		this.time_submitted = time_submitted;
	}

	public Date getTime_resolved() {
		return time_resolved;
	}

	public void setTime_resolved(Date time_resolved) {
		this.time_resolved = time_resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public File getReceipt() {
		return receipt;
	}

	public void setReceipt(File receipt) {
		this.receipt = receipt;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public int getResolver_id() {
		return resolver_id;
	}

	public void setResolver_id(int resolver_id) {
		this.resolver_id = resolver_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", time_submitted=" + time_submitted
				+ ", time_resolved=" + time_resolved + ", description=" + description + ", receipt=" + receipt
				+ ", author_id=" + author_id + ", resolver_id=" + resolver_id + ", status_id=" + status_id
				+ ", type_id=" + type_id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + author_id;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + resolver_id;
		result = prime * result + status_id;
		result = prime * result + ((time_resolved == null) ? 0 : time_resolved.hashCode());
		result = prime * result + ((time_submitted == null) ? 0 : time_submitted.hashCode());
		result = prime * result + type_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount != other.amount)
			return false;
		if (author_id != other.author_id)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (resolver_id != other.resolver_id)
			return false;
		if (status_id != other.status_id)
			return false;
		if (time_resolved == null) {
			if (other.time_resolved != null)
				return false;
		} else if (!time_resolved.equals(other.time_resolved))
			return false;
		if (time_submitted == null) {
			if (other.time_submitted != null)
				return false;
		} else if (!time_submitted.equals(other.time_submitted))
			return false;
		if (type_id != other.type_id)
			return false;
		return true;
	}
	
	
}
