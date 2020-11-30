package bee.beeshroom.comfycozy;

import bee.beeshroom.comfycozy.blocks.BowlOatmeal;
import bee.beeshroom.comfycozy.blocks.BowlSweetBerryOatmeal;
import bee.beeshroom.comfycozy.blocks.OatCrop;
import bee.beeshroom.comfycozy.entities.Shroomin.ShroominRenderer;
import bee.beeshroom.comfycozy.init.BlockInit;
import bee.beeshroom.comfycozy.init.EntityTypes;
import bee.beeshroom.comfycozy.init.ItemInit;
import bee.beeshroom.comfycozy.init.TileEntityInit;
import bee.beeshroom.comfycozy.lists.PotionList;
import bee.beeshroom.comfycozy.sounds.SoundList;
import bee.beeshroom.comfycozy.util.RegistryHandler;
import bee.beeshroom.comfycozy.world.gen.PlantGen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//thanks for your tutorials TurtyWurty and TechnoVision :>

@Mod.EventBusSubscriber(modid = comfycozy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod("comfycozy")
public class comfycozy
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "comfycozy";
    //public static comfycozy Instance;
    public static comfycozy instance;


    public comfycozy() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);

       FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

       // ParticleInit.PARTICLE_TYPES.register(modEventBus);
        SoundList.SOUNDS.register(modEventBus);
     //   PotionList.POTIONS.register(modEventBus);
        PotionList.EFFECTS.register(modEventBus);
     //   EnchantmentInit.ENCHANTMENTS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        TileEntityInit.TILE_ENTITY_TYPES.register(modEventBus);
      //  ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
        EntityTypes.ENTITY_TYPES.register(modEventBus);

        instance = this;
        RegistryHandler.init();
        MinecraftForge.EVENT_BUS.register(this);
    }

    // thanks turtywurty
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream()
                .filter(block -> !(block.get() instanceof OatCrop))//&& !(block.get() instanceof FlowingFluidBlock))
                .filter(block -> !(block.get() instanceof BowlOatmeal))
                .filter(block -> !(block.get() instanceof BowlSweetBerryOatmeal))
                .map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(comfycozy.TAB);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });

        LOGGER.debug("Registered BlockItems!");
    }

    private void setup(final FMLCommonSetupEvent event) {
        BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.fromItems(ItemInit.LUCKY_PICKAXE.get()), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),  Potions.LUCK));
        PotionList.addBrewingRecipes();
        DeferredWorkQueue.runLater(PlantGen::generate);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        RenderTypeLookup.setRenderLayer(BlockInit.TABANATA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.BUNTING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.CHECKER_BUNTING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.SPOOKY_BUNTING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.HEART_BUNTING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.FISHTANK.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.OAT_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.GLASS_PANEL.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(BlockInit.IRIS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.CLOVER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.FOUR_LEAF_CLOVER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.GOLEM_FLOWER.get(), RenderType.getCutout());


        RenderingRegistry.registerEntityRenderingHandler(EntityTypes.SHROOMIN_ENTITY.get(), ShroominRenderer::new);
    }


    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        //LOGGER.info("HELLO from server starting");
    }

/*
    public static class CozyItemGroup extends ItemGroup {
        public static final ItemGroup instance = new CozyItemGroup(ItemGroup.GROUPS.length, "comfycozytab");

        private CozyItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockInit.DARUMA.get());
        }
    } */

    public static final ItemGroup TAB = new ItemGroup("comfycozy") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockInit.DARUMA.get());
        }
    };



}
