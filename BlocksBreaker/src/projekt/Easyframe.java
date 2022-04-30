package projekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Easyframe extends JPanel implements KeyListener, ActionListener{

	private boolean start = false;
	private int blocks = 20;
	private int posX = 300;
	private int ballX = 300;
	private int ballY = 300;
	private int ballvx = -1;
	private int ballvy = -2;
	private Timer timer;
	private int a = 8;
	
	public Easyframe() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(a, this);
		timer.start();
	}
	
	public void paint (Graphics g) {
		//t³o
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, 800, 600);
		
		//kontury
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 3, 600);
		g.fillRect(0, 0, 770, 3);
		g.fillRect(770, 0, 3, 600);
		
		//pilka
		g.setColor(Color.BLUE);
		g.fillOval(ballX, ballY, 20, 20);
		
		//platforma
		g.setColor(Color.RED);
		g.fillRect(posX, 550, 100, 7);
		
		g.dispose();
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(posX >= 670) {
			 posX = 670;
			} else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(posX <= 3) {
				 posX = 3;
				} else {
					moveLeft();
				}
		}
	}
	
	public void moveRight() {
		start = true;
		posX += 20;
	}
	
	public void moveLeft() {
		start = true;
		posX -= 20;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(start) {
			ballX += ballvx;
			ballY += ballvy;
			if(ballX<0) {
				ballvx = -ballvx;	
			}
			if(ballY<0) {
				ballvy = -ballvy;	
			}
			if(ballX>750) {
				ballvx = -ballvx;	
			}
			if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(posX, 550, 100, 7))) {
				ballvy = -ballvy;
			}
		}
		
		repaint();
		
	}

}
