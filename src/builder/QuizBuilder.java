package builder;

public abstract class QuizBuilder 
{
	Questionnaire questionnaire;
	
	abstract void createQuestionOne();
	abstract void createQuestionTwo();
	abstract void createQuestionThree();
	abstract void createQuestionFour();
	
	abstract void createAnswerOne();
	abstract void createAnswerTwo();
	abstract void createAnswerThree();
	abstract void createAnswerFour();
	
	public Questionnaire getQuestionnaire() 
	{
		return questionnaire;
	}
	
	public void createNewQuestionnaire()
	{
		questionnaire = new Questionnaire();
	}

}
