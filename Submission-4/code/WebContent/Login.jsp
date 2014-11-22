<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
</head>
<body>
<table class="title">
<tr><th>Login Page</th></tr>
</table>
<br/><br/><br/>

<form action="LoginServlet">
<fieldset>
<legend>Enter user Details</legend>
<br/><br/>
<b>User Name : </b><input type="text" name="username" required><br/><br/><br/>
<b>Password : </b><input type="password" name="password" required><br/><br/><br/>
</fieldset>
<br/><br/>
<input type="submit" name="Submit">
</form>

<br/><br/><br/>
<b><a href="index.jsp">Click here to go back</a></b>

</body>
</html>