package bee.beeshroom.comfycozy.init;

import bee.beeshroom.comfycozy.blocks.*;
import bee.beeshroom.comfycozy.comfycozy;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit
{
   //public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, comfycozy.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, comfycozy.MOD_ID);

    //blocks
    public static final RegistryObject<Block> DARUMA = BLOCKS.register("daruma", Daruma::new);

    public static final RegistryObject<Block> LUCKYCAT_WHITE = BLOCKS.register("luckycat_white", LuckyCat_White::new);

 public static final RegistryObject<Block> LUCKYCAT_BLACK = BLOCKS.register("luckycat_black", LuckyCat_Black::new);

 public static final RegistryObject<Block> LUCKYCAT_GOLD = BLOCKS.register("luckycat_gold", LuckyCat_Gold::new);

    public static final RegistryObject<Block> TABANATA = BLOCKS.register("tabanata", Tabanata::new);

    public static final RegistryObject<Block> BUNTING = BLOCKS.register("bunting", Bunting::new);
    public static final RegistryObject<Block> SPOOKY_BUNTING = BLOCKS.register("spooky_bunting", Bunting::new);
    public static final RegistryObject<Block> CHECKER_BUNTING = BLOCKS.register("checker_bunting", Bunting::new);
    public static final RegistryObject<Block> HEART_BUNTING = BLOCKS.register("heart_bunting", Bunting::new);

    public static final RegistryObject<Block> BOOK_STACK = BLOCKS.register("book_stack", BookStack::new);
    public static final RegistryObject<Block> BESTIARY_BOOK_STACK = BLOCKS.register("bestiary_book_stack", BookStack::new);
    public static final RegistryObject<Block> BOOK_STACK_ALT = BLOCKS.register("book_stack_alt", BookStack::new);

    public static final RegistryObject<Block> FISHTANK = BLOCKS.register("fishtank", FishTank::new);

    public static final RegistryObject<Block> OAT_CROP = BLOCKS.register("oats",
            () -> new OatCrop(Block.Properties.from(Blocks.WHEAT)));

    public static final RegistryObject<Block> OAT_BLOCK = BLOCKS.register("oat_block", OatBlock::new);

    public static final RegistryObject<Block> IRIS = BLOCKS.register("iris",
            () -> new CozyFlower(Block.Properties.from(Blocks.POPPY)));

    public static final RegistryObject<Block> GOLEM_FLOWER = BLOCKS.register("golem_flower",
            () -> new GolemFlower(Block.Properties.from(Blocks.POPPY)));

    public static final RegistryObject<Block> CLOVER = BLOCKS.register("clover",
            () -> new Clover(Block.Properties.from(Blocks.POPPY)));

    public static final RegistryObject<Block> FOUR_LEAF_CLOVER = BLOCKS.register("four_leaf_clover",
            () -> new Clover(Block.Properties.from(Blocks.POPPY)));



    public static final RegistryObject<Block> RED_CUSHION = BLOCKS.register("red_cushion", Cushion::new);
    public static final RegistryObject<Block> ORANGE_CUSHION = BLOCKS.register("orange_cushion", Cushion::new);
    public static final RegistryObject<Block> YELLOW_CUSHION = BLOCKS.register("yellow_cushion", Cushion::new);
    public static final RegistryObject<Block> LIME_CUSHION = BLOCKS.register("lime_cushion", Cushion::new);
    public static final RegistryObject<Block> GREEN_CUSHION = BLOCKS.register("green_cushion", Cushion::new);
    public static final RegistryObject<Block> CYAN_CUSHION = BLOCKS.register("cyan_cushion", Cushion::new);
    public static final RegistryObject<Block> LIGHT_BLUE_CUSHION = BLOCKS.register("light_blue_cushion", Cushion::new);
    public static final RegistryObject<Block> BLUE_CUSHION = BLOCKS.register("blue_cushion", Cushion::new);
    public static final RegistryObject<Block> PURPLE_CUSHION = BLOCKS.register("purple_cushion", Cushion::new);
    public static final RegistryObject<Block> MAGENTA_CUSHION = BLOCKS.register("magenta_cushion", Cushion::new);
    public static final RegistryObject<Block> PINK_CUSHION = BLOCKS.register("pink_cushion", Cushion::new);
    public static final RegistryObject<Block> WHITE_CUSHION = BLOCKS.register("white_cushion", Cushion::new);
    public static final RegistryObject<Block> LIGHT_GRAY_CUSHION = BLOCKS.register("light_gray_cushion", Cushion::new);
    public static final RegistryObject<Block> GRAY_CUSHION = BLOCKS.register("gray_cushion", Cushion::new);
    public static final RegistryObject<Block> BLACK_CUSHION = BLOCKS.register("black_cushion", Cushion::new);
    public static final RegistryObject<Block> BROWN_CUSHION = BLOCKS.register("brown_cushion", Cushion::new);

    public static final RegistryObject<Block> GLASS_PANEL = BLOCKS.register("glass_panel",
            () -> new GlassPanel(Block.Properties.from(Blocks.OAK_TRAPDOOR)));


   public static final RegistryObject<Block> BOWL_OATMEAL = BLOCKS.register("bowl_oatmeal", BowlOatmeal::new);
   public static final RegistryObject<Block> BOWL_SWEET_BERRY_OATMEAL = BLOCKS.register("bowl_sweet_berry_oatmeal", BowlSweetBerryOatmeal::new);







    //block items

  //  public static final RegistryObject<Item> DARUMA_ITEM = ITEMS.register("daruma", () -> new BlockItem(Daruma.get(), new Item.Properties().group(comfycozy.TAB)));

   // public static final RegistryObject<Item> DARUMA_ITEM = ITEMS.register("daruma", ()-> new BlockItemBase(DARUMA.get()));

 //public static final RegistryObject<Item> TABANATA_ITEM = ITEMS.register("tabanata", ()-> new BlockItemBase(TABANATA.get()));


}
