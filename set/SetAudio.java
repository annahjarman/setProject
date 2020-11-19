package set;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SetAudio 
{
	private Clip audioClip;
	private boolean playing;
	
	public SetAudio(String audioFilePath)
	{
		playing = false;
		try
		{
			File file = new File(audioFilePath);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			audioClip = AudioSystem.getClip();
			audioClip.open(sound);
		} catch (UnsupportedAudioFileException ex) {
	        System.out.println("The specified audio file is not supported.");
	        ex.printStackTrace();
	    } catch (LineUnavailableException ex) {
	        System.out.println("Audio line for playing back is unavailable.");
	        ex.printStackTrace();
	    } catch (IOException ex) {
	        System.out.println("Error playing the audio file.");
	        ex.printStackTrace();
	    }
	}
	
	public void startSounds() {
		audioClip.setFramePosition(0);
		audioClip.start();
		playing = true;
	     
	}
	
	public void stopSounds() {
		audioClip.stop();
		playing = false;
	}
	
	public boolean isPlaying()
	{
		return playing;
	}

}
