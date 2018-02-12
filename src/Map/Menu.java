package Map;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Menu {

	public JPanel panelMenu;
	public JLabel labMenu;
	public JButton button ;
	
	public Menu(){
		panelMenu = new JPanel();
		labMenu = new JLabel("Menu");
		panelMenu.add(labMenu);
		panelMenu.setPreferredSize(new Dimension(300,60));
		
		
	}

	
}
