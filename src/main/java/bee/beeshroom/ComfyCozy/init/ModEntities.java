package bee.beeshroom.ComfyCozy.init;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities 
{
	
/*	public static void registerEntities(Object registerEntity)
	{
		registerEntity
	}*/

	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range) 
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, range, 1, true);
	} 
	
	
} 