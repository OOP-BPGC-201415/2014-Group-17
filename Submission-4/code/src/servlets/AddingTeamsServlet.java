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
/**
 * Servlet implementation class AddidngTeamsServlet
 */
@WebServlet("/AddingTeamsServlet")
public class AddingTeamsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventID=request.getParameter("id");
		int count=Integer.parseInt(request.getParameter("count"));
		String hostelName=request.getParameter("hostel");
		String sqlparam="select distinct eventID,hostelName from participants";
		boolean check=false;
		
		for(int i=0;i<count;i++)
		{
			if(!request.getParameter("p"+i).trim().equals(""))
				check=Database.checkIfValidTeam(eventID, hostelName, request.getParameter("p"+i), sqlparam);
			System.out.println(check);
			if(check==false)
				break;
		}
		if(check)
		{
			Database.connect();
			for(int i=0;i<count;i++)
			{
				if(!request.getParameter("p"+i).trim().equals(""))
					Database.runUpdate("insert into participants values ("+eventID+",'"+hostelName+"','"+request.getParameter("p"+i)+"')");	 
			}
			Database.close();
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/register.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red><b>Team has been registered.</b></font><br/><br/>");
			dispatcher.include(request, response);
		}
		else
		{
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/addTeams.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red><b>Participant details are clashing with other events or team already registered for this event</b></font><br/><br/>");
			dispatcher.include(request, response);
		}
	}

}
