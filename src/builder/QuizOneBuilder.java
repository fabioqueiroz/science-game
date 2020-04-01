package builder;

public class QuizOneBuilder extends QuizBuilder
{

	@Override
	void createQuestionOne() 
	{
		questionnaire.setQuestionOne("1) quiz 1, question 1");
		
	}

	@Override
	void createQuestionTwo() 
	{
		questionnaire.setQuestionTwo("2) quiz 1,  question 2");
		
	}

	@Override
	void createQuestionThree() 
	{
		questionnaire.setQuestionThree("3) quiz 1, question 3");
		
	}

	@Override
	void createQuestionFour() 
	 {
		questionnaire.setQuestionFour("2) quiz 1,  question 4");
		
	}
	
}
