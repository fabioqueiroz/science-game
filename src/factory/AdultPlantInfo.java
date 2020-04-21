package factory;

import interfaces.PlantInfoInterface;

/*
 * This class is responsible for generating
 * the information about the adult plant.
 * 
 */

public class AdultPlantInfo implements PlantInfoInterface
{
	@Override
	public String displayName() 
	{
		return "Adult Plant";
	}
	@Override
	public String displayTextInfo() 
	{
		return "Adult plant: It's now mature and has the ability to reproduce through spores or flowers. "
				+ "After flowers are pollinated, they get bigger and turn into fruit with seeds inside, "
				+ "so the cycle can start again.";
	}

}
