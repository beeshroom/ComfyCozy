package bee.beeshroom.comfycozy.init;

import bee.beeshroom.comfycozy.blocks.Daruma;
import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.items.Lucky_Bell;
import net.minecraft.block.Block;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS,
            comfycozy.MOD_ID);

   // public static final RegistryObject<Item>

    public static final RegistryObject<Item> LUCKY_BELL = ITEMS.register("lucky_bell",
            () -> new Item(new Item.Properties().group(comfycozy.TAB)));

    public static final RegistryObject<PickaxeItem> LUCKY_PICKAXE = ITEMS.register("lucky_pickaxe",
            () -> new PickaxeItem(ItemTier.IRON, 0, 1f,
                    new Item.Properties().group(comfycozy.TAB)));

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
