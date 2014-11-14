// Import statements
import java.sql.*;

public class Convener
{
	private String name;

	// Constructor
	public Convener(String user)
	{
		Database.connect();
		try
		{
			ResultSet rs = Databse.runQuery("SELECT name FROM event_head WHERE username = '" + user + "'");
			rs.next();
			this.name = rs.getString("name");
			rs.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Database.close();
	}

	// Functions
	public void reviewTshirts()
	{
		
	}

	// Get methods
	public String getName()
	{
		return this.name;
	}
}