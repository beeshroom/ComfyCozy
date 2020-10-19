package bee.beeshroom.comfycozy.events;

import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.sounds.SoundList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = comfycozy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LuckEvent
{
    @SubscribeEvent
    public static void LuckyMiss(LivingDamageEvent event) {

        LivingEntity livingentity = event.getEntityLiving();
       World world = livingentity.getEntityWorld();
        Random random = livingentity.world.rand;

     //   EffectInstance luck = livingentity.getActivePotionEffect(Effects.LUCK);

   /*     if (luck.getAmplifier() >= 0)
        { return; }

            int amplifier = luck.getAmplifier(); */

        if (livingentity.isPotionActive(Effects.LUCK))
        {
            //amplifier == 0 &&
            if (random.nextInt(4) == 0) {
                if (!world.isRemote) {
                    livingentity.world.playSound((PlayerEntity) null, livingentity.getPosition(), SoundList.LUCKY.get(), SoundCategory.PLAYERS, 0.8F, 0.9f);
                }
                livingentity.extinguish();
                livingentity.world.addParticle((ParticleTypes.HAPPY_VILLAGER), livingentity.getPosX(), livingentity.getPosY() + 0.15D, livingentity.getPosZ(), 4.0D, 0.5D, 0.5D);
                event.setCanceled(true);
            }

    /*        if (amplifier == 1 && random.nextInt(3) == 0) {
                if (!world.isRemote) {
                    livingentity.world.playSound((PlayerEntity) null, livingentity.getPosition(), SoundList.LUCKY.get(), SoundCategory.PLAYERS, 0.8F, 1.0f);
                }
                event.setCanceled(true);
            }

            if (amplifier == 2 && random.nextInt(2) == 0) {
                if (!world.isRemote) {
                    livingentity.world.playSound((PlayerEntity) null, livingentity.getPosition(), SoundList.LUCKY.get(), SoundCategory.PLAYERS, 0.8F, 1.0f);
                }
                event.setCanceled(true);
            }

            if (amplifier >= 3) {
                if (!world.isRemote) {
                    livingentity.world.playSound((PlayerEntity) null, livingentity.getPosition(), SoundList.LUCKY.get(), SoundCategory.PLAYERS, 0.8F, 1.0f);
                }
                event.setCanceled(true);
            } */
        }
    }





 /*   @SubscribeEvent
    public static void LuckyHit(CriticalHitEvent event) {

        LivingEntity livingentity = event.getEntityLiving();
        World world = livingentity.getEntityWorld();
        Random random = livingentity.world.rand;
        if (livingentity.isPotionActive(Effects.LUCK) && (random.nextInt(4) == 0))
        {
            event.setResult(Event.Result.ALLOW);
        }
            //event.setCanceled(true);
            if (!world.isRemote) {
                // this.spawnParticles();
                livingentity.world.playSound((PlayerEntity) null, livingentity.getPosition(), SoundList.LUCKY_CAT_ATTACK.get(), SoundCategory.BLOCKS, 1.0F, 1.0f);
            }
        } */


}
