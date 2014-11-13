// Import statements
import java.sql.*;

public class Database
{
	//  JDBC driver name and database URL
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/Zephyr";

	//  Database credentials
	public static final String USER = "root";
	public static final String PASS = "qwerty123";

	// Connection variables
	private static Connection conn = null;
	private static Statement stmt = null;

	// Functions
	public static void connect()
	{
		try
		{
			// Register JDBC Driver
			Class.forName(Database.JDBC_DRIVER);

			// Set up connection
			conn = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void close()
	{
		try
		{
			if(stmt != null)
			{
				conn.close();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
}