package bee.beeshroom.ComfyCozy.util.handlers;

import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID)
@EventBusSubscriber(modid = Reference.MOD_ID)
public class ConfigHandler 
{	
	@Name("Oat Lambs")
	@Comment("Oat Lambs will ocassionally spawn when harvesting oats")
	public static boolean OAT_LAMBS = true;
	
	@Name("Furnace Golem")
	@Comment("Furnace Golems will be enabled. Experimental on servers (shouldn't crash, but it could.)")
	public static boolean FURNACE_GOLEM = true;
	
	@RequiresMcRestart
	@Name("Mushroom Beret")
	@Comment("A mushroom hat.\n"
			+ "Disabling this option will stop the item from being registered, removing it entirely.")
	public static boolean MUSHROOM_BERET = true;
	
	@Name("Cinnamon Trees anywhere")
	@Comment("Cinnamon trees will be able to spawn in regular forest biomes and not just Jungles.")
	public static boolean CINNAMON_ANYWHERE = false;
	
	@Name("Cinnamon Trees")
	@Comment("Cinnamon trees will spawn in the jungle.")
	public static boolean CINNAMON = true;
	
	@Name("Peach Trees")
	@Comment("Peach trees will spawn in forest biomes.")
	public static boolean PEACH = true;
	
	@Name("Peach Trees spawn chance")
	@Comment("The chance that peach trees will spawn.")
	public static int PEACH_CHANCE = 20;
	
	@Name("Strawberry plants")
	@Comment("Strawberry plants will spawn in Forests and Taigas.")
	public static boolean STRAWBERRY = true;
	
	@Name("Dirty Pigs")
	@Comment("Dirty Pigs will spawn in the world.")
	public static boolean DIRTY_PIG = true;
	
	@Name("Dirty Pigs spawn in forests")
	@Comment("Dirty Pigs will spawn in forest biomes.")
	public static boolean DIRTY_PIG_FORESTS = false;
	
	@Name("Shroomini")
	@Comment("Shroomini will spawn in the world.")
	public static boolean SHROOMINI = true;
	
	@Name("Shroomini spawn in forests")
	@Comment("Shroomini will spawn in forest biomes.")
	public static boolean SHROOMINI_FORESTS = false;
	
	@Name("Oat Lamb Flavoring")
	@Comment("Oat sheep will be 'dyeable' with bowls of oatmeal.")
	public static boolean OATSHEEPFLAVOR = true;
	
	@Name("Gold Apple Cinnamon Oat Lambs")
	@Comment("Gold Apple Cinnamon Oatmeal Lambs are enabled (requires 'Oat Lamb Flavoring' Config to be enabled also).")
	public static boolean GAPPLESHEEP = false;
	
	@Name("Secret Summon")
	@Comment("A secret method to summon a hardly special Gold Apple Cinnamon Oat Lamb will be available.")
	public static boolean SECRET_SUMMON = true;
	
	@Name("White Mushrooms")
	@Comment("Enables white mushrooms, a third mushroom variant that can spawn in certain biomes and the Nether.")
	public static boolean WHITE_MUSHROOM = true;
	
	@RequiresMcRestart
	@Name("Grass drops Strawberry Seeds")
	public static boolean STRAWBERRY_SEEDS = true;
	
	@RequiresMcRestart
	@Comment("Determines the chance of a strawberry seed dropping after breaking grass is, the lower the number, the rarer the chance.\n"
			+ "For reference, wheat has a weight value of 10.")
	@Name("Strawberry Seeds drop weight")
	public static int STRAWBERRY_SEEDS_WEIGHT = 2;
	
	@RequiresMcRestart
	@Name("Grass drops Oat Seeds")
	public static boolean OAT_SEEDS = true;
	
	@RequiresMcRestart
	@Comment("Determines the chance of an oat seed dropping after breaking grass, the lower the number, the rarer the chance.\n"
			+ "For reference, wheat has a weight value of 10.")
	@Name("Oat Seeds drop weight")
	public static int OAT_SEEDS_WEIGHT = 14;
	
	
	@SubscribeEvent
	public static void registerConfig(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID() == Reference.MOD_ID)
		{
			ConfigManager.sync(Reference.MOD_ID, Type.INSTANCE);
		}
	}
}
