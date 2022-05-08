package projekt;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
  
public class StartFrame extends JFrame 
{
	JButton buttonEasy, buttonHard;
	JLabel label1;
	int fontSize=33;
	Font font=new Font("Helvetica", Font.BOLD, fontSize);
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem item1, item2;
	StartFrame()
	{
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setSize(900,600);
	    this.setTitle("BlocksBreaker");
	    this.setResizable(false); 
	    JLabel label = new JLabel();
		ImageIcon image = new ImageIcon("images/image.png");
		label.setIcon(image);
	    label.setLayout(new GridLayout(4, 5,30, 20));
	    label1=new JLabel("Wybierz poziom trudno�ci gry");
	    label1.setFont(font);
	    label1.setVerticalAlignment(SwingConstants.CENTER);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        Color color1 = new Color(245, 244, 151);
	    buttonEasy = new JButton("�atwy");
	    buttonEasy.setBackground(color1);
	    ActionListener latwy = new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Main frame = new Main();
		        frame.setVisible(true);;
			}	
		};
		buttonEasy.addActionListener(latwy);
	    buttonHard = new JButton("Trudny");
	    buttonHard.setBackground(color1);
	    label.add(label1);
	    label.add(buttonEasy);
	    label.add(buttonHard);
		this.add(label);
	    
	    //MENUJ�ZYK
	    menuBar = new JMenuBar();
	    menu = new JMenu("J�zyk");
	    item1 = new JMenuItem("polski");
	    item2 = new JMenuItem("angielski");
	    menu.add(item1);
	    menu.add(item2);
	    menuBar.add(menu);
	    this.setJMenuBar(menuBar);
	}
	public static void main(String[] args) 
	{
		StartFrame okno= new StartFrame();
		okno.setVisible(true);
	}
}
