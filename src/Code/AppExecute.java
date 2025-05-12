package Code;
import javax.swing.*;

import helper.GameManager;
public class AppExecute {
	public static void main(String[] args) {
		int height = 700;
		int width = 550;
		String title="Flappy Bird : By Rohitpreet Singh.";
		JFrame baseFrame=new JFrame(title);
		baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		baseFrame.setSize(width,height);
		baseFrame.setResizable(false);
		
		baseFrame.setLocationRelativeTo(null);
		GameManager.f = baseFrame;
		GameManager.switchTo(new MainWindow());
			
			

		baseFrame.pack();
		baseFrame.setVisible(true);
	}
	
	

} 
