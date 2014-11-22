<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Participants</title>
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
<tr><th>View Participants</th></tr>
</table>
<br/><br/>

<form action="viewParticipants.jsp">
<h2>Choose an event id to modify</h2>
<select name="viewByID">
<%
	Database.connect();
	ResultSet rs=Database.runQuery("select id,name from event");
	boolean more=rs.next();
	while(more)
	{		
%>
		<option value="<%=rs.getString(1)%>"> <%=rs.getString(2) %></option>
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
		rs=Database.runQuery("select hostelName,participant from participants where eventID="+eventID);
		more=rs.next();

%>
<table width="100%">
		<tr>
		<th>Hostel</th>
		<th>Participant</th>
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
			</tr>
		<% more=rs.next();	
		}
		%>
</table>	
<br/><br/>
<% 	
	}
		rs.close();
		Database.close();
	}
%>

<br/><br/>
<form action="<%=(String)session.getAttribute("type")%>.jsp" method="post">
<input type="submit" value="Go back to main menu" >
</form>
<br/><br/>

<br/><br/>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
<br/><br/>	
</body>
</html>