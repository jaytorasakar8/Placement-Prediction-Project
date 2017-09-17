<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
        <%@ page import="java.io.*,java.util.*" %>
        
 <sql:setDataSource var="ds" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/placement_db"
     user="root"  password="" scope="session"/>

<sql:query dataSource="${ds}" var="row001">
SELECT * from student,test_marks,academic where student.id = <c:out value="${StudentID }" /> and
 test_marks.student_id = <c:out value="${StudentID }" /> and academic.student_id = <c:out value="${StudentID }" />;
</sql:query>

<html>
<head>
	<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title> Admin | Student Detailed Report  </title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
  </script>

  <script type="text/javascript" src="pages/admin/js/jquery.backstretch.min.js"></script>
  <script type="text/javascript" src="pages/admin/js/result.js"></script>
  <link rel="stylesheet" type="text/css" href="pages/admin/css/result.css">
  <link rel="stylesheet" href="pages/admin/fontawesome/css/font-awesome.min.css">
  <script src="pages/admin/js/canvasjs.min.js"></script>

<!-- for charts-->
  <script type="text/javascript" src="pages/admin/js/chart1.js"></script>
  <style>
	   .img-circle {
    border-radius: 50%;}	
    </style>
</head>


<c:if test='${ name == null }'>
    <%
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location","?task=login"); 
    %>
</c:if>

<c:forEach var="row00" items="${row001.rows}">
<body  onload="a1(); a2(); a3(); a4(); a5(); a6(); a7(); a8(); a9(); a10();">

<div class="col-lg-offset-1 col-lg-10 col-sm-6">
    <br>
    <div class="well well-lg" >
		<div class="row">
			<div class="col-md-3">
            
            	<img class="img-circle" style="width: 210px;height:150px;" alt="<c:out value="${row00.name }" />" src="pages/admin/images/user1.jpg">
       		 
			</div>
			<div class="col-md-4">
				<i class="fa fa-user fa-2x" aria-hidden="true"></i>&nbsp;
				<font size="4"> <b>Student Name:</b> <c:out value="${row00.name }" /></font>
			</div>
			
			<div class="col-md-3 col-md-offset-1">
				
				<a class="btn btn-success btn-md" href="<%=request.getContextPath() %>/Admin">Search Another Student</a>
			</div>
			
			<br><br>
			<div class="col-md-4">
				<i class="fa fa-address-card-o fa-2x" aria-hidden="true"></i>&nbsp;
				<font size="4"> <b>Student ID:</b> <c:out value="${StudentID }" /></font>
			</div>
			
			<br><br>
			<div class="col-md-4">
				<i class="fa fa-calendar fa-2x" aria-hidden="true"></i>&nbsp;
				<font size="4"> <b>Date of Birth:</b><c:out value="${row00.dob }" /></font>
			</div>
			<div class="col-md-4 ">
				<i class="fa fa-mobile-phone fa-2x" aria-hidden="true"></i>&nbsp;&nbsp;
				<font size="4"> <b>Mobile Number : </b><c:out value="${row00.mobile }" /></font>
			</div>
			<br><br>
			<div class="col-md-9">
				<i class="fa fa-envelope-o fa-2x" aria-hidden="true"></i>&nbsp;
				<font size="4"> <b>Email Address : </b><c:out value="${row00.email }" /></font>
			</div>
		
		</div>
	</div>
	
	<div class="panel-group">
	    <div class="panel panel-primary">
     		 <div class="panel-heading">
     		 	<font size="4">Student's Skills (General Information)</font>
     		 </div>
      		 <div class="panel-body">
      		 	<p><font size="3">
      		 		1. Academics(Average)</font>
      		 	</p>
      		 	<div class="progress">
    				<div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" aria-valuenow="40" 
    				aria-valuemin="0" aria-valuemax="10" style="width:<c:out value="${cgpi*10 }" />%"><c:out value="${cgpi }" /> CGPI</div>
  				</div>
			</div>

			<div class="panel-body">
      		 	<p><font size="3">
      		 		2. Aptitude Tests</font>
      		 	</p>
      		 	<div class="progress">
      		 	 <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"  aria-valuemin="0" 
    				 aria-valuemax="20" style="width:<c:out value="${((row00.aptitude)*100)/20 }" />%"> <c:out value="${((row00.aptitude)*100)/20 }" /> %</div>
  				</div>
			</div>

			<div class="panel-body">
      		 	<p><font size="3">
      		 		3. Programming Skills</font>
      		 	</p>
      		 	<div class="progress">
      		 	 <div class="progress-bar progress-bar-default progress-bar-striped active" role="progressbar"  aria-valuemin="0" 
    				 aria-valuemax="20" style="width:<c:out value="${(((row00.c)+(row00.cpp)+(row00.java))*100)/60 }" />%"> <c:out value="${(((row00.c)+(row00.cpp)+(row00.java))*100)/60 }" /> %</div>
  				</div>
			</div>
    	</div>
    </div>
    <br>

    <div class="panel-group">
       <div class="panel panel-primary">
     	  <div class="panel-heading"><b>Result and Analysis</b></div>
      	  <div class="panel-body">
      		<ul class="nav nav-tabs nav-justified">
   				<li class="active"><a data-toggle="tab" href="#menu1">Academics</a></li>
                <li><a data-toggle="tab" href="#menu2">Aptitude Results</a></li>
    			<li><a data-toggle="tab" href="#menu3">Computer Test</a></li>
   			    <li><a data-toggle="tab" href="#menu4">Programming Skills</a></li>
  			</ul>


