<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" %>
    <%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String qname = (String)session.getAttribute("qname");
String uname = (String)session.getAttribute("username");

int id=0;
int i=1;
try
{
	int marks = 0;
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
	
	  String query = "select * from answer where quizname= '"+qname+"' and student_name = '"+uname+"'";
	  String query1 = "select * from "+ qname  ;
	  PreparedStatement ps = con.prepareStatement(query);
	  
	  ResultSet rs = ps.executeQuery();
	  rs.last();
	  while(true)
 	  {  
		  
		  String b =  rs.getString("response");
		  PreparedStatement ps1 = con.prepareStatement(query1);
		  
		  ResultSet rs1 = ps1.executeQuery();
		  rs1.next();
		  String crct = rs1.getString("Answer");
		 if(b.equals(crct))
		 {
			 System.out.println("hi1");
			 marks=marks+2;
		 }
		 else
		 {
			 System.out.println("hi");
			 marks=marks-1;
		 }
		 
		 if(rs.previous())
		 {
			 continue;
		 }
		 else
		 {
			 break;
		 }
	  }
	  
	  session.setAttribute("marks",marks);
	  
	  out.println("your marks for the quiz is"+marks);
%>			  
	  <form action="result" method="post"><input type="submit" name="submit" value="go to homepage" /></form>
<%
}catch(Exception e){e.printStackTrace();}

%>

</body>
</html>