package bee.beeshroom.comfycozy.init;

import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.items.Oatmeal;
import bee.beeshroom.comfycozy.items.SweetBerryOatmeal;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.item.Food;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, comfycozy.MOD_ID);

   // public static final RegistryObject<Item>

    public static final RegistryObject<Item> OATS = ITEMS.register("oats",
            () -> new Item(new Item.Properties().group(comfycozy.TAB)));

    public static final RegistryObject<BlockItem> OAT_SEEDS = ITEMS.register("oat_seeds",
            () -> new BlockItem(BlockInit.OAT_CROP.get(), new Item.Properties().group(comfycozy.TAB)));


      public static final RegistryObject<Item> OATMEAL = ITEMS.register("oatmeal",
            () -> new Oatmeal(new Item.Properties().food(new Food.Builder().hunger(5).saturation(.4f).build()).maxStackSize(16).group(comfycozy.TAB)));

    public static final RegistryObject<Item> SWEET_BERRY_OATMEAL = ITEMS.register("sweet_berry_oatmeal",
            () -> new SweetBerryOatmeal(new Item.Properties().food(new Food.Builder().hunger(7).saturation(.6f).build()).maxStackSize(16).group(comfycozy.TAB)));



    public static final RegistryObject<Item> LUCKY_BELL = ITEMS.register("lucky_bell",
            () -> new Item(new Item.Properties().group(comfycozy.TAB)));

    public static final RegistryObject<PickaxeItem> LUCKY_PICKAXE = ITEMS.register("lucky_pickaxe",
            () -> new PickaxeItem(ItemTier.IRON, 0, 1f,
                    new Item.Properties().group(comfycozy.TAB)));

}
