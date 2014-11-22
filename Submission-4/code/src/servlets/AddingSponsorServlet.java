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
 * Servlet implementation class AddingSponsorServlet
 */
@WebServlet("/AddingSponsorServlet")
public class AddingSponsorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sqlparam="select sponsorName from sponsors";
		
		boolean check=Database.checkIfValidSponsor(request.getParameter("name"),sqlparam);
		if(check)
		{
			Database.connect();
			String sql="insert into sponsors (sponsorName,finance,description) ";
			sql+="values ('";
			sql+=request.getParameter("name")+"',";
			sql+=request.getParameter("finance")+",'";
			sql+=request.getParameter("description")+"')";
		
			Database.runUpdate(sql);
			Database.close();
		
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/sponsors.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red><b>Sponsor has been added.</b></font>");
			dispatcher.include(request, response);
		}
		else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/addSponsors.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red><b>Name of the sponsor already exists</b></font><br/><br/>");
            rd.include(request, response);
		}
	}

	
}
