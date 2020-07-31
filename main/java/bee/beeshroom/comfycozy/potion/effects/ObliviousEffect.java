/* package bee.beeshroom.comfycozy.potion.effects;

import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObliviousEffect extends Effect {
    public ObliviousEffect(EffectType type, int color) {
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
*/