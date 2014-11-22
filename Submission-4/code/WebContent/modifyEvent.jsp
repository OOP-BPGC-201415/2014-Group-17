<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display T-Shirt Design</title>
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
<tr><th>Modify Event</th></tr>
</table>
<br/><br/>

<form action="modifyEvent.jsp">
<h2>Choose an event id to modify</h2>
<select name="modifyByID">
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
	String eventID=request.getParameter("modifyByID");
	if(eventID!=null)
	{
		Database.connect();
		rs=Database.runQuery("select * from event where id="+eventID);
		rs.next();
%>
<br/><br/>
<fieldset>
<legend>Enter the details</legend>
<br/><br/>
<form action="ModifyEventServlet">
<input type="hidden" name="eventID" value=" <%= eventID %>" /> 
<input type="hidden" name="oldName" value="<%= rs.getString(2) %>" />
Name of the Event : <input type="text" name="name" value="<%=rs.getString(2) %>" required><br/><br/>
Start (date) : <input type="date" name="startdate" value="<%=rs.getDate(3) %>" required> Start (time) : <input type="time" name="starttime" value="<%=rs.getTime(3) %>" required><br/><br/>
End (date) : <input type="date" name="enddate" value="<%=rs.getDate(4) %>" required> End (time) : <input type="time" name="endtime"value="<%=rs.getTime(4) %>" required><br/><br/>
Venue : <input type="text" name="venue" value="<%=rs.getString(5) %>" required><br/><br/>
VenueAvailable : <select name="venueAvailable" required>
<option value="true">True</option>
<option value="false">False</option>
</select><br/><br/>
Rules : <textarea name="rules" cols="40" rows="5"><%= rs.getString(7) %></textarea><br/><br/>
Scores for the first position : <input type="number" name="first" value="<%=rs.getInt(9) %>" required><br/><br/>
Scores for the second position : <input type="number" name="second" value="<%=rs.getInt(10) %>" required><br/><br/>
Scores for the third position : <input type="number" name="third" value="<%=rs.getInt(11) %>" required><br/><br/>
Number of Participants : <input type="number" name="noOfParticipants" value="<%=rs.getInt(8) %>" required><br/><br/>
Judge : <select name="judge">
<%
	ResultSet rs2=Database.runQuery("select username from login where type='judge'");
	boolean more2=rs2.next();
	while(more2)
	{		
%>
		<option value="<%=rs2.getString(1)%>" > <%=rs2.getString(1) %></option>
<%
		more2=rs2.next();
	}
	rs2.close();
%>
</select> 
 Original judge: <%=rs.getString(12) %>
<br/><br/> 
<input type="submit" name="Modify"><br/><br/>
</form>
</fieldset>

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