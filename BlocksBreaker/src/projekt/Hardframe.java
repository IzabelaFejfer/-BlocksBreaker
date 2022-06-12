package projekt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;


public class Hardframe extends JPanel implements KeyListener, ActionListener{

	private boolean start = false;
	Random r = new Random();
	private int posX = 300;
	private int ballX = r.nextInt(760)+5;
	private int ballY = 300;
	private int ballvx = -3;
	private int ballvy = 4;
	private Timer timer;
	private int delay = 3;
	private Map mapa;
	private int score=0;
	int a=5;
	int b=8;
	String audioFilePath = "";
	Clip audioClip = null;
	File audioFile = null;
	AudioInputStream audioStream = null;
	 boolean playCompleted = false;
	 int d;
	private JLabel my_scorelabel;

	 
	public Hardframe(int c, JLabel scorelabel) {
		
		my_scorelabel=scorelabel;
		mapa = new Map(a,b,c);
		addKeyListener(this);
		setFocusable(true);
		//setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		d=c;

	}
	

	
	public void paint (Graphics g) {
		if(d==0)
		{
		//tï¿½o
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
		
		//g.dispose();
		}
		else if(d==1)
		{
		//tï¿½o
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 800, 600);
		
		//mapka
		
		
		mapa.draw((Graphics2D)g);
		
		//kontury
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 3, 600);
		g.fillRect(0, 0, 770, 3);
		g.fillRect(770, 0, 3, 600);
		
		//pilka
		g.setColor(Color.WHITE);
		g.fillOval(ballX, ballY, 15, 15);
		
		//platforma
		g.setColor(Color.WHITE);
		g.fillRect(posX, 550, 70, 7);
		
		//g.dispose();
		}
		
		
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
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			start = true;
		}
	}
	
	public void moveRight() {
		//start = true;
		posX += 20;
	}
	
	public void moveLeft() {
		//start = true;
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
			
			
			if(ballY>this.getHeight()) {
				Font font=new Font("Helvetica", Font.BOLD, 20);
				JDialog okienko = new JDialog();
				JPanel panel = new JPanel();
        		okienko.setSize(300,200);
        		panel.setLayout(new GridLayout(3,1));
        		JLabel label2 = new JLabel("Przegra³eœ!");
        		JLabel label3 = new JLabel("Zdobyte punkty: "+score);
        		label2.setFont(font);
        		label3.setFont(font);
        		label2.setVerticalAlignment(SwingConstants.CENTER);
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                label3.setVerticalAlignment(SwingConstants.CENTER);
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                Color color1 = new Color(176, 224, 230);
                panel.setBackground(color1);				                
        		panel.add(label2);
        		panel.add(label3);
        		okienko.add(panel);
        		okienko.setVisible(true);
        		ballX = r.nextInt(768);
        		ballY = 300;
        		ballvx=0;
        		ballvy=0;
        		play("./images/c.wav");
			}
			
			if(ballX<0) {
				ballvx = -ballvx;	
			}
			if(ballY<0) {
				ballvy = -ballvy;	
			}
			if(ballX>750) {
				ballvx = -ballvx;	
			}
			if(new Rectangle(ballX, ballY, 15, 15).intersects(new Rectangle(posX-3, 550, 76, 7))) {
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
							my_scorelabel.setText(""+score);
							play("./images/a.wav");
							if(score==5*a*b)
							{
								Font font=new Font("Helvetica", Font.BOLD, 20);
								JDialog okienko = new JDialog();
								JPanel panel = new JPanel();
				        		okienko.setSize(300,200);
				        		panel.setLayout(new GridLayout(3,1));
				        		JLabel label2 = new JLabel("Wygraï¿½eï¿½!");
				        		JLabel label3 = new JLabel("Zdobyte punkty: "+score);
				        		label2.setFont(font);
				        		label3.setFont(font);
				        		label2.setVerticalAlignment(SwingConstants.CENTER);
				                label2.setHorizontalAlignment(SwingConstants.CENTER);
				                label3.setVerticalAlignment(SwingConstants.CENTER);
				                label3.setHorizontalAlignment(SwingConstants.CENTER);
				                Color color1 = new Color(176, 224, 230);
				                panel.setBackground(color1);				                
				        		panel.add(label2);
				        		panel.add(label3);
				        		okienko.add(panel);
				        		okienko.setVisible(true);
				        		//okienko.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				        		ballvx=0;
				        		ballvy=0;
				        		play("./images/b.wav");
							}
							
						if(ballX+1<=brickRect.x||ballX>= brickRect.x+brickRect.width-3) {
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
		void play(String audioFilePath) {
	    	
	        try {
	            audioFile = new File(audioFilePath);
	            audioStream = AudioSystem.getAudioInputStream(audioFile);
	            AudioFormat format = audioStream.getFormat();
	            DataLine.Info info = new DataLine.Info(Clip.class, format);
	            Clip audioClip = (Clip) AudioSystem.getLine(info);
	            audioClip.open(audioStream);
	          
	            Thread thread = new Thread(new Runnable() {

	                public void run() {
	                	 audioClip.start();
						while(playCompleted){          	
	     	            	audioClip.close();    	            	
	     	            }
	     	            

	     	            try {
	 						audioStream.close();
	 					} catch (IOException e) {
	 				            e.printStackTrace();
	 					}
	                            
	                }
	                        
	            });
	            thread.start();
	            
	            

	        } catch (UnsupportedAudioFileException ex) {
	            System.out.println("The specified audio file is not supported.");
	            ex.printStackTrace();
	        } catch (LineUnavailableException ex) {
	            System.out.println("Audio line for playing back is unavailable.");
	            ex.printStackTrace();
	        } catch (IOException e1) {
	            System.out.println("Error playing the audio file.");
				e1.printStackTrace();
			} 
	         
	    
	}

}
