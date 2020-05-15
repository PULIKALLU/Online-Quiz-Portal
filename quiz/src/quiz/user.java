package quiz;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/user")
public class user extends HttpServlet {
	
	String username;
	String password;
	String usertype;

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String x = request.getParameter("submit");
		
		user u  = new user();
		if(x.equals("login"))
		{
		   u.login(request,response);
		}
		
		if(x.equals("register"))
		{
		   u.register(request,response);
		}
	}
   
   
   public void login(HttpServletRequest request, HttpServletResponse response)
   {
	   String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		String usertype = request.getParameter("usertype");
		
		String query = "select * from user1 where username=? and password=? and usertype=?";
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSL=false","root","");
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1,username);
		ps.setString(2,password);
		ps.setString(3,usertype);

		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
		{
			System.out.println("login successful");			
			if(usertype.equals("Teacher"))
			{
			 HttpSession session = request.getSession();
			 session.setAttribute("username",username);
			 response.sendRedirect("Teacher.jsp");
			}
			
			else
			{
				 HttpSession session = request.getSession();
				 session.setAttribute("username",username);
				 
					
				 PrintWriter out = response.getWriter();
				 out.println("<script>alert('Quiz has negative marks')</script>");
				 response.sendRedirect("Student.jsp");
			}
		}
		
		else
		{
			PrintWriter out = response.getWriter();
			out.println("<script>alert('Invalid Username or Password!')</script>");
			out.println("<script>location.href='login.jsp'</script>");
			
		}
		
				
		ps.close();
		con.close();
		}catch(Exception e){e.printStackTrace();}

   }
   
   



   public void register(HttpServletRequest request, HttpServletResponse response)
   {
	   String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		String usertype = request.getParameter("usertype");
		
		String query = "insert into user1(username,password,usertype) values(?,?,?)";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSL=false","root","");
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1,username);
		ps.setString(2,password);
		ps.setString(3,usertype);
		int a = ps.executeUpdate();
		if(a>0)
		{   
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'> alert('registered successfully!please login')</script>");
			out.println("<script language='javascript'> location.href='login.jsp'</script>");
		}
		
		ps.close();
		con.close();
		}catch(Exception e){e.printStackTrace();}

   }
   
    public void logout(HttpServletResponse response) throws IOException
    {
     response.sendRedirect("login.jsp");	
    }
    
   }