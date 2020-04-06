package factory;

import interfaces.InformationInterface;

public class InformationFactory 
{
	InformationInterface info;
	
	public InformationInterface getInformation(int age)
	{
		if (age >= 0) 
		{
			info = new SeedInfo();
			
		} 
		
		if (age >= 2 && age < 4) 
		{
			info = new SproutInfo();
			
		} 
		if (age >= 4 && age < 6) 
		{
			info = new YoungPlantInfo();
			
		} 
		else if (age >= 6) 
		{
			info = new AdultPlantInfo();
			
		} 
		
		return info;
	}

}
