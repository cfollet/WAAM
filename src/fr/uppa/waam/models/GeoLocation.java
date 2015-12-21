package fr.uppa.waam.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class GeoLocation {
	public final static String JSON_TAG_LATITUDE = "my_latitude";
	public final static String JSON_TAG_LONGITUDE = "my_longitude";
	public final static String JSON_TAG_RADIUS = "my_radius";
	public final static String JSON_TAG_DISTANCE = "meters";

	private double latitude;
	private double longitude;
	/**
	 * Distance from the device (given by the web service) or used to define the
	 * range
	 **/
	private double distance;

	public GeoLocation(double latitude, double longitude, double distance) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getDistance() {
		return distance;
	}

	public List<NameValuePair> toNameValuePairs() {
		List<NameValuePair> result = new ArrayList<NameValuePair>();

		result.add(new BasicNameValuePair(GeoLocation.JSON_TAG_LATITUDE, String.valueOf(this.getLatitude())));
		result.add(new BasicNameValuePair(GeoLocation.JSON_TAG_LONGITUDE, String.valueOf(this.getLongitude())));
		result.add(new BasicNameValuePair(GeoLocation.JSON_TAG_RADIUS, String.valueOf(this.getDistance())));

		return result;
	}

	@Override
	public String toString() {
		String result;

		result = "my_latitude=" + this.getLatitude();
		result += "&";
		result += "my_longitude=" + this.getLongitude();
		result += "&";
		result += "my_radius=" + this.getDistance();

		return result;
	}

}
