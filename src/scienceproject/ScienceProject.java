package scienceproject;

import java.util.*;
//import java.util.ArrayList;
//import java.util.Random;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

@SuppressWarnings("rawtypes")
public class ScienceProject extends Application implements EventHandler
{	
	Pane root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	Button waterButton;
	BackgroundImage bgImage = new BackgroundImage(new Image("/resources/garden.PNG",32,32,false,true),
	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	        BackgroundSize.DEFAULT);
	
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
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
		//root.setBackground(new Background(bgImage));
		//root.setStyle("-fx-background-image: url('/resources/garden.PNG');");
		
//		String image = ScienceProject.class.getResource("/resources/garden.PNG").toExternalForm();
//		root.setStyle("-fx-background-image: url('" + image + "'); " +
//		           "-fx-background-position: center center; " +
//		           "-fx-background-repeat: stretch;");
		
		
		scene = new Scene(root,1200,800);
		scene.getStylesheets().add(stylesheet);

	
		stage.setTitle("Growing plants");
		stage.setScene(scene);
		stage.show();
		
		canvas = new Canvas(1200,800);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.MEDIUMSEAGREEN);
		gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
		
		waterButton = new Button("Water");
		waterButton.setLayoutX(200);
		waterButton.setLayoutY(500);
				waterButton.setOnAction(this);
		
		gameObjects.add(new Seed(800,300, gc));
		
		//root.getChildren().add(canvas);	
		root.getChildren().addAll(canvas, waterButton);
						
	}

	@Override
	public void handle(Event event) 
	{
	
		if(event.getSource() == this.waterButton)
		{

			for (GameObject gameObject : gameObjects) 
			{
				((Seed)gameObject).changeGrowthStage();
			}
		}	
		
	}
		

}
