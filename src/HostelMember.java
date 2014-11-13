// Import statements
import java.sql.*;

public class HostelMember
{
	// Properties
	private String name;
	private String hostel;
	private String id;

	// Constructor
	public HostelMember(String user)
	{
		Database.connect();
		try
		{
			ResultSet rs = Database.runQuery("SELECT name, hostel, id FROM hostel_members WHERE username = '" + user + "'");			
			rs.next();
			name = rs.getString("name");
			hostel = rs.getString("hostel");
			id = rs.getString("id");
			rs.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		Database.close();
	}

	// Get methods
	public String getName()
	{
		return this.name;
	}

	public String getHostel()
	{
		return this.hostel;
	}

	public String getID()
	{
		return this.id;
	}
}