<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Scores</title>
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
<tr><th>Add Scores</th></tr>
</table>
<br/><br/>

<fieldset>
<legend>Enter the details</legend>
<br/><br/>

<form action="AddingScoresServlet">
<b>Select an event </b>
<select name="event">
<%
	Database.connect();
	ResultSet rs=Database.runQuery("select id,name from event where judge='"+(String)session.getAttribute("user")+"'");
	boolean more=rs.next();
	while(more)
	{		
%>
		<option value="<%=rs.getString(1)%>"> <%=rs.getString(2) %></option>
<%
	more=rs.next();
	}
	rs.close();
%>
</select>
<br/><br/>
<b>First </b>
<select name="first" required>
<%

	rs=Database.runQuery("select name from hostel");
	more=rs.next();
	while(more)
	{		
%>
		<option value="<%=rs.getString(1)%>"> <%=rs.getString(1) %></option>
<%
	more=rs.next();
	}
	rs.close();
%>

</select>

<br/><br/>
<b>Second </b>
<select name="second" required>
<%
	rs=Database.runQuery("select name from hostel");
	more=rs.next();
	while(more)
	{		
%>
		<option value="<%=rs.getString(1)%>"> <%=rs.getString(1) %></option>
<%
	more=rs.next();
	}
	rs.close();
%>
</select>

<br/><br/>
<b>Third </b>
<select name="third" required>
<%
	rs=Database.runQuery("select name from hostel");
	more=rs.next();
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

<input type="submit" value="Submit"><br/><br/>
</form>
</fieldset>

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