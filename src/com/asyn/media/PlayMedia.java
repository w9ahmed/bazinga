package com.asyn.media;

import android.content.Context;
import android.media.MediaPlayer;

public class PlayMedia {

	private MediaPlayer mediaPlayer;
	private Context context;
	
	public PlayMedia(Context setContext) {
		context = setContext;
	} // end constructor
	
	public void setPlayableMedia(int playableMedia) {
		destroy();
		mediaPlayer = MediaPlayer.create(context, playableMedia);
	} // end method setPlayableMedia
	
	public int getFileDuration() {
		return mediaPlayer.getDuration();
	} // end method getFileDuration
	
	public void startPlayableMedia() {
		mediaPlayer.start();
	} // end method startPlayableMedia
	
	public MediaPlayer playMediaFile() {
		return mediaPlayer;
	} // end method playMediaFile
	
	public void setAndPlayMedia(int playableMedia) {
		setPlayableMedia(playableMedia);
		startPlayableMedia();
	} // end method setAndPlayMedia
	
	public int setAndPlayReturnFileDuration(int playableMedia) {
		setAndPlayMedia(playableMedia);
		return getFileDuration();
	} // end method setAndPlayReturnFileDuration
	
	public void destroy() {
		if(mediaPlayer!=null)
			mediaPlayer.release();
	} // end method destroy
	
} // end class PlayMedia
