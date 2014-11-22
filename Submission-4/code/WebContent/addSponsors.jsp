<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Sponsor</title>
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
<tr><th>Add Sponsor</th></tr>
</table>
<br/><br/>

<fieldset>
<legend>Enter the details</legend>
<br/><br/>
<form action="AddingSponsorServlet">
Name of the Sponsor : <input type="text" name="name" required><br/><br/>
Financial Assistance Provided : <input type="number" name="finance" required><br/><br/>
Description : <textarea name="description" cols="40" rows="5" required>no description entered</textarea><br/><br/>
<input type="submit" name="Submit"><br/><br/>
</form>
</fieldset>

<br/><br/>
<form action="sponsors.jsp" method="post">
<input type="submit" value="Go back to Sponsors" >
</form>
<br/><br/>

<br/><br/>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
<br/><br/>
</body>
</html>