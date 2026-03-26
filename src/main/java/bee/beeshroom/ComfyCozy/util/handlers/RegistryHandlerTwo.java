
package bee.beeshroom.ComfyCozy.util.handlers;
import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.Reference;
import bee.beeshroom.ComfyCozy.util.compat.OreDictionaryCompat;
import bee.beeshroom.ComfyCozy.world.generation.generators.WorldGen;
//import bee.beeshroom.ComfyCozy.world.generation.generators.WorldGenTrees;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

// Credit to Harry's Tech Reviews / Modding Tutorials on youtube 

@EventBusSubscriber(modid = Reference.MOD_ID)
public class RegistryHandlerTwo
	{
		public static void preInitRegistries(FMLPreInitializationEvent event)
		{
			//FluidInit.registerFluids();
			
			//GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
			//GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
			GameRegistry.registerWorldGenerator((IWorldGenerator) new WorldGen(), 0);
			if (ConfigHandler.STRAWBERRY_SEEDS) MinecraftForge.addGrassSeed(new ItemStack(ModItems.STRAWBERRY_SEEDS), ConfigHandler.STRAWBERRY_SEEDS_WEIGHT);
			if (ConfigHandler.OAT_SEEDS) MinecraftForge.addGrassSeed(new ItemStack(ModItems.OAT_SEEDS), ConfigHandler.OAT_SEEDS_WEIGHT);
			EventHandler.registerEvents();
			SoundsHandler.registerSounds();
			//EntityList.addMapping(EntityOatmealSheep.class, "EntityOatmealSheep", 3, 4930341, 1584909);
			//BiomeInit.registerBiomes();
			//ModConfiguration.registerConfig(event);
		}
		
		public static void initRegistries(FMLInitializationEvent event) 
		{
			OreDictionaryCompat.registerOres();
			Main.proxy.render();
		}
	}