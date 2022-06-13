package projekt;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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
import javax.swing.SwingUtilities;
  
public class StartFrame extends JFrame {
	JButton buttonlatwy, buttontrudny;
	JLabel label1;
	int fontSize=33;
	Font font=new Font("Helvetica", Font.BOLD, fontSize);
	JMenuBar menubar;
	JMenu menu;
	JMenuItem item1, item2;
	Color zolty = new Color(245, 244, 151);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			 public void run() {
				StartFrame okno= new StartFrame();
				okno.setVisible(true);
			 }
		});	
	}
	
	public StartFrame() throws HeadlessException {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setSize(900,600);
	    this.setTitle("BlocksBreaker");
	    this.setResizable(false); 
	    JLabel label = new JLabel();
		ImageIcon image = new ImageIcon("images/image.png");
		label.setIcon(image);
	    label.setLayout(new GridLayout(4, 5, 30, 20));
	    label1=new JLabel("Wybierz poziom trudnoœci gry");
	    label1.setFont(font);
	    label1.setVerticalAlignment(SwingConstants.CENTER);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
	    buttonlatwy = new JButton("£atwy");
	    buttonlatwy.setBackground(zolty);
	    
	    ActionListener latwy = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 synchronized (this){
						EasyFrame frame = new EasyFrame();
				        frame.setVisible(true);
				 }
			}	
		};
		
		buttonlatwy.addActionListener(latwy);
	    buttontrudny = new JButton("Trudny");
	    buttontrudny.setBackground(zolty);
	    
	    ActionListener trudny = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				synchronized (this){
						HardFrame frame = new HardFrame();
				        frame.setVisible(true);;
				}	
			}
		};
		
	    buttontrudny.addActionListener(trudny);
	    label.add(label1);
	    label.add(buttonlatwy);
	    label.add(buttontrudny);
		this.add(label);

	}
}