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

@WebServlet("/quiz")
public class quiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	String quizname;
	String time;
	int no_of_questions;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ 
	RequestDispatcher rt=request.getRequestDispatcher("addquestion.jsp");
	rt.forward(request, response);
	}

	}

