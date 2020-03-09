package bee.beeshroom.ComfyCozy.util.handlers;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bee.beeshroom.ComfyCozy.entity.EntityFurnaceGolem;
import bee.beeshroom.ComfyCozy.entity.EntityMushy;
import bee.beeshroom.ComfyCozy.entity.EntityOatmealSheep;
import bee.beeshroom.ComfyCozy.entity.entitydirtypig.EntityDirtyPig;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.Reference;
//import bee.beeshroom.ComfyCozy.world.generation.generators.WorldGenTrees;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

//CREDIT to Cadiboo for a lot of this registry code.

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public final class RegistryHandler {

	private static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID + " Event Subscriber");

	private static int entityId = 0; 

/*@EventBusSubscriber
public class RegistryHandler
{   
*/
	
@SubscribeEvent

public static void onItemRegister(RegistryEvent.Register<Item> event)
{
	event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
}

@SubscribeEvent

public static void onBlockRegister(RegistryEvent.Register<Block> event)
{
	event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
}

@SubscribeEvent
public static void onRegisterEntitiesEvent(@Nonnull final RegistryEvent.Register<EntityEntry> event) {

    final ResourceLocation EntityOatmealSheep = new ResourceLocation(Reference.MOD_ID, "entityoatmealsheep");
    final ResourceLocation EntityMushy = new ResourceLocation(Reference.MOD_ID, "entitymushy");
    final ResourceLocation EntityDirtyPig = new ResourceLocation(Reference.MOD_ID, "entitydirtypig");
    final ResourceLocation EntityFurnaceGolem = new ResourceLocation(Reference.MOD_ID, "entityfurnacegolem");
    
    

    event.getRegistry().registerAll(
        EntityEntryBuilder.create()
            .entity(EntityOatmealSheep.class)
            .id(EntityOatmealSheep, entityId++)
            .name(EntityOatmealSheep.getResourcePath())
            .tracker(32, 5, false)
            .build(),
            
        EntityEntryBuilder.create()
            .entity(EntityMushy.class)
            .id(EntityMushy, entityId++)
            .name(EntityMushy.getResourcePath())
            .tracker(32, 5, false)
            .build(),
            
            EntityEntryBuilder.create()
            .entity(EntityDirtyPig.class)
            .id(EntityDirtyPig, entityId++)
            .name(EntityDirtyPig.getResourcePath())
            .tracker(32, 5, false)
            .build(),
            
            EntityEntryBuilder.create()
            .entity(EntityFurnaceGolem.class)
            .id(EntityFurnaceGolem, entityId++)
            .name(EntityFurnaceGolem.getResourcePath())
            .tracker(32, 5, false)
            .build()
         
                /*    EntityEntryBuilder.create()
                        .entity(EntityCarpet.class)
                        .id(EntityCarpet, entityId++)
                        .name(EntityCarpet.getResourcePath())
                        .tracker(32, 10, false)
                        .build(), */
                        
                       
                  
    ); 

    LOGGER.debug("Registered entities");
    addSpawns();
}

private static void addSpawns() {
	//EntityRegistry.addSpawn(EntityMushy.class, 10, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MAGICAL));
	EntityRegistry.addSpawn(EntityMushy.class, 5, 1, 3, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MUSHROOM));
	EntityRegistry.addSpawn(EntityMushy.class, 45, 2, 3, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SPOOKY));
	EntityRegistry.addSpawn(EntityMushy.class, 35, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
	EntityRegistry.addSpawn(EntityMushy.class, 7, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
	EntityRegistry.addSpawn(EntityDirtyPig.class, 20, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
	EntityRegistry.addSpawn(EntityDirtyPig.class, 25, 1, 3, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
	//copySpawns(EntityPlayerAvoidingCreeper.class, EnumCreatureType.CREATURE, EntityCreeper.class, EnumCreatureType.MONSTER);
}

private static Biome[] getBiomes(final BiomeDictionary.Type type) {
	return BiomeDictionary.getBiomes(type).toArray(new Biome[0]);
}

/*
public void preInit(FMLPreInitializationEvent e) 
{
	System.out.println("preINIT");
	//GameRegistry.registerWorldGenerator(new WorldGenTrees(), 0);
	MinecraftForge.addGrassSeed(new ItemStack(ModItems.STRAWBERRY_SEEDS), 10);
	MinecraftForge.addGrassSeed(new ItemStack(ModItems.OAT_SEEDS), 15);
} */

/*
public void init(FMLInitializationEvent e) {
	GameRegistry.registerWorldGenerator(new WorldGenTrees(), 0);
  
} */

/*
@EventHandler
public static void preInitRegistries(FMLPreInitializationEvent event)
//public static void onRegisterEntitiesEvent(@Nonnull final RegistryEvent.Register<EntityEntry> event)
{
	System.out.println("preINIT");
	GameRegistry.registerWorldGenerator(new WorldGenTrees(), 0);
	MinecraftForge.addGrassSeed(new ItemStack(ModItems.STRAWBERRY_SEEDS), 10);
	MinecraftForge.addGrassSeed(new ItemStack(ModItems.OAT_SEEDS), 15);
	
	//ModBiomes.registerBiomes();
}*/

//i should probably put all the awnings in here.. and cinnamon wood..  
//note to self.... look up what burn times those would have...

	
@SubscribeEvent
public static void furnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event)
{
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.AWNING_WHITE)) 
	{
		event.setBurnTime(12);
	}
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.AWNING_RED)) 
	{
		event.setBurnTime(12);
	}
	//orange
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.AWNING_YELLOW)) 
	{
		event.setBurnTime(12);
	}
	//lime
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.AWNING_GREEN)) 
	{
		event.setBurnTime(12);
	}
	//cyan, dark blue
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.AWNING_LIGHT_BLUE)) 
	{
		event.setBurnTime(12);
	}
	//purple, magenta
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.AWNING_PINK)) 
	{
		event.setBurnTime(12);
	}
	//gray, dark grey, black, brown
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.CINNAMON_SAPLING))
	{
		event.setBurnTime(5);
	} 
	
	if(event.getItemStack().getItem() == ModItems.COZY_HAMMER)
	{
		event.setBurnTime(5);
	} 
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.TABLE))
	{
		event.setBurnTime(15);
	} 
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.POLE))
	{
		event.setBurnTime(10);
	} 
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.CARP_BANNER))
	{
		event.setBurnTime(12);
	} 
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.CUSHION_DIAMOND))
	{
		event.setBurnTime(10);
	} 
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.CUSHION_FIRE))
	{
		event.setBurnTime(10);
	} 
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.CUSHION_PIKA))
	{
		event.setBurnTime(10);
	} 
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.CUSHION_PORG))
	{
		event.setBurnTime(10);
	} 
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.CUSHION_ZIGZAG))
	{
		event.setBurnTime(10);
	}
	
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.BUNTING))
	{
		event.setBurnTime(2);
	}
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.BUNTING_CHECK))
	{
		event.setBurnTime(2);
	}
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.BUNTING_HEART))
	{
		event.setBurnTime(2);
	}
	







}}
