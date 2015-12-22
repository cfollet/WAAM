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
		// then you use
		SharedPreferences preferences = this.getPreferences(MODE_PRIVATE);
		boolean isFirstTime = preferences.getBoolean("isFirstTime", true);
		Log.i("test", String.valueOf(isFirstTime));
	}
}
