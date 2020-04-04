package crosscutting;

import java.util.ArrayList;

import javafx.scene.text.Text;

public class QuizValidator 
{
	public static int checkMatch(Text targetOne, Text targetTwo, Text targetThree, Text targetFour, ArrayList<String> sourceTextAnswer)
	{
		int correct = 0;
		
		if(targetOne.getText().equals(sourceTextAnswer.get(1)))
		{
			correct++;
		}
		if(targetTwo.getText().equals(sourceTextAnswer.get(0)))
		{
			correct++;
		}
		if(targetThree.getText().equals(sourceTextAnswer.get(3)))
		{
			correct++;
		}
		if(targetFour.getText().equals(sourceTextAnswer.get(2)))
		{
			correct++;
		}
			
		return calculateMark(correct);
	}
	
	private static int calculateMark(int points)
	{
		return (points * 100)/4;
	}
}
