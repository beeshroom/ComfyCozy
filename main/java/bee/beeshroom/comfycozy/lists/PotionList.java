/*package bee.beeshroom.comfycozy.lists;

import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.init.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PotionList {


    public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, comfycozy.MOD_ID);

    public static final RegistryObject<Effect> FORTUNE_EFFECT = EFFECTS.register("fortune", () -> new FortuneEffect(EffectType.BENEFICIAL, 0xd4ff00));


    public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES, comfycozy.MOD_ID);

    public static final RegistryObject<Potion> FORTUNE_POTION = POTIONS.register("fortune", () -> new Potion(new EffectInstance(FORTUNE_EFFECT.get(), 3600)));






    private static Method brewing_mixes;


    private static void addMix(Potion start, Item ingredient, Potion result) {
        if(brewing_mixes == null) {
            brewing_mixes = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "addMix", Potion.class, Item.class, Potion.class);
            brewing_mixes.setAccessible(true);
        }

        try {
            brewing_mixes.invoke(null, start, ingredient, result);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void addBrewingRecipes() {
        addMix(Potions.AWKWARD, Items.BELL, PotionList.FORTUNE_POTION.get());
    }



    public static class FortuneEffect extends Effect {

        public FortuneEffect(EffectType typeIn, int liquidColorIn) {
            super(typeIn, liquidColorIn);
        }

    }
}*/