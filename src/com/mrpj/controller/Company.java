package com.mrpj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mrpj.model.CA;

public class Company extends HttpServlet {
	HttpSession session;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task=request.getParameter("task");
		if(task==null){
			request.getRequestDispatcher("pages/company/index.jsp").forward(request, response);
		}
		else
			doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task=request.getParameter("task");
		if(task.equals("login")){
			request.getRequestDispatcher("pages/company/login/login.jsp").forward(request, response);
		}
		else if(task.equals("login-form")){
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			CA ca=new CA();
			String alert=ca.validateUser(email ,password);
			if(!alert.contains("Oops")){
				session=request.getSession();
				//session.setAttribute("useremail", email);
				response.setHeader("Cache-Control","no-cache");
				response.setHeader("Cache-Control","no-store");
				response.setDateHeader("Expires", 0);
				response.setHeader("Pragma","no-cache");

				session.setAttribute("name", email);
				request.getRequestDispatcher("pages/company/index.jsp").forward(request, response);
			}
			else {
				request.setAttribute("alert", alert);
				request.getRequestDispatcher("pages/company/login/login.jsp").forward(request, response);
			}
		}
		else if(task.equals("viewStudentProfile")){
			if(session==null){
				request.setAttribute("alert", "Please Login First..!");
				request.getRequestDispatcher("pages/company/login/login.jsp").forward(request, response);
			}
			else{
				String StudentID= request.getParameter("StudentID");
				CA ca = new CA();
				String cgpi=ca.getCGPI(StudentID);
				request.setAttribute("StudentID",StudentID);
				
				request.setAttribute("cgpi",cgpi);
			request.getRequestDispatcher("pages/company/studentProfile.jsp").forward(request, response);
			}
		}
	else if(task.equals("search")){
		if(session==null){
			request.setAttribute("alert", "Please Login First..!");
			request.getRequestDispatcher("pages/company/login/login.jsp").forward(request, response);
		}
		else{
		String name=request.getParameter("name");
		request.getRequestDispatcher("pages/company/searchResult.jsp?name="+name).forward(request, response);
		}
	}
	else if (task.equals("logout")) {
		if(session==null){
			request.getRequestDispatcher("pages/company/index.jsp").forward(request, response);	
		}
		else{
		session.removeAttribute("alert");
		session.invalidate();
		session=null;
		request.getRequestDispatcher("pages/company/index.jsp").forward(request, response);
		}
	}
	else {
		request.getRequestDispatcher("pages/company/index.jsp").forward(request, response);
	}
}
}