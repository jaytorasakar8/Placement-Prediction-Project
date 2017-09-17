<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<head>
	<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Admin | Student Update Profile</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
  </script>

  <script type="text/javascript" src="pages/admin/js/jquery.backstretch.min.js"></script>
  <script type="text/javascript" src="pages/admin/js/result.js"></script>
  <link rel="stylesheet" type="text/css" href="pages/admin/css/result.css">
  <link rel="stylesheet" href="pages/admin/fontawesome/css/font-awesome.min.css">

</head>

<sql:setDataSource 
	  var="ds" 
      driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/placement_db"
     user="root" 
      password="" 
      scope="session"/>
     
<sql:query dataSource="${ds}" var="result">
SELECT * from student where id=<%=request.getAttribute("id") %>;
</sql:query>


<body>

<div class="col-lg-offset-1 col-lg-10 col-sm-6">
    <div class="card hovercard">
        <div class="card-background">
            <img class="card-bkimg" alt="User" src="pages/admin/images/user1.jpg">
            <!-- http://lorempixel.com/850/280/people/9/ -->
        </div>
        <div class="useravatar">
            <img alt="User" src="pages/admin/images/user1.jpg">
        </div>
        <div class="card-info"> 
        	<span class="card-title">
        		 <c:forEach var="row" items="${result.rows }">
                 	<c:out value="${row.name }"></c:out>
                 </c:forEach>
        	</span>
		</div>

    </div>

    <br>

     <div class="panel-group">
    <div class="panel panel-primary">
      <div class="panel-heading">
        Update Student Profile
      </div>


<form action="<%=request.getContextPath() %>/Admin" method="get">
      <div class="panel-body">
      <input type="hidden" name="task" value="updateStudentData">
      
      <input type="hidden" name="StudentID" value='<%=request.getAttribute("id") %>'>
        <div class="row">
          <div class="col-md-5">
            <p><font size="5">1. Student Identification Number : </font></p>
          </div>
          <div class="col-md-6">
              <input type="text" class="form-control" name="StudentID" value='<%=request.getAttribute("id") %>' disabled>
          </div>
        </div>

        <div class="row">
          <div class="col-md-5">
            <p><font size="5">2. Is the student offered a Job : </font></p>
          </div>
          <div class="col-md-6">
              <input type="text" class="form-control" placeholder="YES" disabled>
              <small style="color:red;">Please refrain from updating the profile is student is not offered a Job.</small>
          </div>
        </div>
<br>
        <div class="row">
          <div class="col-md-5">
            <p><font size="5">3. Name of the Company : </font></p>
          </div>
          <div class="col-md-6">
              <input type="text" class="form-control" name="cname" id="cname" placeholder="Enter the Name of Company in which Student is offered a Job...">
          </div>
        </div>

        <div class="row">
          <div class="col-md-5">
            <p><font size="5">4. Package offered : </font></p>
          </div>
          <div class="col-md-6">
              <input type="text" class="form-control" name="pkg" id="pkg" placeholder="Enter the Amount offered to Student(in Lakhs)...">
          </div>
        </div>
    <br>
         <center>
           <input type="submit" class="btn btn-success btn-lg">
         </center>

        
      </div>

</form>

    </div>
  </div>

 
     </div>


  </body>
  </html>
 