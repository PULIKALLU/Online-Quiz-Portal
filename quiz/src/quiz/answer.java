package quiz;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/answer")
public class answer extends HttpServlet {
	public static int count = 0;
	String qname=null;
	String uname = null;
	int q_no;
	String opt;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		session.setAttribute("count",count);	
		
		try
		  {
			  Class.forName("com.mysql.jdbc.Driver");
			  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
		  }catch(Exception e){e.printStackTrace();}
	    
		answer e = new answer();
		String x = (String)request.getParameter("pre").toString();
		
		if(x.equals("qprev"))
		{
         e.prev(request , response);
        }
		
		if(x.equals("qnext"))
		{
         e.next(request , response);
        }
		
	}
	
	public void prev(HttpServletRequest request, HttpServletResponse response)  throws IOException
	{
		HttpSession session = request.getSession();
		String choice = (String)request.getParameter("option");
		String ans = (String)request.getParameter("ans");
		int  q_no = Integer.parseInt(session.getAttribute("q_no").toString());
		String qname = (String)session.getAttribute("qname");
		String uname = (String)session.getAttribute("username");

		try
		  {
			  Class.forName("com.mysql.jdbc.Driver");
			  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
			  String query = "insert into answer(quizname,student_name,qn_id,response) values (?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1,qname);
				ps.setString(2,uname);
				ps.setInt(3,q_no);
		        ps.setString(4,choice);
		        ps.executeUpdate();
		  }catch(Exception e){e.printStackTrace();}	
		
		if(choice == ans)
		{	
			int  a = Integer.parseInt(session.getAttribute("count").toString());
			int  b = Integer.parseInt(session.getAttribute("q_no").toString());
			a = a + 1;
			b = b - 1;
			session.setAttribute("count",a);
			session.setAttribute("q_no",b);
			response.sendRedirect("quiz.jsp");
			
		}
		else
		{
			int  a = Integer.parseInt(session.getAttribute("count").toString());
			int  b = Integer.parseInt(session.getAttribute("q_no").toString());
			
			b = b - 1;
			session.setAttribute("count",a);
			session.setAttribute("q_no",b);
			response.sendRedirect("quiz.jsp");
		}
		
	}
	
	public void next(HttpServletRequest request, HttpServletResponse response) throws IOException
	{ 
		
	    HttpSession session = request.getSession();
		String choice = (String)request.getParameter("option");
		String ans = (String)request.getParameter("ans");
		int  q_no = Integer.parseInt(session.getAttribute("q_no").toString());
		String qname = (String)session.getAttribute("qname");
		String uname = (String)session.getAttribute("username");

		try
		  {
			  Class.forName("com.mysql.jdbc.Driver");
			  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
			  String query = "insert into answer(quizname,student_name,qn_id,response) values (?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1,qname);
				ps.setString(2,uname);
				ps.setInt(3,q_no);
		        ps.setString(4,choice);
		        ps.executeUpdate();
		       
		 

		if(choice==ans)
		{
			int  a = Integer.parseInt(session.getAttribute("count").toString());
			int  b = Integer.parseInt(session.getAttribute("q_no").toString());
			a = a + 1;
			b = b + 1;
			session.setAttribute("count",a);
			System.out.println(a);
			session.setAttribute("q_no",b);
			response.sendRedirect("quiz.jsp");
			
		}
		else
		{
			int  a = Integer.parseInt(session.getAttribute("count").toString());
			int  b = Integer.parseInt(session.getAttribute("q_no").toString());
			
			b = b + 1;
			session.setAttribute("count",a);
			System.out.println(a);
			session.setAttribute("q_no",b);
			response.sendRedirect("quiz.jsp");
		}
	con.close();
}catch(Exception e){e.printStackTrace();}
	}}		


