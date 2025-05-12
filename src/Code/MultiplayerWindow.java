package Code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import helper.GameManager;

public class MultiplayerWindow extends JPanel implements KeyListener{
	private Window player1;
	private Window player2;
	private javax.swing.Timer deathChecker;
	public MultiplayerWindow() {
		
		setPreferredSize(new Dimension(900,700));
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		
		
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(10,700));
		spacer.setBackground(Color.black);
		
        player1 = new Window(1);
		player2 = new Window(1);
		player1.restartGameLoop();
		player2.restartGameLoop();
		player1.removeKeyListener(player1);
		player2.removeKeyListener(player2);
		add(player1);
		add(spacer);
		
		add(player2);
		player1.isMultiplayerPlayer1 = true;

		deathChecker = new javax.swing.Timer(1000, e -> {
		    if (player1.isDead && player2.isDead) {
		    	int winner;
		    	if (player1.getScore() > player2.getScore()) winner = 1;
		    	else if (player2.getScore() > player1.getScore()) winner = 2;
		    	else winner = -1;
		    	GameManager.switchTo(new GameEnd(1, winner));

		        GameManager.f.setSize(550, 700);
		        deathChecker.stop();
		    }
		});
		deathChecker.start();

		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
	}
		 public void keyPressed(KeyEvent e) {
		      
		        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		            player1.jumpAction(); 
		        }
		        else if (e.getKeyCode() == KeyEvent.VK_UP) {
		            player2.jumpAction();
		        }
		    }
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
	}


