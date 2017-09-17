package com.mrpj.controller;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mrpj.model.SA;

public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action=request.getParameter("action");
		if(action == null)
		{
			
			request.getRequestDispatcher("pages/student/index/index.jsp").forward(request, response);  
		}
		else
		{
			doPost(request,response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action= request.getParameter("action"); 
		HttpSession session = request.getSession(); //Starting a session
		int i;
		
		if(action.equals("login"))//To go to login form
		{
			request.setAttribute("alert", "");
			request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);
		}
		
		if(action.equals("signup"))//To go to signup form
		{
			request.setAttribute("alert", "");
			request.getRequestDispatcher("pages/student/signup/signup.jsp").forward(request, response);
		}
		
		if(action.equals("loginform"))//To go to check if user is valid or not
		{
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			SA sa = new SA();
			SA sa1 = new SA();
			int studentid = 0;
			
			String alert=sa.validateUser(email, password);
			if(alert.startsWith("Suc"))
			{
				session=request.getSession();
				//session.setAttribute("useremail", email);
				response.setHeader("Cache-Control","no-cache");
				response.setHeader("Cache-Control","no-store");
				response.setDateHeader("Expires", 0);
				response.setHeader("Pragma","no-cache");
				request.setAttribute("alert", "Succcessful login");
				
				try
			    {
				studentid=sa1.getuserid(email,password);
				request.setAttribute("email", email);
				session.setAttribute("status", "loggedin");
				session.setAttribute("studentid", studentid );
				} 
			     catch (Exception e) 
			     {
				request.setAttribute("alert", e);
				request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
			    }
				
			request.getRequestDispatcher("pages/student/user/studentdashboard.jsp").forward(request, response);  
			}
			else
			{
				request.setAttribute("alert", "Invalid");
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);
			}
		}
		
		if(action.equals("signupform"))
		{
			int id=0;
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			String dob=request.getParameter("dob");
			String mobile=request.getParameter("mobile");
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			String degreetype=request.getParameter("degreetype");  // 1 for diploma and 2 for regular
			String ssc=request.getParameter("ssc");
			String hsc=request.getParameter("hsc");
			String sem1="";
			String sem2="";
            String sem3=request.getParameter("sem3");
			String sem4=request.getParameter("sem4");
			String sem5=request.getParameter("sem5");
			String sem6=request.getParameter("sem6");
			
			if(degreetype.equals("1")) // for diploma
			{
				double i1=Double.parseDouble(sem3);
				double i2=Double.parseDouble(sem4);
				double i3=Double.parseDouble(sem5);
				double i4=Double.parseDouble(sem6);
				
				 sem1=Double.toString((i1+i2)/2);
				 sem2=Double.toString((i3+i4)/2);
			}
			else
				if(degreetype.equals("2")) //for regular, so keep it as it is
			{
				 sem1=request.getParameter("sem1");
				 sem2=request.getParameter("sem2");
			}
			
			SA sa1=new SA();
			SA sa2=new SA();
			SA sa3=new SA();
			SA sa4= new SA();
			
			try
		    {
			//here we are trying to insert data into the database
			sa1.insert1(name,address,dob,mobile,email,password);
			
			id=sa2.getuserid(email,password);
			String id1= Integer.toString(id);
			
			sa3.insert2(id1,ssc,hsc,sem1,sem2,sem3,sem4,sem5,sem6);
			sa4.insert3(id1);
			
		    } 
		     catch (Exception e) 
		     {
			request.setAttribute("alert", e);
			request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
		    }
			
			request.setAttribute("alert", "signupdone");
			request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response); 
			
		}
		
		if (action.equals("studentdashboard")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/studentdashboard.jsp").forward(request, response);
			}
		}
	
		if (action.equals("viewprofile")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/myprofile.jsp").forward(request, response);
			}
		}
		
		if (action.equals("viewmocktest")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/mocktest.jsp").forward(request, response);
			}
		}
		
		if (action.equals("viewskills")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/skills.jsp").forward(request, response);
			}
		}
		
		if (action.equals("viewresults")) 
		{
			Double prediction = 0.0, notplaced=0.0;
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			//	return;
			}
			else
			{
				Integer id=(Integer)session.getAttribute("studentid");
				SA sa = new SA();
				prediction = sa.getmarks(id);
				
				DecimalFormat df = new DecimalFormat("#.##");      
				prediction = Double.valueOf(df.format(prediction));
				
				notplaced = 100.0 - prediction;
				DecimalFormat df1 = new DecimalFormat("#.##");      
				notplaced = Double.valueOf(df1.format(notplaced));
				
				request.setAttribute("prediction", prediction);
				request.setAttribute("notplaced", notplaced);
				
				request.getRequestDispatcher("pages/student/user/myresults.jsp").forward(request, response);
				
			}
			 
		}
		
		if (action.equals("viewtimeline")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/timeline.jsp").forward(request, response);
			}
		}
		

		if (action.equals("viewcompanyprofile")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/companyprofile1.jsp").forward(request, response);
			}
		}
		
		
		if (action.equals("viewcompany")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/companyprofile2.jsp").forward(request, response);
			}
		}
		
		if (action.equals("viewaptitudetest")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/aptitudetest.jsp").forward(request, response);
			}
		}
		
		
		if (action.equals("viewcomputertest")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/computertest.jsp").forward(request, response);
			}
		}
		
		if (action.equals("viewctest")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/ctest.jsp").forward(request, response);
			}
		}

		if (action.equals("viewcpptest")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/cpptest.jsp").forward(request, response);
			}
		}
		

		if (action.equals("viewjavatest")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/javatest.jsp").forward(request, response);
			}
		}
		
		