<div class="tab-content">
    
  <div id="menu1" class="tab-pane fade in active">
    <h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Academic Scores</h3>
    <div class="row">
    	<div class="col-md-10 col-md-offset-1">
    		<table class="table table-hover table-striped table-bordered">
   		 <thead>
    	  <tr>
    	  <th><center>Sr No</th>
        <th><center>Academics</th>
        <th><center>Scores(Percentage/Pointers)</th>
      	</tr>
    	</thead>
   		 <tbody>
     	 <tr>
     	 	<td><center>1</td>
        <td><center>SSC or Equivalent</td>
        <td><center><c:out value="${row00.ssc }" /> %</center></td>
        </tr>

     	 <tr>
     	 	<td><center>2</td>
        <td><center>HSC or Equivalent</td>
        <td><center><c:out value="${row00.hsc }" /> %</center></td>
       </tr>
      
      	<tr>
      		<td><center>3</td>
        <td><center>Semester 1</td>
        <td><center><c:out value="${row00.sem1 }" /> SGPI</center></td>
        </tr>

        <tr>
        	<td><center>4</td>
        <td><center>Semester 2</td>
        <td><center><c:out value="${row00.sem2 }" /> SGPI</center></td>
        </tr>

        <tr>
        	<td><center>5</td>
        <td><center>Semester 3</td>
        <td><center><c:out value="${row00.sem3 }" /> SGPI</center></td>
        </tr>

        <tr>
        	<td><center>6</td>
        <td><center>Semester 4</td>
        <td><center><c:out value="${row00.sem4 }" /> SGPI</center></td>
        </tr>

        <tr>
        	<td><center>7</td>
        <td><center>Semester 5</td>
        <td><center><c:out value="${row00.sem5 }" /> SGPI</center></td>
        </tr>

        <tr>
        	<td><center>8</td>
        <td><center>Semester 6</td>
        <td><center><c:out value="${row00.sem6 }" /> SGPI</center></td>
        </tr>

    	</tbody>
       </table>
    </div>
   </div> <!-- end of row-->

   <br><br>
		
   	<!-- Making of chart 1 -->
  	<div id="chartContainer1" style="height: 350px; width: 80%; margin-left:100px;">
  </div>
  <br><br><br>
<!-- Making of chart 2 -->
  <div id="chartContainer2" style="height: 350px; width: 80%; margin-left:100px;">
  </div>

 </div> <!--end of menu 1></!-->

