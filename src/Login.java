// Import statements
import java.sql.*;

public class Login
{
	public static boolean login(String user, String pass)
	{
		boolean valid = false;
		Database.connect();
		try
		{
			ResultSet rs = Database.runQuery("SELECT password FROM login_details WHERE username = '" + user + "'");			
			// Compare data from the ResultSet
			rs.next();
			if(pass.equals(rs.getString("password")))
			{
				valid = true;
			}
			rs.close();			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		Database.close();
		return valid;
	}
}