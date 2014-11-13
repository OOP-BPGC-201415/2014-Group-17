// Import statements
import java.sql.*;
import java.util.Calendar;

public class Utilities
{
	// Functions
	public static String displayScores()
	{
		String scores = "";
		Database.connect();
		try
		{
			ResultSet rs = Database.runQuery("SELECT Hostel, Score FROM Scoreboard ORDER BY Score");
			int i = 0;
			while(rs.next())
			{
				scores += Integer.toString(i) + ") " + rs.getString("Hostel") + " - " + rs.getString("Score") + "\n";
				i++;
			}
			rs.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		Database.close();
		return scores;
	}

	public static String displayTopThree()
	{
		String scores = "";
		Database.connect();
		try
		{
			ResultSet rs = Database.runQuery("SELECT Hostel, Score FROM Scoreboard ORDER BY Score");
			int i = 0;
			while(i < 3 && rs.next())
			{
				scores += Integer.toString(i) + ") " + rs.getString("Hostel") + " - " + rs.getString("Score") + "\n";
				i++;
			}
			rs.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		Database.close();
		return scores;
	}

	public static String displayOnGoingEvents()
	{
		String onGoing = "";
		Database.connect();
    	Calendar calendar = Calendar.getInstance();
   		Timestamp currentTime = new Timestamp(calendar.getTime().getTime());	
		try
		{
			ResultSet rs = Database.runQuery("SELECT Event, Event_head, Start, End FROM event_list");
			int i = 0;
			while(rs.next())
			{
				if(currentTime.after(rs.getTimestamp("Start")) && currentTime.before(rs.getTimestamp("End")))
				{
					onGoing += (i + ") " + rs.getString("Event") + " -- " + rs.getString("Event_head") + "\n");
					i++;
				}
			}
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
		if(onGoing.equals(""))
		{
			onGoing = "No events going on at this moment.\n";
		}
		return onGoing;
	}

	public static String displayUpcomingEvents()
	{
		String upComing = "Events taking place in the next hour: \n";
		Database.connect();
    	Calendar calendar = Calendar.getInstance();
   		Timestamp currentTime = new Timestamp(calendar.getTime().getTime());
   		currentTime.setTime(currentTime.getTime() + (((14 * 60) + 59)* 1000));   				
		try
		{
			ResultSet rs = Database.runQuery("SELECT Event, Event_head, Start, End FROM event_list");
			int i = 0;
			while(rs.next())
			{
				if(currentTime.after(rs.getTimestamp("Start")) && currentTime.before(rs.getTimestamp("End")))
				{
					upComing += (i + ") " + rs.getString("Event") + " -- " + rs.getString("Event_head") + " -- Starts At : " + rs.getTimestamp("Start") + ", Ends At : " + rs.getTimestamp("End") + "\n");
					i++;
				}
			}
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
		if(upComing.equals("Events taking place in the next hour: \n"))
		{
			upComing = "No upcoming events in the next hour.";
		}
		return upComing;
	}

	public static String displayAllEvents()
	{
		String ev = "";
		Database.connect();
		try
		{
			ResultSet rs = Database.runQuery("SELECT Event, Event_head, Start, End FROM event_list");
			int i = 0;
			while(rs.next())
			{
				ev += (i + ") " + rs.getString("Event") + " -- " + rs.getString("Event_head") + " -- Starts At : " + rs.getTimestamp("Start") + ", Ends At : " + rs.getTimestamp("End") + "\n");
				i++;
			}
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
		if(ev.equals(""))
		{
			ev = "No events to display. \n";
		}
		return ev;
	}
}