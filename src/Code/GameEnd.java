package Code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import helper.GameManager;

public class GameEnd extends JPanel{
	
	private Image backgroundImage;
	private ImageIcon playAgain;
	private ImageIcon mainMainu;
	private ImageIcon exit;
	private int a;
	private int finalScore;
	private String out="";
	
	GameEnd(int a,int b){
		if (a == 1) {
		    if (b== -1) {out= "Draw!";}
		    else if (b== 1) {out= "Player 1 Wins!";}
		    else if (b== 2) {out = "Player 2 Wins!";
		}}
		
		this.finalScore=b;
		this.a=a;
		setPreferredSize(new Dimension(450,700));
		setLayout(null);
		
		//loading images
		String bgLink=new String("resources/endpage/finalBG.png");
		backgroundImage=new ImageIcon(bgLink).getImage();
		String linkPlayAgain = new String("resources/endpage/playAgain.png");
		ImageIcon playAgainIcon = new ImageIcon(linkPlayAgain);
		String linkMainMenu=new String("resources/endpage/mainMenu.png");
		ImageIcon mainMenuIcon=new ImageIcon(linkMainMenu);
		String linkExit=new String("resources/endpage/exit.png");
		ImageIcon exitIcon=new ImageIcon(linkExit);
		
		// creating the needed buttons
		JButton playAgainButton=new JButton(playAgainIcon);
		playAgainButton.setBounds(165,360,240,240);
		playAgainButton.setContentAreaFilled(false);
		playAgainButton.setBorderPainted(false);
		playAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent h) {
				GameManager.switchTo(new Window(0));
			}
		});
		
		JButton mainMenuButton = new JButton(mainMenuIcon);
		mainMenuButton.setBounds(165,160,240,240);
		mainMenuButton.setContentAreaFilled(false);
		mainMenuButton.setBorderPainted(false);
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent g) {
				 GameManager.switchTo(new MainWindow());
			}
		});
		if(a==0) {
	        add(playAgainButton);
		}
	        add(mainMenuButton);
	      
	}
	        public void paintComponent(Graphics g) {
	        	super.paintComponent(g);
	        	g.drawImage(backgroundImage,0,0,550,700,this);
	        	 g.setColor(Color.WHITE);
	        	    g.setFont(new Font("Arial", Font.BOLD, 42));
	        	    if(a==0) {
	        	    	g.drawString("POINTS EARNED: " + finalScore, 85, 110);
	        	    	
	        	    }else {
	        	    	 g.drawString(out, 150, 110);}
	        	
	        }
	}
	


