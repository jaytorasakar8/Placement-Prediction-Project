package com.mrpj.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SA 

{
	Connection con;
	String user = "root";
	String password = "";
	String url = "jdbc:mysql://localhost:3306/";
	String dbname = "placement_db";
	String dbname1 = "exam";
	String driver = "com.mysql.jdbc.Driver";
	
	private void dbConnect() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection(url + dbname, user, password);
		con.setAutoCommit(false);
	}
	
	private void dbConnect1() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection(url + dbname1, user, password);
		con.setAutoCommit(false);
	}
	

	private void dbClose() throws SQLException {//committing normally 
		con.commit();
		con.close();
		con=null;
	}
	private void dbRClose() throws SQLException {//Rollback 
		con.rollback();
		con.close();
		con=null;
	}

	public String validateUser(String email, String password) {
		
		try {
			dbConnect();
		String sql="SELECT * FROM student WHERE email=? and password=?";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, email);
		pstm.setString(2, password);
		ResultSet rst = pstm.executeQuery();
		if(rst.next()){
			String name="Successfull";
				dbClose();
				return name;
		}
		else{
			
			String name="unsuccessful";
		dbClose();
		return name;
		}
		}catch (Exception e) {
			try {
				dbRClose();
			} catch (Exception e1) {
			e1.printStackTrace();	
			return "1";
			}
			e.printStackTrace();
			return "1";
		}
		
	}
	
	public void insert1(String name, String address,String dob,String mobile,String email,String password) throws Exception
	{
		dbConnect();
		
		String sql = "insert into student(name,address,dob, mobile, email, password) values (?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, address);
		pstmt.setString(3, dob);
		pstmt.setString(4, mobile);
		pstmt.setString(5, email);
		pstmt.setString(6, password);
		
		pstmt.executeUpdate();
		dbClose();
		
	}
	
	
	public int getuserid(String email, String password) throws ClassNotFoundException, SQLException
	{
		dbConnect();
		int count = 0;
		int id = 0;
		
		String sql="select id,count(*) as count from student where email = ? AND password =?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		
		ResultSet rst = pstmt.executeQuery();
	    
		while(rst.next())
		{
			count = rst.getInt("count"); //1
			id = rst.getInt("id"); //1000 onwards
		}
	
		
		dbClose();
		
		return id;
	}
	
	public void insert2(String id1,String ssc,String hsc,String sem1,String sem2,String sem3,String sem4,String sem5,String sem6) throws Exception
	{
		dbConnect();
		
		String sql = "insert into academic(ssc,hsc,sem1,sem2,sem3,sem4,sem5,sem6,student_id) values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, ssc);
		pstmt.setString(2, hsc);
		pstmt.setString(3, sem1);
		pstmt.setString(4, sem2);
		pstmt.setString(5, sem3);
		pstmt.setString(6, sem4);
		pstmt.setString(7, sem5);
		pstmt.setString(8, sem6);
		pstmt.setString(9, id1);
		
		pstmt.executeUpdate();
		dbClose();
	}
	
	public void insert3(String id1) throws Exception
	{
		dbConnect();
		String c,cpp,java,aptitude,basiccomputer;
		c="0";
		cpp="0";
		java="0";
		aptitude="0";
		basiccomputer="0";
		String sql = "insert into test_marks(c,cpp,java,aptitude,basiccomputer,student_id) values (?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, c);
		pstmt.setString(2, cpp);
		pstmt.setString(3, java);
		pstmt.setString(4, aptitude);
		pstmt.setString(5, basiccomputer);
		pstmt.setString(6, id1);
		
		pstmt.executeUpdate();
		dbClose();
	}
	
	public String[] getanswer1() throws Exception
	{
		dbConnect1();
		String count;
		int i,count1=0;
		String sql="select count(srno1) as count  from aptitudetable" ;
		String sql1="select srno1,answer1 from aptitudetable" ;
		
		String a[] = new String[100];
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
	    
		PreparedStatement pstmt1 = con.prepareStatement(sql1); //for getting the column of answers
		ResultSet rst1 = pstmt1.executeQuery();
	    
		while(rst.next())
		{
			count = rst.getString("count"); //1
			count1= Integer.parseInt(count);
		}
		
		int  k=1;
		//use rst1 since we need to access the answers
		while(rst1.next())
		{
			a[k]= rst1.getString("answer1");
			k++;
    	}
			dbClose();
	      return a;	
	 
	}
	
	
	public String[] getanswer2() throws Exception
	{
		dbConnect1();
		String count;
		int i,count1=0;
		String sql="select count(srno2) as count  from basiccomputertable" ;
		String sql1="select srno2,answer2 from basiccomputertable" ;
		
		String a[] = new String[100];
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
	    
		PreparedStatement pstmt1 = con.prepareStatement(sql1); //for getting the column of answers
		ResultSet rst1 = pstmt1.executeQuery();
	    
		while(rst.next())
		{
			count = rst.getString("count"); //1
			count1= Integer.parseInt(count);
		}
		
		int  k=1;
		//use rst1 since we need to access the answers
		while(rst1.next())
		{
			a[k]= rst1.getString("answer2");
			k++;
    	}
			dbClose();
	      return a;	
	 
	}
	
	
	public String[] getanswer3() throws Exception
	{
		dbConnect1();
		String count;
		int i,count1=0;
		String sql="select count(srno3) as count  from ctable" ;
		String sql1="select srno3,answer3 from ctable" ;
		
		String a[] = new String[100];
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
	    
		PreparedStatement pstmt1 = con.prepareStatement(sql1); //for getting the column of answers
		ResultSet rst1 = pstmt1.executeQuery();
	    
		while(rst.next())
		{
			count = rst.getString("count"); //1
			count1= Integer.parseInt(count);
		}
		
		int  k=1;
		//use rst1 since we need to access the answers
		while(rst1.next())
		{
			a[k]= rst1.getString("answer3");
			k++;
    	}
			dbClose();
	      return a;	
	 
	}
	
	public String[] getanswer4() throws Exception
	{
		dbConnect1();
		String count;
		int i,count1=0;
		String sql="select count(srno4) as count  from cpptable" ;
		String sql1="select srno4,answer4 from cpptable" ;
		
		String a[] = new String[100];
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
	    
		PreparedStatement pstmt1 = con.prepareStatement(sql1); //for getting the column of answers
		ResultSet rst1 = pstmt1.executeQuery();
	    
		while(rst.next())
		{
			count = rst.getString("count"); //1
			count1= Integer.parseInt(count);
		}
		
		int  k=1;
		//use rst1 since we need to access the answers
		while(rst1.next())
		{
			a[k]= rst1.getString("answer4");
			k++;
    	}
			dbClose();
	      return a;	
	 
	}
	
	public String[] getanswer5() throws Exception
	{
		dbConnect1();
		String count;
		int i,count1=0;
		String sql="select count(srno5) as count  from javatable" ;
		String sql1="select srno5,answer5 from javatable" ;
		
		String a[] = new String[100];
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
	    
		PreparedStatement pstmt1 = con.prepareStatement(sql1); //for getting the column of answers
		ResultSet rst1 = pstmt1.executeQuery();
	    
		while(rst.next())
		{
			count = rst.getString("count"); //1
			count1= Integer.parseInt(count);
		}
		
		int  k=1;
		//use rst1 since we need to access the answers
		while(rst1.next())
		{
			a[k]= rst1.getString("answer5");
			k++;
    	}
			dbClose();
	      return a;	
	 
	}
	
	public void update1(int id, String correct ) throws Exception
	{
		dbConnect();
		String sql="update test_marks set aptitude=? where student_id=? " ;
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, correct);
		pstmt.setInt(2, id);
		
		 pstmt.executeUpdate();
		
		dbClose();
	}
	

	public void update2(int id, String correct ) throws Exception
	{
		dbConnect();
		String sql="update test_marks set basiccomputer=? where student_id=? " ;
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, correct);
		pstmt.setInt(2, id);
		
		 pstmt.executeUpdate();
		
		dbClose();
	}
	
	public void update3(int id, String correct ) throws Exception
	{
		dbConnect();
		String sql="update test_marks set c=? where student_id=? " ;
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, correct);
		pstmt.setInt(2, id);
		
		 pstmt.executeUpdate();
		
		dbClose();
	}
	
	public void update4(int id, String correct ) throws Exception
	{
		dbConnect();
		String sql="update test_marks set cpp=? where student_id=? " ;
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, correct);
		pstmt.setInt(2, id);
		
		 pstmt.executeUpdate();
		
		dbClose();
	}
	
	public void update5(int id, String correct ) throws Exception
	{
		dbConnect();
		String sql="update test_marks set java=? where student_id=? " ;
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, correct);
		pstmt.setInt(2, id);
		
		 pstmt.executeUpdate();
		
		dbClose();
	}
	
	public Double getmarks(Integer id) 
	{
		Double p = null;
		
		try{
		dbConnect();
		String ssc="", hsc="", sem1="", sem2="", sem3="", sem4="", sem5="", sem6="",
				c="", cpp="", java="", aptitude="", basiccomputer="";
		/*int i,count1=0;*/
		String sql="select *  from academic where student_id=?" ;
		String sql1="select * from test_marks where student_id=? " ;
		
		String a[] = new String[100];
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rst = pstmt.executeQuery();
	    
		PreparedStatement pstmt1 = con.prepareStatement(sql1); //for getting the column of answers
		pstmt1.setInt(1, id);
		ResultSet rst1 = pstmt1.executeQuery();
	    
		while(rst.next())
		{
			 ssc = rst.getString("ssc"); //ssc marks
			 hsc = rst.getString("hsc"); //hsc marks
			 sem1 = rst.getString("sem1"); //sem1
			 sem2 = rst.getString("sem2"); //sem2
			 sem3 = rst.getString("sem3"); //sem3
			 sem4 = rst.getString("sem4"); //sem4
			 sem5 = rst.getString("sem5"); //sem5
			 sem6 = rst.getString("sem6"); //sem6
			 
			 
		}
		
		while(rst1.next())
		{
			 c = rst1.getString("c"); //ssc marks
			 cpp = rst1.getString("cpp"); //ssc marks
			 java = rst1.getString("java"); //ssc marks
			 basiccomputer = rst1.getString("basiccomputer"); //ssc marks
			 aptitude = rst1.getString("aptitude"); //ssc marks
					 
		}
		History h =new History();
	
		 p = h.getPrediction(ssc,hsc,sem1,sem2,sem3,sem4,sem5,sem6,c,cpp,java,aptitude,basiccomputer);
		
		dbClose();
	      return p;
	      }
		catch(Exception e)
		{
			e.printStackTrace();
			return p;
		}
	}
	
}
