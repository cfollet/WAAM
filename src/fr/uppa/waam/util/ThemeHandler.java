package fr.uppa.waam.util;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import fr.uppa.waam.R;
import fr.uppa.waam.models.Message;

public class ThemeHandler {
	private Activity activity;

	public ThemeHandler(Activity activity) {
		this.activity = activity;
	}

	private void init() {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
		int gender = preferences.getInt(Message.POST_TAG_GENDER, -1);

		ActionBar actionBar = this.activity.getActionBar();
		
		if (gender == Message.MALE_CODE) {
			actionBar.setBackgroundDrawable(new ColorDrawable(R.color.primary_indigo_500));
		}
		if (gender == Message.FEMALE_CODE) {

		}
	}
	
}
