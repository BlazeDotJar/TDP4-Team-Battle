package me.xxfreakdevxx.de.game.audio;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class GameAudio {
	/*
	 * Accepted audio file formats:
	 * - .wav (16Bit max!)
	 * - .mid
	 * - .au
	 */
	private String name = "";
	private String filePath = "";
//	private Sound audio;
	private Clip audioClip;
	private float volume = 0.05f;
	
	public GameAudio(String name, String filePath) {
		this.name=name;
		this.filePath=filePath;
		if(!filePath.equalsIgnoreCase("none")) loadAudioFile();
	}
	
	public void loadAudioFile() {
//		audio = new Sound(filePath);
		
		try{
			URL defaultSound = this.getClass().getResource(filePath);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(defaultSound);
			audioClip = AudioSystem.getClip();
			audioClip.open(audioInputStream);
			setVolume(volume);
		} catch (Exception ex) {ex.printStackTrace();}
	}
	
	public boolean play() {
//		if(audio == null) return false;
		audioClip.loop(9999);
		return true;
	}
	public boolean stop() {
//		if(audio == null) return false;
		audioClip.stop();
		return true;
	}
	public boolean playOnce() {
//		if(audio == null) return false;
		audioClip.start();
		return true;
	}
	public void setVolume(float volume) {
	    if (volume < 0f || volume > 1f)
	        throw new IllegalArgumentException("Volume not valid: " + volume);
	    FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}

	public GameAudio getClone() {
		GameAudio audio = new GameAudio(this.name, this.filePath);
		audio.setVolume(this.volume);
		return audio;
	}
	public String getName() {
		return name;
	}
}
