<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Place Bets</title>
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
<tr><th>Place Bets</th></tr>
</table>

<form action="bets.jsp">
<h2>Choose an event id </h2>
<select name="id">
<%
	Database.connect();
	ResultSet rs=Database.runQuery("select distinct event.id, event.name from event inner join participants on event.id=participants.eventID");
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
	String id=request.getParameter("id");
	if(id!=null)
	{
%>
	<br/><br/><b>Select the button if you want to place your sure to win in the event</b><br/><br/>
	<form action="PlaceBetsServlet">
	<input type="hidden" name="id" value="<%=id %>" >
	<input type="hidden" name="hostel" value="<%= (String)(session.getAttribute("user"))%>" >
	<input type="submit" value="Place your Bet!">
	</form>
<%
	}
%>
<br/><br/>
<form action="<%=(String)session.getAttribute("type")%>.jsp" method="post">
<input type="submit" value="Go back to Main Menu" >
</form>
<br/><br/>

<br/><br/>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>