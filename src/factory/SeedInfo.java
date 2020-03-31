package factory;

import Interfaces.InformationInterface;

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
		
		return "Seed: lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Ut consequat sem diam, et tincidunt risus egestas vitae. "
				+ "Sed pretium convallis leo, sagittis malesuada nisi dignissim ac.";
	}

	

}
