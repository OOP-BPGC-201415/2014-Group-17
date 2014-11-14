import junit.framework.TestCase;


public class displayAllEventsTest extends TestCase {
	public displayAllEventsTest(){
		super();
	}
	
	Utilities ne = new Utilities();
	
	public void testCase1(){
		assertEquals(ne.displayAllEventsTest(),"No events to display. \n");
	}
	
	public void testCase2(){
		assertEquals(ne.displayAllEventsTest(),"list of events in DB goes here");
	}
	
}
