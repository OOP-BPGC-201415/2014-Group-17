<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Shirt Design</title>
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
<tr><th>Upload T-Shirt Design</th></tr>
</table>
<fieldset>
<legend>Select File</legend><br/><br/><br/>
<center>
<form method="post" action="UploadShirtServlet" enctype="multipart/form-data">     
<b>Portrait Photo: </b>
<input type="hidden" name="hostel" value="<%=(String)(session.getAttribute("user")) %>" >
<input type="file" name="photo" size="50"/><br/><br/>
<input type="submit" value="Save">
</form>
</center>
</fieldset>
<br/><br/>
<form action="<%=(String)session.getAttribute("type")%>.jsp" method="post">
<input type="submit" value="Go back to Main Menu" >
</form>
<br/><br/>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>