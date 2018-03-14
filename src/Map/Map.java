package Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;


public class Map {
	/*
	 * 0 = sol
	 * 1 = emplacements tourelles
	 * 2 = depart
	 * 3 = arrivee
	 * 4 = tourelle1
	 * 5 = tourelle2
	 * 6 = chemin
	 * 7 = ennemis
	 */
	protected int matMap[][] = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,6,6,6,6,0,0,0},
			{0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,6,0,0,0,1,6,0,0,0},
			{0,6,0,0,0,0,0,0,0,0,0,6,6,6,6,6,0,0,0,6,6,6,0,0,0,0,6,6,6,0},
			{0,6,0,0,0,0,0,0,0,0,1,6,0,0,1,6,0,0,0,6,0,0,0,0,0,0,0,0,6,0},
			{0,6,0,0,0,0,0,0,0,0,6,6,0,0,0,6,0,0,0,6,0,0,0,0,0,0,0,0,3,0},
			{0,6,0,0,0,0,0,0,0,0,6,0,0,0,0,6,0,0,0,6,1,0,0,0,0,0,0,0,0,0},
			{0,6,6,6,6,0,0,0,0,0,6,0,0,0,0,6,0,0,0,6,6,6,6,6,6,0,0,0,0,0},
			{0,0,0,1,6,0,0,0,0,0,6,0,0,0,0,6,0,0,0,0,0,0,0,1,6,0,0,0,0,0},
			{0,0,0,0,6,0,0,0,0,1,6,0,0,0,0,6,0,0,0,0,0,0,0,0,6,6,6,6,0,0},
			{0,0,0,0,6,0,0,0,6,6,6,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,1,6,0,0},
			{0,0,0,0,6,0,0,0,6,0,0,0,0,0,0,6,1,0,0,0,0,0,0,0,0,0,0,6,0,0},
			{0,0,0,0,6,0,0,0,6,0,0,0,0,0,0,6,6,6,6,0,0,0,0,0,0,0,0,6,0,0},
			{0,0,0,0,6,0,0,0,6,0,0,0,0,0,0,0,0,1,6,0,0,6,6,6,6,0,0,6,6,0},
			{0,6,6,6,6,0,0,0,6,0,0,0,0,0,0,0,0,0,6,0,0,6,1,1,6,0,0,0,6,0},
			{0,6,1,0,0,0,0,1,6,0,0,0,0,0,0,0,0,0,6,6,6,6,0,0,6,0,0,0,6,0},
			{0,6,6,6,6,6,6,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,6,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,6,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,6,6,6,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}

	};

	//panel de la map
	protected JPanel panelMap;

	//coord depart
	protected int xDepart, yDepart;
	//coord arrivee
	protected int xArrivee,yArrivee;

	//position de la map sur l'ecran
	protected int offsetX, offsetY;

	//liste d'ennemis
	protected ArrayList <Ennemis> ennemis = new ArrayList<Ennemis>() ;

	protected Menu menu ;

	//images utilisees
	protected static BufferedImage chemin, sol, emplacement, base1, base2, ennemi ;

	//taille en pixels de chaque case (images)
	protected int tailleCase = 31 ;

	//nombre de lignes de la matrice map
	protected int nbLgs = 20 ;

	//nombre de colonnes de la matrice map
	protected int nbCols = 30 ;

	protected int posx,posy;

	protected Tourelle tourelle ;

	private int argent ;

	//constructeur
	public Map(Dimension screenSize){
		menu = new Menu(this);
		tourelle = new Tourelle();

		//tourelle = menu.tourelle;
		//calcul pour que la map soit centree sur l'ecran
		offsetX = (int)(screenSize.width * 0.1) ;
		offsetY = (int)(screenSize.height * 0.1);

		//chargement des images
		chargeIgm();

		//coordonnees du depart et de l'arrivee
		coordDepartArrivee() ;



		panelMap = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);

				Graphics2D g2 = (Graphics2D)g;



				//affichage de la map
				for (int ligne = 0 ; ligne < nbLgs ; ligne++){
					for (int colonne = 0 ; colonne < nbCols ; colonne++){

						switch (matMap[ligne][colonne]){
						case 0: //image du sol
							g2.drawImage(sol,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null); 
							break;
						case 1: //image des emplacements de tourelles
							g2.drawImage(emplacement,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null);
							break;
						case 2: //image de la base de depart des ennemis
							g2.drawImage(base1,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null);
							break;
						case 3: //image de la base alliee
							g2.drawImage(base2,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null);
							break;
						case 4: //image de la tourelle1
							g2.drawImage(menu.tourelle1.image,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null);
							break;
						case 5: //image de la tourelle2
							g2.drawImage(menu.tourelle2.image,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null);
							break;
						case 6: //image du chemin
							g2.drawImage(chemin,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null);
							break;
						case 7: //image de l'ennemi
							g2.drawImage(ennemi,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null);
							break;
						}
					}
				}
			}
		};


		//placement d'une tourelle sur la map
		class TourListener implements MouseListener{
			@Override
			public void mouseClicked(MouseEvent e) {
				posx = (int)(e.getX()/31)-6;
				posy = (int)(e.getY()/31)-4;
				//System.out.println(tourelle.nom);
				//System.out.println(menu.getArgent());
				//System.out.println(argent);
				//seulement sur les emplacements de tourelles (=1 dans la matrice)
				if (matMap[posy][posx] == 1){
					//determination de la tourelle selectionnee
					if (tourelle.nom == "Tourelle1"){
						tourelle = new Tourelle1() ;
						//modification de la matrice avec la tourelle1
						//System.out.println(menu.getArgent());
						matMap[posy][posx] = 4;	
						argent = argent-tourelle.prix ;
						System.out.println(tourelle.nom);
						//menu.setArgent(argent);
						System.out.println("apres placement " + menu.getArgent());
						System.out.println("argent " + argent);

						menu.setTextAg(String.valueOf(argent));
						tourelle.nom = null;
					}

					else if (tourelle.nom == "Tourelle2"){
						//modification de la matrice avec la tourelle2
						tourelle = new Tourelle2() ;
						matMap[posy][posx] = 5;	
						//System.out.println(menu.getArgent());
						argent = argent-tourelle.prix ;
						//menu.setArgent(argent);
						System.out.println("apres placement " + menu.getArgent());
						System.out.println("argent " + argent);

						menu.setTextAg(String.valueOf(argent));
						tourelle.nom = null;
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		}
		TourListener po =  new TourListener();
		panelMap.addMouseListener(po);

	}


	//chargement de images
	public void chargeIgm(){
		try {
			chemin = ImageIO.read(new File("chemin.png"));
			sol = ImageIO.read(new File("sol.png"));
			emplacement = ImageIO.read(new File("emplacement.png"));
			base1 = ImageIO.read(new File("baseDepart.png"));
			base2 = ImageIO.read(new File("baseArrivee.png"));
			ennemi = ImageIO.read(new File("ennemi.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//coordonnees du depart et de l'arrivee
	public void coordDepartArrivee(){
		for (int ligne = 0 ; ligne < nbLgs ; ligne++){
			for (int colonne = 0 ; colonne < nbCols ; colonne++){
				if(matMap[ligne][colonne] == 2){
					xDepart = ligne ;
					yDepart = colonne ;
				}
				if(matMap[ligne][colonne] == 3){
					xArrivee = ligne ;
					yArrivee = colonne ;
				}
			}
		}
	}



	public int getxDepart(){
		return xDepart;
	}
	public int getyDepart(){
		return yDepart;
	}
	public int getxArrivee(){
		return xArrivee;
	}
	public int getyArrivee(){
		return yArrivee;
	}
	public int getoffsetX(){
		return offsetX;
	}
	public int getoffsetY(){
		return offsetY;
	}
	public int getTailleCase(){
		return tailleCase ;
	}
	public int getnbLgs(){
		return nbLgs;
	}
	public int getnbCols(){
		return nbCols;
	}
	public int getposx(){
		return posx;
	}
	public int getposy(){
		return posy;
	}	
	public int getArgent2(){
		return argent;
	}
	public void setArgent(int arge){
		argent = arge ;
	}
}
