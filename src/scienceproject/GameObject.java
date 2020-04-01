package scienceproject;

import interfaces.PlantInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class GameObject implements PlantInterface
{
	protected Image image;
	protected double x, y;
	protected GraphicsContext gc;
	protected double dx = 1, dy = 1;
	protected Rectangle rectangle;
	
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
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public Image getImage()
	{
		return this.image;
	}
	
	public Rectangle getRectangle()
	{
		return this.rectangle;
	}

}
