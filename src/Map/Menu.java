package Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Menu {

/*********************************************************************/
	/***ATTRIBUTS***/
/*********************************************************************/
	//panel du menu
	private JPanel panelMenu;

	//titre du menu
	private JLabel labMenu;

	//boutons des tourelles
	private JButton btour1, btour2 ;
	
	//Argent possede
	private int argent = 0;
	private JLabel arg ;
	private JLabel ag ;
	
	/**********************************************/
	/*BOUTONS TESTS*/
	//incremente et decremente le prix
	private JButton incrPrix, decrPrix ;
	protected Tourelle1 tourelle1 ;
	protected Tourelle2 tourelle2 ;
	/**********************************************/

/********************************************************************/
	
	//CONSTRUCTEUR
	public Menu(Map map){
		tourelle1 = new Tourelle1() ;
		tourelle2 = new Tourelle2() ;

		//construction du panel menu
		constructionPanelMenu();

		//ecouteur des boutons tourelles
		class TourelleListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				map.tourelle.nom = e.getActionCommand();
				System.out.println(e.getActionCommand());
				 
			}
		}

		//si souris sur l'image de la tourelle et clic, recuperation du label
		TourelleListener tl =  new TourelleListener();

		btour1.addActionListener(tl);
		btour2.addActionListener(tl);
		
		/**********************TESTS***********************/
		//ecouteur des boutons incr et decr
		class IncrDecrListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
						
				System.out.println("argent1 " + argent);
				argent = map.getArgent2();
				ag.setText(String.valueOf(argent));
				
				//incrementation de l'argent possede
				if(e.getActionCommand() == "Prix + 10"){
					argent = argent + 10 ;
					map.setArgent(argent);
				}
				//decrementation de l'argent possede
				else if (e.getActionCommand() == "Prix - 10" && argent > 0){
					argent = argent - 10 ;
					map.setArgent(argent);

				}
				System.out.println("argent3 " + argent);
				//modification de l'affichage
				ag.setText(String.valueOf(argent));
				
				//si l'argent total est supperieur ou egal au prix de la premiere tourelle, on peut la selectionner
				if(argent >= tourelle2.prix){
					btour2.setEnabled(true);
				}
				else {
					btour2.setEnabled(false);
				}
				//si l'argent total est supperieur ou egal au prix de la premiere tourelle, on peut la selectionner
				if (argent >= tourelle1.prix){
					btour1.setEnabled(true);
				}
				else{
					btour1.setEnabled(false);
				}
				
			}
		}
		
		IncrDecrListener id = new IncrDecrListener();
		incrPrix.addActionListener(id);
		decrPrix.addActionListener(id);
		/**********************************************/
	}


	//construction du panel menu avec titre et boutons initialisation
	protected void constructionPanelMenu(){
		//construction du panel menu
		panelMenu = new JPanel();
		panelMenu.setLayout(new GridLayout(20,1));
		panelMenu.setPreferredSize(new Dimension(200,20));

		labMenu = new JLabel("Menu");
		
		btour1 = new JButton("Tourelle1", new ImageIcon(tourelle1.image));
		btour2 = new JButton("Tourelle2", new ImageIcon(tourelle2.image));
		
		
				
		panelMenu.add(labMenu);

		//boutons
		btour1.setPreferredSize(new Dimension(31,31));
		btour1.setBorder(BorderFactory.createEmptyBorder());
		btour1.setContentAreaFilled(false);
		panelMenu.add(btour1);
		

		btour2.setPreferredSize(new Dimension(31,31));
		btour2.setBorder(BorderFactory.createEmptyBorder());
		btour2.setContentAreaFilled(false);
		panelMenu.add(btour2);
		
		/*******************TESTS**************************/
		incrPrix = new JButton("Prix + 10");
		decrPrix = new JButton("Prix - 10");
		panelMenu.add(incrPrix);
		panelMenu.add(decrPrix);
		
		//si l'argent possede est inferieur au prix des tourelles, on ne peut cliquer sur une tourelle
		if (argent < tourelle1.prix){
			btour1.setEnabled(false);
			btour2.setEnabled(false);
		}
		
		arg = new JLabel("Argent :");
		
		panelMenu.add(arg);
		
		ag = new JLabel(String.valueOf(argent));
		panelMenu.add(ag);
		/**************************************************/
	}
	
	public JPanel getPanelMenu(){
		return panelMenu;
	}
	public JLabel getLabMenu(){
		return labMenu;
	}
	public JButton getbtour1(){
		return btour1;
	}
	public JButton getbtour2(){
		return btour2;
	}
	public int getArgent(){
		return argent;
	}
	public void setArgent(int arge){
		argent = arge ;
	}
	public void setTextAg(String text){
		ag.setText(text);
	}
}
