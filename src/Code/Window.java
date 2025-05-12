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
	public class Window extends JPanel implements ActionListener,KeyListener {
		// creating variables 
		// first for the game window
		Image bird1;
		Image bird2;
		Image bird3;
		Equipped eq=new Equipped();
		public boolean isDead = false;
		public boolean isMultiplayerPlayer1=false;
		public void disableGame() {
			gameLoop.stop();
			pipeTimer.stop();
		}
		
		int initialPoints;
		boolean multiplayer=false;
		int panelHeight=700;
		int panelWidth=550;
		int score=0;
		Font scoreFont=new Font("Arial",Font.BOLD,36);
		Color scoreColor=Color.white;
		// variables for the bird
		int birdWidth=55;
		int birdHeight=55;
		int birdX=panelWidth/5;
		int birdY=panelHeight/3;
		double birdHead;
		double birdFoot;
	// for movement
		int verticalVel=-5;
		int gravity=7;
		int jump=-50;
		int center=300;
		int velocityX=-4;
		
		// images
		 Image backGround;
		    Image bird;
		    Image topPipe;
		    Image bottomPipe;
		    
		    
		    // loops
		    javax.swing.Timer gameLoop;
		    javax.swing.Timer pipeTimer;
		    Sound sounds=new Sound();
		    Points points=new Points();
		    
		    
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
		    // to contain all our pipes
		    ArrayList<Pipe> pipes = new ArrayList<Pipe>();
		   
		    
		
		/*constructor
		 * code that will be run when we create an instance using run the the main application
		 * class file.
		 * 
		 */
		    
	      Window(int i) {
	    	  String bird1Link=new String("resources/images/bird1.png");
	  		bird1=new ImageIcon(bird1Link).getImage();
	  		String bird2Link=new String("resources/images/bird2.png");
	  		bird2=new ImageIcon(bird2Link).getImage();
	  		String bird3Link=new String("resources/images/bird3.png");
	  		bird3=new ImageIcon(bird3Link).getImage();
	    	  if(i==1) {
	    		  multiplayer=true;
	    	  }
	    	  initialPoints=points.getPoints();
			setPreferredSize(new Dimension(panelWidth,panelHeight));
			setFocusable(true);
			requestFocusInWindow();
			addKeyListener(this);
			
			// creating links and importing image objects.
			String backGroundLink=new String("resources/images/finalBG.png");
			String birdLink=new String("resources/images/birdFP.png");
			String topPipeLink=new String("resources/images/Top_pipe.png");
			String bottomPipeLink = new String("resources/images/bottom_pipe.png");
			
			
			topPipe=new ImageIcon(topPipeLink).getImage();
			bottomPipe=new ImageIcon(bottomPipeLink).getImage();
			backGround=new ImageIcon(backGroundLink).getImage();
			
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
	      // each drawimage statement is used to drow an object
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backGround,0,0, panelWidth, panelHeight,this);
			g.drawImage(bird,birdX,birdY-birdHeight/2,birdWidth,birdHeight,this);
			for(int i=0;i<pipes.size();i++) { if(pipes.get(i).pipeX+pipes.get(i).pipeWidth>0){
				g.drawImage(pipes.get(i).bottomPipe,pipes.get(i).pipeX,pipes.get(i).pipeBottomY,pipes.get(i).pipeWidth,pipes.get(i).pipeBottomHeight,this);
				g.drawImage(pipes.get(i).topPipe,pipes.get(i).pipeX,pipes.get(i).pipeTopY,pipes.get(i).pipeWidth,pipes.get(i).pipeTopHeight,this);
				g.setFont(scoreFont);
				g.setColor(scoreColor);
				
				g.drawString("SCORE : "+score,20,50);
				}}}
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


