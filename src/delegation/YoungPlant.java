package delegation;

import interfaces.PlantInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import scienceproject.GameObject;

/*
 * This class is responsible for defining a YoungPlant object
 * and its instantiation is responsibility of the Seed class.
 * 
 */

public class YoungPlant extends GameObject implements PlantInterface
{

	public YoungPlant(double x, double y, GraphicsContext gc) 
	{
		super(x, y, gc);
		image = new Image(YoungPlant.class.getResource("/resources/sprout3.PNG").toExternalForm());
		rectangle = new Rectangle(x, y-100, image.getWidth(), image.getHeight());
		update();
	}
	
	@Override
	public void update() 
	{
		gc.drawImage(image, x, y-100, 140, 200);
			
	}

}
