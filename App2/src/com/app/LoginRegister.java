package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
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
		
		if( uri.compareTo("/App2/register")==0 )
		{
			PrintWriter out=response.getWriter();
			
			/*Enumeration<String>keynum =getInitParameterNames();
			while(keynum.hasMoreElements())
			{
				String key=keynum.nextElement();
				String val=request.getParameter(key);
			}*/
			
			String fname=request.getParameter("fname");
			String lname=request.getParameter("lname");
			String age=request.getParameter("age");
			String gender=request.getParameter("gender");
			String mail=request.getParameter("mail");
			String password=request.getParameter("pwd");
			String conPass=request.getParameter("conpwd");
			
			
			if( fname!="" && mail!="" && password!=""&&age!=""&&gender!="" )
			{
				if( password.equals(conPass) )
				{
					boolean isUpdated= dataUpdating.update(fname,lname, age, gender, mail,password);
					
						if(isUpdated){
							out.println("<html><body>Registration Completed</body><br/></html>");
							RequestDispatcher requestDispatcher=request.getRequestDispatcher("login.html");
							requestDispatcher.include(request, response);
						}
						else{
							out.println("<html><body><p style=\"color:red;\">This E-mail ID already Registered</p></body></html>");
							
							RequestDispatcher requestDispatcher=request.getRequestDispatcher("login.html");
							requestDispatcher.include(request, response);
						}
					
				}
				
				else{
					out.println("<html><body><p style=\"color:red;\">Password not matching with Conformation Password</p></body></html>");
					RequestDispatcher requestDispatcher=request.getRequestDispatcher("register.html");
					requestDispatcher.include(request, response);
				}
			}
			
			
			else{
				out.println("<html><body><p style=\"color:red;\">Fill Mandatory Informations</p></body></html>");
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("register.html");
				requestDispatcher.include(request, response);
			}

		}
		
		
		
		
		if(uri.equals("/App2/login"))
		{
			
			String mail=request.getParameter("mail");
			String password=request.getParameter("pwd");
			String user = dataUpdating.authenticate(mail,password);
			
			PrintWriter out=response.getWriter();
			 if( user!=null ){
					out.println("<html><body>WEL COME "+user+"<br/></body></html>");
					RequestDispatcher requestDispatcher=request.getRequestDispatcher("loginDisplay.jsp");
					requestDispatcher.include(request, response);
			 }
			 else{
					out.println("<html><body><p style =\"color:red;\">Invalid E-mail OR Password</body></html>");
					RequestDispatcher requestDispatcher=request.getRequestDispatcher("login.html");
					requestDispatcher.include(request, response);
			 }
			 
		}
		
	}

}
