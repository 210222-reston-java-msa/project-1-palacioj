package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.RequestHelper;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String URI = request.getRequestURI().replace("/project-1/", "");
		
		switch(URI) {
		case "login":
			RequestHelper.processLogin(request, response);
			break;
		case "logout":
			RequestHelper.processLogout(request, response);
			break;
		case "employees":
			RequestHelper.processEmployees(request, response);
			break;
		case "error":
			RequestHelper.processError(request, response);
			break;
		case "pending":
			RequestHelper.processPendingRequest(request, response);
			break;
		case "resolved":
			RequestHelper.processResolvedRequest(request, response);
			break;
		case "reimbursement":
			RequestHelper.processReimbursementRequest(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
