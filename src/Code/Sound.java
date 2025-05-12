package Code;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	private Clip jumpClip;
	private Clip collisionClip;
	public Sound() {
		  loadJumpSound("resources/sounds/bird_jump.wav");
		    loadCollisionSound("resources/sounds/collision_audio.wav");
		
	}
	private void loadJumpSound(String path) {
		try {
			
			File jumpAudio = new File(path);
			AudioInputStream audioIn=AudioSystem.getAudioInputStream(jumpAudio);
			jumpClip=AudioSystem.getClip();
			jumpClip.open(audioIn);
			
			
		}catch(Exception a) {
			a.printStackTrace();
		}
	}
	private void loadCollisionSound(String collisionLink) {
		try {
			
			File collisionAudio=new File(collisionLink);
			AudioInputStream collisionAudioIn=AudioSystem.getAudioInputStream(collisionAudio);
			collisionClip=AudioSystem.getClip();
			collisionClip.open(collisionAudioIn);
		}catch(Exception b) {
			b.printStackTrace();
		}
	}
	public void playJumpSound() {
		if (jumpClip != null) {
	        if (jumpClip.isRunning()) jumpClip.stop();         
	        jumpClip.setFramePosition(0);                      
	        jumpClip.start();                                  
	    }
	}
	public void playCollision() {
	    if (collisionClip != null) {
	        if (collisionClip.isRunning()) collisionClip.stop(); 
	        collisionClip.setFramePosition(0); 
	        collisionClip.start();
	    }
	}

	

}
