package quiz;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/result")
public class result extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	String quizname;
	String student_name;
	int score;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try
		{
		      Class.forName("com.mysql.jdbc.Driver");
			  Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
			
			    
			  String q="insert into result values(?,?,?,?)";
			  PreparedStatement ps=con.prepareStatement(q);
		HttpSession session=request.getSession();
		String uname = (String)session.getAttribute("username");
		

String qname = (String)session.getAttribute("qname");
int marks = Integer.parseInt(session.getAttribute("marks").toString());
String att;

	  
		  att="present";
	  ps.setString(1,qname);
	  ps.setString(2,uname);
	  ps.setInt(3,marks);
	  ps.setString(4, att);
	 ps.executeUpdate(); 
	 
	
	 PrintWriter out = response.getWriter();
	 out.println("<script>location.href='Student.jsp'</script>");
	 con.close();
		}
		catch(Exception e){e.printStackTrace();}


	
	}

}
