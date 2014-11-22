<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Options</title>
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
<%
	String address="LogoutServlet";
	String next=request.getParameter("scores");
	if(next.equals("view"))
		address="viewJudgeScores.jsp";
	else if(next.equals("add"))
		address="addJudgeScores.jsp";
	else if(next.equals("modify"))
		address="modifyJudgeScores.jsp";
	else if(next.equals("delete")) 
		address="deleteJudgeScores.jsp";
	RequestDispatcher dispatcher=request.getRequestDispatcher(address);
	dispatcher.forward(request, response);
	%>
</body>
</html>