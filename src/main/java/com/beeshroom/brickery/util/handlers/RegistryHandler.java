package com.beeshroom.brickery.util.handlers;

import com.beeshroom.brickery.init.ModBlocks;
import com.beeshroom.brickery.init.ModItems;
import com.beeshroom.brickery.util.IHasModel;
import com.beeshroom.brickery.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
        TileEntityHandler.registerTileEntities();
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ModItems.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }
        for (Block block : ModBlocks.BLOCKS) {
            if (block instanceof IHasModel) {
                ((IHasModel) block).registerModels();
            }
        }

    }
//burnanble items vvvv
//@SubscribeEvent
//public static void furnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event)
//{
//	if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.BLAZERODBUNDLE))
//	{
//		event.setBurnTime(24000);
//	}

    //if(event.getItemStack().getItem() == Item.getItemFromBlock(ModBlocks.STICKBUNDLE))
    //{
    //	event.setBurnTime(1000);
    //}

//}

}
