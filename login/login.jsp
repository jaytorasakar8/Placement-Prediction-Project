
<!DOCTYPE HTML>
<html lang="en">
<head>
<title>Student Login | Placement Prediction System</title>
<!-- Meta tag Keywords -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Classic Login Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Meta tag Keywords -->

<!-- css files -->
<link rel="stylesheet" href="pages/student/login/css/style.css" type="text/css" media="all" /> <!-- Style-CSS --> 
<link rel="stylesheet" href="pages/student/login/css/font-awesome.css"> <!-- Font-Awesome-Icons-CSS -->
<!-- //css files -->

<!-- js -->
<script type="text/javascript" src="pages/student/login/js/jquery-2.1.4.min.js"></script>
<!-- //js -->

<!-- online-fonts -->
<link href="//fonts.googleapis.com/css?family=Oleo+Script:400,700&amp;subset=latin-ext" rel="stylesheet">
<!-- //online-fonts -->
</head>
<body>
<script src="pages/student/login/js/jquery.vide.min.js"></script>
	<!-- main -->
	<div data-vide-bg="pages/student/login/video/Ipad">
		<div class="center-container">
			<!--header-->
			<div class="header-w3l">
				<h1>Student Login </h1>
			</div>
			<!--//header-->
			<div class="main-content-agile">
				<div class="sub-main-w3">	
					<div class="wthree-pro">
						<h2>Login Here</h2>
						<% if((request.getAttribute("alert")).equals("signupdone")){ %>
									<font color="#ffad33"> Registration Successful ! Please Login !</font>
						<% } else { if((request.getAttribute("alert")).equals("logout")){ %>
									<font color="#ffad33"> Logout Successful ! Please Login Again !</font> 
								    
						<% } } %>
					</div>
					<form action="<%=request.getContextPath()%>/Student" method="post">
					<input type="hidden" name="action" value="loginform" >
						<input placeholder="Your Registered E-mail" name="email" class="user" type="email" required="">
						<span class="icon1"><i class="fa fa-user" aria-hidden="true"></i></span>
						<br><br>
						<input  placeholder="Password" name="password" class="pass" type="password" required="">
						<span class="icon2"><i class="fa fa-unlock" aria-hidden="true"></i></span><br>
						<div class="sub-w3l">
							<h6><a href="<%=request.getContextPath() %>/Student?action=signup">Sign Up! It's free </a></h6>

							<div class="right-w3l">
								<input type="submit" value="Login">
								
								<% if((request.getAttribute("alert")).equals("Invalid")){ %>
									<br><font color="#ffad33"> Wrong USername/Password</font>
								<% } else { %>
								    <p></p>
								<% } %>
								
								<!-- <br><font color="#ffad33"> Wrong USername/Password -->
								<!-- <br><font color="#ffad33"> Field cannot be empty-->
							</div>

						</div>
					</form>
				</div>
			</div>
			<!--//main-->

			<!--footer-->
			<div class="footer">
				
			</div>
			<!--//footer-->
		</div>
	</div>

</body>
</html>