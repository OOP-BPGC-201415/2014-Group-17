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
 * Servlet implementation class AddingTransactionServlet
 */
@WebServlet("/AddingTransactionServlet")
public class AddingTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sqlparam="select name from transaction";
		
		boolean check=Database.checkIfValidTransaction(request.getParameter("name"),sqlparam);
		if(check)
		{
			Database.connect();
			String sql="insert into transaction (name,money,description,gain) ";
			sql+="values ('";
			sql+=request.getParameter("name")+"',";
			sql+=request.getParameter("money")+",'";
			sql+=request.getParameter("description")+"',";
			sql+=request.getParameter("gain")+")";
		
			Database.runUpdate(sql);
			Database.close();
		
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/transaction.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red><b>Transaction has been added.</b></font>");
			dispatcher.include(request, response);
		}
		else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/addTransaction.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red><b>Name of the transaction already exists</b></font><br/><br/>");
            rd.include(request, response);
		}
	}
}
