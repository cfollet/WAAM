package fr.uppa.waam.listeners;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import fr.uppa.waam.models.GeoLocation;
import fr.uppa.waam.models.Message;
import fr.uppa.waam.tasks.RetrieveMessages;
import fr.uppa.waam.tasks.SendMessage;
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
		// create a message and ask the async task to send it
		// Caution : if no GPS/location are known ! What to do ?
		String content = this.input.getText().toString();
		//GeoLocation location = 
		//Message m = new Message(, , new Date(), gender)
		//new SendMessage(this.activity).execute();
	}

}
