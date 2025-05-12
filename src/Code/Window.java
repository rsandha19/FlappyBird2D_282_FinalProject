package Code;


	import java.awt.event.*;
	import java.awt.*;
	import java.util.*;
	import java.io.*;
	import javax.sound.sampled.AudioInputStream;
	import javax.sound.sampled.AudioSystem;
	import javax.sound.sampled.Clip;
	import javax.swing.*;
	import helper.GameManager;
	
	 // a pipe class- this will hold properties belonging to each of the pipes
    class Pipe{
    	// 460 is the decided positions for appearance of pipes
    	boolean multi;
    	int pipeX=460;
    	int pipeTopY;
    	int pipeBottomY;
    	int pipeTopHeight;
    	int pipeBottomHeight;
    	int pipeWidth=60;
    	Image topPipe;
    	Image bottomPipe;
    	boolean passed=false;
    	// we only take images and the location of the gap in the pipes as an input.
    	// then we use the num to calculate other attributes
    	public Pipe( Image top,Image bottom,int num) {
    		this.bottomPipe=bottom;
    		this.topPipe=top;
    		pipeTopY=0;
    		pipeTopHeight=num-70;
    		pipeBottomY=pipeTopHeight+140;
    		pipeBottomHeight=700-pipeBottomY;
    	}
    }
	public class Window extends JPanel implements ActionListener,KeyListener {
		
		
		// creating variables 
		// first for the game window
		// images
		private Image backGround;
		private Image bird;
		private Image topPipe;
		private Image bottomPipe;
		private Image bird1;
		private Image bird2;
		private Image bird3;
		
		
		public boolean isDead = false;
		public boolean isMultiplayerPlayer1=false;
		boolean multiplayer=false;
		
		
		private int initialPoints;
		private int score=0;
		private Font scoreFont=new Font("Arial",Font.BOLD,36);
		private Color scoreColor=Color.white;
		
		
		private int panelHeight=700;
		private int panelWidth=550;
		
		
		// variables for the bird
		private int birdWidth=55;
		private int birdHeight=55;
		private int birdX=panelWidth/5;
		private int birdY=panelHeight/3;
		private double birdHead;
		private double birdFoot;
	// for movement
		private int verticalVel=-5;
		private int gravity=7;
		private int jump=-50;
		private int center=300;
		private int velocityX=-4;
		
		
		    
	// loops
		private javax.swing.Timer gameLoop;
		private javax.swing.Timer pipeTimer;
		    
	    private Equipped eq=new Equipped();
		private Sound sounds=new Sound();
		private Points points=new Points();
		    
		    
		   
	// to contain all our pipes
        private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
		   
		    
		
		/*constructor
		 * code that will be run when we create an instance using run the the main application
		 * class file.
		 * 
		 */
		    
	      Window(int i) {if(i==1) {
    		  multiplayer=true;
    	  }
	    	  // setting the points 
	      
    	  initialPoints=points.getPoints();
	    	  
    	 
	  		//JFrame things
	    	 
			setPreferredSize(new Dimension(panelWidth,panelHeight));
			setFocusable(true);
			requestFocusInWindow();
			addKeyListener(this);
			
			// creating links and importing image objects.
			String backGroundLink=new String("resources/images/finalBG.png");
			String birdLink=new String("resources/images/birdFP.png");
			String topPipeLink=new String("resources/images/Top_pipe.png");
			String bottomPipeLink = new String("resources/images/bottom_pipe.png");
			
			 // loading images 
	    	  
	    	  String bird1Link=new String("resources/images/bird1.png");
	  		bird1=new ImageIcon(bird1Link).getImage();
	  		String bird2Link=new String("resources/images/bird2.png");
	  		bird2=new ImageIcon(bird2Link).getImage();
	  		String bird3Link=new String("resources/images/bird3.png");
	  		bird3=new ImageIcon(bird3Link).getImage();
			topPipe=new ImageIcon(topPipeLink).getImage();
			bottomPipe=new ImageIcon(bottomPipeLink).getImage();
			backGround=new ImageIcon(backGroundLink).getImage();
			
			// loading equipped skin
			
			if(eq.getEquippedIndex()==0) {
				bird = new ImageIcon(birdLink).getImage();
			}else if(eq.getEquippedIndex()==1){
				bird=bird1;
			}else if(eq.getEquippedIndex()==2) {
				bird=bird2;
			}else {
				bird=bird3;
			}
			
			
			// creating the timer loops and starting them.
			gameLoop=new javax.swing.Timer(1000/60,this);
			gameLoop.start();
			pipeTimer=new javax.swing.Timer(1500,new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					placePipes();
					}});
			pipeTimer.setInitialDelay(2000);
			pipeTimer.start();
			
		}
	      public int getScore() {
	    	  return score;
	      }
	      public void placePipes() {

	    	  /* use random to generate the random location of the gap in the pipes. 
	    	   * when using only random and a given range sometimes the gaps were too far away
	    	   * from one another, so modified that it does not move too far from the previous 
	    	   * location while still maintaining the end points of our range.
	    	   */
	    	  
	    	  Random rand = new Random();
	    	    int range = 100;
	    	    int minLimit = 140;
	    	    int maxLimit = 560;

	    	    int min = Math.max(center - range, minLimit);
	    	    int max = Math.min(center + range, maxLimit);

	    	    int gapLoc = rand.nextInt(max - min + 1) + min;
	    	    center = gapLoc;
	    	    // creating pipes and adding it to list
	    	    Pipe pipe = new Pipe(topPipe, bottomPipe, gapLoc);
	    	    pipes.add(pipe);
	    	}
	// this is the thing that draws visuals
	      // each draw image statement is used to drow an object
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// background
			g.drawImage(backGround,0,0, panelWidth, panelHeight,this);
			//bird
			g.drawImage(bird,birdX,birdY-birdHeight/2,birdWidth,birdHeight,this);
			// pipes
			for(int i=0;i<pipes.size();i++) { if(pipes.get(i).pipeX+pipes.get(i).pipeWidth>0){
				g.drawImage(pipes.get(i).bottomPipe,pipes.get(i).pipeX,pipes.get(i).pipeBottomY,pipes.get(i).pipeWidth,pipes.get(i).pipeBottomHeight,this);
				g.drawImage(pipes.get(i).topPipe,pipes.get(i).pipeX,pipes.get(i).pipeTopY,pipes.get(i).pipeWidth,pipes.get(i).pipeTopHeight,this);
				
				}}
			// score
			g.setFont(scoreFont);
			g.setColor(scoreColor);
			g.drawString("SCORE : "+score,20,50);
			}
		
		// the action performed by the gameloop timer
		public void actionPerformed(ActionEvent e) {
			move();
			repaint();
			if(isDead) return;
			
		}
		
		public void restartGameLoop() {
		    if (gameLoop != null) gameLoop.start();
		    if (pipeTimer != null) pipeTimer.start();
		}
		public void disableGame() {
			gameLoop.stop();
			pipeTimer.stop();
		}
		

		public void move() {
			
			// changing the required co-ordinates 
			birdY=birdY+verticalVel;
			birdY=birdY+gravity;
			birdY=Math.max(birdY,0);
			birdY=Math.min(birdY,645);
			birdHead=birdY-(birdHeight / 2);
			birdFoot=birdY+(birdHeight / 2);
			
			
			// changing location of each pipe and removing them from array when not needed
			for(int i=pipes.size()-1;i>=0;i--) {
				Pipe p =pipes.get(i);
				p.pipeX+=velocityX;
				if (!p.passed && birdX > p.pipeX + p.pipeWidth) {
				    p.passed = true;
				    score++;
				}
				if(p.pipeX+p.pipeWidth<0) {
					pipes.remove(i);
				}
				// checking for collision here with movement
				int marginX = 6;
				int marginY = 6;
				boolean withinPipeX = (birdX + marginX) + (birdWidth - 2 * marginX) > p.pipeX
				                    && (birdX + marginX) < (p.pipeX + p.pipeWidth);
                boolean hitTopPipe = (birdHead + marginY) < p.pipeTopHeight;
				boolean hitBottomPipe = (birdFoot - marginY) > p.pipeBottomY;

				if (withinPipeX && (hitTopPipe || hitBottomPipe)) {
				    gameOver();
				    return;
				}
			}
			}
			
		public void gameOver() {
		disableGame();
		sounds.playCollision();
		isDead=true;
		if(!multiplayer) {
		GameManager.switchTo(new GameEnd(0,score));
		initialPoints=initialPoints+score;
		points.setPoints(initialPoints);
		score=0;
		GameManager.f.setSize(550,700);}
	    }
		public void jumpAction() {
				birdY=birdY+jump;
				sounds.playJumpSound();
		}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				jumpAction();
			}
		}
		public void keyTyped(KeyEvent e) {};
		public void keyReleased(KeyEvent e) {};

	}


