package fr.uppa.waam.models;

import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import fr.uppa.waam.tasks.RetrieveMessagesTask;
import fr.uppa.waam.tasks.SendMessageTask;
import fr.uppa.waam.views.WallActivity;

public class MessagesManager {

	WallActivity activity;
	EditText input;

	public MessagesManager(WallActivity activity) {
		super();
		this.activity = activity;
	}

	public MessagesManager(WallActivity activity, EditText input) {
		super();
		this.activity = activity;
		this.input = input;
	}

	public void getMessages() {
		if (isNetworkAvaliable()) {
			GeoLocation myLocation = getMyLocation();
			if (myLocation != null) {
				new RetrieveMessagesTask(this.activity).execute(myLocation);
			}
		} else {
			Toast.makeText(this.activity.getApplicationContext(), "Réseau indisponible.", Toast.LENGTH_LONG).show();

		}
	}

	/**
	 * To be valid, a location must have a longitude, latitude, radius !=0
	 * 
	 * @return
	 */
	private GeoLocation getMyLocation() {
		GeoLocation myLocation = null;

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
		double latitude = preferences.getFloat(GeoLocation.PREFERENCE_TAG_LATITUDE, GeoLocation.DEFAULT_LATITUDE);
		double longitude = preferences.getFloat(GeoLocation.PREFERENCE_TAG_LONGITUDE, GeoLocation.DEFAULT_LONGITUDE);
		int radius = preferences.getInt(GeoLocation.PREFERENCE_TAG_RADIUS, GeoLocation.DEFAULT_RADIUS);

		if (latitude + longitude != 0 && radius != 0) {
			myLocation = new GeoLocation(latitude, longitude, GeoLocation.DEFAULT_DISTANCE, radius);
		}

		return myLocation;
	}

	private boolean isNetworkAvaliable() {
		return ((ConnectivityManager) this.activity.getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
	}

	public void sendMessage() {
		if (isNetworkAvaliable()) {
			GeoLocation myLocation = getMyLocation();
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
			String gender = preferences.getString(Message.PREFERENCE_TAG_GENDER, "");

			Log.i("test", "Gender : " + gender);

			// If the shared preferences can be retrieved and are correct
			if (myLocation != null && !gender.isEmpty()) {
				if (this.input.getText().toString().length() >= 3) {
					Message message = new Message(this.input.getText().toString(), myLocation, new Date(),
							Integer.parseInt(gender));
					new SendMessageTask(this.activity).execute(message);
				} else {
					Toast.makeText(this.activity.getApplicationContext(),
							"Le message doit faire au moins 3 carractères.", Toast.LENGTH_LONG).show();
				}

			} else {
				Toast.makeText(this.activity.getApplicationContext(), "Verifier le GPS et vos préférences.",
						Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(this.activity.getApplicationContext(), "Réseau indisponible.", Toast.LENGTH_LONG).show();

		}
	}
}
