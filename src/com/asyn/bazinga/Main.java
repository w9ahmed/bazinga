package com.asyn.bazinga;

import com.asyn.media.Media;
import com.asyn.media.PlayMedia;
import com.asyn.tools.ShakeListener;
import com.google.android.gms.ads.*;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Main extends Activity {
	
	Button bazBu;	Button knPen;
	SlidingDrawer drawer;
	
	RadioButton pennyNormal;	RadioButton pennyRobot;		RadioButton rajRadio;
	RadioButton whipRadio;		RadioButton knocksRadio;	RadioButton leoRadio;
	RadioButton shelCoRadio; 	RadioButton batCoRadio;

	PlayMedia playMedia;
	
	Handler mHandler = new Handler();
	Handler kHandler = new Handler();
	ShakeListener mShaker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mShaker = new ShakeListener(this, 3, 1500);
        bazBu = (Button) findViewById(R.id.button);
        knPen = (Button) findViewById(R.id.knock);
        
        pennyNormal = (RadioButton) findViewById(R.id.pennyRadio);
        pennyRobot = (RadioButton) findViewById(R.id.robotRadio);
        rajRadio = (RadioButton) findViewById(R.id.rajRadio);
        whipRadio = (RadioButton) findViewById(R.id.whipRadio);
        knocksRadio = (RadioButton) findViewById(R.id.knocksRadio);
        leoRadio = (RadioButton) findViewById(R.id.leonardRadio);
        drawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
        shelCoRadio = (RadioButton) findViewById(R.id.shelRadio);
        batCoRadio = (RadioButton) findViewById(R.id.batRadio);

        playMedia = new PlayMedia(this);
        
        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
        
        
        bazBu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					playMedia.setAndPlayMedia(Media.Sounds.BAZINGA);
			}
		});
        
        bazBu.setOnLongClickListener(new OnLongClickListener() {
			int duration = 0;
			@Override
			public boolean onLongClick(View v) {				
				duration = middleClickSounds();
				mShaker.pause();
				bazBu.setEnabled(false);
				knPen.setEnabled(false);
				bazBu.setBackgroundResource(Media.Images.SHELDON_SPEAKS);
				mHandler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						bazBu.setBackgroundResource(Media.Images.DEFAULT_DRAW);
						bazBu.setEnabled(true);
						knPen.setEnabled(true);
						mShaker.resume();
					}
				}, duration);
				return true;
			}
		});
        
        /* WHIP CRACK SOUND EFFECT */
        
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
        	//int counter = 1;
			@Override
			public void onShake() {
					//mShaker.setShakeOptions(3, 1500);
					playMedia.setAndPlayMedia(Media.Sounds.WHIP_CRACK);
			}
		});
        
        /* *********************** */
        
        /* KNOCK KNOCK KNOCK PENNY */
        
        knPen.setOnClickListener(new OnClickListener() {
        	int counter = 1;
			@Override
			public void onClick(View v) {
				if(counter==3) {
					playMedia.setAndPlayMedia(Media.Sounds.SINGLE_KNOCK);
					knPen.setEnabled(false);
					kHandler.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							shortClickSounds();
							playMedia.startPlayableMedia();
							counter = 1;
							knPen.setEnabled(true);
							bazBu.setBackgroundResource(Media.Images.SHELDON_SPEAKS);
						}
					}, 250);
					
					kHandler.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							bazBu.setBackgroundResource(Media.Images.DEFAULT_DRAW);
						}
					}, 500);
				}
				
				else {
					playMedia.setAndPlayMedia(Media.Sounds.SINGLE_KNOCK); // need to check this
					counter++;
				}
				
			}
		});
        
        knPen.setOnLongClickListener(new OnLongClickListener() {
			int duration = 0;
			@Override
			public boolean onLongClick(View v) {
				
				duration = longClickSounds();
				
				mShaker.pause();
				bazBu.setEnabled(false);
				knPen.setEnabled(false);
				bazBu.setBackgroundResource(Media.Images.SHELDON_SPEAKS);
				kHandler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						mShaker.resume();
						bazBu.setEnabled(true);
						knPen.setEnabled(true);
						bazBu.setBackgroundResource(Media.Images.DEFAULT_DRAW);
					}
				}, duration);
				return false;
			}
		});
        /* ****  ****  ****  ****  */
        
        drawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				knPen.setEnabled(false);
				bazBu.setEnabled(false);
			}
		});
        
        drawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				knPen.setEnabled(true);
				bazBu.setEnabled(true);					
			}
		});
        
    }
    
    public void shortClickSounds() {
    	if(pennyNormal.isChecked()) {
    		playMedia.setPlayableMedia(Media.Sounds.PENNY);
    	}
		else if(pennyRobot.isChecked()) {
			playMedia.setPlayableMedia(Media.Sounds.PENNY_ROBOT);
		}
		else if(rajRadio.isChecked()) {
			playMedia.setPlayableMedia(Media.Sounds.RAJ);
		}
		else if(leoRadio.isChecked()) {
			playMedia.setPlayableMedia(Media.Sounds.LEONARD);
		}
    } // end method shortClickSounds
    
    public int longClickSounds() {
    	int duration = 0;
    	
    	if(pennyNormal.isChecked()) {
			duration = playMedia.setAndPlayReturnFileDuration(Media.Sounds.KNOCKS_PENNY);
		}
		else if(pennyRobot.isChecked()) {
			duration = playMedia.setAndPlayReturnFileDuration(Media.Sounds.KNOCKS_ROBOT_PENNY);
		}
		else if(rajRadio.isChecked()) {
			duration = playMedia.setAndPlayReturnFileDuration(Media.Sounds.KNOCKS_RAJ);
		}
		else if(leoRadio.isChecked()) {
			duration = playMedia.setAndPlayReturnFileDuration(Media.Sounds.KNOCKS_LEONARD);
		}
    	
    	return duration;
    } // end method longClickSounds
    
    public int middleClickSounds() {
    	int duration = 0;
    	if(shelCoRadio.isChecked()) {
    		duration = playMedia.setAndPlayReturnFileDuration(Media.Sounds.IM_DR_SHELDON);
    	}
    	
    	else if(batCoRadio.isChecked()) {
    		duration = playMedia.setAndPlayReturnFileDuration(Media.Sounds.IM_BATMAN);
    	}
    	
    	return duration;
    } // end method middleLongClickSounds

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	mShaker.pause();
    	playMedia.destroy();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	mShaker.pause();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	mShaker.resume();
    }
    
}
