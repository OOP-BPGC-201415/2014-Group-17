package javacodes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends Database{
	private String username="";
	private String password="";
	private String type="";
	
	public boolean login(String user, String pass)
	{
		username=user;
		password=pass;
		boolean valid = false;
		Database.connect();
		try
		{
			ResultSet rs = Database.runQuery("SELECT password,type FROM login WHERE username='"+user+"'");			
			// Compare data from the ResultSet
			boolean more=rs.next();
			if(more==true)
			{
				if(pass.equals(rs.getString("password")))
				{
				valid = true;
				type=rs.getString("type");
				}
			}
			rs.close();			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		Database.close();
		return valid;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public String getType()
	{
		return type;
	}
}
