<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.Cookie" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HR</title>
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
<tr><th>HR</th></tr>
</table>
<br/><br/>
<h1>Welcome <%= userName %></h1>
<br/><br/>

<form action="hrOptions.jsp">
<fieldset>
<legend>Select Any Option</legend>
<br/><br/>
<input type="radio" name="hr" value="register" checked> Register Teams<br/><br/>
<input type="radio" name="hr" value="shirt"> Upload T-shirts<br/><br/>
<input type="radio" name="hr" value="bets"> Place Bets<br/><br/>
</fieldset>
<br/><br/>
<input type="submit" name="Submit">
</form>

<br/><br/>
<br/>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>

</body>
</html>