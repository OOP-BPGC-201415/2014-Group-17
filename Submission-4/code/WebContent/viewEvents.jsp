<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Events</title>
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
<tr><th>View Events</th></tr>
</table>
<br/><br/><br/><br/>
<%
	Database.connect();
	ResultSet rs=Database.runQuery("select * from event");
	boolean more=rs.next();
%>
		<table width="100%">
		<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Start</th>
		<th>End</th>
		<th>Venue</th>
		<th>VenueAvailable</th>
		<th>No. of Participants</th>
		<th>First</th>
		<th>Second</th>
		<th>Third</th>
		<th>Judge</th>
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
			<td><%=rs.getString(5) %></td>
			<td><%=rs.getBoolean(6) %></td>
			<td><%=rs.getString(8) %></td>
			<td><%=rs.getString(9) %></td>
			<td><%=rs.getString(10) %></td>
			<td><%=rs.getString(11) %></td>
			<td><%=rs.getString(12) %></td>
			</tr>
		<% more=rs.next();	
		}
		%>
		</table>
	

<br/><br/>
<form action="eventRules.jsp">
<p>Choose an event id to view rules</p>
<select name="rules">
<%
	rs=Database.runQuery("select id from event");
	more=rs.next();
	while(more)
	{
		
%>
		<option value="<%=rs.getInt(1)%>"> <%=rs.getInt(1) %></option>
<%
	more=rs.next();
	}
	Database.close();
%>
</select>
<br/><br/>
<input type="submit" name="Submit">
</form>
<%
	}
%>
<br/><br/>
<form action="events.jsp" method="post">
<input type="submit" value="Go back to Events" >
</form>
<br/><br/>

<br/><br/>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
<br/><br/>
</body>
</html>