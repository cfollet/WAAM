package fr.uppa.waam.models;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import fr.uppa.waam.tasks.RetrieveMessagesTask;
import fr.uppa.waam.views.WallActivity;

public class MessagesManager {
	
	WallActivity activity;
	
	public MessagesManager(WallActivity activity) {
		super();
		this.activity = activity;
	}

	public void getMessages(){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
		double latitude = preferences.getFloat(GeoLocation.JSON_TAG_LATITUDE, 0);
		double longitude = preferences.getFloat(GeoLocation.JSON_TAG_LONGITUDE, 0);
		int radius = preferences.getInt("radius_preference", GeoLocation.DEFAULT_RADIUS);
		radius = 1000000;
		Log.i("test", "Location : " + String.valueOf(latitude)+", "+String.valueOf(longitude));
		
		// If the shared preferences can be retrieved and are correct
		if(longitude+latitude != 0 && radius !=0){
			GeoLocation myLocation = new GeoLocation(latitude, longitude, 0, radius);
			new RetrieveMessagesTask(this.activity).execute(myLocation);
		}
	}
}
