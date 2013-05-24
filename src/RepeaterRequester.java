import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class RepeaterRequester {

	private ArrayList<Repeater> repeaters;
	private URL url;

	public RepeaterRequester(URL url) {
		repeaters = new ArrayList<Repeater>();
		this.url = url;
	}

	public ArrayList<Repeater> MakeRequest() {
		
		
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document doc = db.parse(new InputSource(url
					.openStream()));
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("Folder");

			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);
				
				Element placemark = (Element) node;

				Repeater repeater = new Repeater();
				
				Element nameElement = (Element) placemark.getElementsByTagName(
						"name").item(0);
				Element descElement = (Element) placemark.getElementsByTagName(
						"description").item(0);
				
				Element coordElement = (Element) placemark
						.getElementsByTagName("coordinates").item(0);

				repeater.Name(nameElement.getChildNodes().item(0)
						.getNodeValue());

				/*
				 * The description element comes to us in html. jsoup is used to
				 * distribute the elements contents
				 */
				String html = descElement.getChildNodes().item(0)
						.getNodeValue();

				org.jsoup.nodes.Document htmlDoc = Jsoup
						.parseBodyFragment(html);

				Elements elements = htmlDoc.body().children();

				for (int k = 0; k < elements.size(); k++) {

					if (elements.get(k).tagName().equalsIgnoreCase("h3")) {
						repeater.Description(elements.get(k).html());
					} else if (elements.get(k).tagName().equalsIgnoreCase("p")) {
						if (!elements.get(k).html().contains("<a href=")) {
							String[] details = elements.get(k).html()
									.split("<br />");
							for (int l = 0; l < details.length; l++) {
								repeater.Description(details[l]);
							}
						} else {
							repeater.Description(elements.get(k)
									.select("a[href]").first().attr("href"));
						}
					}
				}
				// End of description element's html parsing

				double lat = 0.0, lng = 0.0;
				String[] coords = coordElement.getChildNodes().item(0)
						.getNodeValue().split(",");
				if (coords != null) {
					lat = Double.parseDouble(coords[0]);
					lng = Double.parseDouble(coords[1]);
				}
				repeater.Latitude(lat);
				repeater.Longitude(lng);

				// End of line...
				repeaters.add(repeater);
			}
		} catch (Exception e) {
			return null;
		}
		return repeaters;
	}

}
