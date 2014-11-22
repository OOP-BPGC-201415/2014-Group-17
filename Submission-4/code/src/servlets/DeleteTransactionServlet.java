package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javacodes.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteTransactionServlet
 */
@WebServlet("/DeleteTransactionServlet")
public class DeleteTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Database.connect();
		Database.runUpdate("delete from transaction where id="+request.getParameter("eventID"));
		Database.close();
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/transaction.jsp");
		PrintWriter out= response.getWriter();
		out.println("<font color=red><b>Transaction has been deleted.</b></font>");
		dispatcher.include(request, response);
	}

}
