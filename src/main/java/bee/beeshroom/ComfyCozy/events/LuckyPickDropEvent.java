/*package bee.beeshroom.comfycozy.events;

import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.init.ItemInit;
import bee.beeshroom.comfycozy.sounds.SoundList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = comfycozy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LuckyPickDropEvent {

    @SubscribeEvent
    public static void FortuneEvent(ItemTossEvent event) {

        PlayerEntity playerEntity = event.getPlayer();
        World world = playerEntity.getEntityWorld();
        ItemStack itemstack = playerEntity.getHeldItem();
        Item item = itemstack.getItem();

        if (item == ItemInit.LUCKY_PICKAXE.get())
        {
            if (!world.isRemote) {
                item.world.playSound((PlayerEntity) null, item.getPosition(), SoundList.LUCKY_CAT_ATTACK.get(), SoundCategory.PLAYERS, 0.8F, 0.7f);
            }
            event.setCanceled(true);
        }
    }
}

*/