package builder;

import java.util.ArrayList;

public class Questionnaire 
{
	private String questionOne, questionTwo, questionThree, questionFour;
	private String answerOne, answerTwo, answerThree, answerFour;
	
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
	
	public String getAnswerOne() 
	{
		return answerOne;
	}
	
	public String getAnswerTwo() 
	{
		return answerTwo;
	}
	
	public String getAnswerThree() 
	{
		return answerThree;
	}
	
	public String getAnswerFour() 
	{
		return answerFour;
	}
	
	public void setAnswerOne(String answerOne) 
	{
		this.answerOne = answerOne;
	}
	
	public void setAnswerTwo(String answerTwo) 
	{
		this.answerTwo = answerTwo;
	}
	
	public void setAnswerThree(String answerThree)
	{
		this.answerThree = answerThree;
	}
	
	public void setAnswerFour(String answerFour) 
	{
		this.answerFour = answerFour;
	}

	public String displayQuestions()
	{
		return getQuestionOne() + "\n\n\n" + getQuestionTwo() + "\n\n\n" 
				+ getQuestionThree() + "\n\n\n" + getQuestionFour() + "\n\n\n" + "Answers: " + "\n";
	}
	
	public ArrayList<String> getAnswers()
	{
		ArrayList<String> answers = new ArrayList<String>();
		
		answers.add(getAnswerOne());
		answers.add(getAnswerTwo());
		answers.add(getAnswerThree());
		answers.add(getAnswerFour());
		
		return answers;
	}
	
}
