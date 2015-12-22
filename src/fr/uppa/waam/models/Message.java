package fr.uppa.waam.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.JsonReader;
import android.util.Log;
import android.webkit.GeolocationPermissions;
import fr.uppa.waam.models.GeoLocation;;

public class Message {
	public final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public final static String JSON_TAG_CONTENT = "msg";
	public final static String JSON_TAG_LOCATION = "geo";
	public final static String JSON_TAG_TIMESTAMP = "time";
	public final static String JSON_TAG_GENDER = "gender";

	public static final Integer MALE_CODE = 1;
	public static final Integer FEMALE_CODE = 0;

	private String content;
	private GeoLocation location;
	private Date timestamp;
	private Integer gender;

	public Message(String content, GeoLocation location, Date timestamp, Integer gender) {
		super();
		this.content = content;
		this.location = location;
		this.timestamp = timestamp;
		this.gender = gender;
	}

	public Message(JSONObject jsonMessage) {
		try {

			// get Message Informations
			this.content = jsonMessage.getString(Message.JSON_TAG_CONTENT);
			this.gender = jsonMessage.getInt(Message.JSON_TAG_GENDER);
			this.timestamp = formatter.parse(jsonMessage.getString(Message.JSON_TAG_TIMESTAMP));

			// get GeoLocation Informations
			int distance = ((Number)jsonMessage.getDouble(GeoLocation.JSON_TAG_DISTANCE)).intValue();
			JSONArray location = jsonMessage.getJSONArray(Message.JSON_TAG_LOCATION);
			double latitude = location.getDouble(0);
			double longitude = location.getDouble(1);
			this.timestamp = new Date();
			this.location = new GeoLocation(latitude, longitude, distance, 0);

		} catch (Exception e) {
			Log.i("test", e.getCause().toString());
		}
	}

	public List<NameValuePair> toNameValuePairs() {
		List<NameValuePair> result = new ArrayList<NameValuePair>();

		result.add(new BasicNameValuePair(GeoLocation.JSON_TAG_LATITUDE, String.valueOf(this.location.getLatitude())));
		result.add(new BasicNameValuePair(GeoLocation.JSON_TAG_LONGITUDE, String.valueOf(this.location.getLongitude())));
		result.add(new BasicNameValuePair(Message.JSON_TAG_CONTENT, this.content));
		result.add(new BasicNameValuePair(Message.JSON_TAG_GENDER, String.valueOf(this.getGender())));

		return result;
	}

	public String getContent() {
		return content;
	}

	public GeoLocation getLocation() {
		return location;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Integer getGender() {
		return gender;
	}

}
