// Import statements
import java.sql.*;
import java.util.Calendar;

public class EventHead
{
	private String name;
	private String event;

	// Constructor
	public EventHead(String user)
	{
		Database.connect();
		try
		{
			ResultSet rs = Databse.runQuery("SELECT name, Event FROM event_head WHERE username = '" + user + "'");
			rs.next();
			this.name = rs.getString("name");
			this.event = rs.getString("Event");
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
	public void uploadSchedule(Timestamp start, Timestamp end)
	{
		Database.connect();
		try
		{
			Database.runUpdate("UPDATE event_list SET Start = '" + start + "' WHERE Event = '" + this.event + "'");
			Database.runUpdate("UPDATE event_list SET End = '" + end + "' WHERE Event = '" + this.event + "'");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Database.close();
	}

	public void uploadRules()
	{
		
	}

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