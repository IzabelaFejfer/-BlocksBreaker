package projekt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;


public class Gameeasy extends JFrame {
	Easyframe easyframe;
	JPanel panel1, panel2;
	JButton button1, button2, button3 ;
	JMenuBar menubar;
	JMenu menu;
	JMenuItem item1, item2;
	JRadioButton radiobutton1, radiobutton2;
	ButtonGroup grupa, group;
	JLabel scorelabel, sclabel;
	int score=0;
	int fontSize=20;
	int c;
	boolean pauza = false;
	Font font=new Font("Helvetica", Font.BOLD, fontSize);
	
	Gameeasy(){	

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setSize(900,600);
	    this.setTitle("BlocksBreaker");
	    this.setLayout(new BorderLayout());
	    this.setResizable(false);
	    
	    
	    //prawy panel
	    panel1=new JPanel();
	    panel1.setLayout(new GridLayout(8,1, 15, 15));
	    Color color1 = new Color(176, 224, 230);
	    Color color2 = new Color(255, 200, 255);
	    panel1.setBackground(color1);
	    scorelabel=new JLabel("Punkty:");
	    scorelabel.setFont(font);
	    scorelabel.setVerticalAlignment(SwingConstants.CENTER);
	    scorelabel.setHorizontalAlignment(SwingConstants.CENTER);
	    sclabel=new JLabel("");
	    sclabel.setFont(font);
	    sclabel.setVerticalAlignment(SwingConstants.CENTER);
	    sclabel.setHorizontalAlignment(SwingConstants.CENTER);
	    radiobutton1 = new JRadioButton("Tryb jasny");
	    radiobutton1.setBackground(color1);
	    radiobutton2 = new JRadioButton("Tryb ciemny");
	    radiobutton2.setBackground(color1);
	    button1 = new JButton("Nowa Gra");
	    button1.setBackground(color2);
	    button2 = new JButton("Zatrzymaj grê");
	    button2.setBackground(color2);
	    button3 = new JButton("Zapisz punkty");
	    button3.setBackground(color2);
	    grupa= new ButtonGroup();
	    panel1.add(scorelabel);
	    panel1.add(sclabel);
	    panel1.add(radiobutton1);
	    panel1.add(radiobutton2);
	    panel1.add(button1);
	    panel1.add(button2);
	    panel1.add(button3);
	    this.add(panel1, BorderLayout.LINE_END);
	
	     
	    Thread threadPrawy = new Thread () {
			  public void run () {
				  
				  grupa.add(radiobutton1);
				    grupa.add(radiobutton2);
				    radiobutton1.setSelected(true);
				  
				  radiobutton1.addActionListener(new ActionListener() 		//Listener do zapisu 
				            {
				   	        @Override
				   	        public void actionPerformed(ActionEvent arg) 
				   	        {
				   	        	radiobutton1.setFocusable(false);
							    easyframe.setFocusable(true);
							    easyframe.requestFocusInWindow(); 
				   	        }
				            });
				  
				  radiobutton2.addActionListener(new ActionListener() 		//Listener do zapisu 
				            {
				   	        @Override
				   	        public void actionPerformed(ActionEvent arg) 
				   	        {
				   	        	radiobutton2.setFocusable(false);
							    easyframe.setFocusable(true);
							    easyframe.requestFocusInWindow(); 
				   	        }
				            });
				  
				    
				    button3.addActionListener(new ActionListener() 		//Listener do zapisu 
				            {
				   	        @Override
				   	        public void actionPerformed(ActionEvent arg) 
				   	        {
				   	        	JFileChooser jchooser = new JFileChooser();
				   	            jchooser.setDialogTitle("Wybierz plik");
				   	            
				   	            int returnVal = jchooser.showSaveDialog(null);
				   	            
				   	            if (returnVal==JFileChooser.APPROVE_OPTION) 
				   	            {
				   	                try 
				   	                {
				   	                    File plik = jchooser.getSelectedFile();
				   	                    OutputStreamWriter osw = new OutputStreamWriter( new FileOutputStream(plik), Charset.forName("UTF-8").newEncoder());
				   	                    osw.write(sclabel.getText());
				   	                    osw.close();
				   	                } 
				   	                catch (FileNotFoundException e) 
				   	                {
				   	                    e.printStackTrace();
				   	                } 
				   	                catch (IOException e) 
				   	                {
				   	                    e.printStackTrace();
				   	                }
				   	            }
							    button3.setFocusable(false);
							    easyframe.setFocusable(true);	
							    easyframe.requestFocusInWindow(); 

				   	        };
				            });
				  
				    
				    button1.addActionListener(new ActionListener() 		//Listener do nowej gry 
				            {
				    		
				   	        @Override
				   	        public void actionPerformed(ActionEvent arg) 
				   	        {
				   	        	easyframe.newGame();
				   	        	button1.setFocusable(false);
							    easyframe.setFocusable(true);
							    easyframe.requestFocusInWindow(); 
						   	    
				   	        };
				   	        
				   	     

		   	        
		            });
				    
				    
				    button2.addActionListener(new ActionListener() 		//Listener do zatrzymywania 
				            {
				    		
				   	        @Override
				   	        public void actionPerformed(ActionEvent arg) 
				   	        {
				   	            if (pauza==false) 
				   	            {
				   	            	easyframe.stopGame();
								    pauza = true;
								    button2.setText("Wznow gre");

				   	            }
				   	            else
				   	            {
				   	            	easyframe.startGame();	
								    pauza = false;
								    button2.setText("Zatrzymaj gre");

				   	            }


			   	            	button2.setFocusable(false);
							    easyframe.setFocusable(true);
							    easyframe.requestFocusInWindow(); 
				   	        };
		   	        
		            });
		   	     
				    
		
				
				 
			  }
			};
			
		threadPrawy.start();
		
		this.easyframe =  new Easyframe(c, sclabel);
		this.add(easyframe, BorderLayout.CENTER);

	}

}