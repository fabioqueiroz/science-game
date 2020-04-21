package scienceproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import mvc.GameController;
import mvc.GameObjectModel;
import mvc.GameView;

/*
 * This class is responsible for launching
 * the application.
 * 
 */

public class ScienceProject extends Application
{	
	private FlowPane root;
	private Scene scene;	
	private GameObjectModel model;
	private GameView view;
	@SuppressWarnings("unused")
	private GameController controller;

	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		String stylesheet = getClass().getResource("/resources/styles.css").toExternalForm();
		
		root = new FlowPane();
		scene = new Scene(root,1500,800);
		scene.getStylesheets().add(stylesheet);
	
		stage.setTitle("Plant Growth Stages");
		stage.setScene(scene);
		stage.show();
				
		model = new GameObjectModel();
		view = new GameView(root, model);
		controller = new GameController(model, view);
			
	}

}
