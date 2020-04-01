package scienceproject;

import java.util.*;

import builder.ContentCreator;
import builder.Questionnaire;
import builder.QuizBuilder;
import builder.QuizOneBuilder;
import factory.AdultPlantInfo;
import factory.InformationFactory;
import factory.SeedInfo;
import factory.SproutInfo;
import factory.YoungPlantInfo;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
	Label plantName, displayDays;// displayInfo;
	Text displayInfo, source, target, displayQuestions;
	Rectangle soil, grass;
	InformationFactory informationFactory;
	
	boolean isRainButtonClicked;
	int noOfDays;
	
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
			
		//displayInfo = new Label(informationFactory.getInformation(0).displayTextInfo());
		displayInfo = new Text(informationFactory.getInformation(0).displayTextInfo());
		displayInfo.setLayoutX(450);
		displayInfo.setLayoutY(200);
		//displayInfo.setMaxWidth(250);
		//displayInfo.setWrapText(true);
		displayInfo.wrappingWidthProperty().set(200);
		displayInfo.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		displayInfo.setTextAlignment(TextAlignment.JUSTIFY);
		
		source = new Text(700, 300, "DRAG ME");
      	target = new Text(1000, 300, "______________");
      	
      	ContentCreator content = new ContentCreator();
      	QuizBuilder quizBuilder = new QuizOneBuilder();
      	content.setQuizBuilder(quizBuilder);
      	content.generateNewQuiz();
      	Questionnaire questionnaire = content.getQuestionnaire();

      	displayQuestions = new Text(questionnaire.displayQuestions());
      	displayQuestions.setLayoutX(1000);
      	displayQuestions.setLayoutY(100);
      	displayQuestions.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
      	displayQuestions.setTextAlignment(TextAlignment.JUSTIFY);
		
        root.getChildren()
        	.addAll(canvas, rainButton, daysButton, resetButton, plantName, 
        			soil, grass, displayDays, displayInfo, source, target, displayQuestions);
        
        // Adapted from https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm
        source.setOnDragDetected(new EventHandler<MouseEvent>() 
		{
		    public void handle(MouseEvent event) 
		    {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
		        
		        /* Put a string on a dragboard */
		        ClipboardContent content = new ClipboardContent();
		        content.putString(source.getText());
		        db.setContent(content);
		        
		        event.consume();
		    }
		});

        source.setOnDragDone(new EventHandler<DragEvent>() 
        {
            public void handle(DragEvent event) 
            {
                /* the drag and drop gesture ended */
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) 
                {
                    source.setText("__________");
                }
                event.consume();
            }
        });

        target.setOnDragOver(new EventHandler<DragEvent>() 
		{
		    public void handle(DragEvent event) 
		    {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target &&
		                event.getDragboard().hasString()) 
		        {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});

		target.setOnDragEntered(new EventHandler<DragEvent>() 
		{
		    public void handle(DragEvent event) 
		    {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target &&
		                 event.getDragboard().hasString()) 
		         {
		             target.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});

		target.setOnDragExited(new EventHandler<DragEvent>() 
		{
		    public void handle(DragEvent event) 
		    {
		        /* mouse moved away, remove the graphical cues */
		        target.setFill(Color.BLACK);

		        event.consume();
		    }
		});

		target.setOnDragDropped(new EventHandler<DragEvent>() 
		{
		    public void handle(DragEvent event) 
		    {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) 
		        {
		           target.setText(db.getString());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
						
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
			source.setText("DRAG ME AGAIN");
			target.setText("______________");
			
		}
		
	}

}
