package delegation;

import interfaces.PlantInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import scienceproject.GameObject;

public class Seed extends GameObject implements PlantInterface
{
	PlantInterface plantInterface;
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

//		x++;	
//		x += dx;
//		if(x>800)
//			dx=-1;
//		if(x<0)
//			dx=1;
//		y+=dy;
//		if(y>600)
//			dy=-1;
//		if(y<0)
//			dy=1;

		gc.drawImage(image, x, y, 120, 100);
		
	}
	
	public void changeGrowthStage() 
	{
		age += 1;
		

		if (age == 2) 
		{
			plantInterface = new Sprout(x,y,gc);
		}
		
		if (age == 4) 
		{
			plantInterface = new YoungPlant(x,y,gc);
		}
		
		if (age == 6) 
		{
			plantInterface = new AdultPlant(x,y,gc);
		}
		
		plantInterface.update();	
	}

}
