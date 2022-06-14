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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class EasyGame extends JPanel implements ActionListener, KeyListener {
	private boolean start = false;
	private boolean playCompleted = false;
	private Timer timer;
	Random r = new Random();
	String audioFilePath = "";
	Clip audioClip = null;
	File audioFile = null;
	AudioInputStream audioStream = null;	
	private int posX = 300;
	private int platv = 20;
	private int ballX = r.nextInt(600)+5;
	private int ballY = 300;
	private int ballvx = -2;
	private int ballvy = 3;
	private int delay = 0;
	private int score = 0;
	private int a = 4;
	private int b = 8;
	private int c;
	private JLabel my_scorelabel;
	private Map mapa;
	Color k1=Color.BLACK, k2;
	Color niebieski = new Color(176, 224, 230);
	private Color kolorPilki  = Color.BLUE;
	private Color kolorPlatformy = Color.RED;
	private Color kolorTla = Color.WHITE;
	private Color kolorPanelPrawy = niebieski;

	public EasyGame(int c, JLabel scorelabel) {
		this.c = c;
		my_scorelabel = scorelabel;
		mapa = new Map(a,b,c);
		mapa.trybJasny();
		addKeyListener(this);
		setFocusable(true);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint (Graphics g) {
		//tlo
		g.setColor(kolorTla);
		g.fillRect(1, 1, 800, 600);
		g.setColor(k1);
		g.drawString("Naciœnij Enter, aby rozpocz¹æ grê", 300, 30);
		//mapka
		mapa.draw((Graphics2D)g);
		//kontury
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 3, 600);
		g.fillRect(0, 0, 770, 3);
		g.fillRect(770, 0, 3, 600);
		//pilka
		g.setColor(kolorPilki);
		g.fillOval(ballX, ballY, 20, 20);
		//platforma
		g.setColor(kolorPlatformy);
		g.fillRect(posX, 550, 100, 7);
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
			} 
			else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(posX <= 3) {
				 posX = 3;
			} 
			else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			start = true;
			k1=k2;
			repaint();
		}
	}
	
	public void moveRight() {
		posX += platv;
	}
	
	public void moveLeft() {
		posX -= platv;
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
					
			if(ballY>getHeight()) {
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
		        panel.setBackground(kolorPanelPrawy);				                
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
									score += 5;
									my_scorelabel.setText(""+score);
									play("./images/a.wav");
									
									if(score == 5*a*b) {
										Font font = new Font("Helvetica", Font.BOLD, 20);
										JDialog okienko = new JDialog();
										JPanel panel = new JPanel();
						        		okienko.setSize(300,200);
						        		panel.setLayout(new GridLayout(3,1));
						        		JLabel label2 = new JLabel("Wygra³eœ!");
						        		JLabel label3 = new JLabel("Zdobyte punkty: "+score);
						        		label2.setFont(font);
						        		label3.setFont(font);
						        		label2.setVerticalAlignment(SwingConstants.CENTER);
						                label2.setHorizontalAlignment(SwingConstants.CENTER);
						                label3.setVerticalAlignment(SwingConstants.CENTER);
						                label3.setHorizontalAlignment(SwingConstants.CENTER);
						                panel.setBackground(kolorPanelPrawy);				                
						        		panel.add(label2);
						        		panel.add(label3);
						        		okienko.add(panel);
						        		okienko.setVisible(true);
						        		ballvx=0;
						        		ballvy=0;
						        		play("./images/b.wav");
									}
									
									if(ballX<=brickRect.x||ballX+1>= brickRect.x+brickRect.width) {
										ballvx = -ballvx;
									}
									else {
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
     	             } 
     	             catch (IOException e) {
 				         e.printStackTrace();
 					}        
                }          
            });
            thread.start();   
        } 
        catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } 
        catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } 
        catch (IOException e1) {
            System.out.println("Error playing the audio file.");
			e1.printStackTrace();
		}  
    }
     
    public void stopGame() {
    	ballvx = 0;
    	ballvy = 0;
	 	platv = 0;
	}
	
	public void startGame() {
		 ballvx = -2;
		 ballvy = -3;
		 platv = 20;
	}
	
	public void newGame() {
		mapa = new Map(a,b,c);
		ballX = r.nextInt(650)+5;
		ballY = 300;
		posX = 300;
		score = 0;
		my_scorelabel.setText("");
		ballvx = -2;
		ballvy = 3;
		platv = 20;
		start=false;
		repaint();
	}
	
	public void trybCiemny() {
		kolorPilki = Color.white;
		kolorPlatformy = Color.gray;
		kolorTla = Color.black;
		mapa.trybCiemny();
		k1=Color.white;
		k2=Color.black;
		repaint();
	}
	
	public void trybJasny() {
		kolorPilki  = Color.BLUE;
		kolorPlatformy = Color.RED;
		kolorTla = Color.WHITE;
		mapa.trybJasny();
		k1=Color.black;
		k2=Color.white;
		repaint();
	}
}