////Aptitude test description	
		if (action.equals("startaptitudetest")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/test1.jsp").forward(request, response);
			}
		}
		
		if (action.equals("submitaptitudetest")) 
		{ 
			Integer id=(Integer)session.getAttribute("studentid");
			String q[]=new String[100];
			String a[]=new String[100];
			Integer decision[] = new Integer[100];
			int correct=0, wrong=0;
			
			for(i=1;i<=20;i++)
			{
				q[i] = request.getParameter("q"+i);
		    }
			
				SA sa1=new SA();
				
			
			try
			{
				a = sa1.getanswer1(); //has all the answers stored in the array
				
			}
			catch (Exception e) 
			 {
				request.setAttribute("alert", e);
				request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
			 }
			
			for(i=1;i<=20;i++)
			{
				request.setAttribute("q"+i, q[i]); //my answer
			}
			
			for(i=1;i<=20;i++)
			{
				request.setAttribute("a"+i, a[i]);//correct answer
			}
			
			for(i=1;i<=20;i++)
			{
				if(q[i].equals(a[i]))
				{
					correct++;
					decision[i]= 1;
				}
				else
				{
					wrong++;
					decision[i] = 0;
				}
			}
			
			// Update database with the new score of aptitude
			SA sa2=new SA();
			String correct1= Integer.toString(correct);
			try{
				sa2.update1(id,correct1);
			}
			catch (Exception e) 
			 {
				request.setAttribute("alert", e);
				request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
			 }
			
			request.setAttribute("correct", correct);
			request.setAttribute("wrong", wrong);
			request.setAttribute("id", id);////////////////////////////To be inserted in to the database
			for(i=1;i<=20;i++)
			{
				request.setAttribute("decision"+i, decision[i]);
			}
			request.getRequestDispatcher("pages/student/user/testresult1.jsp").forward(request, response);
		}

		
//// End of Aptitude test coding
		
