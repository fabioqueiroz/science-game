package scienceproject;

import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

@SuppressWarnings("rawtypes")
public class ScienceProject extends Application implements EventHandler
{	
	Pane root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	Button rainButton, daysButton;
	Label plantName, displayDays;
	Rectangle soil, grass;
	
	boolean isRainButtonClicked;
	int noOfDays;
	
	BackgroundImage bgImage = new BackgroundImage(new Image("/resources/garden.PNG",32,32,false,true),
	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	        BackgroundSize.DEFAULT);
	
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	ArrayList<GameObject> droplets = new ArrayList<GameObject>();
	
	Random rnd = new Random(System.currentTimeMillis());
	int count = 0;
	
	AnimationTimer timer = new AnimationTimer() 
	{

		@Override
		public void handle(long arg0) 
		{

			if(count++ > 10) 
			{
				droplets.add(new Cloud(rnd.nextInt(1),rnd.nextInt(1), gc));
				droplets.add(new WaterDroplet(rnd.nextInt(400),rnd.nextInt(200), gc));
				count = 0;
			}
					
			for (GameObject droplet : droplets)
			{
				
				for (GameObject gameObject : gameObjects) 
				{
					if (gameObject.getRectangle().getBoundsInParent()
							.intersects(droplet.getX(), droplet.getY() - 80, droplet.getImage().getWidth(), droplet.getImage().getHeight())) 
					{
						((WaterDroplet)droplet).changeToBlankImage();
						
						//System.out.println("Intersected at: x= " + droplet.getX() + ", y= " + droplet.getY());
						//System.out.println("Plant at: x= " + gameObject.getX() + ", y= " + gameObject.getY());
						//System.out.println("Bounds: x= " + droplet.getRectangle().getBoundsInParent());

					} 

					else 
					{
						if (droplet instanceof WaterDroplet) 
						{						
							((WaterDroplet)droplet).move();
						}

					}
				}
				
			}	
						
		}
		
	};
	
	public static void main(String[] args) 
	{
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception 
	{
		String stylesheet = getClass().getResource("/resources/styles.css").toExternalForm();
		
		root = new Pane();
		scene = new Scene(root,1200,800);
		scene.getStylesheets().add(stylesheet);
	
		stage.setTitle("Growing plants");
		stage.setScene(scene);
		stage.show();
		
		canvas = new Canvas(1200,800);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
		
		rainButton = new Button("Let it rain!");
		rainButton.setLayoutX(50);
		rainButton.setLayoutY(700);
		rainButton.setOnAction(this);
		
		daysButton = new Button("Days counter");
		daysButton.setLayoutX(350);
		daysButton.setLayoutY(700);
		daysButton.setOnAction(this);
		
		gameObjects.add(new Seed(200,450, gc));
		droplets.add(new Cloud(1, 1, gc));
		droplets.add(new Cloud(405, 1, gc));
		
		soil = new Rectangle(150, 5);
		soil.setFill(Paint.valueOf("#3f2828"));
		soil.setLayoutX(190);
		soil.setLayoutY(545);
		
		grass = new Rectangle(550, 100);
		grass.setFill(Paint.valueOf("#144e14"));
		grass.setLayoutX(10);
		grass.setLayoutY(550);
		
		plantName = new Label("Seed");
		plantName.setLayoutX(390);
		plantName.setLayoutY(510);
		
		displayDays = new Label("");
		displayDays.setLayoutX(550);
		displayDays.setLayoutY(710);
		
        root.getChildren().addAll(canvas, rainButton, daysButton, plantName, soil, grass, displayDays);
						
	}

	@Override
	public void handle(Event event) 
	{
		
		if(event.getSource() == this.rainButton)
		{
			timer.start();
					
			isRainButtonClicked = true;	
			
		}
	
		if(event.getSource() == this.daysButton)
		{
			
			if (isRainButtonClicked) 
			{
				
				for (GameObject gameObject : gameObjects) 
				{
					((Seed)gameObject).changeGrowthStage();
				}
				
				
				this.daysButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
				{
					String name = "";
				
					@Override
					public void handle(MouseEvent mouseEvent) 
					{
						noOfDays++;
															
						switch (noOfDays) 
						{
						case 2:
							name = "Sprout";
							break;
							
						case 4:
							name = "Young Plant";
							break;
							
						case 6:
							name = "Adult Plant";
							break;

						default:
							break;
						}
						
						plantName.setText(name);
						displayDays.setText(Integer.toString(noOfDays) + " days");

					}			
					
				});
				
			}
		
		}	
		
	}
		

}
