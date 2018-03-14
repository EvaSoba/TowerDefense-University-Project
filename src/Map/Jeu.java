package Map;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class Jeu extends JFrame {
	JLabel scoreL, carL;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Map map1 ;

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


		map1 = new Map(screenSize);


		getContentPane().add(map1.panelMap, BorderLayout.CENTER);


		Menu menu1 = new Menu(map1);
		getContentPane().add(menu1.getPanelMenu(), BorderLayout.WEST);


		this.setTitle("Test");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}



	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		jeu.setSize(jeu.screenSize.width, jeu.screenSize.height);
		jeu.setResizable(false);
		jeu.setVisible(true);

		int yDep = jeu.map1.yDepart ;
		
		Timer t = new Timer();
		t.schedule(new TimerTask(){
			public void run(){
				jeu.map1.panelMap.repaint() ;
			}
		}, 1000, 50);

	}

}