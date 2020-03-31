package factory;

import Interfaces.InformationInterface;

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
		return "Adult plant: lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Ut consequat sem diam, et tincidunt risus egestas vitae. "
				+ "Sed pretium convallis leo, sagittis malesuada nisi dignissim ac.";
	}

}
