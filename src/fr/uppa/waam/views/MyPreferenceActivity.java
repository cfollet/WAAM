package fr.uppa.waam.views;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;
import fr.uppa.waam.R;
import fr.uppa.waam.util.ThemeHandler;

public class MyPreferenceActivity extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.activity_preference);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Toast.makeText(this.getApplicationContext(), "Les préférences ont été sauvegardées.", Toast.LENGTH_LONG).show();
		
	}
	
	
}
