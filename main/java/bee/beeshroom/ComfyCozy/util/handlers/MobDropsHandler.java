package bee.beeshroom.ComfyCozy.util.handlers;
/* package porg.beeshroom.ComfyCozy.util.handlers;

//import com.beeshroom.SecondMod.entity.EntityBrownMooshroom;
import porg.beeshroom.ComfyCozy.init.ModItems;

import akka.actor.FSM.Event;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MobDropsHandler
{
    
	private void setRarity(EnumRarity rare) {} 
	

	
    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event)
    {
       if (event.getEntity() instanceof EntityCreeper)
        if (event.getSource().getDamageType().equals("player"))
        
        {
        	
           // event.drops.clear(); DON'T UNDO THESE //'s!!!
        	
        	setRarity(EnumRarity.RARE);
          ItemStack stack = new ItemStack(ModItems.CREEPYMOSS);
            EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
        
            
            event.getDrops().add(drop);
            
           }
       if (event.getEntity() instanceof EntityCow){
           EntityCow entity = (EntityCow)event.getEntity();
              if (event.getSource().getDamageType().equals("player") && !entity.isChild())
              
              {
                  
                 // event.drops.clear(); DON'T UNDO THESE //'s!!!
                  
                  setRarity(EnumRarity.RARE);
                ItemStack stack = new ItemStack(ModItems.COWHIDE);
                  EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
              
                  
                  event.getDrops().add(drop);
   }}
              
       if (event.getEntity() instanceof EntityMooshroom){
           EntityMooshroom entity = (EntityMooshroom)event.getEntity();
              if (event.getSource().getDamageType().equals("player") && !entity.isChild())
              
              {
                  
                 // event.drops.clear(); DON'T UNDO THESE //'s!!!
                  
                  setRarity(EnumRarity.RARE);
                ItemStack stack = new ItemStack(ModItems.MOOSHROOMHIDE);
                  EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
              
                  
                  event.getDrops().add(drop);
   }}
       
      /* if (event.getEntity() instanceof EntityBrownMooshroom){
           EntityBrownMooshroom entity = (EntityBrownMooshroom)event.getEntity();
              if (event.getSource().getDamageType().equals("player") && !entity.isChild())
              
              {
                  
                 // event.drops.clear(); DON'T UNDO THESE //'s!!!
                  
                  setRarity(EnumRarity.RARE);
                ItemStack stack = new ItemStack(ModItems.MOOSHROOMHIDEBROWN);
                  EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
          
                
                  
                  event.getDrops().add(drop);
   }}
       if (event.getEntity() instanceof EntityBrownMooshroom){
           EntityBrownMooshroom entity = (EntityBrownMooshroom)event.getEntity();
              if (event.getSource().getDamageType().equals("player") && !entity.isChild())
              
              {
                  
       setRarity(EnumRarity.RARE);
       ItemStack stack = new ItemStack(Items.BEEF);
         EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
    
         event.getDrops().add(drop);
    }} */
    
    //}} 