package scienceproject;

import Interfaces.PlantInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprout extends GameObject implements PlantInterface
{

	public Sprout(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		image = new Image(Sprout.class.getResource("/resources/sprout2.PNG").toExternalForm());
		//super.update();
		update();
	}
	
	@Override
	public void update() 
	{
//		x++;	
//		x += dx;
//		if(x>800)
//			dx=-2;
//		if(x<0)
//			dx=2;
//		y+=dy;
//		if(y>600)
//			dy=-2;
//		if(y<0)
//			dy=2;

		gc.drawImage(image, x, y, 120, 100);
		
	}

}
