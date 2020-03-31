package factory;

import Interfaces.InformationInterface;

public class InformationFactory 
{
	InformationInterface info;
	
	public InformationInterface getInformation(int age)
	{
		if (age == 2) 
		{
			info = new SproutInfo();
			
		} 
		if (age == 4) 
		{
			info = new YoungPlantInfo();
			
		} 
		if (age == 6) 
		{
			info = new AdultPlantInfo();
			
		} 
		else 
		{
			info = new SeedInfo();
		}
		
		return info;
	}

}
