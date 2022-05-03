package projekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Hardframe extends JPanel implements KeyListener, ActionListener{

	private boolean start = false;
	Random r = new Random();
	private int posX = 300;
	private int ballX = r.nextInt(770);
	private int ballY = 300;
	private int ballvx = -3;
	private int ballvy = 3;
	private Timer timer;
	private int delay = 8;
	private Map mapa;
	private int score=0;
	
	public Hardframe() {
		
		mapa = new Map(5,8);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	

	
	public void paint (Graphics g) {
		//t³o
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, 800, 600);
		
		//mapka
		
		
		mapa.draw((Graphics2D)g);
		
		//kontury
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 3, 600);
		g.fillRect(0, 0, 770, 3);
		g.fillRect(770, 0, 3, 600);
		
		//pilka
		g.setColor(Color.BLUE);
		g.fillOval(ballX, ballY, 15, 15);
		
		//platforma
		g.setColor(Color.RED);
		g.fillRect(posX, 550, 70, 7);
		
		g.dispose();
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(posX >= 700) {
			 posX = 700;
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
			if(new Rectangle(ballX, ballY, 15, 15).intersects(new Rectangle(posX, 550, 70, 7))) {
				ballvy = -ballvy;
			}
			A: for(int i=0; i<mapa.map.length;i++) {
				for(int j=0; j<mapa.map[0].length;j++ ) {
					if(mapa.map[i][j]>0) {
						int blockX = j*mapa.width+mapa.width;
						int blockY = i*mapa.height+mapa.height;
						int width = mapa.width;
						int height = mapa.height; 
						
						Rectangle rect = new Rectangle(blockX, blockY, width, height);
						Rectangle ballRect = new Rectangle (ballX, ballY, 15, 15);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							mapa.setBrickValue(0,i,j);
							score+=5;
							
						if(ballX+mapa.width<=brickRect.x||ballX+1>= brickRect.x+brickRect.width) {
							ballvx = -ballvx;
						}else {
							ballvy = -ballvy;
						}
							break A;
						}
					}
				}
			}
		}
		
		repaint();
		
	}

}
