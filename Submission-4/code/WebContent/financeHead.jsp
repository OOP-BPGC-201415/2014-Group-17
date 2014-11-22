<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.Cookie" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finance Head</title>
<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
</head>
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
<tr><th>Finance Head</th></tr>
</table>
<br/><br/>
<h1>Welcome <%= userName %></h1>
<br/><br/>

<form action="financeHeadOptions.jsp">
<fieldset>
<legend>Select Any Option</legend>
<br/><br/>
<input type="radio" name="financeHead" value="sponsors"> Sponsors<br/><br/>
<input type="radio" name="financeHead" value="transactions"> Transactions<br/><br/>
</fieldset>
<br/><br/>
<input type="submit" name="Submit">
</form>

<br/><br/>
<b>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>

</body>
</html>