package bee.beeshroom.comfycozy.init;

import bee.beeshroom.comfycozy.comfycozy;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS,
            comfycozy.MOD_ID);

   // public static final RegistryObject<Item>

  /*  public static final RegistryObject<Item> OATS = ITEMS.register("oats",
            () -> new Item(new Item.Properties().group(comfycozy.CozyItemGroup.instance))); */

/*    public static final RegistryObject<Item> OATMEAL = ITEMS.register("oatmeal",
            () -> new Item(new Item.Properties().group(comfycozy.CozyItemGroup.instance).food(new Food.Builder().hunger(6)
                    .saturation(1.2f).build()))); //.effect(new EffectInstance(Effects.ABSORPTION, 6000, 5), 0.7f).build())));
*/
/*
    public static final RegistryObject<oatmeal> OATMEAL = ITEMS.register("oatmeal",
            () -> new oatmeal(new Item.Properties().food(new Food.Builder().hunger(6)
                    .saturation(1.2f).build()).group(comfycozy.CozyItemGroup.instance)));

    public static final RegistryObject<BlockItem> OAT_SEED_ITEM = ITEMS.register("oat_seed_item",
            () -> new BlockItem(BlockInit.OAT_CROP.get(), new Item.Properties().group(comfycozy.CozyItemGroup.instance))); */
}
