import java.util.ArrayList;

//this is the parsed structure of a point
public class Repeater {

	private String name = null;
	private ArrayList<String> description = null;

	private Double latitude = null;
	private Double longitude = null;

	public Repeater() {
		name = new String();
		description = new ArrayList<String>();
		latitude = 0.0;
		longitude = 0.0;
	}

	public String Name() {
		return this.name;
	}

	public String Description() {

		if (this.description.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < this.description.size(); i++) {
				sb.append(this.description.get(i) + "\n");
			}

			sb.delete(sb.length()-"\n".length(), sb.length());
			
			return sb.toString();
		} else {
			return "";
		}
	}

	public String Coordinates(){
		return this.latitude + ", " + this.longitude;
	}
	
	public double Latitude() {
		return this.latitude;
	}

	public double Longitude() {
		return this.longitude;
	}

	public void Name(String name) {
		this.name = name;
	}

	public void Description(String desc) {
		this.description.add(desc);
	}

	public void Latitude(double lat) {
		this.latitude = lat;
	}

	public void Longitude(double lon) {
		this.longitude = lon;
	}

}