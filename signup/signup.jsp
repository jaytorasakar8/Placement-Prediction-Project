<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Student Register | Placement Prediction System</title>

        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="pages/student/signup/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="pages/student/signup/assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="pages/student/signup/assets/css/form-elements.css">
        <link rel="stylesheet" href="pages/student/signup/assets/css/style.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="pages/student/signup/assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="pages/student/signup/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="pages/student/signup/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="pages/student/signup/assets/ico/apple-touch-icon-57-precomposed.png">

        

    </head>

    <body>

		<!-- Top content -->
        <div class="top-content">
            <div class="container">
                
                <div class="row">
                    <div class="col-sm-8 col-sm-offset-2 text">
                        <h1> <strong>Student Login </strong> </h1>
                        <div class="description">
                       	   <p>
                                <strong>Welcome to the <b>Student Placement Prediction </b> System. Don't worry, your future is in safe hands with us !!!</strong>
                            </p>
                            <p>
                             Already Registered ! <a  href="<%=request.getContextPath()%>/Student?action=login" > <u> <b>Login Here </b></u> !  </a>
                            </p>
                        </div>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1 form-box">
                    	<form role="form" action="<%=request.getContextPath()%>/Student" method="get" class="f1" >
                    	<input type="hidden" name="action" value="signupform" >

                    		<h3>Register To Our App</h3>
                    		<p>Fill in the form to get access to success !!!</p>
                    		
                            <div class="f1-steps">
                    			<div class="f1-progress">
                    			    <div class="f1-progress-line" data-now-value="16.66" data-number-of-steps="3" style="width: 16.66%;"></div>
                    			</div>
                    			<div class="f1-step active">
                    				<div class="f1-step-icon"><i class="fa fa-user"></i></div>
                    				<p>About</p>
                    			</div>
                    			<div class="f1-step">
                    				<div class="f1-step-icon"><i class="fa fa-key"></i></div>
                    				<p>Personal</p>
                    			</div>
                    		    <div class="f1-step">
                    				<div class="f1-step-icon"><i class="fa fa-graduation-cap" ></i></div>
                    				<p>Academics</p>
                    			</div>
                    		</div>  
                    		
                    		<fieldset>
                    		
                    		    <h4>Tell us who you are:</h4>
                    		    <div class="form-group">
                    			    <label class="sr-only" for="f1-first-name">Name</label>
                                    <input type="text" name="name" placeholder="Your Full Name..." class="f1-first-name form-control" id="f1-first-name">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="f1-last-name">Date of Birth</label>
                                    &nbsp;Date of Birth
                                    <input type="date" name="dob"  class="f1-last-name form-control" id="f1-last-name">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="f1-about-yourself">Address...</label>
                                    <textarea name="address" placeholder="Address..." class="f1-about-yourself form-control" id="f1-about-yourself"></textarea>
                                </div> 
                                <div class="f1-buttons">
                                    <button type="button" class="btn btn-next">Next</button>
                                </div> 
                            </fieldset>

                            <fieldset>
                                <h4>Set up your account:</h4>
                               <div class="form-group">
                                    <input type="text" name="mobile" placeholder="Mobile Number..." class="f1-first-name form-control">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="f1-email">Email</label>
                                    <input type="text" name="email" placeholder="Email..." class="f1-email form-control" id="f1-email">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="f1-password">Password</label>
                                    <input type="password" name="password" placeholder="Password..." class="f1-password form-control" id="f1-password">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="f1-repeat-password"> Confirm password</label>
                                    <input type="password" name="cpassword" placeholder="Confirm Password..." class="f1-repeat-password form-control" id="f1-repeat-password">
                                </div>    
                                <div class="f1-buttons">
                                    <button type="button" class="btn btn-previous">Previous</button>
                                    <button type="button" class="btn btn-next">Next</button>
                                </div> 
                            </fieldset>

                            <fieldset>
                                <h4>Tell us about your Academic Scores:</h4>
                                <div class="form-group">
                                    <label class="sr-only">SSC Percentage</label>
                                    <input type="text" name="ssc" placeholder= "SSC or Equivalent Percentage..." class="f1-first-name form-control">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only">HSC Percentage</label>
                                    <input type="text" name="hsc" placeholder= "HSC or Equivalent Percentage..." class="f1-first-name form-control">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only">Degree type</label>
                                    <select class="f1-first-name form-control" id="degreetype" name="degreetype" onchange="f1();">
                                        <option value="0">----Select Degree Type----</option>
                                        <option value="1">Diploma or Direct Second Year</option>
                                        <option value="2">Regular or First Year Engineering Passout</option>
                                    </select>
                                </div>


                        <div id="degreeresult">
                                
                        </div>


                                <div class="f1-buttons">
                                    <button type="button" class="btn btn-previous">Previous</button>
                                    <button type="submit" class="btn btn-submit">Submit</button>
                                </div>
                            </fieldset>
                    	
                    	</form>
                    </div>
                </div>
                    
            </div>
        </div>


        <!-- Javascript -->
        <script src="pages/student/signup/assets/js/jquery-1.11.1.min.js"></script>
        <script src="pages/student/signup/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="pages/student/signup/assets/js/jquery.backstretch.min.js"></script>
        <script src="pages/student/signup/assets/js/retina-1.1.0.min.js"></script>
        <script src="pages/student/signup/assets/js/scripts.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
        <script type="text/javascript">
            function f1 () 
            {
                var x=document.getElementById('degreetype').value; 
                if (x==0) 
                {
                    document.getElementById('degreeresult').innerHTML='<font color="red">Please Select your Degree Type ';
                };
                if (x==1) 
                {
                 document.getElementById('degreeresult').innerHTML=
                 '<div class="form-group"><label class="sr-only">Semester 3 Pointer</label><input type="text" name="sem3" placeholder= "Semester 3 Pointer..." class="f1-first-name form-control"></div><div class="form-group"><label class="sr-only">Semester 4 Pointer</label><input type="text" name="sem4" placeholder= "Semester 4 Pointer..." class="f1-first-name form-control"></div><div class="form-group"><label class="sr-only">Semester 5 Pointer</label><input type="text" name="sem5" placeholder= "Semester 5 Pointer..." class="f1-first-name form-control"></div><div class="form-group"><label class="sr-only">Semester 6 Pointer</label><input type="text" name="sem6" placeholder= "Semester 6 Pointer..." class="f1-first-name form-control"></div>'
                };

                if (x==2) 
                {
                 document.getElementById('degreeresult').innerHTML=
                 '<div class="form-group"><label class="sr-only">Semester 1 Pointer</label><input type="text" name="sem1" placeholder= "Semester 1 Pointer..." class="f1-first-name form-control"></div><div class="form-group"><label class="sr-only">Semester 2 Pointer</label><input type="text" name="sem2" placeholder= "Semester 2 Pointer..." class="f1-first-name form-control"></div>   <div class="form-group"><label class="sr-only">Semester 3 Pointer</label><input type="text" name="sem3" placeholder= "Semester 3 Pointer..." class="f1-first-name form-control"></div><div class="form-group"><label class="sr-only">Semester 4 Pointer</label><input type="text" name="sem4" placeholder= "Semester 4 Pointer..." class="f1-first-name form-control"></div><div class="form-group"><label class="sr-only">Semester 5 Pointer</label><input type="text" name="sem5" placeholder= "Semester 5 Pointer..." class="f1-first-name form-control"></div><div class="form-group"><label class="sr-only">Semester 6 Pointer</label><input type="text" name="sem6" placeholder= "Semester 6 Pointer..." class="f1-first-name form-control"></div>'
                };

                
                    return false;
            }
        </script>

    </body>



</html>