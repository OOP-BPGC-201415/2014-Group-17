<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Teams</title>
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
<tr><th>Modify Teams</th></tr>
</table>
<br/><br/>

<form action="modifyTeams.jsp">
<h2>Choose an event id to modify</h2>
<select name="modifyByID">
<%
	Database.connect();
	ResultSet rs=Database.runQuery("select distinct event.id,event.name from event inner join participants on event.id=participants.eventID");
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
		rs=Database.runQuery("select participant from participants where eventID="+eventID+",hostelName='"+(String)request.getAttribute("user")+"'"); 
		ResultSet rs2=Database.runQuery("select participants from event where id="+eventID);
		rs2.next();
		more=rs.next();
		int count=rs2.getInt(1);
%>
<br/><br/>
		<fieldset>
		<legend>Enter the details</legend>
		<form action="ModifyTeamsServlet" ><br/><br/>
		<input type="hidden" name="id" value="<%=eventID %>">
		<input type="hidden" name="count" value="<%=count %>">
		<input type="hidden" name="hostel" value="<%= (String)(session.getAttribute("user"))%>" >
<% 		
		for(int i=0;i<count;i++)
		{
			if(more)
			{
%>
				Participant <%=i %> <input type="text" name="p<%=i %>" value="<%=rs.getString(1) %>" ><br/><br/>
<%
				more=rs.next();
			}
			else
%>
				Participant <%=i %> <input type="text" name="p<%=i %>"><br/><br/>
<%
		}
%>
		<input type="submit" name="Submit"><br/><br/>
		</form>
		</fieldset>
<%
		rs2.close();
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