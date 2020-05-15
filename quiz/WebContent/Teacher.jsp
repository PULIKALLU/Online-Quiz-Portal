<%@ page language="java" session="true" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher</title>
</head>
<body>
<script>
function func()
{
	var x = document.getElementById("addquiz");
	if(x.style.display == "block")
		x.style.display = "none";
	else
		x.style.display = "block";
	
	var y = document.getElementById("removequiz");
	if(y.style.display == "block")
		y.style.display = "none";
	
	var z = document.getElementById("viewresult");
	if(z.style.display == "block")
		z.style.display = "none";
	
}

function func1()
{   
	var x = document.getElementById("addquiz");
	if(x.style.display == "block")
		x.style.display = "none";
	
	var y = document.getElementById("removequiz");
	if(y.style.display == "block")
		y.style.display = "none";
	else
		y.style.display = "block";
	 
	var z = document.getElementById("viewresult");
	if(z.style.display == "block")
		z.style.display = "none";
}

function func2()
{   
	var x = document.getElementById("addquiz");
	if(x.style.display == "block")
		x.style.display = "none";
	
	var y = document.getElementById("removequiz");
	if(y.style.display == "block")
		y.style.display = "none";
	
	var z = document.getElementById("viewresult");
	if(z.style.display == "block")
		z.style.display = "none";
	else
		z.style.display = "block";
}

</script>
<%
String username = (String)session.getAttribute("username");
PrintWriter out1=response.getWriter();
%>

<h1><% out1.print("welcome "+ username);%></h1>


<form action ="teacher" method="post">
<input type="submit" name="submit" value="logout" style ="float:right;" />
</form>


<div style="margin:160px;">
<button style="width:80px; height:80px" onclick="func()" >Add Quiz</button>
<button style="width:80px; height:80px" onclick="func1()">Remove Quiz</button>
<button style="width:80px; height:80px" onclick="func2()">view Result</button>
</div>

<form action="teacher" method="post" >
<div  id="addquiz" style="display:none; margin:160px;">
<table>
<tr> 
<td><label>Enter Quiz Name :</label></td>
<td><input type="text" name="quizname"/></td>
</tr>

<tr> 
<td><label>Enter no.of Questions :</label></td>
<td><input type="number" name="noq" min="1" max="15"/></td>
</tr>

<tr> 
<td><label>Enter Quiz Time :</label></td>
<td><input type="time" name="quiztime"/></td>
</tr>

<tr> 
<td><input type="submit" name="submit" value="create_quiz" /></td>
</tr>

</table>
</div>
</form>
<div id="removequiz" style="display:none; margin:160px">
<p>Enter the name of quiz to be removed :</p>
<form action="teacher" method="post">
<input type="text" name="qu" />
<input type="submit" name="submit" value="remove_quiz" />
</form>
<table border="2">
   <tr>
        <td>quizname</td>
        <td>teacher name</td>
   </tr>

<%

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
        <td>student name</td>
        <td>marks</td>
        <td>attendance</td>
        
   </tr>

<%

String query1 = "select * from result";
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
         <td><%out.println(rs1.getString("uname")); %></td>
         <td><%out.println(rs1.getString("marks")); %></td>
         <td><%out.println(rs1.getString("attendance")); %></td>
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