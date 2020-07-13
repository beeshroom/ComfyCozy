package bee.beeshroom.ComfyCozy.util.handlers;

import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler 
{
	//public static SoundEvent TEST_AMBIENT;
	public static SoundEvent SHROOMINI_HURT;
	public static SoundEvent SHROOMINI_DEATH;
	public static SoundEvent PLANT;
	public static SoundEvent HAMMER;
	public static SoundEvent STRIP;
	public static SoundEvent BONK;
	public static SoundEvent CRACKLE;
	//public static SoundEvent CARVE;
	//public static SoundEvent FLIP;
	public static SoundEvent DIRTY;
	public static SoundEvent OATMEAL;
	
	public static void registerSounds()
	{
	//	TEST_AMBIENT = registerSound("entity.test.test_ambient");
		SHROOMINI_HURT = registerSound("entity.mushy.shroomini_splat");
		PLANT = registerSound("entity.mushy.plant");
		SHROOMINI_DEATH = registerSound("entity.mushy.shroomini_death");
		HAMMER = registerSound("items.cozy_hammer.hammer");
		STRIP = registerSound("blocks.cinnamon_log.strip");
		BONK = registerSound("items.cozy_hammer.bonk");
		CRACKLE = registerSound("blocks.bonfire.crackle");
		//CARVE = registerSound("entity.furnace_golem,.carve");
	//	FLIP = registerSound("blocks.furniture.flip");
		DIRTY = registerSound("entity.dirtypig.dirty");
		OATMEAL = registerSound("entity.oatmealsheep.oatmeal");
	}
	
	private static SoundEvent registerSound(String name)
	{
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}