//package com.beeshroom.brickery.util.handlers;

//import com.beeshroom.brickery.init.ModItems;

//public class MobDropsHandler
//{

//private void setRarity(EnumRarity rare) {}


//  @SubscribeEvent
//  public void onMobDrops(LivingDropsEvent event)
//    {
//       if (event.getEntity() instanceof EntityCreeper)
//      if (event.getSource().getDamageType().equals("player"))

//  {

// event.drops.clear(); DON'T UNDO THESE //'s!!!

//       	setRarity(EnumRarity.RARE);
// ItemStack stack = new ItemStack(ModItems.CREEPYMOSS);
//    EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);


//      event.getDrops().add(drop);

//      }

// }}


//  }}