<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.PrintWriter , javax.servlet.http.HttpSession,java.io.IOException" %>
<%@ page import="java.sql.*;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table >

<%
 String qname = (String)session.getAttribute("qname");


System.out.println(qname);
int b = Integer.parseInt(session.getAttribute("q_no").toString());
session.setAttribute("q_no",b);

String query = "select * from " + qname;

try
{
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
	  Statement ps = con.createStatement();
	  ResultSet rs = ps.executeQuery(query);
	  

	  
	  if(b <= Integer.parseInt(session.getAttribute("qcount").toString()))	
	  {
		
	  rs.absolute(b);
	  
		
         if(b == Integer.parseInt(session.getAttribute("qcount").toString()))
         {
  %>      	 
         <form action="answer" method="post"  target="_parent">
         <%= rs.getString("id") %><p>.</p>
		 <tr>
		 <td><%out.println(rs.getString("question")); %></td>
		 </tr>
		<tr><td><input type="radio" name="option" value="A"><%=rs.getString("A")%></td></tr>
        <tr><td><input type="radio" name="option" value="B"><%=rs.getString("B")%></td></tr>
        <tr><td><input type="radio" name="option" value="C"><%=rs.getString("C")%></td></tr>
        <tr><td><input type="radio" name="option" value="D"><%=rs.getString("D")%></td></tr>
        <input type="text" name="ans" value="<%=rs.getString("Answer")%>" style="display:none" />
        <tr><td><input type="submit" name="pre" value="qprev" /></td></tr>
        <tr><td><input type="submit" name="pre" value="qnext" /></td></tr>
         </form>
 <%        
        }
        
         else
         {
 %>       	 
         <form action="answer" method="post" >
         <%= rs.getString("id") %><p>.</p>
		 <tr>
		 <td><%out.println(rs.getString("question")); %></td>
		 </tr>
		<tr><td><input type="radio" name="option" value="A"><%=rs.getString("A")%></td></tr>
        <tr><td><input type="radio" name="option" value="B"><%=rs.getString("B")%></td></tr>
        <tr><td><input type="radio" name="option" value="C"><%=rs.getString("C")%></td></tr>
        <tr><td><input type="radio" name="option" value="D"><%=rs.getString("D")%></td></tr>
        <input type="text" name="ans" value="<%=rs.getString("Answer")%>" style="display:none" />
        <tr><td><input type="submit" name="pre" value="qprev" /></td></tr>
        <tr><td><input type="submit" name="pre" value="qnext" /></td></tr>
         </form>
 <%         
        } 
	   
	  }
	  
	  else
	  {
		 response.sendRedirect("endTest.jsp");
	  }
}catch(Exception e){e.printStackTrace();}

%>



</body>
</html>