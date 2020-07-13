package bee.beeshroom.ComfyCozy.util.handlers;

import java.util.Random;

import bee.beeshroom.ComfyCozy.entity.EntityFurnaceGolem;
import bee.beeshroom.ComfyCozy.entity.EntityMushy;
import bee.beeshroom.ComfyCozy.entity.entitydirtypig.EntityDirtyPig;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MobDropsHandler
{
    
	private void setRarity(EnumRarity rare) {} 
	
	
	
    @SubscribeEvent //added random random
    public void onMobDrops(LivingDropsEvent event)
    {
    	  Random rnd = new Random();
    	  
       if (event.getEntity() instanceof EntityFurnaceGolem)
        //if (event.getSource().getDamageType().equals("player"))
        
        {
        	
           // event.drops.clear(); DON'T UNDO THESE //'s!!!
        	
        	setRarity(EnumRarity.UNCOMMON);
          ItemStack stack = new ItemStack(Item.getItemFromBlock(Blocks.FURNACE));
            EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
        
            
            event.getDrops().add(drop);
            
           }
       
       if (event.getEntity() instanceof EntityMushy)
         //  if (event.getSource().getDamageType().equals("player"))
           
           {
           	
              // event.drops.clear(); DON'T UNDO THESE //'s!!!
    	   if (rnd.nextFloat() >= 0.5f )
    	   {   
           	setRarity(EnumRarity.UNCOMMON);
             ItemStack stack = new ItemStack(Item.getItemFromBlock(Blocks.BROWN_MUSHROOM));
               EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
           
               
               event.getDrops().add(drop);
    	   }
    	   
    	   if (rnd.nextFloat() >= 0.2f && rnd.nextFloat() <= 0.5f)
    	   {   
           	setRarity(EnumRarity.UNCOMMON);
             ItemStack stack = new ItemStack(Item.getItemFromBlock(ModBlocks.WHITE_MUSHROOM));
               EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
           
               
               event.getDrops().add(drop);
    	   }
    	   
    	   if (rnd.nextFloat() <= 0.2f)
    	   {   
           	setRarity(EnumRarity.UNCOMMON);
             ItemStack stack = new ItemStack(Item.getItemFromBlock(Blocks.RED_MUSHROOM));
               EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
           
               
               event.getDrops().add(drop);
    	   }
    	   
               
              }
       
       if (event.getEntity() instanceof EntityDirtyPig)
           //  if (event.getSource().getDamageType().equals("player"))
             
             {
             	
                // event.drops.clear(); DON'T UNDO THESE //'s!!!
             	
             	setRarity(EnumRarity.UNCOMMON);
               ItemStack stack = new ItemStack(Item.getItemFromBlock(Blocks.DIRT));
                 EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
             
                 
                 event.getDrops().add(drop);
                 
                }
     
         
            			
       }}
