package fr.uppa.waam.views;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import fr.uppa.waam.R;
import fr.uppa.waam.listeners.MyLocationListener;
import fr.uppa.waam.listeners.SendMessageListener;
import fr.uppa.waam.models.Message;
import fr.uppa.waam.presenters.WallAdapter;

public class WallActivity extends Activity {

	MyLocationListener locationListener;
	LocationManager locationManager;
	WallAdapter wallAdapter;
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wall);

		// Attach the adapter with the list view
		this.list = (ListView) findViewById(R.id.messages);
		List<Message> messages = new ArrayList<Message>();
		this.wallAdapter = new WallAdapter(this, R.layout.message, messages);
		this.list.setAdapter(this.wallAdapter);

		// Location management
		this.locationListener = new MyLocationListener(this, this.wallAdapter);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, this.locationListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_message:
			LayoutInflater layoutInflater = LayoutInflater.from(this);
			View messageDialogView = layoutInflater.inflate(R.layout.activity_message, null);
			EditText input = (EditText) findViewById(R.id.input);
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

			alertDialogBuilder.setView(messageDialogView);
			alertDialogBuilder.setCancelable(true);
			alertDialogBuilder.setPositiveButton("Envoyer", new SendMessageListener(input, this));
			alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});

			// create an alert dialog
			AlertDialog alertD = alertDialogBuilder.create();

			alertD.show();
			break;
			
		case R.id.menu_preference:
			Intent intent = new Intent();
			intent.setClass(this, MyPreferenceActivity.class);
			this.startActivity(intent);
			break;

		default:
			break;
		}
		return true;
	}

	public void populate(List<Message> messages) {
		this.wallAdapter.clear();
		this.wallAdapter.addAll(messages);
		this.wallAdapter.notifyDataSetChanged();
	}

}
