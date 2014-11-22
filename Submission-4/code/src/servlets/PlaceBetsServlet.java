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
 * Servlet implementation class PlaceBetsServlet
 */
@WebServlet("/PlaceBetsServlet")
public class PlaceBetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id=request.getParameter("id");
		String hostel=request.getParameter("hostel");
		boolean check=Database.checkIfValidBet(id, hostel);
		if(check)
		{
			Database.connect();
			Database.runUpdate("insert into bets values ("+id+",'"+hostel+"')");
			Database.close();
			Database.close();
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/hr.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red><b>Bets have been placed.</b></font><br/><br/>");
			dispatcher.include(request, response);
		}
		else
		{
			Database.close();
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/bets.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red><b>Bets have already been placed or event has already occured.</b></font><br/><br/>");
			dispatcher.include(request, response);
		}
	}

}
