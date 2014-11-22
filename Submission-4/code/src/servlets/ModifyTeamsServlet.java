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
 * Servlet implementation class ModifyTeamsServlet
 */
@WebServlet("/ModifyTeamsServlet")
public class ModifyTeamsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventID=request.getParameter("id");
		int count=Integer.parseInt(request.getParameter("count"));
		String hostelName=request.getParameter("hostel");
		String sqlparam="select distinct eventID,hostelName from participants eventID not in("+eventID+")";
		boolean check=false;
		for(int i=0;i<count;i++)
		{
			if(!request.getParameter("p"+i).trim().equals(""))
				check=Database.checkIfValidTeam(eventID, hostelName, request.getParameter("p"+i), sqlparam);
		}
		if(check)
		{
			Database.connect();
			Database.runUpdate("delete from participants where eventID="+eventID+" and hostelName='"+hostelName+"'");
			for(int i=0;i<count;i++)
			{
				if(!request.getParameter("p"+i).trim().equals(""))
					Database.runUpdate("insert into participants values ("+eventID+",'"+hostelName+"','"+request.getParameter("p"+i)+"')");	 
			}
			Database.close();
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/register.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red><b>Team has been modified.</b></font><br/><br/>");
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
