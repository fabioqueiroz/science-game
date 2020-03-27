package scienceproject;

import Interfaces.PlantInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject implements PlantInterface
{
	protected Image image;
	protected double x, y;
	protected GraphicsContext gc;
	//protected double dx = 1, dy = 1;
	
	public GameObject(double x, double y, GraphicsContext gc) 
	{
		this.x = x;
		this.y = y;
		this.gc = gc;
	}
	
	@Override
	public void update() 
	{
		if(image != null)
		{
			gc.drawImage(image, x, y, 30, 30);
		}	
		
	}

}
