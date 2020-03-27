package scienceproject;

import Interfaces.PlantInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Seed extends GameObject implements PlantInterface
{
	PlantInterface plantInterface;
	protected int age = 0;

	public Seed(double x, double y, GraphicsContext gc) 
	{
		super(x, y, gc);
		image = new Image(Seed.class.getResource("/resources/seed2.PNG").toExternalForm());
		super.update();
		plantInterface = this;
		update();
	}
	
	public int getAge() 
	{
		return age;
	}
	
	@Override
	public void update() 
	{

		x++;	
		x += dx;
		if(x>800)
			dx=-1;
		if(x<0)
			dx=1;
		y+=dy;
		if(y>600)
			dy=-1;
		if(y<0)
			dy=1;

		gc.drawImage(image, x, y, 30, 30);
		
	}
	
	public void grow() 
	{
		age += 1;
		if(age == 100)
			plantInterface = new Sprout(x,y,gc);
		if(age == 200)
			plantInterface = new Sprout(x,y,gc);
		
		plantInterface.update();	
	}

}
