<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
        <%@ page import="java.io.*,java.util.*" %>
        <!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Search Engine</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <link rel="shortcut icon" href="http://icanbecreative.com/resources/images/favico.ico" />
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

       
<!-- My imported libraries -->
        <link rel="stylesheet" href="pages/admin/fontawesome/css/font-awesome.min.css">
        <link href="pages/admin/css/index.css" rel="stylesheet" type="text/css">
        <link href="pages/admin/css/snackbar.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="pages/admin/js/index.js"></script>
        <script type="text/javascript" src="pages/admin/js/snackbar.js"></script>

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
   
    <div class="jumbotron" >
    <c:if test="${updatestatus == null }">
    <span class="dropbtn" style="color: yellow; margin-left:40%; font-size: x-large; " >Welcome ! <c:out value="${name }" /></span>
    </c:if>
     <c:if test="${updatestatus != null  }">
    <span class="dropbtn" style="color: yellow; margin-left:40%; font-size: x-large; " >Student Data Updated successfully
    </c:if>
      <span class="btn btn-primary">
         <a style="color: orange;  margin-left:20%; font-size: x-large;" href="<%=request.getContextPath() %>/Admin?task=logout">LOGOUT</a>
       </span>
      
      </div>
        <form  action="<%=request.getContextPath() %>/Admin" onsubmit="myFunction();">
         <input type="hidden" name="task" value="search" />
            <div class="search-wrapper">
                <div class="input-holder">
                    <input type="text" name="queryName" class="search-input" placeholder="Type name of student to search" />
                    <button type="submit" class="search-icon" onclick="searchToggle(this, event);"><span></span></button>
                    
                </div>
				 <span class="close" onclick="searchToggle(this, event);"></span>
                <div class="result-container">
					
                </div>
                
                
            </div>

        </form>
        
      
        <div id="snackbar"><i class="fa fa-spinner fa-pulse fa-1x fa-fw"></i>
            Just a sec, Opening the Doors of Success......
        </div> 
<script type="text/javascript">
function myFunction() {
    var x = document.getElementById("snackbar")
    x.className = "show";
}
</script>
    </body>
</html>
