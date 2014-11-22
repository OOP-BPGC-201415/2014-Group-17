package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javacodes.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifySponsorServlet
 */
@WebServlet("/ModifySponsorServlet")
public class ModifySponsorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventID=request.getParameter("id");
		String oldName=request.getParameter("oldName");
		
		String sqlparam="select sponsorName from sponsors where sponsorName not in('"+oldName+"')";
		
		boolean check=Database.checkIfValidSponsor(request.getParameter("name"),sqlparam);
		
		if(check)
		{
			Database.connect();
			
			Database.runUpdate("delete from sponsors where id="+eventID);
			
			String sql="insert into sponsors ";
			sql+="values ("+eventID+",'";
			sql+=request.getParameter("name")+"',";
			sql+=request.getParameter("finance")+",'";
			sql+=request.getParameter("description")+"')";
		
			Database.runUpdate(sql);
			Database.close();
		
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/sponsors.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red><b>Soonsor has been modified.</b></font>");
			dispatcher.include(request, response);
		}
		else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/modifySponsors.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red><b>Name of the sponsor already exists</b></font><br/><br/>");
            rd.include(request, response);
		}
	}

}
