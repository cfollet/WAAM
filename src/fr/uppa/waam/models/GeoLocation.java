package fr.uppa.waam.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class GeoLocation {
	public final static String JSON_TAG_LATITUDE = "my_latitude";
	public final static String JSON_TAG_LONGITUDE = "my_longitude";
	public final static String JSON_TAG_RADIUS = "my_radius";
	public final static int DEFAULT_RADIUS = 250;
	public final static String JSON_TAG_DISTANCE = "meters";

	private double latitude;
	private double longitude;
	private int distance;
	private int radius;

	public GeoLocation(double latitude, double longitude, int distance, int radius) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
		this.radius = radius;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public int getDistance() {
		return distance;
	}

	public int getRadius() {
		return radius;
	}

	public List<NameValuePair> toNameValuePairs() {
		List<NameValuePair> result = new ArrayList<NameValuePair>();

		result.add(new BasicNameValuePair(GeoLocation.JSON_TAG_LATITUDE, String.valueOf(this.getLatitude())));
		result.add(new BasicNameValuePair(GeoLocation.JSON_TAG_LONGITUDE, String.valueOf(this.getLongitude())));
		result.add(new BasicNameValuePair(GeoLocation.JSON_TAG_RADIUS, String.valueOf(this.getRadius())));

		return result;
	}
	
	

}
