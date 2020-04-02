package builder;

public class QuizTwoBuilder extends QuizBuilder
{

	@Override
	public void createQuestionOne() 
	{
		questionnaire.setQuestionOne("1) quiz 2, question 1");
		
	}

	@Override
	public void createQuestionTwo() 
	{
		questionnaire.setQuestionTwo("2) quiz 2,  question 2");
		
	}

	@Override
	public void createQuestionThree() 
	{
		questionnaire.setQuestionThree("3) quiz 2, question 3");
		
	}

	@Override
	public void createQuestionFour() 
	 {
		questionnaire.setQuestionFour("4) quiz 2,  question 4");
		
	}

	@Override
	public void createAnswerOne()
	{
		questionnaire.setAnswerOne("ANSWER 2.1");
	}
	
	@Override
	public void createAnswerTwo()
	{
		questionnaire.setAnswerTwo("ANSWER 2.2");
	}
	
	@Override
	public void createAnswerThree()
	{
		questionnaire.setAnswerThree("ANSWER 2.3");
	}
	
	@Override
	public void createAnswerFour()
	{
		questionnaire.setAnswerFour("ANSWER 2.4");
	}

}
