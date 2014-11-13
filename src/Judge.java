// Import statements
import java.sql.*;

public class Judge
{
	private String event;
	private String name;

	// Constructor
	public Judge(String name, String event)
	{
		this.name = name;
		this.event = event;
	}

	// Functions
	public void SubmitEventScores(String event, String first, String second, String third)
	{
		Database.connect();
		try
		{
			ResultSet rs = Database.runQuery("SELECT Score FROM Scoreboard WHERE Hostel IN ('" + first + "','" + second + "','" + third + "')");
			ResultSet rs2 = Database.runQuery("SELECT " + first + "," + second + "," + third + " FROM events_participation WHERE Event = '" + event + "'");
			rs2.next();
			rs.next();
			if(rs2.getInt(first) == 1)
			{
				Database.runUpdate("UPDATE Scoreboard SET Score = " + (200 + rs.getInt(first)) + "WHERE Hostel = '" + first + "'");
			}
			else if(rs2.getInt(first) == 2)
			{
				Database.runUpdate("UPDATE Scoreboard SET Score = " + (300 + rs.getInt(first)) + "WHERE Hostel = '" + first + "'");				
			}
			rs.next();
			rs2.next();
			if(rs2.getInt(second) == 1)
			{
				Database.runUpdate("UPDATE Scoreboard SET Score = " + (150 + rs.getInt(second)) + "WHERE Hostel = '" + second + "'");
			}
			else if(rs2.getInt(second) == 2)
			{
				Database.runUpdate("UPDATE Scoreboard SET Score = " + (250 + rs.getInt(second)) + "WHERE Hostel = '" + second + "'");
			}			
			rs.next();
			rs2.next();
			if(rs2.getInt(third) == 1)
			{
				Database.runUpdate("UPDATE Scoreboard SET Score = " + (100 + rs.getInt(third)) + "WHERE Hostel = '" + third + "'");
			}
			else if(rs2.getInt(third) == 2)
			{
				Database.runUpdate("UPDATE Scoreboard SET Score = " + (200 + rs.getInt(third)) + "WHERE Hostel = '" + third + "'");				
			}
			rs.close();
			rs2.close();
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

	// Get methods
	// Get methods
	public String getName()
	{
		return this.name;
	}
		
	public String getEvent()
	{
		return this.event;
	}
}