package builder;

public class QuizOneBuilder extends QuizBuilder
{

	@Override
	public void createQuestionOne() 
	{
		questionnaire.setQuestionOne("1) quiz 1, question 1");
		
	}
	
	@Override
	public void createQuestionTwo() 
	{
		questionnaire.setQuestionTwo("2) quiz 1,  question 2");
		
	}

	@Override
	public void createQuestionThree() 
	{
		questionnaire.setQuestionThree("3) quiz 1, question 3");
		
	}

	@Override
	public void createQuestionFour() 
	 {
		questionnaire.setQuestionFour("4) quiz 1,  question 4");
		
	}
	
	@Override
	public void createAnswerOne()
	{
		questionnaire.setAnswerOne("ANSWER 1.1");
	}
	
	@Override
	public void createAnswerTwo()
	{
		questionnaire.setAnswerTwo("ANSWER 1.2");
	}
	
	@Override
	public void createAnswerThree()
	{
		questionnaire.setAnswerThree("ANSWER 1.3");
	}
	
	@Override
	public void createAnswerFour()
	{
		questionnaire.setAnswerFour("ANSWER 1.4");
	}
	
}
