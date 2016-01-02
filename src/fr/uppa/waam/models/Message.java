package fr.uppa.waam.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;;

public class Message {
	public static final Integer FEMALE_CODE = 0;

	public final static SimpleDateFormat JSON_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public final static String JSON_TAG_CONTENT = "msg";
	public final static String JSON_TAG_GENDER = "gender";
	public final static String JSON_TAG_LOCATION = "geo";

	public final static String JSON_TAG_TIMESTAMP = "time";
	public static final Integer MALE_CODE = 1;

	public final static String POST_TAG_CONTENT = "my_message";

	public final static String POST_TAG_GENDER = "my_gender";
	public final static String PREFERENCE_TAG_GENDER = "my_gender";

	private String content;
	private Integer gender;
	private GeoLocation location;
	private Date timestamp;

	public Message(JSONObject jsonMessage) {
		try {

			// get Message Informations
			this.content = jsonMessage.getString(Message.JSON_TAG_CONTENT);
			this.gender = jsonMessage.getInt(Message.JSON_TAG_GENDER);
			this.timestamp = JSON_DATE_FORMATTER.parse(jsonMessage.getString(Message.JSON_TAG_TIMESTAMP));

			// get GeoLocation Informations
			int distance = ((Number) jsonMessage.getDouble(GeoLocation.JSON_TAG_DISTANCE)).intValue();
			JSONArray location = jsonMessage.getJSONArray(Message.JSON_TAG_LOCATION);
			double latitude = location.getDouble(0);
			double longitude = location.getDouble(1);
			this.location = new GeoLocation(latitude, longitude, distance, 0);

		} catch (Exception e) {
			Log.i("test", e.getCause().toString());
		}
	}

	public Message(String content, GeoLocation location, Date timestamp, Integer gender) {
		super();
		this.content = content;
		this.location = location;
		this.timestamp = timestamp;
		this.gender = gender;
	}

	public String getContent() {
		return content;
	}

	public Integer getGender() {
		return gender;
	}

	public GeoLocation getLocation() {
		return location;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public List<NameValuePair> toNameValuePairs() {
		List<NameValuePair> result = new ArrayList<NameValuePair>();

		result.add(new BasicNameValuePair(GeoLocation.GET_TAG_LATITUDE, String.valueOf(this.location.getLatitude())));
		result.add(new BasicNameValuePair(GeoLocation.GET_TAG_LONGITUDE, String.valueOf(this.location.getLongitude())));
		result.add(new BasicNameValuePair(Message.POST_TAG_CONTENT, this.content));
		result.add(new BasicNameValuePair(Message.POST_TAG_GENDER, String.valueOf(this.getGender())));

		return result;
	}

}
