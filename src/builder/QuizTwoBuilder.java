package builder;

public class QuizTwoBuilder extends QuizBuilder
{

	@Override
	void createQuestionOne() 
	{
		questionnaire.setQuestionOne("1) quiz 2, question 1");
		
	}

	@Override
	void createQuestionTwo() 
	{
		questionnaire.setQuestionTwo("2) quiz 2,  question 2");
		
	}

	@Override
	void createQuestionThree() 
	{
		questionnaire.setQuestionThree("3) quiz 2, question 3");
		
	}

	@Override
	void createQuestionFour() 
	 {
		questionnaire.setQuestionFour("4) quiz 2,  question 4");
		
	}

}