////////Start of computer test coding		
		if (action.equals("startcomputertest")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/test2.jsp").forward(request, response);
			}
		}
		
		if (action.equals("submitcomputertest")) 
		{ 
			Integer id=(Integer)session.getAttribute("studentid");
			String q[]=new String[100];
			String a[]=new String[100];
			Integer decision[] = new Integer[100];
			int correct=0, wrong=0;
			
			for(i=1;i<=20;i++)
			{
				q[i] = request.getParameter("q"+i);
		    }
			
				SA sa1=new SA();
			
			try
			{
				a = sa1.getanswer2(); //has all the answers stored in the array
			}
			catch (Exception e) 
			 {
				request.setAttribute("alert", e);
				request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
			 }
			
			for(i=1;i<=20;i++)
			{
				request.setAttribute("q"+i, q[i]); //my answer
			}
			
			for(i=1;i<=20;i++)
			{
				request.setAttribute("a"+i, a[i]);//correct answer
			}
			
			for(i=1;i<=20;i++)
			{
				if(q[i].equals(a[i]))
				{
					correct++;
					decision[i]= 1;
				}
				else
				{
					wrong++;
					decision[i] = 0;
				}
			}
			
			
			//Update the result for basic computer test
			SA sa2=new SA();
			String correct1= Integer.toString(correct);
			try{
				sa2.update2(id,correct1);
			}
			catch (Exception e) 
			 {
				request.setAttribute("alert", e);
				request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
			 }
			
			request.setAttribute("correct", correct);
			request.setAttribute("wrong", wrong);
			request.setAttribute("id", id);////////////////////////////To be inserted in to the database
			for(i=1;i<=20;i++)
			{
				request.setAttribute("decision"+i, decision[i]);
			}
			request.getRequestDispatcher("pages/student/user/testresult2.jsp").forward(request, response);
		} 	
		
////End of Computer test coding		
	
			
/////////////Start of C test coding		
		if (action.equals("startctest")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else
			{
				request.getRequestDispatcher("pages/student/user/test3.jsp").forward(request, response);
			}
		}
		
		if (action.equals("submitctest")) 
		{ 
			Integer id=(Integer)session.getAttribute("studentid");
			String q[]=new String[100];
			String a[]=new String[100];
			Integer decision[] = new Integer[100];
			int correct=0, wrong=0;
			
			for(i=1;i<=20;i++)
			{
				q[i] = request.getParameter("q"+i);
		    }
			
				SA sa1=new SA();
			
			try
			{
				a = sa1.getanswer3(); //has all the answers stored in the array
			}
			catch (Exception e) 
			 {
				request.setAttribute("alert", e);
				request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
			 }
			
			for(i=1;i<=20;i++)
			{
				request.setAttribute("q"+i, q[i]); //my answer
			}
			
			for(i=1;i<=20;i++)
			{
				request.setAttribute("a"+i, a[i]);//correct answer
			}
			
			for(i=1;i<=20;i++)
			{
				if(q[i].equals(a[i]))
				{
					correct++;
					decision[i]= 1;
				}
				else
				{
					wrong++;
					decision[i] = 0;
				}
			}
			
			// Update database with the new score of c score
			SA sa2=new SA();
			String correct1= Integer.toString(correct);
			try{
					sa2.update3(id,correct1);
				}
			catch (Exception e) 
			 {
				request.setAttribute("alert", e);
				request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
			 }
			
			request.setAttribute("correct", correct);
			request.setAttribute("wrong", wrong);
			request.setAttribute("id", id);////////////////////////////To be inserted in to the database
			for(i=1;i<=20;i++)
			{
				request.setAttribute("decision"+i, decision[i]);
			}
			request.getRequestDispatcher("pages/student/user/testresult3.jsp").forward(request, response);
		} 
		
