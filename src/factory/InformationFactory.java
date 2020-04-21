package factory;

import interfaces.InfoFactoryInterface;
import interfaces.PlantInfoInterface;

/*
 * This class is responsible for generating
 * the concrete realisation of the classes involved
 * in the implementation of the Factory Pattern.
 * 
 */

public class InformationFactory implements InfoFactoryInterface
{
	private PlantInfoInterface info;
	
	public PlantInfoInterface createInformation(int age)
	{
		if (age >= 0) 
		{
			info = new SeedInfo();
			
		} 
		
		if (age >= 10 && age < 20) 
		{
			info = new SproutInfo();
			
		} 
		if (age >= 20 && age < 30) 
		{
			info = new YoungPlantInfo();
			
		} 
		else if (age >= 30) 
		{
			info = new AdultPlantInfo();
			
		} 
		
		return info;
	}

}
