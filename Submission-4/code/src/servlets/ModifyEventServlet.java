package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javacodes.Database;
import javacodes.Error;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/ModifyEventServlet")
public class ModifyEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventID=request.getParameter("eventID");
		String oldName=request.getParameter("oldName");
		Timestamp start=Timestamp.valueOf(request.getParameter("startdate")+" "+request.getParameter("starttime"));
		Timestamp end=Timestamp.valueOf(request.getParameter("enddate")+" "+request.getParameter("endtime"));
			
		String sqlparam="select name,start,end,venue from event where name not in('"+oldName+"')";
		
		boolean check=Database.checkIfValidEvent(sqlparam,request.getParameter("name"), request.getParameter("venue"),start, end);
			
		if(check)
		{	
			Database.connect();
			Database.runUpdate("delete from event where id="+eventID);
			
			String sql="insert into event ";
			sql+="values ("+eventID+",'";
			sql+=request.getParameter("name")+"','";
			sql+=start+"','";
			sql+=end+"','";
			sql+=request.getParameter("venue")+"',";
			sql+=request.getParameter("venueAvailable")+",'";
			sql+=request.getParameter("rules")+"',";
			sql+=request.getParameter("noOfParticipants")+",";
			sql+=request.getParameter("first")+",";
			sql+=request.getParameter("second")+",";
			sql+=request.getParameter("third")+",'";
			sql+=request.getParameter("judge")+"')";
			
			Database.runUpdate(sql);
			System.out.println(Error.error);
			Database.close();
		
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/events.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red><b>Event has been modified.</b></font>");
			dispatcher.include(request, response);
		}
		else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/modifyEvent.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red><b>Either the name of the event is already present or the timing of the event is clashing with the others</b></font><br/><br/>");
            rd.include(request, response);
		}
	}

}
