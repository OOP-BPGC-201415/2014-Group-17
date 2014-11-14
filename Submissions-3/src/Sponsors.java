// Import statements
import java.sql.*;

public class Sponsors
{
	public static String displaySponsors()
	{
		String sponsorList = "";
		Database.connect();
		try
		{
			ResultSet rs = Database.runQuery("Select sponsor, Confirmed FROM sponsors");
			int i = 0;
			while(rs.next())
			{
				if(rs.getInt("Confirmed") == 0)
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

	public void addSponsor(String sname, int amount)
	{
		Database.connect();
		try
		{
			Database.runUpdate("INSERT INTO sponsors VALUES ('" + sname + "'," + amount + ")");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Database.close();
	}
}