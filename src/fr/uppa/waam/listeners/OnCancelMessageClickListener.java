package fr.uppa.waam.listeners;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class OnCancelMessageClickListener implements OnClickListener {

	@Override
	public void onClick(DialogInterface dialog, int which) {
		dialog.cancel();
	}

}
