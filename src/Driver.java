/*
 * public use - use it, take it, make it your own
 * 
 * Description: Parsing XML from a website.  jsoup lib used to parse some html inside of the XML document. 
 * 
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Driver {

	
	public static void main(String[] args) {
		
		try {
			ArrayList<Repeater> repeaters = new ArrayList<Repeater>();
			
			URL url = new URL("http://k5ehx.net/repeaters/kmldynamic.php?BBOX=-122.497790,37.730385,-122.380087,37.812331");
			RepeaterRequester requester = new RepeaterRequester(url);
			
			
			repeaters = requester.MakeRequest();
			
			for(int i = 0; i < repeaters.size(); i++)
			{
				System.out.println("*** Repeater #" + (i+1) + " ***");
				System.out.println(repeaters.get(i).Name());
				System.out.println(repeaters.get(i).Coordinates());
				System.out.println(repeaters.get(i).Description());
				System.out.println("******************\n");
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
