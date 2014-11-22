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
 * Servlet implementation class ModifyScoresServlet
 */
@WebServlet("/ModifyScoresServlet")
public class ModifyScoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String event=request.getParameter("eventID");
		String first=request.getParameter("first");
		String second=request.getParameter("second");
		String third=request.getParameter("third");
		
		String sql="select * from event where name not in('"+event+"')";
		
		boolean check1=false,check2=false,check3=false,check=true;
		check1=Database.checkIfValidScores(sql, event, first);
		check2=Database.checkIfValidScores(sql, event, second);
		check3=Database.checkIfValidScores(sql, event, third);
		
		if(check1==false || check2==false || check3==false)
			check=false;
		
		if(event==null)
			check=false;
		else if(first.equals(second))
			check=false;
		else if(first.equals(third))
			check=false;
		else if(second.equals(third))
			check=false;
		if(check)
		{
		
		Database.connect();
		
		Database.runUpdate("delete from scores where id="+event); 
		
		Database.runUpdate("insert into scores values("+event+",'"+first+"',1)");
		Database.runUpdate("insert into scores values("+event+",'"+second+"',2)");
		Database.runUpdate("insert into scores values("+event+",'"+third+"',3)");

		Database.close();
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/scores.jsp");
		PrintWriter out= response.getWriter();
		out.println("<font color=red><b>Scores modified</b></font><br/><br/>");
		dispatcher.include(request, response);
		
		}
		else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/modifyScores.jsp");
	        PrintWriter out= response.getWriter();
	        out.println("<font color=red><b>One of the winners is not a participant</b></font><br/><br/>");
	        rd.include(request, response);
		}		
	}

}
