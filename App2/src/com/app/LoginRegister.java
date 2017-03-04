package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginRegister
 */
public class LoginRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String uri=request.getRequestURI();
		System.out.println(uri); 
		System.out.println( System.getProperty("catalina.home") ) ; 
		System.out.println( System.getProperty("catalina.base") );
		DataUpdating dataUpdating=new DataUpdating();
		
		if( uri.endsWith("register") )
		{
			PrintWriter out=response.getWriter();
			
			String fname=request.getParameter("fname");
			String lname=request.getParameter("lname");
			String mail=request.getParameter("mail");
			String password=request.getParameter("pwd");
			String conPass=request.getParameter("conpwd");
			
			boolean check=dataUpdating.checkRegistration(mail);
			
			
			boolean isUpdated= dataUpdating.update(fname,lname,mail,password);
			if(isUpdated){
				out.println("<html><body>Registration Completed</body></html>");
			}
			else{
				out.println("<html><body>This E-mail ID already Registered</body></html>");
			}
			
			
		}
		
		
		
		
		if(uri.endsWith("login"))
		{
			String mail=request.getParameter("mail");
			String password=request.getParameter("pwd");
			String user = dataUpdating.authenticate(mail,password);
			
			PrintWriter out=response.getWriter();
			 if( user!=null ){
					out.println("<html><body>WEL COME "+user+"</body></html>");
			 }
			 else{
					out.println("<html><body>Invalid E-mail OR Password</body></html>");
			}
		}
		
		
	}

}
