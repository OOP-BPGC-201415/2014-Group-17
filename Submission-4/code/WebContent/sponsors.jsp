<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sponsors</title>
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
System.out.println((String)session.getAttribute("user"));
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
<tr><th>Sponsors</th></tr>
</table>
<br/><br/><br/><br/>
<fieldset>
<legend>Select Any Option</legend>
<br/><br/>
<form action="sponsorsOptions.jsp">
<input type="radio" name="sponsors" value="view" checked> View sponsors details<br/><br/>
<input type="radio" name="sponsors" value="add"> Add new sponsor<br/><br/>
<input type="radio" name="sponsors" value="modify"> Modify existing sponsor details<br/><br/>
<input type="radio" name="sponsors" value="delete"> Delete existing sponsor from list<br/><br/>
<input type="submit" name="Submit"><br/><br/> 
</form>
</fieldset>
<br/><br/>
<form action="<%=(String)session.getAttribute("type")%>.jsp" method="post">
<input type="submit" value="Go back to main menu" >
</form>
<br/><br/>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>