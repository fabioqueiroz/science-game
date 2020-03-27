package scienceproject;

import java.util.ArrayList;
import java.util.Random;

import com.sun.javafx.sg.prism.NGImageView;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ScienceProject extends Application 
{	
	Pane root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
//	BackgroundImage bgImage = new BackgroundImage(new Image("resources/garden.PNG",32,32,false,true),
//	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//	        BackgroundSize.DEFAULT);
	

	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	Random rnd = new Random(System.currentTimeMillis());
	int count = 0;
	
	AnimationTimer timer = new AnimationTimer() 
	{

		@Override
		public void handle(long arg0) 
		{
			gc.setFill(Color.MEDIUMSEAGREEN);
			gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
			
			if(count++ > 10) 
			{
				gameObjects.add(new Seed(rnd.nextInt(1200),rnd.nextInt(800), gc));
				count = 0;
			}
			
			for (GameObject gameObject : gameObjects) 
			{
				((Seed)gameObject).grow();
			}
			
		}
		
	};
	
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		root = new Pane();
		scene = new Scene(root,1200,800);
		stage.setScene(scene);
		stage.show();
		
		canvas = new Canvas(1200,800);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.MEDIUMSEAGREEN);
		gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
			
		root.getChildren().add(canvas);
		
		
		timer.start();
		
	}
	
	

}
