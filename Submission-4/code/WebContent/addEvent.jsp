<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Event</title>
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
<tr><th>Add Event</th></tr>
</table>
<br/><br/>
<fieldset>
<legend>Enter the details</legend>
<br/><br/>
<form action="AddingEventServlet">
Name of the Event : <input type="text" name="name" required><br/><br/>
Start (date) : <input type="date" name="startdate" required> Start (time) : <input type="time" name="starttime" required><br/><br/>
End (date) : <input type="date" name="enddate" required> End (time) : <input type="time" name="endtime" required><br/><br/>
Venue : <input type="text" name="venue" required><br/><br/>
VenueAvailable : <select name="venueAvailable" required>
<option value="true">True</option>
<option value="false">False</option>
</select><br/><br/>
Rules : <textarea name="rules" cols="40" rows="5" required>no rules entered</textarea><br/><br/>
Scores for the first position : <input type="number" name="first" required><br/><br/>
Scores for the second position : <input type="number" name="second" required><br/><br/>
Scores for the third position : <input type="number" name="third" required><br/><br/>
Number of Participants : <input type="number" name="noOfParticipants" reqired><br/><br/>
Judge : <select name="judge">
<%
	Database.connect();
	ResultSet rs=Database.runQuery("select username from login where type='judge'");
	boolean more=rs.next();
	while(more)
	{		
%>
		<option value="<%=rs.getString(1)%>" > <%=rs.getString(1) %></option>
<%
		more=rs.next();
	}
	rs.close();
	Database.close();
%>
</select><br/><br/>
<input type="submit" name="Submit"><br/><br/>
</form>
</fieldset>

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