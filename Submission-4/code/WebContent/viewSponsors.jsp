<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Sponsors</title>
<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
</head>
<body>
<%
//allow access only if session exists
if(session.getAttribute("user") == null){
  response.sendRedirect("login.html");
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
<tr><th>View Sponsors</th></tr>
</table>
<br/><br/><br/><br/>
<%
	Database.connect();
	ResultSet rs=Database.runQuery("select * from sponsors");
	boolean more=rs.next();
%>
	<table width="100%">
	<tr>
	<th>ID</th>
	<th>Name</th>
	<th>Finance Provided</th>
	<th>Description</th>
	</tr>
<% 
	if(more)
	{
		while(more)
		{
		%>
			<tr>
			<td><%=rs.getString(1) %></td>
			<td><%=rs.getString(2) %></td>
			<td><%=rs.getString(3) %></td>
			<td><%=rs.getString(4) %></td>	
			</tr>
<% 
			more=rs.next();	
		}
	}
	rs.close();
	Database.close();
%>
</table>

<br/><br/>
<form action="sponsors.jsp" method="post">
<input type="submit" value="Go back to Sponsors" >
</form>
<br/><br/>

<br/><br/>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
<br/><br/>
</body>
</html>