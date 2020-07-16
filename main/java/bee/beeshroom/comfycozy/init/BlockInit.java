package bee.beeshroom.comfycozy.init;

import bee.beeshroom.comfycozy.blocks.BlockItemBase;
import bee.beeshroom.comfycozy.blocks.Daruma;
import bee.beeshroom.comfycozy.blocks.LuckyCat_White;
import bee.beeshroom.comfycozy.blocks.Tabanata;
import bee.beeshroom.comfycozy.comfycozy;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit
{
   //public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, comfycozy.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, comfycozy.MOD_ID);

    //blocks
    public static final RegistryObject<Block> DARUMA = BLOCKS.register("daruma", Daruma::new);

    public static final RegistryObject<Block> TABANATA = BLOCKS.register("tabanata", Tabanata::new);

    public static final RegistryObject<Block> LUCKYCAT_WHITE = BLOCKS.register("luckycat_white", LuckyCat_White::new);


    //block items

  //  public static final RegistryObject<Item> DARUMA_ITEM = ITEMS.register("daruma", () -> new BlockItem(Daruma.get(), new Item.Properties().group(comfycozy.TAB)));

   // public static final RegistryObject<Item> DARUMA_ITEM = ITEMS.register("daruma", ()-> new BlockItemBase(DARUMA.get()));

 //public static final RegistryObject<Item> TABANATA_ITEM = ITEMS.register("tabanata", ()-> new BlockItemBase(TABANATA.get()));


}
