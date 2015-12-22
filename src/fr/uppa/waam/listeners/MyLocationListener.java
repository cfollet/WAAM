package fr.uppa.waam.listeners;

import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import fr.uppa.waam.models.GeoLocation;
import fr.uppa.waam.presenters.WallAdapter;
import fr.uppa.waam.tasks.RetrieveMessages;
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
		/** Save the location and radius into shared preferences **/
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putFloat(GeoLocation.JSON_TAG_LATITUDE, (float) location.getLatitude());
		editor.putFloat(GeoLocation.JSON_TAG_LONGITUDE, (float) location.getLongitude());
		editor.commit();

		/** Get messages from async task **/
		double latitude = 48.856614;//location.getLatitude();
		double longitude = 2.352233;//location.getLongitude();
		
		int radius = preferences.getInt(GeoLocation.JSON_TAG_RADIUS, GeoLocation.DEFAULT_RADIUS);
		Log.i("test", "radius="+String.valueOf(radius));
		GeoLocation myLocation = new GeoLocation(latitude, longitude, 0, radius);
		
		new RetrieveMessages(this.activity).execute(myLocation);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// if the position isn't fixed we unset the longitude and latitude from shared preferences.
		if(status != LocationProvider.AVAILABLE){
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
			SharedPreferences.Editor editor = preferences.edit();
			editor.remove(GeoLocation.JSON_TAG_LATITUDE);
			editor.remove(GeoLocation.JSON_TAG_LONGITUDE);
			editor.commit();
		}
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.i("test", "location Down");
	}

}