<!-- Menu 2 -->
    <div id="menu2" class="tab-pane fade ">
    	<h3>Aptitude Test Scores</h3>
    	<div class="row">
    	<div class="col-md-12">
    		<table class="table table-hover table-striped table-bordered">
   		 <thead>
    	  <tr>
        <th><center>Test</th>
        <th><center>Student's Score</th>
        <th><center>Maximum Score</th>
        <th><center>Percentage</th>
        </tr>
    	</thead>
   		 <tbody>
     	 <tr>
        <td><center>Aptitude Test</td>
        <td><center> <c:out value="${row00.aptitude }" /> </center></td>
        <td><center>20</center></td>
        <td><center> <c:out value="${((row00.aptitude)*100)/20 }" /> %</center></td>
    	</tr>
    	</tbody>
    	</table>
        </div>
        </div>
        <br><br>

        <div class="col-md-10 col-md-offset-1" style="height: 400px;margin-left: 150px;">
		   <div id="chartContainer3" style="height: 300px; width: 80%;">
		   </div>
		</div>
		<br>

		<div class="row">
		  <div class="col-md-6 " style="height: 400px;">
		   	<div id="chartContainer4" style="height: 300px; width: 80%;"></div>
		  </div>

		   <div class="col-md-6" style="height: 400px;">
		   		<div id="chartContainer5" style="height: 300px; width: 80%; "></div>
			</div>
		</div>


     </div>
<!--end of menu 2></!-->


   <div id="menu3" class="tab-pane fade">
      
       <h3>Basic Computer Test Scores</h3>
    	<div class="row">
    	<div class="col-md-12">
    		<table class="table table-hover table-striped table-bordered">
   		 <thead>
    	  <tr>
        <th><center>Test</th>
        <th><center>Student's Score</th>
        <th><center>Maximum Score</th>
        <th><center>Percentage</th>
        </tr>
    	</thead>

   		 <tbody>
     	 <tr>
 
        <td><center>Basic Computer Test</td>
        <td><center>  <c:out value="${row00.basiccomputer }" /> </center></td>
        <td><center>20</center></td>
        <td><center><c:out value="${((row00.basiccomputer)*100)/20 }" /> %</center></td>
    	</tr>

    	</tbody>
    	</table>
        </div>
        </div>
        <br>
		<div class="row" style="height: 400px;">
        <div class="col-md-10 col-md-offset-1" style="margin-left: 150px;">
		   <div id="chartContainer6" style="height: 300px; width: 80%;">
		   </div>
		</div>
		</div>


 </div><!--end of menu 3></!-->



    <div id="menu4" class="tab-pane fade">
      
      <h3>Programming Skills</h3>
    	<div class="row">
    	<div class="col-md-12">
    		<table class="table table-hover table-striped table-bordered">
   		 <thead>
    	  <tr>
    	 <th><center>Sr No</th>
        <th><center>Test</th>
        <th><center>Student's Score</th>
        <th><center>Maximum Score</th>
        <th><center>Percentage</th>
        </tr>
    	</thead>

   		 <tbody>
     	 <tr>
        <td><center>1</center></td>
     	<td><center>C Programming Test</td>
        <td><center> <c:out value="${row00.c }" /> </center></td>
        <td><center>20</center></td>
        <td><center><c:out value="${((row00.c)*100)/20 }" />  %</center></td>
    	</tr>

    	 <tr>
        <td><center>2</center></td>
     	<td><center>C++ Programming Test</td>
        <td><center> <c:out value="${row00.cpp }" /> </center></td>
        <td><center>20</center></td>
        <td><center><c:out value="${((row00.cpp)*100)/20 }" />  %</center></td>
    	</tr>

    	 <tr>
        <td><center>3</center></td>
     	<td><center>Java Programming Test</td>
        <td><center> <c:out value="${row00.java }" /> </center></td>
        <td><center>20</center></td>
        <td><center><c:out value="${((row00.java)*100)/20 }" />  %</center></td>
    	</tr>
		</tbody>
    	</table>
        </div>
        </div>
        <br><br>

        <div class="row">
        <div class="col-md-10 col-md-offset-1" style="height: 400px;">
		  <div id="chartContainer7" style="height: 400px; width: 100%;"></div>
		</div>
		</div>
		<br>

		<div class="row" style="height: 400px;">
		  <div class="col-md-8 col-md-offset-2">
		   	<div id="chartContainer8" style="height: 300px; width: 120%;"></div>
		  </div>
		</div>
		<div class="row" style="height: 400px;">
		   <div class="col-md-8 col-md-offset-2">
		   		<div id="chartContainer9" style="height: 300px;width: 120%; "></div>
			</div>
		</div>
		<br>

		<div class="row" style="height: 400px;">
			 <div class="col-md-8 col-md-offset-2">
		   		<div id="chartContainer10" style="height: 300px; width: 120%; "></div>
			</div>
			
		</div>



    </div><!--end of menu 4></!-->

   
  </div><!-- end of tab-->

      		</div> <!-- end of panel body></!-->	
    	</div> <!--end of panelprimary></!-->
	</div><!-- end of panel group></!-->
    
