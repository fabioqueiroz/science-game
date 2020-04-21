package builder;

/*
 * This class is responsible for defining the questions
 * and answers of a quiz.
 * 
 */

public class QuizThreeBuilder extends QuizBuilder
{
	@Override
	public void createQuestionOne() 
	{
		questionnaire.setQuestionOne("1) What gas do plants absorb?");
		
	}

	@Override
	public void createQuestionTwo() 
	{
		questionnaire.setQuestionTwo("2) What gas do plants produce?");
		
	}

	@Override
	public void createQuestionThree() 
	{
		questionnaire.setQuestionThree("3) Name the source of energy for the leaves.");
		
	}

	@Override
	public void createQuestionFour() 
	 {
		questionnaire.setQuestionFour("4) The main purpose of a leaf is to gather...");
		
	}

	@Override
	public void createAnswerOne()
	{
		questionnaire.setAnswerOne("O2");
	}
	
	@Override
	public void createAnswerTwo()
	{
		questionnaire.setAnswerTwo("CO2");
	}
	
	@Override
	public void createAnswerThree()
	{
		questionnaire.setAnswerThree("Sunlight");
	}
	
	@Override
	public void createAnswerFour()
	{
		questionnaire.setAnswerFour("Glucose");
	}
}
