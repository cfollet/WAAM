package fr.uppa.waam.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import fr.uppa.waam.R;

public class MyPreferenceActivity extends PreferenceActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.activity_preference);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		// then you use
		Log.i("test", String.valueOf(prefs.getInt("radius_preference",0)));
	}
}
