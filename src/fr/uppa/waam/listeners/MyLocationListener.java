package fr.uppa.waam.listeners;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;
import fr.uppa.waam.models.GeoLocation;
import fr.uppa.waam.presenters.WallAdapter;
import fr.uppa.waam.tasks.RetrieveMessages;
import fr.uppa.waam.views.WallActivity;

public class MyLocationListener implements LocationListener {

	WallActivity activity;
	WallAdapter wallAdapter;

	public MyLocationListener(WallActivity activity, WallAdapter wallAdapter) {
		super();
		this.activity 		= activity;
		this.wallAdapter	= wallAdapter;
	}

	@Override
	public void onLocationChanged(Location location) {
		// Save the location and call web service to retrieve data
		String msg = "New Latitude: " + location.getLatitude() + "New Longitude: " + location.getLongitude();
		Toast.makeText(activity.getBaseContext(), msg, Toast.LENGTH_LONG).show();
		GeoLocation myLocation = new GeoLocation(location.getLatitude(), location.getLongitude(), 50); // 50 need to be get from user preferences
		new RetrieveMessages(this.activity).execute(myLocation);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		String msg = "GPS désactivé.";
		Toast.makeText(activity.getBaseContext(), msg, Toast.LENGTH_LONG).show();
		

	}

}
