package bee.beeshroom.comfycozy.items;

import bee.beeshroom.comfycozy.sounds.SoundList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class Lucky_Bell extends Item {

    public Lucky_Bell(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
      //  playerIn.addPotionEffect(new EffectInstance(Effects.LUCK, 1, 5));
        playerIn.world.playSound((PlayerEntity) null, playerIn.getPosition(), SoundList.LUCKY.get(), SoundCategory.PLAYERS, 1.0F, 1.0f);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
