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
 * Servlet implementation class ModifyTransactionServlet
 */
@WebServlet("/ModifyTransactionServlet")
public class ModifyTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventID=request.getParameter("id");
		String oldName=request.getParameter("oldName");
		
		String sqlparam="select name from transaction where name not in('"+oldName+"')";
		
		boolean check=Database.checkIfValidTransaction(request.getParameter("name"),sqlparam);
		
		if(check)
		{
			Database.connect();
			
			Database.runUpdate("delete from transaction where id="+eventID);
			
			String sql="insert into transaction ";
			sql+="values ("+eventID+",'";
			sql+=request.getParameter("name")+"',";
			sql+=request.getParameter("money")+",'";
			sql+=request.getParameter("description")+"',";
			sql+=request.getParameter("gain")+")";
		
			Database.runUpdate(sql);
			Database.close();
		
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/sponsors.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red><b>Transaction has been modified.</b></font>");
			dispatcher.include(request, response);
		}
		else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/modifySponsors.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red><b>Transaction already exists</b></font><br/><br/>");
            rd.include(request, response);
		}
	}

}
