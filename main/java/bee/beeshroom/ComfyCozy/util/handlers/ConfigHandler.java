package bee.beeshroom.ComfyCozy.util.handlers;

import java.io.File;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

//thanks turtywurty 

public class ConfigHandler 
{
	public static Configuration config;
	
	//Options
	public static boolean OAT_LAMBS = true;
	public static boolean FURNACE_GOLEM = true;
	//public static boolean MUSHROOM_BERET = false;
	public static boolean CINNAMON = false;
	public static boolean PEACH = true;
	public static boolean STRAWBERRY = true;
	public static boolean DIRTY_PIG = true;
	public static boolean SHROOMINI = true;
	public static boolean OATSHEEPFLAVOR = true;
	public static boolean GAPPLESHEEP = true;
	public static boolean SECRET_SUMMON = true;
	
	public static void init(File file)
	{
		config = new Configuration(file);
		String category;
		
		category = "Options";
		config.addCustomCategoryComment(category, "configurables");
		OAT_LAMBS = config.getBoolean("Oat Lambs", category, true, "Oat Lambs will ocassionally spawn when harvesting oats");
		FURNACE_GOLEM = config.getBoolean("Furnace Golem", category, true, "Furnace Golems will be enabled. Expirimental on servers (shouldn't crash, but it could.)");
		//MUSHROOM_BERET = config.getBoolean("Mushroom Beret", category, false, "A mushroom hat. WARNING: Will cause crash on servers. Be sure to set to false if you plan to play on a server!");
		CINNAMON = config.getBoolean("Cinnamon Trees anywhere", category, false, "Cinnamon trees will be able to spawn in regular forest biomes and not just Jungles.");
		PEACH = config.getBoolean("Peach Trees", category, true, "Peach trees will spawn in forest biomes.");
		STRAWBERRY = config.getBoolean("Strawberry Plants", category, true, "Strawberry plants will spawn in Forests and Taigas.");
		DIRTY_PIG = config.getBoolean("Dirty Pigs", category, true, "Dirty Pigs will spawn in the world.");
		SHROOMINI = config.getBoolean("Shroomini", category, true, "Shroomini will spawn in the world.");
		OATSHEEPFLAVOR = config.getBoolean("Oat Lamb Flavoring", category, true, "Oat sheep will be 'dyeable' with bowls of oatmeal.");
		GAPPLESHEEP = config.getBoolean("Gold Apple Cinnamon Oat Lambs", category, true, "Gold Apple Cinnamon Oatmeal Lambs are enabled (requires 'Oat Lamb Flavoring' Config to be enabled also).");
		SECRET_SUMMON = config.getBoolean("Secret Summon", category, true, "A secret method to summon a hardly special Gold Apple Cinnamon Oat Lamb will be available.");
		
		
		config.save();
	}
	
	public static void registerConfig(FMLPreInitializationEvent event)
	{
		//Main.config = new File(event.getModConfigurationDirectory(), null ); //+ "/" + Reference.MOD_ID);
		Main.config = new File(event.getModConfigurationDirectory() + "/" + Reference.MOD_ID);
		Main.config.mkdirs();
		init(new File(Main.config.getPath(), Reference.MOD_ID + ".cfg"));
	}
}
