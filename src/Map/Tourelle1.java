package Map;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tourelle1 extends Tourelle{

	public Tourelle1(){
		bullet = new Bullet1() ;
		prix = 50 ;
		nom = "Tourelle1" ;
		
		//Chargement des images
		try {
			image = ImageIO.read(new File("tourelle1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
