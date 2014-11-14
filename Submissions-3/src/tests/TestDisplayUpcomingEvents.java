import junit.framework.TestCase;

public class TestDisplayUpcomingEvents extends TestCase{
	public TestDisplayUpcomingEvents(String message)
	{
		super(message);
	}
void TestdisplayUpcomingEventsOne()
{
	Utilities U;
	assertEquals("No upcoming events in the next hour",U.displayUpcomingEvents()); 
	Utilities U1;
	assertEquals("//to be modified as per database",U1.displayUpcomingEvents());
}


}
