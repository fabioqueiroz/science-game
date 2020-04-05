package mvc;

import java.util.Random;

import builder.QuizOneBuilder;
import builder.QuizThreeBuilder;
import builder.QuizTwoBuilder;
import crosscutting.QuizValidator;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import scienceproject.Cloud;
import scienceproject.GameObject;
import scienceproject.Seed;
import scienceproject.WaterDroplet;

@SuppressWarnings("rawtypes")
public class GameController implements EventHandler
{
	GameObjectModel model;
	GameView view;
	
	Random rnd = new Random(System.currentTimeMillis());
	int count = 0;
	
	@SuppressWarnings("unchecked")
	public GameController(GameObjectModel model, GameView view) 
	{
		this.model = model;
		this.view = view;
		this.view.rainButton.setOnAction(this);
		this.view.daysButton.setOnAction(this);
		this.view.resetButton.setOnAction(this);
		this.view.checkAnswerButton.setOnAction(this);
		this.view.menu.getSelectionModel().selectedItemProperty().addListener(menuListener);
		this.model.addPlant(200, 450, view.gc);
		this.model.addCloud(1, 1, view.gc);
		this.model.addCloud(405, 1, view.gc);
	}

	@Override
	public void handle(Event event) 
	{
		
		if(event.getSource() == this.view.rainButton)
		{
			timer.start();
					
			view.isRainButtonClicked = true;	
			
		}
	
		if(event.getSource() == this.view.daysButton)
		{
			
			if (view.isRainButtonClicked) 
			{
				
				for (GameObject gameObject : model.gameObjects) 
				{
					((Seed)gameObject).changeGrowthStage();
				}
				
				
				this.view.daysButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
				{
				
					@Override
					public void handle(MouseEvent mouseEvent) 
					{
						view.noOfDays++;
																					
						if(view.noOfDays >= 0 && view.noOfDays <= 6)
						{
							view.plantName.setText(view.informationFactory.getInformation(view.noOfDays).displayName());
							view.displayDays.setText(Integer.toString(view.noOfDays) + " days");
							view.displayInfo.setText(view.informationFactory.getInformation(view.noOfDays).displayTextInfo());
						}
						
						else
						{
							view.plantName.setText("");
							view.displayDays.setText("");
							view.displayInfo.setText("");
						}					

					}			
					
				});
				
			}
		
		}	
		
		if(event.getSource() == this.view.resetButton && view.quizBuilder != null)
		{
			
			view.sourceOne.setText("");
			view.sourceTwo.setText("");
			view.sourceThree.setText("");
			view.sourceFour.setText("");

			view.menu.setValue("Choose quiz");
			
			view.displayQuestions.setText("Select a topic and answer the questions");
			view.marks.setText("");
			
		}
		
		if(event.getSource() == this.view.checkAnswerButton && view.quizBuilder != null)
		{						
			int result = QuizValidator.checkMatch(view.targetOne, view.targetTwo, view.targetThree, view.targetFour, view.questionnaire.getAnswers());
			view.marks.setText("Marks: " + result + "%");
		}
		
	}
	
	AnimationTimer timer = new AnimationTimer() 
	{

		@Override
		public void handle(long arg0) 
		{

			if(count++ > 10) 
			{
				model.droplets.add(new Cloud(rnd.nextInt(1),rnd.nextInt(1), view.gc));
				model.droplets.add(new WaterDroplet(rnd.nextInt(400),rnd.nextInt(200), view.gc));
				count = 0;
			}
					
			for (GameObject droplet : model.droplets)
			{
				
				for (GameObject gameObject : model.gameObjects) 
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
	
	ChangeListener menuListener = new ChangeListener() 
	{
		
		@Override
		public void changed(ObservableValue observable, Object oldValue, Object newValue) 
		{
						
			if (observable.getValue().equals("Topic 1")) 
			{
				view.quizBuilder = new QuizOneBuilder();

			}
			
			if (observable.getValue().equals("Topic 2")) 
			{
				view.quizBuilder = new QuizTwoBuilder();

			}
			
			if (observable.getValue().equals("Topic 3")) 
			{
				view.quizBuilder = new QuizThreeBuilder();

			}		
			
			view.content.setQuizBuilder(view.quizBuilder);
			view.content.generateNewQuiz();
			view.questionnaire = view.content.getQuestionnaire();
			view.displayQuestions.setText(view.questionnaire.displayQuestions());
			
			
			for (int i = 0; i < view.questionnaire.getAnswers().size(); i++) 
			{
				view.sourceOne.setText(view.questionnaire.getAnswers().get(0));
				view.sourceTwo.setText(view.questionnaire.getAnswers().get(1));
				view.sourceThree.setText(view.questionnaire.getAnswers().get(2));
				view.sourceFour.setText(view.questionnaire.getAnswers().get(3));
			}
			
			view.targetOne.setText("1) ______________");
			view.targetTwo.setText("2) ______________");
			view.targetThree.setText("3) ______________");
			view.targetFour.setText("4) ______________");
	      	
		}
	};

}
