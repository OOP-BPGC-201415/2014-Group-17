// Import statements.
import java.sql.*;

public class HostelRepresentative extends HostelMember
{
	// Constructor
	public HostelRepresentative(String user)
	{
		super(user);
	}

	// Functions
	public void registerTeam(String event)
	{
		Database.connect();
		try
		{
			Database.runUpdate("UPDATE events_participation SET " + this.getHostel() + "= 1 WHERE event = '" + event + "'");
		}
		catch(Exception se)
		{
			se.printStackTrace();
		}
		Database.close();
	}

	public void cancelRegisteredTeam(String event)
	{
		Database.connect();
		try
		{
			Database.runUpdate("UPDATE events_participation SET " + this.getHostel() + "= 0 WHERE event = '" + event + "'");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Database.close();
	}

	public boolean placeBets(String event)
	{
		Database.connect();
		try
		{
			ResultSet rs = Database.runQuery("SELECT Score FROM Scoreboard WHERE Hostel IN ('" + this.getHostel() + "')");
			ResultSet rs2 = Database.runQuery("SELECT " + this.getHostel() + " FROM events_participation WHERE Event = '" + event + "'");
			rs2.next();
			rs.next();
			if(rs2.getInt(this.getHostel()) == 2)
			{
				// Already placed a bet
				rs.close();
				rs2.close();
				Database.close();
				return false;
			}
			else if(rs2.getInt(this.getHostel()) == 1)
			{
				Database.runUpdate("UPDATE Scoreboard SET Score = " + (rs.getInt(this.getHostel()) - 50) + "WHERE Hostel = '" + this.getHostel() + "'");
				Database.runUpdate("UPDATE events_participation SET " + this.getHostel() + "= 2 WHERE event = '" + event + "'");
				rs.close();
				rs2.close();
				Database.close();
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Database.close();
		return false;
	}
}