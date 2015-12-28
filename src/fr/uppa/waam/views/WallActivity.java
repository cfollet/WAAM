package fr.uppa.waam.views;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import fr.uppa.waam.R;
import fr.uppa.waam.listeners.CancelMessageListener;
import fr.uppa.waam.listeners.MessageTextChangedListener;
import fr.uppa.waam.listeners.MyLocationListener;
import fr.uppa.waam.listeners.SendMessageListener;
import fr.uppa.waam.models.GeoLocation;
import fr.uppa.waam.models.Message;
import fr.uppa.waam.models.MessagesManager;
import fr.uppa.waam.presenters.WallAdapter;
import fr.uppa.waam.util.ThemeHandler;

public class WallActivity extends Activity {

	MessagesManager manager;
	WallAdapter wallAdapter;
	ListView list;
	ThemeHandler themeHandler;
	Menu menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wall);
		
		this.manager = new MessagesManager(this);
		
		// Attach the adapter with the list view
		this.list = (ListView) findViewById(R.id.messages);
		List<Message> messages = new ArrayList<Message>();
		this.wallAdapter = new WallAdapter(this, R.layout.message, messages);
		this.list.setAdapter(this.wallAdapter);
		this.list.setEmptyView(findViewById(R.id.empty));

		// Location management
		MyLocationListener locationListener = new MyLocationListener(this, this.wallAdapter);
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

		// Default preferences initialization
		this.init();

		// UI initialization using preferences
		this.themeHandler = new ThemeHandler(this);
		this.themeHandler.init();

	}

	@Override
	protected void onResume() {
		super.onResume();
		this.themeHandler.init();
		this.manager.getMessages();
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Remove the location at the end of the application
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(GeoLocation.JSON_TAG_LATITUDE);
		editor.remove(GeoLocation.JSON_TAG_LONGITUDE);
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		
		this.menu = menu;
		
		// Disable action button (they will be activated when the phone is located)
		setMenuItemActiveState(false);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void setMenuItemActiveState(boolean state){
		if (this.menu != null) {
			int alpha = (state)?255:130; // Alpha transparency
			menu.findItem(R.id.menu_message).setEnabled(state);
			menu.findItem(R.id.menu_message).getIcon().setAlpha(alpha);
			menu.findItem(R.id.menu_refresh).setEnabled(state);
			menu.findItem(R.id.menu_refresh).getIcon().setAlpha(alpha);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_message:
			LayoutInflater layoutInflater = LayoutInflater.from(this);
			View messageDialogView = layoutInflater.inflate(R.layout.activity_message, null);
			EditText input = (EditText) messageDialogView.findViewById(R.id.input);
			TextView inputCount = (TextView) messageDialogView.findViewById(R.id.inputCount);
			input.addTextChangedListener(new MessageTextChangedListener(inputCount));
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

			alertDialogBuilder.setView(messageDialogView);
			alertDialogBuilder.setCancelable(true);
			alertDialogBuilder.setPositiveButton("Envoyer", new SendMessageListener(input, this));
			alertDialogBuilder.setNegativeButton("Anuler", new CancelMessageListener());

			// create an alert dialog
			AlertDialog alertD = alertDialogBuilder.create();

			alertD.show();
			break;

		case R.id.menu_preference:
			Intent intent = new Intent();
			intent.setClass(this, MyPreferenceActivity.class);
			this.startActivity(intent);
			break;
		case R.id.menu_refresh:
			this.manager.getMessages();
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

	private void init() {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		boolean isFirstTime = preferences.getBoolean("isFirstTime", true);
		if (isFirstTime) {
			// Initialize the default value from the xml preferences file
			PreferenceManager.setDefaultValues(this, R.xml.activity_preference, false);
			SharedPreferences.Editor editor = preferences.edit();
			editor.putBoolean("isFirstTime", false);
			editor.commit();
		}
	}

}
