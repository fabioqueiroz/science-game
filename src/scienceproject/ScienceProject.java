package scienceproject;

import java.util.*;

import builder.ContentCreator;
import builder.Questionnaire;
import builder.QuizBuilder;
import builder.QuizOneBuilder;
import builder.QuizThreeBuilder;
import builder.QuizTwoBuilder;
import crosscutting.ComponentGenerator;
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
	Button rainButton, daysButton, resetButton, checkAnswerButton;
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
			
			targetOne.setText("1) ______________");
	      	targetTwo.setText("2) ______________");
	      	targetThree.setText("3) ______________");
	      	targetFour.setText("4) ______________");
	      	
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
		scene = new Scene(root,1500,800);
		scene.getStylesheets().add(stylesheet);
	
		stage.setTitle("Plant growth stages");
		stage.setScene(scene);
		stage.show();
		
		canvas = new Canvas(1500,800);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
		
		// Create UI buttons and layout
		rainButton = ComponentGenerator.createButton("Let it rain!", 50, 700);
		rainButton.setOnAction(this);
		
		daysButton = ComponentGenerator.createButton("Days counter", 350, 700);
		daysButton.setOnAction(this);
		
		resetButton = ComponentGenerator.createButton("Start again!", 700, 700);
		resetButton.setOnAction(this);
		
		checkAnswerButton = ComponentGenerator.createButton("Check answers", 1200, 700);
		checkAnswerButton.setOnAction(this);
		
		soil = ComponentGenerator.createRectangle(150, 5, "#3f2828", 190, 545);
		grass = ComponentGenerator.createRectangle(650, 100, "#144e14", 10, 550);
		
		plantName = ComponentGenerator.createLabel("Seed", 390, 510);
		displayDays = ComponentGenerator.createLabel("", 550, 710);
		
		// Add new objects to the array lists
		gameObjects.add(new Seed(200,450, gc));
		droplets.add(new Cloud(1, 1, gc));
		droplets.add(new Cloud(405, 1, gc)); 
		
		// Allow specific information to be displayed
		informationFactory = new InformationFactory();
			
		displayInfo = new Text(informationFactory.getInformation(0).displayTextInfo());
		ComponentGenerator.createTextBlock(displayInfo, 450, 200, 200, 20);
		
		
		sourceOne = new Text(700, 200, "DRAG ME 1");
		sourceTwo = new Text(700, 250, "DRAG ME 2");
		sourceThree = new Text(700, 300, "DRAG ME 3");
		sourceFour = new Text(700, 350, "DRAG ME 4");
		
		targetOne = new Text(900, 450, "");
      	targetTwo = new Text(900, 500, "");
      	targetThree = new Text(900, 550, "");
      	targetFour = new Text(900, 600, "");
      	
      	
      	menu = new ComboBox<String>(menuOptions);
      	menu.setLayoutX(700);
      	menu.setLayoutY(10);
      	menu.setValue("Choose quiz");
      	menu.setStyle("-fx-font-size: 20");
      	
      	menu.getSelectionModel().selectedItemProperty().addListener(menuListener);
      	
      	// Quiz generator
      	content = new ContentCreator();
      	quizBuilder = null;
      	
      	// Display the questions area
      	displayQuestions = new Text("Select a topic and answer the questions");
      	ComponentGenerator.createTextBlock(displayQuestions, 900, 100, 450, 20);
      	//displayQuestions.setId("quiz");
      	displayQuestions.setStyle("-fx-border-style: solid;");

		
      	// Build the solution
        root.getChildren()
        	.addAll(canvas, rainButton, daysButton, resetButton, checkAnswerButton, plantName, 
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
		
		if(event.getSource() == this.resetButton && quizBuilder != null)
		{
			sourceOne.setText("DRAG ME AGAIN 1");
			sourceTwo.setText("DRAG ME AGAIN 2");
			sourceThree.setText("DRAG ME AGAIN 3");
			sourceFour.setText("DRAG ME AGAIN 4");
			
//			targetOne.setText("");
//	      	targetTwo.setText("");
//	      	targetThree.setText("");
//	      	targetFour.setText("");
			
			menu.setValue("Choose quiz");
			
			displayQuestions.setText("Select a topic and answer the questions");
			
		}
		
		if(event.getSource() == this.checkAnswerButton)
		{
			//System.out.println("clicked");	
			
		}
		
	}

}
