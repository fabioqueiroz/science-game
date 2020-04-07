package mvc;

import builder.ContentCreator;
import builder.Questionnaire;
import builder.QuizBuilder;
import crosscutting.ComponentGenerator;
import crosscutting.DragAndDropEventGenerator;
import factory.InformationFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class GameView 
{
	FlowPane root;
	Pane plantArea, menuArea, quizArea;
	StackPane infoShadowPane, shadowBoxPane;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	Button rainButton, daysButton, resetButton, checkAnswerButton;
	Label plantName, displayDays, marks;
	Text displayInfo, displayQuestions;
	Text sourceOne, sourceTwo, sourceThree, sourceFour, targetOne, targetTwo, targetThree, targetFour;
	Rectangle soil, grass, test, infoShadowRectangle, quizShadowRectangle;
	InformationFactory informationFactory;
	ImageView greenGrass;
	ComboBox<String> menu;
	ContentCreator content;
	QuizBuilder quizBuilder;
	Questionnaire questionnaire;	
	
	boolean isRainButtonClicked;
	int noOfDays;
	
	GameObjectModel model;
		
	ObservableList<String> menuOptions = FXCollections.observableArrayList("General", "Leaves", "Energy");
	
	@SuppressWarnings("static-access")
	public GameView(FlowPane root, GameObjectModel model) 
	{
		this.root = root;
		this.model = model;
		
		plantArea = new Pane();
		plantArea.setPrefSize(700,800);
		
		menuArea = new Pane();
		menuArea.setPrefSize(200, 800);
		menuArea.setStyle("-fx-background-color: rgba(30, 35, 33, 0.6);");
		
		quizArea = new Pane();
		quizArea.setPrefSize(600, 800);
		quizArea.setStyle("-fx-background-color: white;");
		
		canvas = new Canvas(1500,800);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
		
		// Create UI buttons and layout
		rainButton = ComponentGenerator.createButton("Let it rain!", 50, 700);		
		daysButton = ComponentGenerator.createButton("Days counter", 350, 700);
		resetButton = ComponentGenerator.createButton("Start again!", 20, 700); 
		checkAnswerButton = ComponentGenerator.createButton("Check answers", 300, 700); 
		
		soil = ComponentGenerator.createRectangle(150, 25, "#3f2828", 190, 545);
		//grass = ComponentGenerator.createRectangle(650, 100, "#144e14", 10, 550);
		test = ComponentGenerator.createRectangle(5, 650, "#ffffc8", 700, 5);
		
		plantName = ComponentGenerator.createLabel("Seed", 390, 510);
		displayDays = ComponentGenerator.createLabel("", 550, 710);
		marks = ComponentGenerator.createLabel("", 100, 710);
		
		greenGrass = new ImageView(new Image("resources/grass.png"));
		greenGrass.setFitHeight(300);
		greenGrass.setFitWidth(700);
		greenGrass.setLayoutY(330);


			
		// Allow specific information to be displayed
		informationFactory = new InformationFactory();
			
		displayInfo = new Text(informationFactory.getInformation(0).displayTextInfo());
		ComponentGenerator.createTextBlock(displayInfo, 450, 200, 200, 20);
		
		// Create answers to be dragged
		sourceOne = ComponentGenerator.createText(40, 200, "", "source");
		sourceTwo = ComponentGenerator.createText(40, 250, "", "source");
		sourceThree = ComponentGenerator.createText(40, 300, "", "source");
		sourceFour = ComponentGenerator.createText(40, 350, "", "source"); 
		
		// Create target destinations
		targetOne = ComponentGenerator.createText(200, 400, "", "target");
      	targetTwo = ComponentGenerator.createText(200, 450, "", "target");
      	targetThree = ComponentGenerator.createText(200, 500, "", "target");
      	targetFour = ComponentGenerator.createText(200, 550, "", "target");

      	// Create quiz menu
      	menu = new ComboBox<String>(menuOptions);
      	menu.setLayoutX(10);
      	menu.setLayoutY(10);
      	menu.setValue("Choose quiz");
      	menu.setStyle("-fx-font-size: 20");
      	
      	// Quiz generator
      	content = new ContentCreator();
      	quizBuilder = null;
      	
      	// Display the questions area
      	displayQuestions = new Text("Select a topic and drag the answers");
      	ComponentGenerator.createTextBlock(displayQuestions, 100, 50, 450, 20);
      	
      	// Create a shadow effect display plant information
      	infoShadowPane = new StackPane();
      	infoShadowPane.setLayoutX(420); 
      	infoShadowPane.setLayoutY(130); 
      	infoShadowRectangle = ComponentGenerator.createShadowEffectRectangle(250, 350, 1, 200, 300, 15, 15, 10);
      	
      	// Create a shadow effect display for the questions
      	shadowBoxPane = new StackPane();
      	shadowBoxPane.setLayoutX(40); 
      	quizShadowRectangle = ComponentGenerator.createShadowEffectRectangle(450, 620, 1, 20, 30, 15, 15, 10);
     
     	// Build the solution     	
      	// Plant pane
      	infoShadowPane.getChildren().addAll(infoShadowRectangle, displayInfo);
      	plantArea.getChildren().addAll(canvas, rainButton, daysButton, plantName, soil, greenGrass, displayDays, infoShadowPane); // grass
      	      	
      	// Menu pane
      	menuArea.getChildren().addAll(menu, resetButton, sourceOne, sourceTwo, sourceThree, sourceFour);
      	
      	// Quiz pane
      	shadowBoxPane.getChildren().addAll(quizShadowRectangle, displayQuestions); 
      	shadowBoxPane.setAlignment(displayQuestions, Pos.TOP_CENTER);
      	
      	quizArea.getChildren().addAll(shadowBoxPane, targetOne, targetTwo, targetThree, targetFour, marks, checkAnswerButton);
      	     	
      	// Flow pane
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
	
}
