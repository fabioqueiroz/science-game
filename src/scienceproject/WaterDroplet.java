package scienceproject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class WaterDroplet extends GameObject
{

	public WaterDroplet(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		image = new Image(Sprout.class.getResource("/resources/water-droplet.PNG").toExternalForm());
		update();
	}
	
	public double getX() 
	{
		return this.x;
	}
	
	public double getY() 
	{
		return this.y;
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
		
//		if (y == 600) 
//		{
//			image = null;
//		}
//			
		gc.drawImage(image, x, y-100, 30, 30);
	}

}
