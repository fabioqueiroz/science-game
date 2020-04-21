package builder;

/*
 * This class is responsible for coordinating
 * the concrete realisation of the classes involved
 * in the implementation of the Builder Pattern.
 * 
 */

public class ContentCreator 
{
	private QuizBuilder quizBuilder;
	
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
