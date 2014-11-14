package hello;

import java.util.regex.Pattern;

import junit.framework.TestCase;

public class TestOnGoingEvents extends TestCase {
	public TestOnGoingEvents(String name)
	{
		super(name);
	}
	public void testOnGoingEvents()
	{
		Database.connect();
		try
		{
			
			//Entering a dummy event in the database
			Database.runUpdate("INSERT INTO event_list VALUES ('eventXYZ','eventHead',2014-11-04 09:00:00,2015-11-04 09:00:00");
			
			String testString;
			testString=Utilities.displayOnGoingEvents();

			String pattern = ".*eventXYZ.*";

			boolean matches = Pattern.matches(pattern, testString);

			if(matches==false)
				fail("Should have displayed the event 'eventXYZ'"); 
			else
				assertTrue(true);
			
			//Removing the previously entered event from the database
			Database.runUpdate("DELETE FROM event_list WHERE Event='eventXYZ'");
			
			testString=Utilities.displayOnGoingEvents();
			
			matches=Pattern.matches(pattern, testString);
			if(matches==true)
				fail("Shouldn't have displayed the event 'eventXYZ'");
			else
				assertTrue(true);
			
		}catch(Exception e)
		{
			fail("unknown error");
		}
		Database.close();
	}
}
