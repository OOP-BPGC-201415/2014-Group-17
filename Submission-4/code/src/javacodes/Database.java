package javacodes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Database {
	private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	private static final String JDBC_URL="jdbc:mysql://localhost:3306/db";
	
	private static final String user="root";
	private static final String password="Pass@123";
	
	private static Connection conn;
	private static Statement stmt;
	
	public static void connect()
	{
		try{
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(JDBC_URL,user,password);
		}catch(SQLException e)
		{
			Error.error=e.toString();
		}catch(Exception e)
		{
			Error.error=e.toString();
		}
	}
	public static void disconnect()
	{
		try
		{
			conn.close();
		}catch(Exception e)
		{
			Error.error=e.toString();
		}
	}
	public static ResultSet runQuery(String query) 
 	{ 
 		try 
 		{ 
 			stmt = conn.createStatement();	
 			return stmt.executeQuery(query);
 		} 
 		catch(SQLException se) 
 		{ 
 			Error.error=se.toString();
 		} 
 		catch(Exception e) 
 		{ 
 			Error.error=e.toString();
 		} 
 		return null;
 	}
	public static void runUpdate(String query) 
 	{ 
 		try 
 		{ 
 			stmt = conn.createStatement();			 
 			stmt.executeUpdate(query); 
 		} 
 		catch(SQLException se) 
 		{ 
 			Error.error=se.toString();
 		} 
 		catch(Exception e) 
 		{ 
 			Error.error=e.toString();
 		} 
 	}
	public static void close() 
 	{ 
 		try 
 		{ 
 			if(stmt!=null)
 			{
 				stmt.close();
 			}
 			if(conn != null) 
 			{ 
 				conn.close(); 
 			} 
 		} 
 		catch(SQLException se) 
 		{ 
 			Error.error=se.toString();
 		} 
 	}
	public static boolean checkIfValidScores(String sql,String id,String hostel)
	{
		boolean valid=true;
		boolean participate=false;
		Database.connect();
		ResultSet rs=Database.runQuery(sql);
		try{
		boolean more=rs.next();
		while(more)
		{
			if(id.equals(rs.getString(1)))
			{
				valid=false;
				break;
			}
			more=rs.next();
		}
	
		rs=Database.runQuery("select distinct hostelName from participants where eventID="+id);
		more=rs.next();
		while(more)
		{
			if(hostel.equals(rs.getString(1)))
			{
				participate=true;
				break;
			}
			more=rs.next();
		}
		if(participate==false)
			valid=false;
	
		rs.close();
		
		rs=Database.runQuery("select end from event where id="+id);
		rs.next();
		
		java.util.Date date= new java.util.Date();
		
		Timestamp current=new Timestamp(date.getTime());
	
		if(current.before(rs.getTimestamp(1)))
			valid=false;
		
		rs.close();
		}catch(Exception e)
		{
			Error.error="Unable to check the validity of the data entered";
		}
		
		Database.close();
		System.out.println(valid);
		return valid;
	}
	
	public static boolean checkIfValidSponsor(String name,String sql)
	{
		boolean valid=true;
		Database.connect();
		ResultSet rs=Database.runQuery(sql);
		try{
		boolean more=rs.next();
		while(more)
		{
			if(name.equals(rs.getString(1)))
			{
				valid=false;
				break;
			}
			more=rs.next();
		}
		}catch(Exception e)
		{
			Error.error="Unable to check the validity of the data entered";
		}
		Database.close();
		return valid;
	}
	
	public static boolean checkIfValidEvent(String sql,String name,String venue,Timestamp start,Timestamp end)
	{
		boolean valid=true;
		Database.connect();
		ResultSet rs=Database.runQuery(sql);
		try{
		boolean more=rs.next();
		while(more)
		{
			if(name.equals(rs.getString(1)))
			{
				valid=false;
				break;
			}
			else if(venue.equals(rs.getString(4)))
			{
				
				if(start.before(rs.getTimestamp(3)) && end.after(rs.getTimestamp(2)))
					valid=false;
				else if(start.before(rs.getTimestamp(2)) && end.after(rs.getTimestamp(2)))
					valid=false;
				else if(start.before(rs.getTimestamp(2)) && end.after(rs.getTimestamp(3)))
					valid=false;
				else if(start.before(rs.getTimestamp(3)) && end.after(rs.getTimestamp(3)))
					valid=false;
			}
			if(valid==false)
				break;
			more=rs.next();
		}
		}catch(Exception e)
		{
			Error.error="Unable to check the validity of the data entered";
		}
		Database.close();
		return valid;
	}
	public static boolean checkIfValidTransaction(String name,String sql)
	{
		boolean valid=true;
		Database.connect();
		ResultSet rs=Database.runQuery(sql);
		try{
		boolean more=rs.next();
		while(more)
		{
			if(name.equals(rs.getString(1)))
			{
				valid=false;
				break;
			}
			more=rs.next();
		}
		}catch(Exception e)
		{
			Error.error="Unable to check the validity of the data entered";
		}
		Database.close();
		return valid;
	}
	public static boolean checkIfValidTeam(String id,String hostel,String participant,String sql)
	{
		boolean valid=true;
		Database.connect();
		ResultSet rs=Database.runQuery(sql);
		try{
		boolean more=rs.next();
		while(more)
		{
			if(id.equals(rs.getString(1)) && hostel.equals(rs.getString(2)))
			{
				valid=false;
				break;
			}
			more=rs.next();
		}
		rs.close();
		rs=Database.runQuery("select start,end from event where id="+id);
		rs.next();
		
		java.util.Date date= new java.util.Date();
		
		Timestamp current=new Timestamp(date.getTime());
		if(current.after(rs.getTimestamp(1)))
			valid=false;
		
		Timestamp start=rs.getTimestamp(1);
		Timestamp end=rs.getTimestamp(2);
		rs.close();
		
		rs=Database.runQuery("select eventID from participants where participant='"+participant+"' and eventID!=("+id+")");
		more=rs.next();
		ResultSet rs2;
		while(more)
		{
			if(valid==false)
				break;
			rs2=Database.runQuery("select start,end from event where id="+rs.getString(1));
			if(start.before(rs2.getTimestamp(2)) && end.after(rs2.getTimestamp(1)))
				valid=false;
			else if(start.before(rs2.getTimestamp(1)) && end.after(rs2.getTimestamp(1)))
				valid=false;
			else if(start.before(rs2.getTimestamp(1)) && end.after(rs2.getTimestamp(2)))
				valid=false;
			else if(start.before(rs2.getTimestamp(2)) && end.after(rs2.getTimestamp(2)))
				valid=false;
			rs2.close();
			more=rs.next();
			
		}
		rs.close();
		}catch(Exception e)
		{
			Error.error="Unable to check the validity of the data entered";
		}
		Database.close();
		return valid;
	}
	public static boolean checkIfValidBet(String id,String hostel)
	{
		boolean valid=true;
		Database.connect();
		ResultSet rs=Database.runQuery("select eventID,hostelname from bets where eventID="+id);
		try{
		boolean more=rs.next();
		while(more)
		{
			if(hostel.equals(rs.getString(2)))
			{
				valid=false;
				break;
			}
			more=rs.next();
		}
		rs=Database.runQuery("select start,end from event where eventID="+id+"");
		rs.next();
		
		java.util.Date date= new java.util.Date();
		
		Timestamp current=new Timestamp(date.getTime());
		if(current.after(rs.getTimestamp(1)))
			valid=false;
		
		rs.close();
		}catch(Exception e)
		{
			Error.error="Unable to check the validity of the data entered";
		}
		Database.close();
		return valid;
	}
}
