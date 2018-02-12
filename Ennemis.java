package Map;

public class Ennemis {
	public int [] cheminAllee;
	public int [] cheminRetour;
	
	public int vie;
	public int vitesse;
	public int dommages;
	public int x;
	public int y;
	
	
	public Ennemis(Map m) {
		this.x = m.xDepart;
		this.y = m.yDepart;
	}
}
