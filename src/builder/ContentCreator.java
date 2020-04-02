package builder;

public class ContentCreator 
{
	QuizBuilder quizBuilder;
	
	public Questionnaire getQuestionnaire() 
	{
		return quizBuilder.getQuestionnaire();
	}
	
	public void setQuizBuilder(QuizBuilder quizBuilder) 
	{
		this.quizBuilder = quizBuilder;
	}
	
	public void generateNewQuiz()
	{
		quizBuilder.createNewQuestionnaire();
		
		quizBuilder.createQuestionOne();
		quizBuilder.createQuestionTwo();
		quizBuilder.createQuestionThree();
		quizBuilder.createQuestionFour();
		
		quizBuilder.createAnswerOne();
		quizBuilder.createAnswerTwo();
		quizBuilder.createAnswerThree();
		quizBuilder.createAnswerFour();
	}
}
