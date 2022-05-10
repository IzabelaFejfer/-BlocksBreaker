package projekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Graphics2D;

import javax.sound.midi.SysexMessage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import properties.BallProperties;
public class Easyframe extends JPanel implements KeyListener, ActionListener{

	private boolean start = false;
	private int posX = 300;
	private int ballX = BallProperties.getBallX();
	private int ballY = BallProperties.getBallY();
	private int ballvx = BallProperties.getBallvx();
	private int ballvy = BallProperties.getBallvy();
	private Timer timer;
	private int delay = 5;
	private Map mapa;
	private int score=0;
	private JLabel my_scorelabel;

	public Easyframe(JLabel scorelabel) {
		my_scorelabel=scorelabel;
		mapa = new Map(4,8);
		addKeyListener(this);
		setFocusable(true);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint (Graphics g) {
		
		//t�o
		
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
		
		g.setColor(BallProperties.getColor_tryb_jasny());
		g.fillOval(ballX, ballY, BallProperties.getRadious(), BallProperties.getRadious());

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
			if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(posX-3, 550, 106, 7))) {
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
						Rectangle ballRect = new Rectangle (ballX, ballY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							mapa.setBrickValue(0,i,j);
							score+=5;
							my_scorelabel.setText(""+score);

						if(ballX+1<=brickRect.x||ballX+1>= brickRect.x+brickRect.width) {
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
