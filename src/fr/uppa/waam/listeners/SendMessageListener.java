package fr.uppa.waam.listeners;

import java.util.Date;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import fr.uppa.waam.models.GeoLocation;
import fr.uppa.waam.models.Message;
import fr.uppa.waam.tasks.SendMessageTask;
import fr.uppa.waam.views.WallActivity;

public class SendMessageListener implements OnClickListener {
	
	EditText input;
	WallActivity activity;
	

	public SendMessageListener(EditText input, WallActivity activity) {
		super();
		this.input = input;
		this.activity = activity;
	}



	@Override
	public void onClick(DialogInterface dialog, int which) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
		
		double latitude = preferences.getFloat(GeoLocation.JSON_TAG_LATITUDE, 0);
		double longitude = preferences.getFloat(GeoLocation.JSON_TAG_LONGITUDE, 0);
		String gender = preferences.getString("gender_preference","");
		
		Log.i("test", "Location : " + String.valueOf(latitude)+", "+String.valueOf(longitude));
		Log.i("test", "Gender : " + gender);
		
		// If the shared preferences can be retrieved and are correct
		if(longitude+latitude != 0 && !gender.isEmpty()){
			// If the message length is not < 3
			if (this.input.getText().toString().length() >= 3) {
				GeoLocation location = new GeoLocation(latitude, longitude, 0, 0);
				Message message = new Message(this.input.getText().toString(), location, new Date(), Integer.parseInt(gender));
				new SendMessageTask(this.activity).execute(message);
			} else {
				Toast.makeText(this.activity.getApplicationContext(), "Le message doit faire au moins 3 carractères.", Toast.LENGTH_LONG).show();
			}
			
		}else{
			Toast.makeText(this.activity.getApplicationContext(), "Verifier le GPS et vos préférences.", Toast.LENGTH_LONG).show();
		}
	}

}
