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
		return "Young plant: A typical young seedling consists of three main parts: the radicle (embryonic root), "
				+ "the hypocotyl (embryonic shoot), and the cotyledons (seed leaves). "; 
	}

}
