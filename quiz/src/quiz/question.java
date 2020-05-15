package quiz;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.sql.*;

@WebServlet("/question")
public class question extends HttpServlet
{
	String question;
	String A,B,C,D;
	String crct;
	int qn_no;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	  String question = (String)request.getParameter("question");
	  String A = (String)request.getParameter("a");
	  String B = (String)request.getParameter("b");
	  String C = (String)request.getParameter("c");
	  String D = (String)request.getParameter("d");
	  String ans = (String)request.getParameter("answer");
	  
	  HttpSession session = request.getSession();
	  String quizname = (String)session.getAttribute("quizname");
	  String time = session.getAttribute("t").toString();
	  
	  String query1 =  " create table if not exists "+quizname+"(id int not null auto_increment,question varchar(200),A varchar(50),B varchar(50),C varchar(50),D varchar(50),Answer varchar(10), time varchar(30), primary key(id));";
	  String query = "insert into "+quizname+"(question,A,B,C,D,Answer,time) values(?,?,?,?,?,?,?)";
	  try
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useSSl=false","root","");
		  PreparedStatement ps = con.prepareStatement(query1);
		  ps.executeUpdate();
		  
		  PreparedStatement ps1 = con.prepareStatement(query);
		  
		  ps1.setString(1,question);
		  ps1.setString(2,A);
		  ps1.setString(3,B);
		  ps1.setString(4,C);
		  ps1.setString(5,D);
		  ps1.setString(6,ans);
		  ps1.setString(7,time);
		  int n = ps1.executeUpdate();
		  
		  if(n>0)
		  {   
			  PrintWriter out1 = response.getWriter();
			  out1.println("table created and data entered successfully");
		  }
			  
		  
	 
	  
	  String num = (String)session.getAttribute("n");
	  int s = Integer.parseInt(num.trim());

	  
	  
	  if(s > 1)
	  {
		  s = s - 1;
		  String str = String.valueOf(s);
		  session.setAttribute("n",str);
		  response.sendRedirect("addquestion.jsp");
	  }
	  else
	  {
         response.sendRedirect("Teacher.jsp");
	  }
	  con.close();
	}catch(Exception e){e.printStackTrace();}
	  		
	}

}
