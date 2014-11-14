// Import statements
import java.sql.*;

public class FinanceHead
{
	private String name;

	// Constructor
	public FinanceHead(String user)
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
	public boolean confirmSponsor(String sponsor)
	{
		Database.connect();
		try
		{
			ResultSet rs = Database.runQuery("SELECT Confirmed FROM sponsors WHERE sponsor = '" + sponsor + "'");
			rs.next();
			if(rs.getInt("Confirmed") == 1)
			{
				// Already confirmed
				rs.close();
				Database.close();
				return false;
			}
			rs.close();
			Database.runUpdate("UPDATE sponsors SET Confirmed = 1 WHERE sponsor = '" + sponsor + "'");
			Database.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Database.close();
		return false;
	}

	public String displayNCSponsors()
	{
		String sponsorList = "";
		Database.connect();
		try
		{
			ResultSet rs = Database.runQuery("SELECT sponsor, Confirmed FROM sponsors");
			int i = 0;
			while(rs.next())
			{
				if(rs.getInt("Confirmed") == 1)
				{
					continue;
				}
				sponsorList += (i + ") " + rs.getString("sponsor") + "\n");
				i++;
			}
			rs.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		Database.close();
		return sponsorList;		
	}

	// Get methods
	public String getName()
	{
		return this.name;
	}
}