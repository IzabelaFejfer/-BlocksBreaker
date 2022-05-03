package projekt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;



public class Gamehard extends JFrame {
	JPanel panel1, panel2;
	JButton button1, button2, button3 ;
	JMenuBar menubar;
	JMenu menu;
	JMenuItem item1, item2;
	JRadioButton radiobutton1, radiobutton2;
	ButtonGroup grupa;
	JLabel scorelabel, sclabel;
	//int score=0;
	int fontSize=20;
	Font font=new Font("Helvetica", Font.BOLD, fontSize);
	Gamehard(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setSize(900,600);
	    this.setTitle("BlocksBreaker");
	    this.setLayout(new BorderLayout());
	    this.setResizable(false);
	    
	    //œrodkowy panel
	    
	    Hardframe hardframe =  new Hardframe();
	    this.add(hardframe, BorderLayout.CENTER);
	    
	    
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
	    grupa.add(radiobutton1);
	    grupa.add(radiobutton2);
	    radiobutton1.setSelected(true);
	    panel1.add(scorelabel);
	    panel1.add(sclabel);
	    panel1.add(radiobutton1);
	    panel1.add(radiobutton2);
	    panel1.add(button1);
	    panel1.add(button2);
	    panel1.add(button3);
	    this.add(panel1, BorderLayout.LINE_END);
	}

}