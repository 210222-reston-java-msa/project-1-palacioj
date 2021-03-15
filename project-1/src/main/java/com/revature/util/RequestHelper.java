package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginTemplate;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class RequestHelper {
	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class);
		
		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();
		
		log.info("User attempted to login with username: " + username);
		User u = UserService.confirmLogin(username, password);
		
		if (u != null) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			pw.println(om.writeValueAsString(u));
			log.info(username + " has successfully login!");
		} else {
			res.setStatus(204);
		}
	}
	
	public static void processLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession(false); // I'm capturing the session, but if there ISN'T one, I don't create a new one
		
		log.info("Processing logout");
		
		if(session != null) {
			String username = (String) session.getAttribute("username");
			log.info(username + " has logged out");
			session.invalidate();
		}
			
		res.setStatus(200);
	}
	
	public static void processError(HttpServletRequest req, HttpServletResponse res) throws IOException{
		try {
			req.getRequestDispatcher("error.html").forward(req, res);
			// we do NOT create a new request
			// we also maintain the url...
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void processEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 1. Set the content type to app/json because we will be sending json data back to the client
		// stuck alongside the response
		res.setContentType("application/json");
		
		// 2. Get a list of all Employees in the DB
		List<User> allEmps = UserService.findAllEmp();
		
		// 3. Turn the list of Java Objs to a JSON string
		String json = om.writeValueAsString(allEmps);
		
		// 4. Use getWriter() from the response object to return the json string
		PrintWriter pw = res.getWriter();
		pw.println(json);
	}

	public static void processReimbursementRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		Reimbursement reimb = om.readValue(body, Reimbursement.class);
		
		log.info("User attempted to send request: " + reimb);
		boolean success = ReimbursementService.insert(reimb);
		
		if (success) {
			HttpSession session = req.getSession();
			session.setAttribute("success", success);
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			log.info(reimb + " has successfully added to DB!");
			pw.println(om.writeValueAsString(reimb));
		} else {
			res.setStatus(204);
		}
	}
	
	public static void processPendingRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		List<Reimbursement> allPending = ReimbursementService.listAllPending();
		String json = om.writeValueAsString(allPending);
		PrintWriter pw = res.getWriter();
		pw.println(json);
	}
	
	public static void processResolvedRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		List<Reimbursement> allPending = ReimbursementService.listAllResolved();
		String json = om.writeValueAsString(allPending);
		PrintWriter pw = res.getWriter();
		pw.println(json);
	}
}
