package com.asyn.tools;

import android.os.Handler;
import android.widget.Button;

public class ButtonEffects {
	
	private ButtonEffects() {		
	} // private constructor 		
	
	public static void buttonSwitch(final Button button, int changeDrawbaleTo, final int defaultDrawable, int mediaDuration) {
		button.setBackgroundResource(changeDrawbaleTo);
		button.setEnabled(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				button.setBackgroundResource(defaultDrawable);
				button.setEnabled(true);
			}
		}, mediaDuration);
	} // end method switch buttons

	
} // end class ButtonEffects
