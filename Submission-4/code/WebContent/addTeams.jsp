<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javacodes.Database" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<br/><br/>

<table class="title">
<tr><th>Add Teams</th></tr>
</table>

<form action="addTeams.jsp">
<h2>Choose an event id </h2>
<select name="id">
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
	String id=request.getParameter("id");
	if(id!=null)
	{
		Database.connect();
		rs=Database.runQuery("select participants from event where id="+id);
		rs.next();
		int count=rs.getInt(1);
%>
		<fieldset>
		<legend>Enter the details</legend>
		<form action="AddingTeamsServlet" ><br/><br/>
		<input type="hidden" name="id" value="<%=id %>">
		<input type="hidden" name="count" value="<%=count %>">
		<input type="hidden" name="hostel" value="<%= (String)(session.getAttribute("user"))%>" >
<% 		
		for(int i=0;i<count;i++)
		{
%>
			Participant <%=i %> <input type="text" name="p<%=i %>"><br/><br/>
<%
		}
%>
		<input type="submit" name="Submit"><br/><br/>
		</form>
		</fieldset>
<%
		rs.close();
		Database.close();
	}
%>

<br/><br/>
<form action="register.jsp" method="post">
<input type="submit" value="Go back to register teams page" >
</form>
<br/><br/>

<br/><br/>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>