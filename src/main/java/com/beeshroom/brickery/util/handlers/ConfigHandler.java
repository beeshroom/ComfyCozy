package com.beeshroom.brickery.util.handlers;

import com.beeshroom.brickery.util.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID)
@EventBusSubscriber(modid = Reference.MOD_ID)
public class ConfigHandler {
	@RequiresMcRestart
	@Name("Brick Furnace")
	@Comment("Enables the Brick Furnace, an upgrade to the Furnace"
			+ "Disabling this will stop the block from being registered, removing it completely.")
	public static boolean BRICK_FURNACE = true;

	@Name("Brick Furnace Cook Time")
	@Comment("Changes how much time the furnace takes to cook an item, in ticks.")
	public static int BRICK_FURNACE_COOKTIME = 160;

	@Name("Brick Furnace Fuel Multiplier")
	@Comment("Applies a multiplier to how long a fuel lasts.")
	public static double BRICK_FURNACE_FUELMULTIPLIER = 1.0;

	@RequiresMcRestart
	@Name("Brickery")
	@Comment("Enables ALL Brickery content, disable this if you just want Comfy Cozy")
	public static boolean BRICKERY = true;
	
	@Name("Flooring Tooltips")
	@Comment("The wood flooring blocks will contain tooltips explaining their pattern.")
	public static boolean TOOLTIPS = false;
	
	@SubscribeEvent
	public static void registerConfig(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID() == Reference.MOD_ID)
		{
			ConfigManager.sync(Reference.MOD_ID, Type.INSTANCE);
		}
	}
}
