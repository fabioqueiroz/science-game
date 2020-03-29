package scienceproject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class WaterDroplet extends GameObject
{

	public WaterDroplet(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		image = new Image(Sprout.class.getResource("/resources/water-droplet.PNG").toExternalForm());
		rectangle = new Rectangle(x, y-100, image.getWidth(), image.getHeight());
		update();
	}
	
	@Override
	public void update() 
	{
		if(image != null)
		{
			gc.drawImage(image, x, y-100, 30, 30);
		}	
		
	}
	
	public void move()
	{
		
		y += dy;
		
		if(y > 0)
		{
			dy = 1;
		}
		
		if (y == 580) 
		{
			image = changeToBlankImage();
		}
			
		gc.drawImage(image, x, y-100, 30, 30);
			
	}
	
	public Image changeToBlankImage()
	{
		return image = new Image(Sprout.class.getResource("/resources/blank-droplet.PNG").toExternalForm());
	}

}
