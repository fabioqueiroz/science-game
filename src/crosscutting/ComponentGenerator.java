package crosscutting;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ComponentGenerator 
{
	public static Button createButton(String title, double layoutX, double layoutY)
	{
		Button button = new Button(title);	
		button.setLayoutX(layoutX);
		button.setLayoutY(layoutY);
		
		return button;
	}
	
	public static Rectangle createRectangle(double width, double height, String color, double layoutX, double layoutY)
	{
		Rectangle rectangle = new Rectangle(width, height);
		rectangle.setFill(Paint.valueOf(color));
		rectangle.setLayoutX(layoutX);
		rectangle.setLayoutY(layoutY);
		
		return rectangle;
	}
	
	public static Label createLabel(String title, double layoutX, double layoutY)
	{
		Label label = new Label(title);
		label.setLayoutX(layoutX);
		label.setLayoutY(layoutY);
		
		return label;
	}
	
	public static void createTextBlock(Text text, double layoutX, double layoutY, int maxWidth, int fontSize)
	{
		text.setLayoutX(layoutX);
		text.setLayoutY(layoutY);
		text.wrappingWidthProperty().set(maxWidth);
		text.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, fontSize));
		text.setTextAlignment(TextAlignment.JUSTIFY);

	}
	
	public static Text createText(double x, double y, String title)
	{
		Text text = new Text(x, y, title);
		text.setId("quiz");
		
		return text;
	}
	

}
