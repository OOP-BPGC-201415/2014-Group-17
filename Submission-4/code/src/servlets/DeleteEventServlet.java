package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javacodes.Database;
import javacodes.Error;
/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteEventServlet")
public class DeleteEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Database.connect();
		Database.runUpdate("delete from event where id="+request.getParameter("eventID"));
		Database.runUpdate("delete from scores where id="+request.getParameter("eventID"));
		Database.runUpdate("delete from participants where eventID="+request.getParameter("eventID"));
		Database.close();
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/events.jsp");
		PrintWriter out= response.getWriter();
		out.println("<font color=red><b>Event has been deleted.</b></font>");
		dispatcher.include(request, response);
	}

}
