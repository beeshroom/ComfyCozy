package bee.beeshroom.ComfyCozy.util.compat;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryCompat 
{
	public static void registerOres()
	{
		OreDictionary.registerOre("dustCinnamon", ModItems.CINNAMON);
		OreDictionary.registerOre("cropOat", ModItems.OATS);
		OreDictionary.registerOre("cropOats", ModItems.OATS);
		OreDictionary.registerOre("cropCinnamon", ModItems.CINNAMON);
		OreDictionary.registerOre("cropStrawberry", ModItems.STRAWBERRY);
		OreDictionary.registerOre("cropPeach", ModItems.PEACH);
		OreDictionary.registerOre("stickWood", ModItems.CINNAMON_STICK);
		OreDictionary.registerOre("stick", ModItems.CINNAMON_STICK);
		OreDictionary.registerOre("itemStick", ModItems.CINNAMON_STICK);
		OreDictionary.registerOre("slimeball", ModItems.STRAWBERRY_JAM);
		OreDictionary.registerOre("itemSlimeball", ModItems.STRAWBERRY_JAM);
		OreDictionary.registerOre("itemSlime", ModItems.STRAWBERRY_JAM);
	//	OreDictionary.registerOre("plankWood", ModBlocks.PLUM_PLANK);
		OreDictionary.registerOre("blockLog", ModBlocks.CINNAMON_LOG);
		OreDictionary.registerOre("blockWood", ModBlocks.CINNAMON_LOG);
		OreDictionary.registerOre("logWood", ModBlocks.CINNAMON_LOG);
		
		OreDictionary.registerOre("listAllseed", ModItems.STRAWBERRY_SEEDS);
		OreDictionary.registerOre("listAllseed", ModItems.OAT_SEEDS);

		OreDictionary.registerOre("plankWood", ModBlocks.PEACH_PLANKS);
		OreDictionary.registerOre("blockLog", ModBlocks.PEACH_LOG);
		OreDictionary.registerOre("logWood", ModBlocks.PEACH_LOG);
		OreDictionary.registerOre("treeSapling", ModBlocks.PEACH_SAPLING);
		OreDictionary.registerOre("treeSapling", ModBlocks.CINNAMON_SAPLING);
		
		OreDictionary.registerOre("dyeWhite", ModBlocks.WHITE_MUSHROOM);
		OreDictionary.registerOre("mushroom", ModBlocks.WHITE_MUSHROOM);
		OreDictionary.registerOre("listAllMushroom", ModBlocks.WHITE_MUSHROOM);
		

		OreDictionary.registerOre("listAllMushroom", Blocks.BROWN_MUSHROOM);
		OreDictionary.registerOre("listAllMushroom", Blocks.RED_MUSHROOM);
}
}
