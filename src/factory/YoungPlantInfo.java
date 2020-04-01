package factory;

import interfaces.InformationInterface;

public class YoungPlantInfo implements InformationInterface
{
	@Override
	public String displayName() 
	{
		return "Young Plant";
	}
	
	@Override
	public String displayTextInfo() 
	{
		return "Young plant: lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Ut consequat sem diam, et tincidunt risus egestas vitae. "
				+ "Sed pretium convallis leo, sagittis malesuada nisi dignissim ac.";
	}

}
