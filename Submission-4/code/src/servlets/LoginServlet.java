package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javacodes.Login;
import javacodes.Error;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Login login=new Login();
		String address="";
		String type="";
		boolean valid=login.login(request.getParameter("username"),request.getParameter("password"));
		type=login.getType();
		if(valid==true)
		{

			HttpSession session = request.getSession();
            session.setAttribute("user", request.getParameter("username"));
            session.setAttribute("type", type); 
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);
			Cookie loginCookie = new Cookie("user",request.getParameter("username"));
            //setting cookie to expiry in 30 mins
            loginCookie.setMaxAge(30*60);
            response.addCookie(loginCookie);
           
			if(type.equals("convenor"))
				address="convenor.jsp";
			else if(type.equals("eventHead"))
				address="eventHead.jsp";
			else if(type.equals("financeHead"))
				address="financeHead.jsp";
			else if(type.equals("hr"))
				address="hr.jsp";
			else if(type.equals("judge"))
				address="judge.jsp";
			else if(type.equals("dopy"))
				address="dopy.jsp";
			response.sendRedirect(address);
		}
		else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red><b>Either user name or password is wrong.</b></font>");
            rd.include(request, response);
		}
		
	}

}
