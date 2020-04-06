package factory;

import interfaces.InformationInterface;

public class SeedInfo implements InformationInterface
{
	@Override
	public String displayName() 
	{
		return "Seed";
	}

	@Override
	public String displayTextInfo() 
	{
		
		return "Seed: Plants come from seeds after they are planted in the ground. "
				+ "It's inside them that a process called germination occurs, in"
				+ "which they take oxygen and minerals from the soil and water to grow.";
	}

	

}
