<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Scores</title>
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
<tr><th>Delete Scores</th></tr>
</table>
<br/><br/>

<form action="deleteScores.jsp">
<h2>Choose an event id to modify</h2>
<select name="viewByID">
<%
	Database.connect();
	ResultSet rs=Database.runQuery("select distinct event.id,event.name from event inner join scores on event.id=scores.id");
	boolean more=rs.next();
	while(more)
	{		
%>
		<option value="<%=rs.getString(1)%>"> <%=rs.getString(1) %></option>
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
	String eventID=request.getParameter("viewByID");
	if(eventID!=null)
	{
		Database.connect();
		rs=Database.runQuery("select * from scores where id="+eventID+ " order by position asc");
		ResultSet rs2=Database.runQuery("select name from event where id="+eventID);
		more=rs.next();
		rs2.next();
%>
		<br/><br/>
		<table align="center"> 
		<tr>
		<th>Event Id</th>
		<td><%=rs.getString(1) %></td>
		</tr>
		<tr>
		<th>Name</th>
		<td><%=rs2.getString(1) %></td>
		</tr>
		<tr>
<% 
		while(more)
		{
%>
		<th>Position <%=rs.getString(3) %></th> 
		<td><%=rs.getString(2) %></td>
		</tr>
<%
		more=rs.next();
		}
%>
</table>
<br/><br/>
<% 	
		rs2.close();
		rs.close();
		Database.close();
%>
<form action="DeleteScoresServlet">
<input type="hidden" name="eventID" value="<%= eventID %>" />
<input type="submit" name="Delete">
</form>

<%
	}
%>

<br/><br/>
<form action="scores.jsp" method="post">
<input type="submit" value="Go back to Scores" >
</form>
<br/><br/>

<br/><br/>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
<br/><br/>	
</body>
</html>