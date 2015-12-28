package fr.uppa.waam.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import fr.uppa.waam.views.WallActivity;

public class OnNextButtonClickListener implements OnClickListener {
	
	WallActivity activity;
	
	

	public OnNextButtonClickListener(WallActivity activity) {
		super();
		this.activity = activity;
	}



	@Override
	public void onClick(View v) {
        this.activity.incrementPage();
        this.activity.populate(this.activity.getMessages());
	}

}
