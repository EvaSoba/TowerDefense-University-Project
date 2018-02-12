package Map;

import javax.swing.*;
import java.awt.*;


public class Jeu extends JFrame {
	public JPanel jp;
	JLabel scoreL, carL;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	public Jeu(){
		setLayout(new BorderLayout());
		scoreL = new JLabel("score : 0");
	    carL   = new JLabel("temps : 100");
	    JPanel top = new JPanel();
	    top.setLayout(new BorderLayout());
	    top.add(scoreL, BorderLayout.WEST);
	    top.add(carL,   BorderLayout.EAST);
	    getContentPane().add(top, BorderLayout.NORTH);
	    top.setPreferredSize(new Dimension(screenSize.width, 50));
	    
	    Map map1 = new Map(screenSize);
		getContentPane().add(map1.panelMap, BorderLayout.CENTER);
		
		
		Menu menu1 = new Menu();
		getContentPane().add(menu1.panelMenu, BorderLayout.WEST);
		
		
		this.setTitle("Test");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}



	public static void main(String[] args) {
		Jeu frame = new Jeu();
	    frame.setSize(frame.screenSize.width, frame.screenSize.height);
		frame.setResizable(false);
		frame.setVisible(true);
	}

}