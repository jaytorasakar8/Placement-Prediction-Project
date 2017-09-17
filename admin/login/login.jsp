<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
        <%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Placement Portal | Admin Login</title>
    	<link rel="stylesheet" href="pages/admin/login/css/style.css">
  </head>

  <body >

    <div style="margin-top: -3%; height: 100%;" class="wrapper">
	<div class="container">
		<div style="text-align: center;">
<img src="pages/admin/images/aceLogo.jpg" style="height: 80px; width: 70px;"> <br>
<span style="color: #ffff4d; font-size: xx-large;"><strong>Atharva College Of Engineering, Malad (West)
<br>Admin Login
</strong></span>
</div>
		<form  method="post" action="<%=request.getContextPath() %>/Admin"">
			<input type="hidden" name="task" value="login-form">
			<input type="text" name="email" placeholder="Username">
			<input type="password" name="password" placeholder="Password">
			<br>
			<span style="color: yellow; font-size: large;"><c:out value="${alert }" /></span>
			<input  type="submit" value="Login" id="login-button" />
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li><li></li><li></li><li></li>
		<li></li><li></li><li></li><li></li>
		<li></li><li></li><li></li><li></li>
		<li></li><li></li><li></li><li></li>
		<li></li><li></li><li></li><li></li>
		<li></li><li></li><li></li><li></li>
		<li></li><li></li><li></li><li></li>
		<li></li><li></li><li></li><li></li>
		<li></li><li></li>
		<li></li><li></li>
		<li></li><li></li>
		<li></li><li></li>
		
	</ul>
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="pages/admin/login/js/index.js"></script>
 
  </body>
</html>
