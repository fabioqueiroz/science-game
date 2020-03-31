package factory;

import Interfaces.InformationInterface;

public class SproutInfo implements InformationInterface
{
	@Override
	public String displayName() 
	{
		return "Sprout";
	}

	@Override
	public String displayTextInfo() 
	{
		return "Sprout: lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Ut consequat sem diam, et tincidunt risus egestas vitae. "
				+ "Sed pretium convallis leo, sagittis malesuada nisi dignissim ac.";
	}

}
