SET SCHEMA 'ers'

CREATE TABLE ers_reimbursement (
	reimb_id SERIAL PRIMARY KEY,
	reimb_amount INTEGER,
	reimb_submitted TIMESTAMP,
	reimb_resolved TIMESTAMP,
	reimb_description VARCHAR(250),
	reimb_receipt BYTEA,
	reimb_author INTEGER,
	reimb_resolver INTEGER,
	reimb_status_id INTEGER,
	reimb_type_id INTEGER
);

CREATE TABLE ers_users (
	ers_users_id SERIAL PRIMARY KEY,
	ers_username VARCHAR(250) UNIQUE,
	ers_password VARCHAR(250),
	ers_first_name VARCHAR(100),
	ers_last_name VARCHAR(100),
	user_email VARCHAR(150) UNIQUE,
	user_role_id INTEGER
);

--The following unique is added as a fail-safe to prevent duplicate values inserts into the table
CREATE TABLE ers_reimbursement_status (
	reimb_status_id SERIAL PRIMARY KEY,
	reimb_status VARCHAR(10) UNIQUE
);

CREATE TABLE ers_reimbursement_type (
	reimb_type_id SERIAL PRIMARY KEY,
	reimb_type VARCHAR(10) UNIQUE
);

CREATE TABLE ers_user_roles(
	ers_user_role_id SERIAL PRIMARY KEY,
	user_role VARCHAR(10) UNIQUE
);

ALTER TABLE ers_reimbursement
ADD FOREIGN KEY(REIMB_AUTHOR) REFERENCES ers_users(ERS_USERS_ID)

ALTER TABLE ers_reimbursement
ADD FOREIGN KEY(REIMB_RESOLVER) REFERENCES ers_users(ERS_USERS_ID)

ALTER TABLE ers_reimbursement 
ADD FOREIGN KEY(reimb_status_id) REFERENCES ers_reimbursement_status(reimb_status_id)

ALTER TABLE ers_reimbursement 
ADD FOREIGN KEY(reimb_type_id) REFERENCES ers_reimbursement_type(reimb_type_id)

ALTER TABLE ers_users
ADD FOREIGN KEY(user_role_id) REFERENCES ers_user_roles(ers_user_role_id)

INSERT INTO ers_reimbursement_status (reimb_status)
	VALUES ('Pending'),
			('Approved'),
			('Denied')
			
INSERT INTO ers_reimbursement_type (reimb_type)
	VALUES ('LODGING'),
			('TRAVEL'),
			('FOOD'),
			('OTHER')
			
--I know I can changed the length of string to be able to accept Finance Manager but I am holder it off for the moment
INSERT INTO ers_user_roles(user_role)
	VALUES ('Employee'),
			('Fi Manager')