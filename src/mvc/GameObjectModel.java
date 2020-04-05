package mvc;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import scienceproject.Cloud;
import scienceproject.GameObject;
import scienceproject.Seed;

public class GameObjectModel 
{	
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	ArrayList<GameObject> droplets = new ArrayList<GameObject>();
	
	public void addPlant(double x, double y, GraphicsContext gc) 
	{
		gameObjects.add(new Seed(x, y, gc));
	}
	
	public void addCloud(double x, double y, GraphicsContext gc)
	{
		droplets.add(new Cloud(x, y, gc));
	}
	
}