</div><!--End of div whole div -->

</body>

<script type="text/javascript">
function a1() {
	var chart = new CanvasJS.Chart("chartContainer1", {
		title: {
			text: "Comparision of Academic Performances"
		},
		data: [{
			type: "column",
			dataPoints: [
				{ y: <c:out value="${row00.ssc }" />, label: "SSC or Equivalent ( in %)" },
				{ y: <c:out value="${row00.hsc }" />, label: "HSC/Diploma or Equivalent" },
				{ y: <c:out value="${cgpi*10 }" />, label: "Engineering (Avg CGPI = <c:out value="${cgpi }" />)" },
				
			]
		}]
	});
	chart.render();

}

//Line chart to show the first  6 semesters marks on Acads-Tab 1
function a2() {
var chart = new CanvasJS.Chart("chartContainer2", {
	title: {
		text: "Line Chart for Semester Marks"
	},
	axisX: {
		interval: 1
	},
	data: [{
		type: "line",
		dataPoints: [
		  { x: 1, y: <c:out value="${row00.sem1 }" /> },
		  { x: 2, y: <c:out value="${row00.sem2 }" /> },
		  { x: 3, y: <c:out value="${row00.sem3 }" /> },
		  { x: 4, y: <c:out value="${row00.sem4 }" /> },
		  { x: 5, y: <c:out value="${row00.sem5 }" /> },
		  { x: 6, y: <c:out value="${row00.sem6 }" /> },
		 
		]
	}]
});
chart.render();
}

//Pie chart for displaying aptitude result
function a3() {
var chart = new CanvasJS.Chart("chartContainer3",
{
theme: "theme2",
title:{
	text: "Aptitude Scores Analysis"
},
data: [
{
	type: "pie",
	showInLegend: true,
	toolTipContent: "{y} - #percent %",
	
	legendText: "{indexLabel}",
	dataPoints: [
		{  y: 10, indexLabel: "Quant Correct" },
		{  y: 5, indexLabel: "Verbal Correct" },
		{  y: 2, indexLabel: "Verbal Incorrect" },
		{  y: 3, indexLabel: "Quant Incorrect"},
	]
}
]
});
chart.render();
}

//Donoughnut chart for Verbal correct
function a4() {
	var chart = new CanvasJS.Chart("chartContainer4", {
		title: {
			text: "Verbal Score Analysis"
		},
		animationEnabled: true,
		theme: "theme2",
		data: [
		{
			type: "doughnut",
			indexLabelFontFamily: "Garamond",
			indexLabelFontSize: 20,
			startAngle: 0,
			indexLabelFontColor: "dimgrey",
			indexLabelLineColor: "darkgrey",
			toolTipContent: "{y} %",

			dataPoints: [
			{ y: 60, indexLabel: "Verbal Correct {y}%" },
			{ y: 40, indexLabel: "Verbal Incorrect{y}%" },
			
			]
		}
		]
	});

	chart.render();
}



//Donoughnut chart for Quant
function a5() {
	var chart = new CanvasJS.Chart("chartContainer5", {
		title: {
			text: "Quantitative Score Analysis"
		},
		animationEnabled: true,
		theme: "theme2",
		data: [
		{
			type: "doughnut",
			indexLabelFontFamily: "Garamond",
			indexLabelFontSize: 20,
			startAngle: 0,
			indexLabelFontColor: "dimgrey",
			indexLabelLineColor: "darkgrey",
			toolTipContent: "{y} %",

			dataPoints: [
			{ y: 75, indexLabel: "Quant Correct {y}%" },
			{ y: 25, indexLabel: "Quant Incorrect {y}%" },
			

			]
		}
		]
	});

	chart.render();
}


