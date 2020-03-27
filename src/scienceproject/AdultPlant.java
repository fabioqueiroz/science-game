package scienceproject;

import Interfaces.PlantInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AdultPlant extends GameObject implements PlantInterface
{

	public AdultPlant(double x, double y, GraphicsContext gc) 
	{
		super(x, y, gc);
		image = new Image(Sprout.class.getResource("/resources/plant.PNG").toExternalForm());
		//super.update();
		update();
	}
	
	@Override
	public void update() 
	{
		gc.drawImage(image, x, y, 140, 200);
		
	}

}
