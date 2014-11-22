<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Transactions</title>
<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
</head>
<body>
<%
//allow access only if session exists
if(session.getAttribute("user") == null){
  response.sendRedirect("Login.jsp");
}
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
  if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
%>

<table class="title">
<tr><th>Modify Transactions</th></tr>
</table>
<br/><br/>

<form action="modifyTransaction.jsp">
<h2>Choose an event id to modify</h2>
<select name="modifyByID">
<%
	Database.connect();
	ResultSet rs=Database.runQuery("select id,name from transaction");
	boolean more=rs.next();
	while(more)
	{		
%>
		<option value="<%=rs.getInt(1)%>"> <%=rs.getString(2) %></option>
<%
	more=rs.next();
	}
	rs.close();
	Database.close();
%>
</select>
<br/><br/>
<input type="submit" name="Submit">
</form>

<% 
	String id=request.getParameter("modifyByID");
	if(id!=null)
	{
		Database.connect();
		rs=Database.runQuery("select * from transaction where id="+id);
		rs.next();
%>
<br/><br/>
<fieldset>
<legend>Enter the details</legend>
<br/><br/>
<form action="ModifyTransactionServlet">
<input type="hidden" name="id" value=" <%= id %>" /> 
<input type="hidden" name="oldName" value="<%= rs.getString(2) %>" />
Name of the Sponsor : <input type="text" name="name" value="<%= rs.getString(2) %>" required><br/><br/>
Financial Assistance Provided : <input type="number" name="money" value="<%= rs.getString(3) %>" required><br/><br/>
Description : <textarea name="description" cols="40" rows="5" value="<%= rs.getString(4) %>" required>no description entered</textarea><br/><br/>
Gain : <select name="gain" required>
<option value="true">True</option>
<option value="false">False</option>
</select><br/><br/>
<input type="submit" name="Modify"><br/><br/>
</form>
</fieldset>

<% 	
	rs.close();
	Database.close();
	}
%>

<br/><br/>
<form action="transaction.jsp" method="post">
<input type="submit" value="Go back to transactions" >
</form>
<br/><br/>

<br/><br/>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
<br/><br/>	
</body>
</html>