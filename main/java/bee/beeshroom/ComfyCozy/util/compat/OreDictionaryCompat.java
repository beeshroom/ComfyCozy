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
		OreDictionary.registerOre("cropStrawberry", ModItems.STRAWBERRY);
		OreDictionary.registerOre("stickWood", ModItems.CINNAMON_STICK);
		OreDictionary.registerOre("stick", ModItems.CINNAMON_STICK);
		OreDictionary.registerOre("itemStick", ModItems.CINNAMON_STICK);
		OreDictionary.registerOre("slimeball", ModItems.STRAWBERRY_JAM);
		OreDictionary.registerOre("itemSlimeball", ModItems.STRAWBERRY_JAM);
		OreDictionary.registerOre("itemSlime", ModItems.STRAWBERRY_JAM);
	//	OreDictionary.registerOre("plankWood", ModBlocks.PLUM_PLANK);
		OreDictionary.registerOre("blockLog", ModBlocks.CINNAMON_LOG);
		OreDictionary.registerOre("blockWood", ModBlocks.CINNAMON_LOG);
		
	
}
}
