package builder;

/*
 * This class is responsible for defining the questions
 * and answers of a quiz.
 * 
 */

public class QuizTwoBuilder extends QuizBuilder
{

	@Override
	public void createQuestionOne() 
	{
		questionnaire.setQuestionOne("1) What makes the leaves green?");
		
	}

	@Override
	public void createQuestionTwo() 
	{
		questionnaire.setQuestionTwo("2) What's the name of the tiny holes on the leaf?");
		
	}

	@Override
	public void createQuestionThree() 
	{
		questionnaire.setQuestionThree("3) Type of tree that loses its leaves every fall and grows new leaves each spring.");
		
	}

	@Override
	public void createQuestionFour() 
	 {
		questionnaire.setQuestionFour("4) Part of the seed that becomes the first leaves of a plant.");
		
	}

	@Override
	public void createAnswerOne()
	{
		questionnaire.setAnswerOne("Stoma");
	}
	
	@Override
	public void createAnswerTwo()
	{
		questionnaire.setAnswerTwo("Chlorophyll");
	}
	
	@Override
	public void createAnswerThree()
	{
		questionnaire.setAnswerThree("Epicotyl");
	}
	
	@Override
	public void createAnswerFour()
	{
		questionnaire.setAnswerFour("Deciduous");
	}

}
