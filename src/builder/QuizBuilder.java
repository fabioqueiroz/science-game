package builder;

public abstract class QuizBuilder 
{
	Questionnaire questionnaire;
	
	abstract void createQuestionOne();
	abstract void createQuestionTwo();
	abstract void createQuestionThree();
	abstract void createQuestionFour();
	
	public Questionnaire getQuestionnaire() 
	{
		return questionnaire;
	}
	
	public void createNewQuestionnaire()
	{
		questionnaire = new Questionnaire();
	}

}
