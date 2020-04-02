package scienceproject;

import java.util.*;

import builder.ContentCreator;
import builder.Questionnaire;
import builder.QuizBuilder;
import builder.QuizOneBuilder;
import builder.QuizThreeBuilder;
import builder.QuizTwoBuilder;
import crosscutting.DragAndDropEventGenerator;
import factory.InformationFactory;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

@SuppressWarnings("rawtypes")
public class ScienceProject extends Application implements EventHandler
{	
	Pane root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	Button rainButton, daysButton, resetButton;
	Label plantName, displayDays;
	Text displayInfo, displayQuestions;
	Text sourceOne, sourceTwo, sourceThree, sourceFour, targetOne, targetTwo, targetThree, targetFour;
	Rectangle soil, grass;
	InformationFactory informationFactory;
	ComboBox<String> menu;
	ContentCreator content;
	QuizBuilder quizBuilder;
	Questionnaire questionnaire;
	
	boolean isRainButtonClicked;
	int noOfDays;
	
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	ArrayList<GameObject> droplets = new ArrayList<GameObject>();
	
	// Define the questionnaire topics and listen to changes
	ObservableList<String> menuOptions = FXCollections.observableArrayList("Topic 1", "Topic 2", "Topic 3");
	ChangeListener menuListener = new ChangeListener() 
	{
		
		@Override
		public void changed(ObservableValue observable, Object oldValue, Object newValue) 
		{
						
			if (observable.getValue().equals("Topic 1")) 
			{
				quizBuilder = new QuizOneBuilder();

			}
			
			if (observable.getValue().equals("Topic 2")) 
			{
				quizBuilder = new QuizTwoBuilder();

			}
			
			if (observable.getValue().equals("Topic 3")) 
			{
				quizBuilder = new QuizThreeBuilder();

			}		
			
			content.setQuizBuilder(quizBuilder);
			content.generateNewQuiz();
			questionnaire = content.getQuestionnaire();
			displayQuestions.setText(questionnaire.displayQuestions());
		}
		
	};
			
	
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
		scene = new Scene(root,1400,800);
		scene.getStylesheets().add(stylesheet);
	
		stage.setTitle("Plants growth stages");
		stage.setScene(scene);
		stage.show();
		
		canvas = new Canvas(1400,800);
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
		
		resetButton = new Button("Start again!");
		resetButton.setLayoutX(700);
		resetButton.setLayoutY(700);
		resetButton.setOnAction(this);
		
		gameObjects.add(new Seed(200,450, gc));
		droplets.add(new Cloud(1, 1, gc));
		droplets.add(new Cloud(405, 1, gc));
		
		soil = new Rectangle(150, 5);
		soil.setFill(Paint.valueOf("#3f2828"));
		soil.setLayoutX(190);
		soil.setLayoutY(545);
		
		grass = new Rectangle(650, 100);
		grass.setFill(Paint.valueOf("#144e14"));
		grass.setLayoutX(10);
		grass.setLayoutY(550);
		
		plantName = new Label("Seed");
		plantName.setLayoutX(390);
		plantName.setLayoutY(510);
		
		displayDays = new Label("");
		displayDays.setLayoutX(550);
		displayDays.setLayoutY(710);
		
		informationFactory = new InformationFactory();
			
		displayInfo = new Text(informationFactory.getInformation(0).displayTextInfo());
		displayInfo.setLayoutX(450);
		displayInfo.setLayoutY(200);
		displayInfo.wrappingWidthProperty().set(200);
		displayInfo.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		displayInfo.setTextAlignment(TextAlignment.JUSTIFY);
		
		sourceOne = new Text(700, 200, "DRAG ME 1");
		sourceTwo = new Text(700, 250, "DRAG ME 2");
		sourceThree = new Text(700, 300, "DRAG ME 3");
		sourceFour = new Text(700, 350, "DRAG ME 4");
		
      	targetOne = new Text(1000, 150, "______________");
      	targetTwo = new Text(1000, 250, "______________");
      	targetThree = new Text(1000, 350, "______________");
      	targetFour = new Text(1000, 450, "______________");
      	
      	
      	menu = new ComboBox<String>(menuOptions);
      	menu.setLayoutX(700);
      	menu.setLayoutY(10);
      	menu.setValue("Choose topic");
      	
      	menu.getSelectionModel().selectedItemProperty().addListener(menuListener);
      	
      	// Quiz generator
      	content = new ContentCreator();
      	
      	// Display questions area
      	displayQuestions = new Text("Select a topic and answer the questions");
      	displayQuestions.setLayoutX(900);
      	displayQuestions.setLayoutY(100);
      	displayQuestions.wrappingWidthProperty().set(450);
      	displayQuestions.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
      	displayQuestions.setTextAlignment(TextAlignment.JUSTIFY);
		
        root.getChildren()
        	.addAll(canvas, rainButton, daysButton, resetButton, plantName, 
        			soil, grass, displayDays, displayInfo, sourceOne, sourceTwo, sourceThree, sourceFour,
        			targetOne, targetTwo, targetThree, targetFour, displayQuestions, menu);

        // Source 1, Target 1
     	DragAndDropEventGenerator.DragAndDropCreator(sourceOne, targetOne);
     	// Source 2, Target 2
    	DragAndDropEventGenerator.DragAndDropCreator(sourceTwo, targetTwo);
    	// Source 3, Target 3
    	DragAndDropEventGenerator.DragAndDropCreator(sourceThree, targetThree);
    	// Source 3, Target 3
    	DragAndDropEventGenerator.DragAndDropCreator(sourceFour, targetFour);
        
						
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
				
					@Override
					public void handle(MouseEvent mouseEvent) 
					{
						noOfDays++;
																					
						if(noOfDays >= 0 && noOfDays <= 6)
						{
							plantName.setText(informationFactory.getInformation(noOfDays).displayName());
							displayDays.setText(Integer.toString(noOfDays) + " days");
							displayInfo.setText(informationFactory.getInformation(noOfDays).displayTextInfo());
						}
						
						else
						{
							plantName.setText("");
							displayDays.setText("");
							displayInfo.setText("");
						}					

					}			
					
				});
				
			}
		
		}	
		
		if(event.getSource() == this.resetButton)
		{
			sourceOne.setText("DRAG ME AGAIN 1");
			targetOne.setText("______________");
			sourceTwo.setText("DRAG ME AGAIN 2");
			targetTwo.setText("______________");
			sourceThree.setText("DRAG ME AGAIN 3");
			targetThree.setText("______________");
			sourceFour.setText("DRAG ME AGAIN 4");
			targetFour.setText("______________");
			
			menu.setValue("Choose topic");
			
			displayQuestions.setText("Select a topic and answer the questions");
			
		}
		
	}

}
