package quiz;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/student")
public class student extends user {
	private static final long serialVersionUID = 1L;
	
	String name;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession se=request.getSession();
		String uname=(String) se.getAttribute("username");
		String x = request.getParameter("submit").toString();
		student s = new student();
		if(x.equals("attempt_quiz"))
		{
			s.attempt_quiz(request,response);
		}
		
		if(x.equals("logout"))
		{
			s.logout(request,response,uname);
		}
	}
	
	public void attempt_quiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int count = 0;
		HttpSession session = request.getSession(); 
		String a = request.getParameter("quiz_name").toString();
		int n = Integer.parseInt(request.getParameter("q_no").toString());
		session.setAttribute("q_no",n);
		session.setAttribute("qname",a);

		try
		{
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
		 String query = "select * from " + a;
		 PreparedStatement ps = con.prepareStatement(query);
		 
		 ResultSet rs = ps.executeQuery();
		 String time=null;
		while(rs.next())
		{
			count++;
			time = rs.getString("time");
		}
		session.setAttribute("t",time);
		session.setAttribute("qcount",count);

		}catch(Exception e){e.printStackTrace();}

		String t = session.getAttribute("t").toString();
		String[] time = t.split( ":" );
		int min = Integer.parseInt ( time[0].trim() );
		int sec = Integer.parseInt ( time[1].trim() );
		session.setAttribute("min",min);
		session.setAttribute("sec",sec);

        PrintWriter out = response.getWriter();
        out.println("<script> alert('QUIZ HAS NEGATIVE MARKS')</script>");
        out.println("<script> location.href='timer.jsp'</script>");

	}
	
	
	
	public void logout(HttpServletRequest request, HttpServletResponse response,String username) throws ServletException, IOException
	{
		response.sendRedirect("login.jsp");
	}
	

}
