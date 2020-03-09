package bee.beeshroom.ComfyCozy.util.handlers;
import bee.beeshroom.ComfyCozy.entity.EntityOatmealSheep;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.world.generation.generators.WorldGenCinnamon;
import bee.beeshroom.ComfyCozy.world.generation.generators.WorldGenTrees;
import net.minecraft.entity.EntityList;
//import bee.beeshroom.ComfyCozy.world.generation.generators.WorldGenTrees;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

// Credit to Harry's Tech Reviews / Modding Tutorials on youtube 

@EventBusSubscriber
public class RegistryHandlerTwo
	{
		
		public static void preInitRegistries(FMLPreInitializationEvent event)
		{
			//FluidInit.registerFluids();
			
			//GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
			//GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
			GameRegistry.registerWorldGenerator((IWorldGenerator) new WorldGenTrees(), 0);
			MinecraftForge.addGrassSeed(new ItemStack(ModItems.STRAWBERRY_SEEDS), 7);
			MinecraftForge.addGrassSeed(new ItemStack(ModItems.OAT_SEEDS), 14);
			EventHandler.registerEvents();
			SoundsHandler.registerSounds();
		//	EntityList.addMapping(EntityOatmealSheep.class, "EntityOatmealSheep", 3, 4930341, 1584909);
		//	BiomeInit.registerBiomes();
			//ModConfiguration.registerConfig(event);
		}
	}