<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script>
function func()
{
	var x = document.getElementById("attemptquiz");
	if(x.style.display == "block")
		x.style.display = "none";
	else
		x.style.display = "block";
	
	var y = document.getElementById("viewresult");
	if(y.style.display == "block")
		y.style.display = "none";
	
}

function func1()
{
	var x = document.getElementById("viewresult");
	if(x.style.display == "block")
		x.style.display = "none";
	else
		x.style.display = "block";
	
	var y = document.getElementById("attemptquiz");
	if(y.style.display == "block")
		y.style.display = "none";
	
}


</script>

<div style ="float:left position:relative;">
<form action ="result" method="post">
<input type="submit" name="logout" value="log"  />
</form>
</div>

<div style="margin:160px;">
<button style="width:80px; height:80px" onclick="func()" >Attempt Quiz</button>
<button style="width:80px; height:80px" onclick="func1()">View Result</button>
</div>

<div id="attemptquiz" style="display:none; margin:160px">
<table border="2">
	
	<tr> 
	<form action="student" method="post">
	<input type="text" name="quiz_name" />
	<input type="text" name="q_no" value="1" style="display:none" />
	<input type="submit" name="submit" value="attempt_quiz"/>
	</form>
	</tr>
  
   <tr>
        <td>Quizname</td>
        <td>Teacher name</td>
    </tr>

<%
String a="";
String query = "select * from quiz";
try
{
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
	  PreparedStatement ps = con.prepareStatement(query);
	  ResultSet rs = ps.executeQuery();

  while(rs.next())
	  {
%>		
		 <tr>
		 <td><%out.println(rs.getString("quizname")); %></td>
         <td><%out.println(rs.getString("teachername")); %></td>
         </tr>
<%	 
	  }
  
}
catch(Exception e){e.printStackTrace();}

%>
</table>

</div>



<div id="viewresult" style="display:none; margin:160px">

<table border="2">
   <tr>
        <td>quizname</td>
        <td>marks</td>
   </tr>

<%
String p = session.getAttribute("username").toString();
String query1 = "select * from result where  uname ='"+p+"'";
try
{
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
	  PreparedStatement ps1 = con.prepareStatement(query1);
	  ResultSet rs1 = ps1.executeQuery();

  while(rs1.next())
	  {
%>		  
		 <tr>
		 <td><%out.println(rs1.getString("qname")); %></td>
         <td><%out.println(rs1.getString("marks")); %></td>
         </tr>
<%	 
	  }
  
}
catch(Exception e){e.printStackTrace();}

%>
</table>

</div>


</body>
</html>