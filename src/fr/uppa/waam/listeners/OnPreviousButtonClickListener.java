package fr.uppa.waam.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import fr.uppa.waam.views.WallActivity;

public class OnPreviousButtonClickListener implements OnClickListener {
	
	WallActivity activity;

	public OnPreviousButtonClickListener(WallActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		this.activity.decrementPage();
        this.activity.populate(this.activity.getMessages());
	}

}
