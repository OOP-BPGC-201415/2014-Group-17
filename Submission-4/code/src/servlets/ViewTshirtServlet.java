package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;

import javacodes.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ViewTshirtServlet
 */
@WebServlet("/ViewTshirtServlet")
public class ViewTshirtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hostel=request.getParameter("hostel");
		byte[ ] imgData = null ; 
		Database.connect();
		ResultSet rs=Database.runQuery("select shirt from tshirts where hostelID='"+hostel+"'");
		try
		{
		rs.next();
		Blob image=rs.getBlob(1);
		imgData = image.getBytes(1,(int)image.length());
		response.setContentType("image/gif");

		OutputStream out = response.getOutputStream();

		out.write(imgData);

		out.flush();

		out.close();
		}catch(Exception e)
		{
			System.out.println("unable to display image");
		}

	}
}
