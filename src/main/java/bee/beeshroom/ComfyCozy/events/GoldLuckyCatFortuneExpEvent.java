package bee.beeshroom.comfycozy.events;

import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.lists.PotionList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = comfycozy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GoldLuckyCatFortuneExpEvent {
    @SubscribeEvent
    public static void GoldLuckyCat(LivingExperienceDropEvent event) {

        PlayerEntity player = event.getAttackingPlayer();
        LivingEntity livingentity = event.getEntityLiving();
      //  World world = livingentity.getEntityWorld();
        Random random = livingentity.world.rand;

 if (event.getEntity() instanceof IMob || event.getEntity() instanceof AnimalEntity)
        {
        if (player.isPotionActive(PotionList.FORTUNE_EFFECT.get()) && (random.nextInt(4) == 0))
        {
         //   event.setCanceled(true);
            event.setDroppedExperience(event.getOriginalExperience() + 15);
     /*   if (player.isPotionActive(PotionList.FORTUNE_EFFECT.get())) {
            livingentity.entityDropItem(Items.GOLD_NUGGET);
            } */
        }
    } }
}