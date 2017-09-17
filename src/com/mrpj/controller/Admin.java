package com.mrpj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mrpj.model.AA;

public class Admin extends HttpServlet {
	HttpSession session;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task=request.getParameter("task");
		if(task==null)
		{
			request.getRequestDispatcher("pages/admin/index.jsp").forward(request, response);  ///////////////////////////done  completely
		}
		else
			doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task=request.getParameter("task");
		if(task.equals("login"))
		{
			request.getRequestDispatcher("pages/admin/login/login.jsp").forward(request, response);//////////////////////complete
			
		}
		
		else
			if(task.equals("login-form"))     //////////////////completed
		{
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			AA aa=new AA();
			String alert=aa.validateUser(email ,password);
			if(!alert.contains("Oops"))
			{
				session=request.getSession();
				//session.setAttribute("useremail", email);
				response.setHeader("Cache-Control","no-cache");
				response.setHeader("Cache-Control","no-store");
				response.setDateHeader("Expires", 0);
				response.setHeader("Pragma","no-cache");

				session.setAttribute("name", email);
				request.getRequestDispatcher("pages/admin/index.jsp").forward(request, response);
			}
			else 
			{
				request.setAttribute("alert", alert);
				request.getRequestDispatcher("pages/admin/login/login.jsp").forward(request, response);
			}
		}
		else
			if(task.equals("viewStudentProfile"))      ////////////////////////completed
			{
			   if(session==null)
			   {
				request.setAttribute("alert", "Please Login First..!");
				request.getRequestDispatcher("pages/admin/login/login.jsp").forward(request, response);
			   }
			else
				{
				String StudentID= request.getParameter("StudentID");
				AA aa = new AA();
				String cgpi=aa.getCGPI(StudentID);
				
				request.setAttribute("StudentID",StudentID);
				request.setAttribute("cgpi",cgpi);
				request.getRequestDispatcher("pages/admin/studentProfile.jsp").forward(request, response);
				}
		    }//end of view student profile
	else
		if(task.equals("search"))        ///////////////////////completed
		{
			if(session==null)
			{
				request.setAttribute("alert", "Please Login First..!");
				request.getRequestDispatcher("pages/admin/login/login.jsp").forward(request, response);
			}
			else
			{
				String name=request.getParameter("name");
				request.getRequestDispatcher("pages/admin/searchResult.jsp?name="+name).forward(request, response);
			}
		}
		
		else
			if(task.equals("updateStudentProfile"))      
			{
				if(session==null)
				{
					request.setAttribute("alert", "Please Login First..!");
					request.getRequestDispatcher("pages/admin/login/login.jsp").forward(request, response);
				}
				else
				{
					String id= request.getParameter("StudentID");
					request.setAttribute("id", id);
					request.getRequestDispatcher("pages/admin/updateProfile.jsp").forward(request, response);
					
					/* String name=request.getParameter("name");
					request.getRequestDispatcher("pages/admin/searchResult.jsp?name="+name).forward(request, response);
			*/	}
			}
		
			else
				if(task.equals("updateStudentData"))       
				{
					if(session==null)
					{
						request.setAttribute("alert", "Please Login First..!");
						request.getRequestDispatcher("pages/admin/login/login.jsp").forward(request, response);
					}
					else
					{
						String id=request.getParameter("StudentID");
						String cname=request.getParameter("cname");
						String pkg=request.getParameter("pkg");
						String status="1";
						
						AA aa = new AA();
						String result=aa.updatedata(id, cname, pkg, status);
						
						request.setAttribute("updatestatus", "YES");
						//request.setAttribute("status", id+"\n"+cname+"\n"+pkg+"\n"+status);
						request.getRequestDispatcher("pages/admin/index.jsp").forward(request, response);
					}
				}
		
		
	else 
		if (task.equals("logout")) ////////////////////////completed
	    {
		if(session==null){
			request.getRequestDispatcher("pages/admin/index.jsp").forward(request, response);	
		}
		else{
		session.removeAttribute("alert");
		session.invalidate();
		session=null;
		request.getRequestDispatcher("pages/admin/index.jsp").forward(request, response);
		}
	}
	else {
		request.getRequestDispatcher("pages/admin/index.jsp").forward(request, response);
	}
}
}