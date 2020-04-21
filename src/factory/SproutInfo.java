package factory;

import interfaces.PlantInfoInterface;

/*
 * This class is responsible for generating
 * the information about the sprout.
 * 
 */

public class SproutInfo implements PlantInfoInterface
{
	@Override
	public String displayName() 
	{
		return "Sprout";
	}

	@Override
	public String displayTextInfo() 
	{
		return "Sprout: It's a seed that is either germinated until it has formed a root or "
				+ "until it has developed its first set of leaves.  "
				+ "A sprout is a small growth on a plant, a little new bud.";
	}

}
