package bee.beeshroom.ComfyCozy.util.compat;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryCompat 
{
	public static void registerOres()
	{
		OreDictionary.registerOre("dustCinnamon", ModItems.CINNAMON);
		OreDictionary.registerOre("cropOats", ModItems.OATS);
		OreDictionary.registerOre("cropCinnamon", ModItems.CINNAMON);
		//OreDictionary.registerOre("blockLog", ModBlocks.CINNAMON_TREE);
		
	
}
}
