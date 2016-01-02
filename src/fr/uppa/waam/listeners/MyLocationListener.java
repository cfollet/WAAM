package fr.uppa.waam.listeners;

import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import fr.uppa.waam.R;
import fr.uppa.waam.models.GeoLocation;
import fr.uppa.waam.models.MessagesManager;
import fr.uppa.waam.presenters.WallAdapter;
import fr.uppa.waam.views.WallActivity;

/**
 * Used to detect when the phone location changes.
 * 
 * @author cfollet
 *
 */
public class MyLocationListener implements LocationListener {

	WallActivity activity;
	MessagesManager manager;
	WallAdapter wallAdapter;

	public MyLocationListener(WallActivity activity, WallAdapter wallAdapter) {
		super();
		this.activity = activity;
		this.wallAdapter = wallAdapter;
		this.manager = new MessagesManager(this.activity);
	}

	@Override
	public void onLocationChanged(Location location) {
		this.activity.setMenuItemActiveState(true);
		this.setLocation(location);
		/** retrieve message from database **/
		this.manager.getMessages();

	}

	/**
	 * When the GPS is disabled, we unset the location from shared preferences
	 * and disable the action bar buttons
	 **/
	@Override
	public void onProviderDisabled(String provider) {

		this.unsetLocation();
		TextView text = (TextView) this.activity.findViewById(R.id.empty);
		text.setText("Le GPS est désactivé :(");
		text = (TextView) this.activity.findViewById(R.id.emptyPaginated);
		text.setText("Le GPS est désactivé :(");

		this.activity.setMenuItemActiveState(false);
	}

	@Override
	public void onProviderEnabled(String provider) {
		TextView text = (TextView) this.activity.findViewById(R.id.empty);
		text.setText("En attente le localisation...");
		text = (TextView) this.activity.findViewById(R.id.emptyPaginated);
		text.setText("En attente le localisation...");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		/**
		 * if the position becomes unfixed, we unset the longitude and latitude
		 * from shared preferences and we disable the action bar buttons
		 **/
		if (status != LocationProvider.AVAILABLE) {
			this.unsetLocation();
			this.activity.setMenuItemActiveState(false);
		}
		/**
		 * If the position becomes fixed, we activate the action bar buttons
		 */
		if (status == LocationProvider.AVAILABLE) {
			this.activity.setMenuItemActiveState(true);
		}
	}

	private void setLocation(Location location) {
		/** Save the location into shared preferences **/
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putFloat(GeoLocation.PREFERENCE_TAG_LATITUDE, (float) location.getLatitude());
		editor.putFloat(GeoLocation.PREFERENCE_TAG_LONGITUDE, (float) location.getLongitude());
		editor.commit();
	}

	private void unsetLocation() {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(GeoLocation.PREFERENCE_TAG_LATITUDE);
		editor.remove(GeoLocation.PREFERENCE_TAG_LONGITUDE);
		editor.commit();
	}

}
