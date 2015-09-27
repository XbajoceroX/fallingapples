package fallingApples;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {
	
	Clip clip;
	
	public Sonido(){
		
	}
	
	private void playSoundLoop(String path){
		try {
		       AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
		       clip = AudioSystem.getClip();
		       clip.open(audioInputStream);
		       clip.start();
		       clip.loop(Clip.LOOP_CONTINUOUSLY);
		   } catch(Exception ex) {
			   System.out.println("Error en la reproduccion del sonido.");
		       ex.printStackTrace();
		   }
	}
	private void stop()
	{
		clip.stop();
	}
	
	private void playSound(String path){
		try {
				
		       AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
		       Clip clip2 = AudioSystem.getClip();
		       clip2.open(audioInputStream);
		       clip2.start();
		   } catch(Exception ex) {
			   System.out.println("Error en la reproduccion del sonido.");
		       ex.printStackTrace();
		   }
	}
	
	
	public void playBackgroundSound()
	{
		playSoundLoop("sonidos/backgroundSound.wav");
	}
	public void stopBackgroundSound()
	{
		stop();
	}
	
	public void playWinnerSound()
	{
		playSound("sonidos/victoriaSound.wav");
	}
	
	public void playDerrotaSound()
	{
		playSound("sonidos/derrotaSound.wav");
	}
	
	public void playManzanaPodridaAgarradaSound()
	{
		playSound("sonidos/manzanPodridaSound.wav");
	}
	
	public void playManzanaRecogidaSound()
	{
		playSound("sonidos/manzanRecogidaSound.wav");
	}
	
}
