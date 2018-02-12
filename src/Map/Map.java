package Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class Map {
	public JPanel panelMap;
	/*
	 * 0 = sol
	 * 1 = emplacements tourelles
	 * 2 = depart
	 * 3 = arrivee
	 * 4 = tourelle1
	 * 5 = tourelle2
	 * 6 = chemin
	 */
	public int matMap[][] = {
			/*{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}*/
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
			{0,0,2,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,3,0,0},
			{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			
	};
	
	int offsetX, offsetY;

	public static BufferedImage chemin, sol, emplacement, base1, base2;
	public int tailleCase = 31 ;
	//public double scale ;

	public Map(Dimension screenSize){
		try {
			chemin = ImageIO.read(new File("chemin.png"));
			sol = ImageIO.read(new File("sol.png"));
			emplacement = ImageIO.read(new File("emplacement.png"));
			base1 = ImageIO.read(new File("baseDepart.png"));
			base2 = ImageIO.read(new File("baseArrivee.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		offsetX = (int)(screenSize.width * 0.1) ;
		offsetY = (int)(screenSize.height * 0.1);
		
		panelMap = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);

				Graphics2D g2 = (Graphics2D)g;
				
				//g.setColor(Color.BLACK);
				//g.fillRect(0, 0, getWidth()-50, getHeight()-40);
				
				//affichage de la map
				for (int ligne = 0 ; ligne < 20 ; ligne++){
					for (int colonne = 0 ; colonne < 30 ; colonne++){
						//image du sol
						
						
						if(matMap[ligne][colonne] == 0){
							g2.drawImage(sol,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null);
						}
						
						//image du chemin
						else if(matMap[ligne][colonne] == 6){
							g2.drawImage(chemin,offsetX + colonne*tailleCase, offsetY + ligne*tailleCase, null);
						}
						
						//image des emplacements de tourelles
						else if(matMap[ligne][colonne] == 1){
							g2.drawImage(emplacement,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null);
						}
						
						//image de la base de depart des ennemis
						else if(matMap[ligne][colonne] == 2){
							g2.drawImage(base1,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null);
						}
						
						//image de la base alliee
						else if(matMap[ligne][colonne] == 3){
							g2.drawImage(base2,offsetX + colonne*tailleCase,offsetY + ligne*tailleCase, null);
						}
					}
				}

			}
		};

	}



}
