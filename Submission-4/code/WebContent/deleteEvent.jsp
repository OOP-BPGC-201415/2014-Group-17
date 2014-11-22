<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Event</title>
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
<tr><th>Delete Event</th></tr>
</table>
<br/><br/>

<form action="deleteEvent.jsp">
<h2>Choose an event id to modify</h2>
<select name="modifyByID">
<%
	Database.connect();
	ResultSet rs=Database.runQuery("select id,name from event");
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
	String eventID=request.getParameter("modifyByID");
	if(eventID!=null)
	{
		Database.connect();
		rs=Database.runQuery("select * from event where id="+eventID);
		rs.next();
%>
<br/><br/>
<table>
<tr>
<th>Event Id</th>
<td><%=rs.getString(1) %></td>
</tr>
<tr>
<th>Name</th>
<td><%=rs.getString(2) %></td>
</tr>
<tr>
<th>Start</th>
<td><%=rs.getString(3) %></td>
</tr>
<tr>
<th>End</th>
<td><%=rs.getString(4) %></td>
</tr>
<tr>
<th>Venue</th>
<td><%=rs.getString(5) %></td>
</tr>
<tr>
<th>Venue Available</th>
<td><%=rs.getBoolean(6) %></td>
</tr>
<tr>
<th>No. of Participants</th>
<td><%=rs.getString(8) %></td>
</tr>
<tr>
<th>First</th>
<td><%=rs.getString(9) %></td>
</tr>
<tr>
<th>Second</th>
<td><%=rs.getString(10) %></td>
</tr>
<tr>
<th>Third</th>
<td><%=rs.getString(11) %></td>
</tr>
<tr>
<th>Judge</th>
<td><%=rs.getString(12) %></td>
</tr>
</table>
<br/><br/>
<form action="DeleteEventServlet">
<input type="hidden" name="eventID" value=" <%= eventID %>" /> 
<input type="submit" value="Delete Event" >
</form>
<br/><br/>
<% 	
	rs.close();
	Database.close();
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