package fr.uppa.waam.listeners;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import fr.uppa.waam.models.MessagesManager;
import fr.uppa.waam.views.WallActivity;

public class OnSendMessageClickListener implements OnClickListener {
	
	EditText input;
	WallActivity activity;
	

	public OnSendMessageClickListener(EditText input, WallActivity activity) {
		super();
		this.input = input;
		this.activity = activity;
	}



	@Override
	public void onClick(DialogInterface dialog, int which) {
		MessagesManager messageManager = new MessagesManager(this.activity, this.input);
		messageManager.sendMessage();
	}

}
