package Map;

public class Ennemis {
	public int [] cheminAllee;
	public int [] cheminRetour;
	
	public int vie;
	public int vitesse;
	public int dommages;
	public int x;
	public int y;
	public int[][] matrice;
	
	
	public Ennemis(int[][] m, int posx, int posy) {
		this.x = posx;
		this.y = posy;
		matrice = m ;
	}
}