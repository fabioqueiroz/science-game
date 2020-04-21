package delegation;

import interfaces.PlantInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import scienceproject.GameObject;

/*
 * This class is responsible for defining a AdultPlant object
 * and its instantiation is responsibility of the Seed class.
 * 
 */

public class AdultPlant extends GameObject implements PlantInterface
{

	public AdultPlant(double x, double y, GraphicsContext gc) 
	{
		super(x, y, gc);
		image = new Image(AdultPlant.class.getResource("/resources/plant.PNG").toExternalForm());
		rectangle = new Rectangle(x, y-100, image.getWidth(), image.getHeight());
		update();
	}
	
	@Override
	public void update() 
	{
		gc.drawImage(image, x, y-100, 140, 200);
		
	}

}
