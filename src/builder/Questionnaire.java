package builder;

public class Questionnaire 
{
	String questionOne, questionTwo, questionThree, questionFour;
	
	public String getQuestionOne() 
	{
		return questionOne;
	}
	
	public void setQuestionOne(String questionOne) 
	{
		this.questionOne = questionOne;
	}
	
	public String getQuestionTwo() 
	{
		return questionTwo;
	}
	
	public void setQuestionTwo(String questionTwo) 
	{
		this.questionTwo = questionTwo;
	}
	
	public String getQuestionThree() 
	{
		return questionThree;
	}
	
	public void setQuestionThree(String questionThree) 
	{
		this.questionThree = questionThree;
	}
	
	public String getQuestionFour() 
	{
		return questionFour;
	}
	
	public void setQuestionFour(String questionFour) 
	{
		this.questionFour = questionFour;
	}
	
	public String displayQuestions()
	{
		return getQuestionOne() + "\n" + getQuestionTwo() + "\n" + getQuestionThree() + "\n" + getQuestionFour() + "\n";
	}
}
