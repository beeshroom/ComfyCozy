package bee.beeshroom.comfycozy.events;

import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.init.ItemInit;
import bee.beeshroom.comfycozy.lists.PotionList;
import bee.beeshroom.comfycozy.sounds.SoundList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = comfycozy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FortuneExpirirationEvent {

 @SubscribeEvent
    public static void FortuneEvent(BlockEvent.BreakEvent event) {

        LivingEntity livingEntity = event.getPlayer();
        World world = livingEntity.getEntityWorld();
        Hand handIn = livingEntity.getActiveHand();
        ItemStack itemstack = livingEntity.getHeldItem(handIn);
        Item item = itemstack.getItem();

        if (item == ItemInit.LUCKY_PICKAXE.get() && !livingEntity.isPotionActive(PotionList.FORTUNE_EFFECT.get()))
        {
            itemstack.shrink(1);

            if (!world.isRemote) {
                livingEntity.world.playSound((PlayerEntity) null, livingEntity.getPosition(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 0.8F, 0.7f);

                livingEntity.world.playSound((PlayerEntity) null, livingEntity.getPosition(), SoundList.LUCKY_CAT_ATTACK.get(), SoundCategory.PLAYERS, 0.8F, 0.7f);
            }

        }
    }

  @SubscribeEvent
    public static void FortuneExpired(PotionEvent.PotionRemoveEvent event)
  {
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.getEntityWorld();
        Hand handIn = livingEntity.getActiveHand();
        ItemStack itemstack = livingEntity.getHeldItem(handIn);
        Item item = itemstack.getItem();
        //PlayerEntity playerEntity;
        //Potion potion = event.getPotion();

        if (event.getPotion() == PotionList.FORTUNE_EFFECT.get())
                //!livingEntity.isPotionActive(PotionList.FORTUNE_EFFECT.get()))
          if (livingEntity instanceof PlayerEntity)
      {
          if (item == ItemInit.LUCKY_PICKAXE.get())
          {    itemstack.shrink(1);
            if (!world.isRemote) {
                livingEntity.world.playSound((PlayerEntity) null, livingEntity.getPosition(), SoundList.LUCKY_CAT_ATTACK.get(), SoundCategory.PLAYERS, 0.8F, 0.7f);
            }

        }
    }
}}