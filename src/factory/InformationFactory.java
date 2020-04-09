package factory;

import interfaces.InfoFactoryInterface;
import interfaces.PlantInfoInterface;

public class InformationFactory implements InfoFactoryInterface
{
	PlantInfoInterface info;
	
	public PlantInfoInterface createInformation(int age)
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
