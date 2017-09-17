<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
        <%@ page import="java.io.*,java.util.*" %>
        
 <sql:setDataSource var="ds" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/placement_db"
     user="root"  password="" scope="session"/>
      <sql:query dataSource="${ds}" var="row001">
SELECT * from student where name LIKE '%<c:out value="${param.queryName }" />%';
</sql:query>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Company Search Results</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <link rel="shortcut icon" href="http://icanbecreative.com/resources/images/favico.ico" />
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<!-- My imported libraries -->
        <link rel="stylesheet" href="fontawesome/css/font-awesome.min.css">
        <link href="pages/company/css/search1.css" rel="stylesheet" type="text/css">
        <link href="pages/company/css/snackbar.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="pages/company/js/index.js"></script>
        <script type="text/javascript" src="pages/company/js/snackbar.js"></script>

 <!-- --> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <!-- --> 

    </head>
<input type="hidden" id="refreshed" value="no">
<noscript>
  <style>html{display:none;}</style>
  <meta http-equiv="refresh" content="0.0;url=?task=nojs">
</noscript>
    <SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { 
    	window.history.forward();
    }
</SCRIPT>
<c:if test='${ name == null }'>
    <%
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location","?task=login"); 
    %>
</c:if>

    <body>
    <div >
    <span class="dropbtn" style="color: Blue; margin-left:40%; font-size: x-large; " >Welcome ! <c:out value="${name }" /></span>
      <span style="margin-left:20%;" class="btn btn-primary">
          <a style="color: #8cff1a; text-decoration:none; font-size: x-large;" href="<%=request.getContextPath() %>/Company?task=logout">LOGOUT</a></span>
      <div style="text-align: center;">
       <span  style=" margin-left:12%; font-size:xx-large;  color: #3399ff">Atharva College of Engineering, Malad (West).</span>
      </div>
      </div>
      <div class="row">
      <div style="margin-left: 5%" class="col-sm-8">
        <form action="<%=request.getContextPath() %>/Company" onsubmit="myFunction();">
        <input type="hidden" name="task" value="search" />
     
            <div class="search-wrapper">
     <div class="input-holder">
                    <input type="text" required="required" name="queryName" class="search-input" placeholder="Type name of student to search" />
                    <button type="submit" class="search-icon" onclick="searchToggle(this, event);"><span></span></button>
                </div>

                <span class="close" onclick="searchToggle(this, event);"></span>
                <div class="result-container">

                </div>
            </div>
        </form>
        </div>
        
        </div>
<div id="snackbar"><i class="fa fa-spinner fa-pulse fa-1x fa-fw"></i>
            Just a sec, Refreshing the Doors of Success......
        </div> 
<script type="text/javascript">
function myFunction() {
    var x = document.getElementById("snackbar")
    x.className = "show";
}
</script>
 

            <br><br>

<!-- Displaying Search Results --> 
<div class="container">

    <hgroup class="mb20">
        <h2 class="lead"> We have found the list of students matching
         <span style="color: blue;" class="btn btn-warning"><c:out value="${param.queryName}" /></span></h2>                               
    </hgroup>

    <section class="col-xs-12 col-sm-6 col-md-12">
<c:forEach var="row00" items="${row001.rows}">

        <article class="search-result row">
            <div class="col-xs-12 col-sm-12 col-md-3">
                <a href="#"  class="thumbnail"><img src="pages/company/images/user1.jpg" /></a>
            </div>

            <div class="col-xs-12 col-sm-12 col-md-2">
                <ul class="meta-search">
                    <li><i class="glyphicon glyphicon-education"></i><span><font size="4">Bachelor of Engineering</font></span></li>
                    <li><i class="glyphicon glyphicon-user"></i> <span><font size="4"><c:out value="${row00.id }" /></font></span></li>
                    <li><i class="glyphicon glyphicon-calendar"></i> <span><font size="4"><c:out value="${row00.dob }" /></font></span></li>
                </ul>
            </div>

            <div class="col-xs-12 col-sm-12 col-md-7 excerpet">
                <h3><a href="#" title=""><c:out value="${row00.name }" /></a></h3>
                <p><c:out value="${row00.name }" /> is Student of Atharva College of Engineering. He has secured 8.18 CGPI till his 6th Semester.</p> 
                <center><a href="<%=request.getContextPath() %>/Company?task=viewStudentProfile&StudentID=<c:out value="${row00.id }" />" class="btn btn-md btn-success">View Profile</a></center>
            </div>
            <span class="clearfix borda"></span>
        </article>

</c:forEach>
</section>
</div>

<!-- end Displaying Search Results -->

    </body>

</html>
