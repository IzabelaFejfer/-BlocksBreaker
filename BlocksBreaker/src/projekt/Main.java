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

public class Main extends JFrame 
{
	JPanel panel1, panel2;
	JButton button1, button2, button3 ;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem item1, item2;
	JRadioButton radioButton1, radioButton2;
	ButtonGroup grupa;
	JLabel scoreLabel, scLabel;
	int score=0;
	int fontSize=20;
	Font font=new Font("Helvetica", Font.BOLD, fontSize);
	Main()
	{
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setSize(900,600);
	    this.setTitle("BlocksBreaker");
	    this.setLayout(new BorderLayout());
	    this.setResizable(false);
	    
	    //srodkowy panel
	    panel2= new JPanel();
	    panel2.setBackground(Color.white);
	    this.add(panel2, BorderLayout.CENTER);
	    
	    //prawy panel
	    panel1=new JPanel();
	    panel1.setLayout(new GridLayout(8,1, 15, 15));
	    Color color1 = new Color(176, 224, 230);
	    Color color2 = new Color(255, 200, 255);
	    panel1.setBackground(color1);
	    scoreLabel=new JLabel("Punkty:");
	    scoreLabel.setFont(font);
	    scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
	    scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    scLabel=new JLabel(""+score);
	    scLabel.setFont(font);
	    scLabel.setVerticalAlignment(SwingConstants.CENTER);
	    scLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    radioButton1 = new JRadioButton("Tryb jasny");
	    radioButton1.setBackground(color1);
	    radioButton2 = new JRadioButton("Tryb ciemny");
	    radioButton2.setBackground(color1);
	    button1 = new JButton("Nowa Gra");
	    button1.setBackground(color2);
	    button2 = new JButton("Zatrzymaj gre");
	    button2.setBackground(color2);
	    button3 = new JButton("Zapisz punkty");
	    button3.setBackground(color2);
	    grupa= new ButtonGroup();
	    grupa.add(radioButton1);
	    grupa.add(radioButton2);
	    radioButton1.setSelected(true);					//domyslny jest tryb jasny
	    panel1.add(scoreLabel);
	    panel1.add(scLabel);
	    panel1.add(radioButton1);
	    panel1.add(radioButton2);
	    panel1.add(button1);
	    panel1.add(button2);
	    panel1.add(button3);
	    this.add(panel1, BorderLayout.LINE_END);		//menu gracza po prawej stronie
	}
}