package fr.uppa.waam.views;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;
import fr.uppa.waam.R;

public class MyPreferenceActivity extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.activity_preference);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Toast.makeText(this.getApplicationContext(), "Les préférences ont été sauvegardées.", Toast.LENGTH_LONG).show();
	}

}
