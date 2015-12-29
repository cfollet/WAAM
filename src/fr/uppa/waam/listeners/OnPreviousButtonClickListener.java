package fr.uppa.waam.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fr.uppa.waam.views.WallActivity;

public class OnPreviousButtonClickListener implements OnClickListener {
	
	WallActivity activity;
	Button previousButton;

	public OnPreviousButtonClickListener(WallActivity activity, Button previousButton) {
		this.activity = activity;
		this.previousButton = previousButton;
		this.previousButton.setEnabled(this.activity.isPreviousButtonEnabled());
	}

	@Override
	public void onClick(View v) {
		this.activity.decrementPage();
        this.activity.populate(this.activity.getMessages());
	}

}
