package factory;

import interfaces.PlantInfoInterface;

public class YoungPlantInfo implements PlantInfoInterface
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
