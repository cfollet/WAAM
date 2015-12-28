package fr.uppa.waam.listeners;

import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import fr.uppa.waam.R;
import fr.uppa.waam.models.GeoLocation;
import fr.uppa.waam.presenters.WallAdapter;
import fr.uppa.waam.tasks.RetrieveMessagesTask;
import fr.uppa.waam.views.WallActivity;

public class MyLocationListener implements LocationListener {

	WallActivity activity;
	WallAdapter wallAdapter;

	public MyLocationListener(WallActivity activity, WallAdapter wallAdapter) {
		super();
		this.activity = activity;
		this.wallAdapter = wallAdapter;
	}

	@Override
	public void onLocationChanged(Location location) {
		this.activity.setMenuItemActiveState(true);
		/** Save the location into shared preferences **/
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putFloat(GeoLocation.JSON_TAG_LATITUDE, (float) location.getLatitude());
		editor.putFloat(GeoLocation.JSON_TAG_LONGITUDE, (float) location.getLongitude());
		editor.commit();

		/** Get messages from async task **/
		// double latitude = 48.856614;
		// double longitude = 2.352233;
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();

		int radius = preferences.getInt("radius_preference", GeoLocation.DEFAULT_RADIUS);
		radius = 1000000;
		GeoLocation myLocation = new GeoLocation(latitude, longitude, 0, radius);
		new RetrieveMessagesTask(this.activity).execute(myLocation);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		/**if the position isn't fixed we unset the longitude and latitude from shared preferences. **/
		if (status != LocationProvider.AVAILABLE) {
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
			SharedPreferences.Editor editor = preferences.edit();
			editor.remove(GeoLocation.JSON_TAG_LATITUDE);
			editor.remove(GeoLocation.JSON_TAG_LONGITUDE);
			editor.commit();
		}
		if (status == LocationProvider.AVAILABLE){
			this.activity.setMenuItemActiveState(true);
		}
	}

	@Override
	public void onProviderEnabled(String provider) {
		TextView text = (TextView) this.activity.findViewById(R.id.empty);
		text.setText("En attente le localisation...");
	}

	@Override
	public void onProviderDisabled(String provider) {
		/**if the position isn't fixed we unset the longitude and latitude from shared preferences. **/
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(GeoLocation.JSON_TAG_LATITUDE);
		editor.remove(GeoLocation.JSON_TAG_LONGITUDE);
		editor.commit();

		TextView text = (TextView) this.activity.findViewById(R.id.empty);
		text.setText(":(, Le GPS est désactivé");
		
		this.activity.setMenuItemActiveState(false);
	}

}
