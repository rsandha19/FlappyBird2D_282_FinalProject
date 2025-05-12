package Code;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import helper.GameManager;
public class MainWindow extends JPanel {
	
	Image background;
	ImageIcon shopButtonIcon;
	ImageIcon startButtonIcon;
	ImageIcon multiplayerButtonIcon;
	Image logo;
	int OPTION_CHOSEN=0;
	
	
	MainWindow() {
		setPreferredSize(new Dimension(550,700));
		setLayout(null);
		
		
		String linkBG=new String("resources/Main_menu/finalBG.png");
		String linkStart = new String("resources/Main_menu/start.png");
		String linkShop = new String("resources/Main_menu/shop.png");
		String linkMultiplayer = new String("resources/Main_menu/multiplayer.png");
		 String linkLogo=new String("resources/Main_menu/welcome.png");
		
		background = new ImageIcon(linkBG).getImage();
		 shopButtonIcon = new ImageIcon(linkShop);
		 startButtonIcon = new ImageIcon(linkStart);
		 multiplayerButtonIcon = new ImageIcon(linkMultiplayer);
		 logo=new ImageIcon(linkLogo).getImage();
		
		JButton shopButton = new JButton(shopButtonIcon);
		shopButton.setBounds(185,200,180,180);
		shopButton.setBorderPainted(false);
		shopButton.setContentAreaFilled(false);
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				GameManager.switchTo(new ShopWindow());
			}
		});
		
		JButton startButton = new JButton(startButtonIcon);
		startButton.setBounds(165,355,240,160);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent b) {
				 GameManager.switchTo(new Window(0));
			}
		});
		
		
		JButton multiplayerButton = new JButton(multiplayerButtonIcon);
		multiplayerButton.setBounds(75,530,400,100);
		multiplayerButton.setContentAreaFilled(false);
		multiplayerButton.setBorderPainted(false);
		
		multiplayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				GameManager.f.setSize(900,700);
				GameManager.switchTo(new MultiplayerWindow());
			}
		});
		
		add(shopButton);
		add(startButton);
		add(multiplayerButton);	
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background,0,0,550,700,this);
		g.drawImage(logo,125,30,300,200,this);
	}
	public int getOption() {
		return OPTION_CHOSEN;
	}
	
	

}

