package fr.uppa.waam.listeners;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import fr.uppa.waam.R;
import fr.uppa.waam.models.GeoLocation;
import fr.uppa.waam.presenters.WallAdapter;
import fr.uppa.waam.tasks.RetrieveMessages;
import fr.uppa.waam.util.PreferencesHandler;
import fr.uppa.waam.views.WallActivity;

public class MyLocationListener implements LocationListener {

	WallActivity activity;
	WallAdapter wallAdapter;
	PreferencesHandler preferencesHandler;

	public MyLocationListener(WallActivity activity, WallAdapter wallAdapter) {
		super();
		this.activity = activity;
		this.wallAdapter = wallAdapter;
		this.preferencesHandler = new PreferencesHandler(this.activity);
	}

	@Override
	public void onLocationChanged(Location location) {
		
		// GeoLocation myLocation = new GeoLocation(location.getLatitude(),
		// location.getLongitude(), 50); // preferences
		// new RetrieveMessages(this.activity).execute(myLocation);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		switch (status) {
		case LocationProvider.AVAILABLE:
			Log.v("test", "Status Changed: Available");
			Toast.makeText(activity.getBaseContext(), "Status Changed: Available", Toast.LENGTH_SHORT).show();
			break;
		default:
			Log.i("test", "location Down");
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
