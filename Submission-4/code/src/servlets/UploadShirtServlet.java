package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javacodes.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class uploadShirtServlet
 */
@WebServlet("/UploadShirtServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class UploadShirtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	         
	        InputStream inputStream = null; // input stream of the upload file
	        String hostel=request.getParameter("hostel");
	         
	        // obtains the upload file part in this multipart request
	        Part filePart = request.getPart("photo");
	        if (filePart != null && filePart.getSize()<16177215) {
	             
	            // obtains input stream of the upload file
	            inputStream = filePart.getInputStream();
	         
	            String message = null;  // message will be sent back to client
	 
	        	Database.connect();
	            // constructs SQL statement
	            String sql = "INSERT INTO tshirts (hostelID, shirt) values ('"+hostel+"','"+inputStream+"')";
	 
	            // sends the statement to the database server
	            Database.runUpdate("delete from tshirts where hostelID='"+hostel+"'");
	            Database.runUpdate(sql);
	     
	            RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/hr.jsp");
				PrintWriter out= response.getWriter();
				out.println("<font color=red><b>T-Shirt has been uploaded.</b></font>");
				dispatcher.include(request, response);
	        }
	        else
	        {
	        	RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/shirt.jsp");
				PrintWriter out= response.getWriter();
				out.println("<font color=red><b>File unable to be added.</b></font>");
				dispatcher.include(request, response);
	        }
	}

}
