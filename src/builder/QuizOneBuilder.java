package builder;

public class QuizOneBuilder extends QuizBuilder
{

	@Override
	public void createQuestionOne() 
	{
		questionnaire.setQuestionOne("1) The scientific study of plant life is known as what?");
		
	}
	
	@Override
	public void createQuestionTwo() 
	{
		questionnaire.setQuestionTwo("2) The process of plants using energy from sunlight is known as what?");
		
	}

	@Override
	public void createQuestionThree() 
	{
		questionnaire.setQuestionThree("3) Name the movement of pollen from the anthers to the stigma of a flower.");
		
	}

	@Override
	public void createQuestionFour() 
	 {
		questionnaire.setQuestionFour("4) Name an agent that can transport pollen.");
		
	}
	
	@Override
	public void createAnswerOne()
	{
		questionnaire.setAnswerOne("Photosynthesis");
	}
	
	@Override
	public void createAnswerTwo()
	{
		questionnaire.setAnswerTwo("Botany");
	}
	
	@Override
	public void createAnswerThree()
	{
		questionnaire.setAnswerThree("Wind");
	}
	
	@Override
	public void createAnswerFour()
	{
		questionnaire.setAnswerFour("Pollination"); 
	}
	
}
