package com.app;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataUpdating {

	
		LoginRegister serv=new LoginRegister();
		Connection connection=null;
		ResultSet rs=null;
		
		public boolean update(String fname,String lname,String age,String gender,String mail,String password)
		{
			
			String qry="insert into LogInReg.register values(?,?,?,?,?,?)";
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/LogInReg?user=root&password=tiger");
				PreparedStatement preparedStatement=null;
				preparedStatement=connection.prepareStatement(qry);
				
				preparedStatement.setString(1,fname);
				preparedStatement.setString(2,lname);
				preparedStatement.setString(3, age);
				preparedStatement.setString(4,gender);
				preparedStatement.setString(5,mail);
				preparedStatement.setString(6,password);
				
				preparedStatement.executeUpdate();
				
			}
			catch (SQLException | ClassNotFoundException e) 
			{
				e.printStackTrace();
				return false;
			}
			finally
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
			return true;
		}
		
		
		public String authenticate(String mail, String password)
		{
			String user = null;
			
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/LogInReg?user=root&password=tiger");
				String qry="select * from LogInReg.register where Email = ? and Password = ?";
				
				PreparedStatement preparedStatement=null;
				
				preparedStatement=connection.prepareStatement(qry);
				preparedStatement.setString(1,mail);
				preparedStatement.setString(2,password);
				rs=preparedStatement.executeQuery();
				 
				if( rs.next() ){
					user=rs.getString(1);
				}
				else{
				}
			 } 
			 catch (SQLException | ClassNotFoundException e) 
			 {
				e.printStackTrace();
			 }

			 
			 finally{
				 try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			 }
			 return user;
		 }

}
