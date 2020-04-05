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
import crosscutting.QuizValidator;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
	FlowPane root;
	//Pane root;
	Pane plantArea, menuArea, quizArea;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	Button rainButton, daysButton, resetButton, checkAnswerButton;
	Label plantName, displayDays, marks;
	Text displayInfo, displayQuestions;
	Text sourceOne, sourceTwo, sourceThree, sourceFour, targetOne, targetTwo, targetThree, targetFour;
	Rectangle soil, grass, test, shadowRectangle;
	InformationFactory informationFactory;
	ComboBox<String> menu;
	ContentCreator content;
	QuizBuilder quizBuilder;
	Questionnaire questionnaire;
	StackPane shadowBoxPane;
	
	boolean isRainButtonClicked;
	int noOfDays;
	
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	ArrayList<GameObject> droplets = new ArrayList<GameObject>();
	
	//HashMap<String, ArrayList<String>> answers = new HashMap<String, ArrayList<String>>();
	
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
			
			
			for (int i = 0; i < questionnaire.getAnswers().size(); i++) 
			{
				sourceOne.setText(questionnaire.getAnswers().get(0));
				sourceTwo.setText(questionnaire.getAnswers().get(1));
				sourceThree.setText(questionnaire.getAnswers().get(2));
				sourceFour.setText(questionnaire.getAnswers().get(3));
			}
			
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
		
//		root = new Pane();
//		scene = new Scene(root,1500,800);
//		scene.getStylesheets().add(stylesheet);
		
		root = new FlowPane();
		scene = new Scene(root,1500,800);
		scene.getStylesheets().add(stylesheet);
	
		stage.setTitle("Plant growth stages");
		stage.setScene(scene);
		stage.show();
		
		plantArea = new Pane();
		plantArea.setPrefSize(700,800);
		
		menuArea = new Pane();
		menuArea.setPrefSize(200, 800);
		menuArea.setStyle("-fx-background-color: rgba(43, 43, 43, 0.5);");
		
		quizArea = new Pane();
		quizArea.setPrefSize(600, 800);
		quizArea.setStyle("-fx-background-color: white;");
		
		canvas = new Canvas(1500,800);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
		
		// Create UI buttons and layout
		rainButton = ComponentGenerator.createButton("Let it rain!", 50, 700);
		rainButton.setOnAction(this);
		
		daysButton = ComponentGenerator.createButton("Days counter", 350, 700);
		daysButton.setOnAction(this);
		
		resetButton = ComponentGenerator.createButton("Start again!", 20, 700); // 700
		resetButton.setOnAction(this);
		
		checkAnswerButton = ComponentGenerator.createButton("Check answers", 300, 700); // 1200
		checkAnswerButton.setOnAction(this);
		
		soil = ComponentGenerator.createRectangle(150, 5, "#3f2828", 190, 545);
		grass = ComponentGenerator.createRectangle(650, 100, "#144e14", 10, 550);
		test = ComponentGenerator.createRectangle(5, 650, "#ffffc8", 700, 5);
		
		plantName = ComponentGenerator.createLabel("Seed", 390, 510);
		displayDays = ComponentGenerator.createLabel("", 550, 710);
		marks = ComponentGenerator.createLabel("", 300, 650);
		
		// Add new objects to the array lists
		gameObjects.add(new Seed(200,450, gc));
		droplets.add(new Cloud(1, 1, gc));
		droplets.add(new Cloud(405, 1, gc)); 
		
		// Allow specific information to be displayed
		informationFactory = new InformationFactory();
			
		displayInfo = new Text(informationFactory.getInformation(0).displayTextInfo());
		ComponentGenerator.createTextBlock(displayInfo, 450, 200, 200, 20);
		
		// Create answers to be dragged
		sourceOne = ComponentGenerator.createText(50, 200, "DRAG ME 1", "source"); // 750
		sourceTwo = ComponentGenerator.createText(50, 250, "DRAG ME 2", "source");
		sourceThree = ComponentGenerator.createText(50, 300, "DRAG ME 3", "source");
		sourceFour = ComponentGenerator.createText(50, 350, "DRAG ME 4", "source"); 
		
		// Create target destinations
		targetOne = ComponentGenerator.createText(200, 450, "", "target"); // 100
      	targetTwo = ComponentGenerator.createText(200, 500, "", "target");
      	targetThree = ComponentGenerator.createText(200, 550, "", "target");
      	targetFour = ComponentGenerator.createText(200, 600, "", "target");

      	// Create quiz menu
      	menu = new ComboBox<String>(menuOptions);
      	menu.setLayoutX(10); // 700
      	menu.setLayoutY(10);
      	menu.setValue("Choose quiz");
      	menu.setStyle("-fx-font-size: 20");
      	
      	menu.getSelectionModel().selectedItemProperty().addListener(menuListener);
      	
      	// Quiz generator
      	content = new ContentCreator();
      	quizBuilder = null;
      	
      	// Display the questions area
      	displayQuestions = new Text("Select a topic and drag the answers");
      	ComponentGenerator.createTextBlock(displayQuestions, 100, 50, 450, 20); // 900
      	displayQuestions.setStyle("-fx-border-style: solid;");
      	
      	// Create a shadow effect display for the questions
      	shadowBoxPane = new StackPane();
      	shadowBoxPane.setLayoutX(40);
      	shadowRectangle = ComponentGenerator.createShadowEffectRectangle();

		
//      	// Build the solution
//        root.getChildren()
//        	.addAll(canvas, rainButton, daysButton, resetButton, checkAnswerButton, plantName, 
//        			soil, grass, displayDays, displayInfo, sourceOne, sourceTwo, sourceThree, sourceFour,
//        			targetOne, targetTwo, targetThree, targetFour, displayQuestions, menu, marks); // test
        
     
     	// Build the solution     	
      	// plant pane
      	plantArea.getChildren().addAll(canvas, rainButton, daysButton, plantName, soil, grass, displayDays, displayInfo);
      	
      	// menu pane
      	menuArea.getChildren().addAll(menu, resetButton, sourceOne, sourceTwo, sourceThree, sourceFour);
      	
      	// quiz pane
      	shadowBoxPane.getChildren().addAll(shadowRectangle, displayQuestions);
      	quizArea.getChildren().addAll(shadowBoxPane, targetOne, targetTwo, targetThree, targetFour, marks, checkAnswerButton); // displayQuestions
      	     	
      	// flow pane
      	root.getChildren().addAll(plantArea, menuArea, quizArea);
              

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
			
			sourceOne.setText("");
			sourceTwo.setText("");
			sourceThree.setText("");
			sourceFour.setText("");

			menu.setValue("Choose quiz");
			
			displayQuestions.setText("Select a topic and answer the questions");
			marks.setText("");
			
		}
		
		if(event.getSource() == this.checkAnswerButton && quizBuilder != null)
		{						
			int result = QuizValidator.checkMatch(targetOne, targetTwo, targetThree, targetFour, questionnaire.getAnswers());
			marks.setText("Marks: " + result + "%");
		}
		
	}

}
