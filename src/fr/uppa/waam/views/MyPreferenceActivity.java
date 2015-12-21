package fr.uppa.waam.views;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import fr.uppa.waam.R;

public class MyPreferenceActivity extends PreferenceActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.activity_preference);
	}
}
