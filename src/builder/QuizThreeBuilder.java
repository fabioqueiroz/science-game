package builder;

public class QuizThreeBuilder extends QuizBuilder
{
	@Override
	public void createQuestionOne() 
	{
		questionnaire.setQuestionOne("1) quiz 3, question 1");
		
	}

	@Override
	public void createQuestionTwo() 
	{
		questionnaire.setQuestionTwo("2) quiz 3,  question 2");
		
	}

	@Override
	public void createQuestionThree() 
	{
		questionnaire.setQuestionThree("3) quiz 3, question 3");
		
	}

	@Override
	public void createQuestionFour() 
	 {
		questionnaire.setQuestionFour("4) quiz 3,  question 4");
		
	}

	@Override
	public void createAnswerOne()
	{
		questionnaire.setAnswerOne("ANSWER 3.1");
	}
	
	@Override
	public void createAnswerTwo()
	{
		questionnaire.setAnswerTwo("ANSWER 3.2");
	}
	
	@Override
	public void createAnswerThree()
	{
		questionnaire.setAnswerThree("ANSWER 3.3");
	}
	
	@Override
	public void createAnswerFour()
	{
		questionnaire.setAnswerFour("ANSWER 3.4");
	}
}