//Chart for Personality Tests
function a6() {
	var chart = new CanvasJS.Chart("chartContainer6", {
		title: {
			text: "Basic Computer Test Analysis"
		},
		animationEnabled: true,
		theme: "theme2",
		data: [
		{
			type: "doughnut",
			indexLabelFontFamily: "Garamond",
			indexLabelFontSize: 20,
			startAngle: 0,
			indexLabelFontColor: "dimgrey",
			indexLabelLineColor: "darkgrey",
			toolTipContent: "{y} %",

			dataPoints: [
			{ y: <c:out value="${((row00.basiccomputer)*100)/20 }" /> , indexLabel: "Questions Correct {y}%" },
			{ y: <c:out value="${(100-((row00.basiccomputer)*100)/20) }" />, indexLabel: "Questions Incorrect {y}%" },
			//There is No error here, code is working correctly
			]
		}
		]
	});

	chart.render();
}
//Bar graph for the Comparision of programming skills Tab 4
function a7() {
	var chart = new CanvasJS.Chart("chartContainer7", {
		title: {
			text: "Comparision of Programming Skills"
		},
		data: [{
			type: "column",
			dataPoints: [
				{ y: <c:out value="${((row00.c)*100)/20 }" />, label: "C Program Test" },
				{ y: <c:out value="${((row00.cpp)*100)/20 }" />, label: "C++ Programming Test" },
				{ y: <c:out value="${((row00.java)*100)/20 }" />, label: "Java Programming Test" },
				
			]
		}]
	});
	chart.render();

}

//Donoughnut chart for C programming Test Tab 4
function a8() {
	var chart = new CanvasJS.Chart("chartContainer8", {
		title: {
			text: "C Programming Analysis"
		},
		animationEnabled: true,
		theme: "theme2",
		data: [
		{
			type: "doughnut",
			indexLabelFontFamily: "Garamond",
			indexLabelFontSize: 16,
			startAngle: 0,
			indexLabelFontColor: "dimgrey",
			indexLabelLineColor: "darkgrey",
			toolTipContent: "{y} %",

			dataPoints: [
			{ y: <c:out value="${((row00.c)*100)/20 }" />, indexLabel: "Questions Correct {y}%" },
			{ y: <c:out value="${(100-((row00.c)*100)/20) }" />, indexLabel: "Questions Incorrect {y}%" },
			

			]
		}
		]
	});

	chart.render();
}

//Donoughnut chart for C++ Test Tab 4
function a9() {
	var chart = new CanvasJS.Chart("chartContainer9", {
		title: {
			text: "C++ Programming Analysis"
		},
		animationEnabled: true,
		theme: "theme2",
		data: [
		{
			type: "doughnut",
			indexLabelFontFamily: "Garamond",
			indexLabelFontSize: 16,
			startAngle: 0,
			indexLabelFontColor: "dimgrey",
			indexLabelLineColor: "darkgrey",
			toolTipContent: "{y} %",

			dataPoints: [
			{ y: <c:out value="${((row00.cpp)*100)/20 }" />, indexLabel: "Question Correct {y}%" },
			{ y: <c:out value="${(100-((row00.cpp)*100)/20) }" />, indexLabel: "Question Incorrect {y}%" },
			

			]
		}
		]
	});

	chart.render();
}

//Donoughnut chart for Java Test Tab 4
function a10() {
	var chart = new CanvasJS.Chart("chartContainer10", {
		title: {
			text: "Java Programming Analysis"
		},
		animationEnabled: true,
		theme: "theme2",
		data: [
		{
			type: "doughnut",
			indexLabelFontFamily: "Garamond",
			indexLabelFontSize: 20,
			startAngle: 0,
			indexLabelFontColor: "dimgrey",
			indexLabelLineColor: "darkgrey",
			toolTipContent: "{y} %",

			dataPoints: [
			{ y: <c:out value="${((row00.java)*100)/20 }" />, indexLabel: "Question Correct {y}%" },
			{ y: <c:out value="${(100-((row00.java)*100)/20) }" />, indexLabel: "Question Incorrect {y}%" },
			

			]
		}
		]
	});

	chart.render();
}



</script>

</c:forEach>
</html>