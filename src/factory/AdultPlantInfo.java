package factory;

import interfaces.InformationInterface;

public class AdultPlantInfo implements InformationInterface
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
