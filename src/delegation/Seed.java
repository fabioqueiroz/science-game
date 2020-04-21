package delegation;

import interfaces.PlantInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import scienceproject.GameObject;

/*
 * This class is responsible for defining a Seed object
 * and delegates the creation of Sprout, YoungPlant and 
 * AdultPlant objects via the common implemented interface,
 * leveraging the use of the Delegation Pattern.
 * 
 */

public class Seed extends GameObject implements PlantInterface
{
	private PlantInterface plantInterface;
	protected int age = 0;

	public Seed(double x, double y, GraphicsContext gc) 
	{
		super(x, y, gc);
		image = new Image(Seed.class.getResource("/resources/seed2.PNG").toExternalForm());
		rectangle = new Rectangle(x, y, image.getWidth(), image.getHeight());
		plantInterface = this;
		update();
	}
	
	public int getAge() 
	{
		return age;
	}
	
	public double getPlantHeight()
	{
		return this.x;
	}
	
	public double getPlantWidth()
	{
		return this.y;
	}
	
	@Override
	public void update() 
	{

		gc.drawImage(image, x, y, 120, 100);
		
	}
	
	public void changeGrowthStage() 
	{
		age += 1;
		

		if (age == 10) 
		{
			plantInterface = new Sprout(x,y,gc);
		}
		
		if (age == 20) 
		{
			plantInterface = new YoungPlant(x,y,gc);
		}
		
		if (age == 30) 
		{
			plantInterface = new AdultPlant(x,y,gc);
		}
		
		plantInterface.update();	
	}

}