//////End of c test coding	
		
		
//////////////Start of cpp test coding		
		if (action.equals("startcpptest")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/test4.jsp").forward(request, response);
			}
		}
		
		if (action.equals("submitcpptest")) 
		{ 
			Integer id=(Integer)session.getAttribute("studentid");
			String q[]=new String[100];
			String a[]=new String[100];
			Integer decision[] = new Integer[100];
			int correct=0, wrong=0;
			
			for(i=1;i<=20;i++)
			{
				q[i] = request.getParameter("q"+i);
		    }
			
				SA sa1=new SA();
			
			try
			{
				a = sa1.getanswer4(); //has all the answers stored in the array
			}
			catch (Exception e) 
			 {
				request.setAttribute("alert", e);
				request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
			 }
			
			for(i=1;i<=20;i++)
			{
				request.setAttribute("q"+i, q[i]); //my answer
			}
			
			for(i=1;i<=20;i++)
			{
				request.setAttribute("a"+i, a[i]);//correct answer
			}
			
			for(i=1;i<=20;i++)
			{
				if(q[i].equals(a[i]))
				{
					correct++;
					decision[i]= 1;
				}
				else
				{
					wrong++;
					decision[i] = 0;
				}
			}
			
			// Update database with the new score of cpp score
			SA sa2=new SA();
			String correct1= Integer.toString(correct);
			try{
			    sa2.update4(id,correct1);
				}
		    catch (Exception e) 
				{
				   request.setAttribute("alert", e);
					request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
				 }
			
			
			request.setAttribute("correct", correct);
			request.setAttribute("wrong", wrong);
			request.setAttribute("id", id);////////////////////////////To be inserted in to the database
			for(i=1;i<=20;i++)
			{
				request.setAttribute("decision"+i, decision[i]);
			}
			request.getRequestDispatcher("pages/student/user/testresult4.jsp").forward(request, response);
		} 
				
/////End of cpp test coding		
		

		
////////////////Start java test coding		
		if (action.equals("startjavatest")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			request.getRequestDispatcher("pages/student/user/test5.jsp").forward(request, response);
			}
		}
		
		if (action.equals("submitjavatest")) 
		{ 
			Integer id=(Integer)session.getAttribute("studentid");
			String q[]=new String[100];
			String a[]=new String[100];
			Integer decision[] = new Integer[100];
			int correct=0, wrong=0;
			
			for(i=1;i<=20;i++)
			{
				q[i] = request.getParameter("q"+i);
		    }
			
				SA sa1=new SA();
			
			try
			{
				a = sa1.getanswer5(); //has all the answers stored in the array
			}
			catch (Exception e) 
			 {
				request.setAttribute("alert", e);
				request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
			 }
			
			for(i=1;i<=20;i++)
			{
				request.setAttribute("q"+i, q[i]); //my answer
			}
			
			for(i=1;i<=20;i++)
			{
				request.setAttribute("a"+i, a[i]);//correct answer
			}
			
			for(i=1;i<=20;i++)
			{
				if(q[i].equals(a[i]))
				{
					correct++;
					decision[i]= 1;
				}
				else
				{
					wrong++;
					decision[i] = 0;
				}
			}
			
			// Update database with the new score of c score
			SA sa2=new SA();
			String correct1= Integer.toString(correct);
			try{
			   sa2.update5(id,correct1);
				}
			catch (Exception e) 
			 {
							request.setAttribute("alert", e);
							request.getRequestDispatcher("pages/error/error.jsp").forward(request, response);
			 }
			
			
			request.setAttribute("correct", correct);
			request.setAttribute("wrong", wrong);
			request.setAttribute("id", id);////////////////////////////To be inserted in to the database
			for(i=1;i<=20;i++)
			{
				request.setAttribute("decision"+i, decision[i]);
			}
			request.getRequestDispatcher("pages/student/user/testresult5.jsp").forward(request, response);
		} 
				
/////End of java test coding		
		

		
		if (action.equals("logout")) 
		{
			if(session==null){
				request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);	
			}
			else{
			session.removeAttribute("status");
			session.removeAttribute("studentid");
			session.invalidate();
			
			session=null;
			request.setAttribute("alert", "logout");
			request.getRequestDispatcher("pages/student/login/login.jsp").forward(request, response);
			}
		}
		
		
		
		
		
		
		
			
	}

}
