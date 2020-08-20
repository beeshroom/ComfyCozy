package bee.beeshroom.comfycozy.potion.effects;

import bee.beeshroom.comfycozy.init.BlockInit;
import bee.beeshroom.comfycozy.init.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FortuneEffect extends Effect {
    public FortuneEffect(EffectType type, int color) {
        super(type, color);
    }


    private static Method brewing;

    private static void addMix(Potion original, Item ingredient, Potion result) {

        if (brewing == null) {
            brewing = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "addMix", Potion.class, Item.class, Potion.class);
            brewing.setAccessible(true);
        }

        try {
            brewing.invoke(null, original, ingredient, result);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void addBrewingRecipes() {
        addMix(Potions.AWKWARD, Item.getItemFromBlock(BlockInit.FOUR_LEAF_CLOVER.get()), Potions.LUCK);
    }


    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public boolean isInstant()
    {
        return false;
    }
}
