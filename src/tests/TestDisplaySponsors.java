import junit.framework.*;

public class TestDisplaySponsors
{   public static String slist="slist to be updated later";
    public static void main(String args[])
    {
     if(slist.equals(Sponsors.displaySponsors()))
     {assertTrue(true);
         	
     }
     else
     	 fail("unexpected sponsor list returned");
    }

}