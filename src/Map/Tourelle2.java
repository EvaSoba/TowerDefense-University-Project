package Map;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tourelle2 extends Tourelle{

	public Tourelle2(){
		bullet = new Bullet2() ;
		prix = 100 ;
		nom = "Tourelle2" ;

		//Chargement des images
		try {
			image = ImageIO.read(new File("tourelle2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
