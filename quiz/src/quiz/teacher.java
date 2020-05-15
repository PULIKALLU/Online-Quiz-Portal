package quiz;

import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.text.DateFormat;
import java.time.LocalTime;

@WebServlet("/teacher")
public class teacher extends user {
	private static final long serialVersionUID = 1L;
	
	String name;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ 
		
		String x = request.getParameter("submit").toString();
		teacher t = new teacher();
		HttpSession session = request.getSession();
		String  uname = session.getAttribute("username").toString();
		if(x.equals("create_quiz"))
		{
	      t.create_quiz(request,response);
		}
	
		if(x.equals("remove_quiz"))
		{
          t.remove_quiz(request, response );
		}
		
		if(x.equals("logout"))
		{
          t.logout(request, response , uname);
		}
		
		
	
	}

	public void create_quiz(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		String quizname = (String)request.getParameter("quizname");
		String num = (String)request.getParameter("noq");

		String time = (String)request.getParameter("quiztime");
		

		HttpSession sess = request.getSession();
		sess.setAttribute("quizname",quizname);
		sess.setAttribute("n",num);
		sess.setAttribute("t",time);

		String username = (String)sess.getAttribute("username");
        String q = "select * from quiz where quizname='"+quizname+"'";
        
		String query = "insert into quiz(quizname,teachername) values (?,?)";
		try
		{
			  Class.forName("com.mysql.jdbc.Driver");
			  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
			  PreparedStatement ps1 = con.prepareStatement(q);
			  ResultSet rs1 = ps1.executeQuery();
			  
			  if(rs1.next())
			  {
				  PrintWriter out = response.getWriter();
				  out.println("<script>window.alert('Quiz already exists!')</script>");
				  out.println("<script>location.href='Teacher.jsp'</script>");
			  }
			  else
			  {
				  PrintWriter out = response.getWriter();
					 
			  PreparedStatement ps = con.prepareStatement(query);
			  ps.setString(1,quizname);
			  ps.setString(2,username);
			  ps.executeUpdate();
			  RequestDispatcher rt=request.getRequestDispatcher("quiz");
				rt.forward(request,response);
			  }
			  
		}catch(Exception e){e.printStackTrace();}
      
		
	}
	
	
	public void remove_quiz(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String a = request.getParameter("qu").toString();
	
		 String query = "drop table " + a;
		 String query1 = "delete from quiz where quizname= '"+a+"'";
		try
		{
			  Class.forName("com.mysql.jdbc.Driver");
			  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
			  PreparedStatement ps = con.prepareStatement(query);
			  PreparedStatement ps1 = con.prepareStatement(query1);
			int k =   ps.executeUpdate();
			 int m = ps1.executeUpdate();
			 
			 if(k>0 && m>0)
			 {
				 PrintWriter out = response.getWriter();
				  out.println("<script> location.href='Teacher.jsp'</script>");
				  
			 }
			  
			  
		}catch(Exception e){e.printStackTrace();}
    
		
		
	}	
	
	public void logout(HttpServletRequest request, HttpServletResponse response,String uname) throws IOException
	{
		response.sendRedirect("login.jsp");
	}
 }

