<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Information</title>
</head>
<body bgcolor="silver">
<form style="color:red;">
<%!
Connection connection=null;
ResultSet rs=null;
String fname;
String qry="select * from LogInReg.register where Email=?";
String mail="";
String res="";

int age;
%>
<%
	mail=request.getParameter("mail");
	
	try{
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/LogInReg?user=root&password=tiger");
		PreparedStatement preparedStatement=null;
		preparedStatement=connection.prepareStatement(qry);
		
		preparedStatement.setString(1,mail);
		rs=preparedStatement.executeQuery();
		
		while(rs.next())
		{
			res=res+"<br/>FULL NAME:"+rs.getString("FirstName")+rs.getString("LastName")+"<br/>"+"E-mail ID:"+rs.getString("Email")+"<br/>"+"Age:"+rs.getString("Age")+"<br/>"+"Gender:"+rs.getString("Gender");
		}
		
		
	
	}
	catch(ClassNotFoundException e1){
		e1.printStackTrace();
	}
	
	catch(SQLException e){
		e.printStackTrace();
	}
%>

<%=res %>
</form>
</body>
</html>