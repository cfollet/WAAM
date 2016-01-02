package fr.uppa.waam.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class GeoLocation {
	public final static int DEFAULT_DISTANCE = 0;
	public final static float DEFAULT_LATITUDE = 0.f;
	public final static float DEFAULT_LONGITUDE = 0.f;
	public final static int DEFAULT_RADIUS = 250;

	/** Used to retrieve data from the server using get method **/
	public final static String GET_TAG_LATITUDE = "my_latitude";
	public final static String GET_TAG_LONGITUDE = "my_longitude";
	public final static String GET_TAG_RADIUS = "my_radius";
	public final static String JSON_TAG_DISTANCE = "meters";
	/** Used to parse JSON response from server **/
	public final static String JSON_TAG_LATITUDE = "my_latitude";
	public final static String JSON_TAG_LONGITUDE = "my_longitude";
	public final static String JSON_TAG_RADIUS = "my_radius";

	/** Used to store/retrieve SharedPreferences **/
	public final static String PREFERENCE_TAG_LATITUDE = "my_latitude";
	public final static String PREFERENCE_TAG_LONGITUDE = "my_longitude";
	public final static String PREFERENCE_TAG_RADIUS = "my_radius";

	private int distance;
	private double latitude;
	private double longitude;
	private int radius;

	public GeoLocation(double latitude, double longitude, int distance, int radius) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
		this.radius = radius;
	}

	public int getDistance() {
		return distance;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public int getRadius() {
		return radius;
	}

	public List<NameValuePair> toNameValuePairs() {
		List<NameValuePair> result = new ArrayList<NameValuePair>();

		result.add(new BasicNameValuePair(GeoLocation.GET_TAG_LATITUDE, String.valueOf(this.getLatitude())));
		result.add(new BasicNameValuePair(GeoLocation.GET_TAG_LONGITUDE, String.valueOf(this.getLongitude())));
		result.add(new BasicNameValuePair(GeoLocation.GET_TAG_RADIUS, String.valueOf(this.getRadius())));

		return result;
	}

	@Override
	public String toString() {
		String result;
		result = "GeoLocation(" + this.getLatitude() + ", " + this.getLongitude() + ", " + this.getDistance() + ", "
				+ this.getRadius() + ")";
		return result;
	}

}
