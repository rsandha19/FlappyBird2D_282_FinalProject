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
import javax.swing.JPanel;

import helper.GameManager;

public class ShopWindow extends JPanel{
	private int balance;
	private Image background;
	private Image shopLogo;
	private Image equipped;
	private Image bird1;
	private Image bird2;
	private Image bird3;
	private ImageIcon backButtonIcon;
	private ImageIcon buyLogo;
	private ImageIcon equipLogo;
	
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton brn4;
	
	private boolean bird1Own;
	private boolean bird2Own;
	private boolean bird3Own;
	private int costBird1=500;
	private int costBird2=2000;
	private int costBird3=10000;
	private Skins skins=new Skins();
	private Points points = new Points();
	private Equipped eq = new Equipped();
	private int EquippedIndex;
	public ShopWindow(){
		
		balance=points.getPoints();
		setPreferredSize(new Dimension(550,700));
		setLayout(null);
		String linkBG= new String("resources/images/shopBG.png");
		background = new ImageIcon(linkBG).getImage();
		
		String birdLink = new String("resources/images/birdFP.png");
		equipped=new ImageIcon(birdLink).getImage();
		String bird1Link=new String("resources/images/bird1.png");
		bird1=new ImageIcon(bird1Link).getImage();
		String bird2Link=new String("resources/images/bird2.png");
		bird2=new ImageIcon(bird2Link).getImage();
		String bird3Link=new String("resources/images/bird3.png");
		bird3=new ImageIcon(bird3Link).getImage();
		shopLogo=new ImageIcon(new String("resources/images/shopLogo.png")).getImage();
		buyLogo=new ImageIcon(new String("resources/images/buy.png"));
		String backButtonLink=new String("resources/images/back.png");
		
		JButton buyButton2=new JButton(buyLogo);
		buyButton2.setContentAreaFilled(false);
		buyButton2.setBorderPainted(false);
		buyButton2.setBounds(240,280,140,60);
		buyButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				skins.buyBird1();
				GameManager.switchTo(new MainWindow());
			}
		});
		
		
		
		
		JButton buyButton3=new JButton(buyLogo);
		buyButton3.setContentAreaFilled(false);
		buyButton3.setBorderPainted(false);
		buyButton3.setBounds(240,410,140,60);
		buyButton3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent h) {
				skins.buyBird2();
				GameManager.switchTo(new MainWindow());
			}
		});
		
		JButton buyButton4=new JButton(buyLogo);
		buyButton4.setContentAreaFilled(false);
		buyButton4.setBorderPainted(false);
		buyButton4.setBounds(240,540,140,60);
		buyButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent d) {
				skins.buyBird3();
				GameManager.switchTo(new MainWindow());
			}
		});
		
		equipLogo=new ImageIcon(new String("resources/images/equip.png"));
		
		JButton equipButton=new JButton(equipLogo);
		equipButton.setContentAreaFilled(false);
		equipButton.setBorderPainted(false);
		equipButton.setBounds(240,150,200,80);
		equipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				eq.equipSkin(0);
				GameManager.switchTo(new MainWindow());
				
			}
		});
		
		
		
		

		JButton equipButton2=new JButton(equipLogo);
		equipButton2.setContentAreaFilled(false);
		equipButton2.setBorderPainted(false);
		equipButton2.setBounds(240,280,200,80);
		equipButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				eq.equipSkin(1);
				GameManager.switchTo(new MainWindow());
				
			}
		});
		
		
		

		JButton equipButton3=new JButton(equipLogo);
		equipButton3.setContentAreaFilled(false);
		equipButton3.setBorderPainted(false);
		equipButton3.setBounds(240,410,200,80);
		equipButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				eq.equipSkin(2);
				GameManager.switchTo(new MainWindow());
				
			}
		});
		
		
		JButton equipButton4=new JButton(equipLogo);
		equipButton4.setContentAreaFilled(false);
		equipButton4.setBorderPainted(false);
		equipButton4.setBounds(240,540,200,80);
		equipButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				eq.equipSkin(3);
				GameManager.switchTo(new MainWindow());
				
			}
		});
		

		
		backButtonIcon=new ImageIcon(backButtonLink);
		JButton backButton = new JButton(backButtonIcon);
		backButton.setBounds(5,5,100,100);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				GameManager.switchTo(new MainWindow());
			}
		});
		
		add(backButton);
		if(skins.bird1Status()==0) {
		add(buyButton2);
		}else {
			add(equipButton2);
		}
		if(skins.bird2Status()==0) {
			add(buyButton3);

		}else {
			add(equipButton3);
		}
		if(skins.bird3Status()==0) {
			add(buyButton4);
		}else {
			add(equipButton4);
		}
				
		add(equipButton);
		
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background,0,0,550,700,this);
		g.drawImage(equipped,90,140,80,80,this);
		g.drawImage(bird1,90,250,100,100,this);
		g.drawImage(bird2,90,380,100,100,this);
		g.drawImage(bird3,90,510,100,100,this);
		g.drawImage(shopLogo,50,-120,500,400,this);
		 g.setColor(Color.WHITE);
 	    g.setFont(new Font("Arial", Font.BOLD, 20));
 	    g.drawString("COST : " +costBird1, 80, 360);
 	   g.drawString("COST : " +costBird2, 80, 490);
 	  g.drawString("COST : " +costBird3, 80, 620);
 	 g.setFont(new Font("Arial", Font.BOLD, 36));
 	 g.drawString("Balance : " +balance, 150, 650);
		
	}
	
	

}
