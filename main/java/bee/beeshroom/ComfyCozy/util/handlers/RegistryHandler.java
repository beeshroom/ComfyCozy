package bee.beeshroom.ComfyCozy.util.handlers;


import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bee.beeshroom.ComfyCozy.entity.EntityOatmealSheep;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

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
    
   


    event.getRegistry().registerAll(
        EntityEntryBuilder.create()
            .entity(EntityOatmealSheep.class)
            .id(EntityOatmealSheep, entityId++)
            .name(EntityOatmealSheep.getResourcePath())
            .tracker(32, 10, false)
            .build()/*,
         
                    EntityEntryBuilder.create()
                        .entity(EntityCarpet.class)
                        .id(EntityCarpet, entityId++)
                        .name(EntityCarpet.getResourcePath())
                        .tracker(32, 10, false)
                        .build(), */
                        
                       
                  
    ); 

    LOGGER.debug("Registered entities");


}

//i should probably put all the awnings in here.. and cinnamon wood..  
//note to self.... look up what burn times those would have...

	/*
@SubscribeEvent
public static void furnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event)
{
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.BLAZERODBUNDLE))
	{
		event.setBurnTime(24000);
	}
	
	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.STICKBUNDLE))
	{
		event.setBurnTime(1000);
	} */
	







}
