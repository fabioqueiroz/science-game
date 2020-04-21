package scienceproject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/*
 * This class is responsible for defining
 * a Cloud object.
 * 
 */

public class Cloud extends GameObject
{

	public Cloud(double x, double y, GraphicsContext gc) 
	{
		super(x, y, gc);
		image = new Image(Cloud.class.getResource("/resources/clouds.jpg").toExternalForm());
		update();
	}
	
	@Override
	public void update() 
	{
		if(image != null)
		{
			gc.drawImage(image, x, y, 300, 100);
		}	
		
	}

}
