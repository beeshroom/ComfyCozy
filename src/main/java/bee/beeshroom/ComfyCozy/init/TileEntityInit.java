package bee.beeshroom.comfycozy.init;

import bee.beeshroom.comfycozy.comfycozy;
import bee.beeshroom.comfycozy.tileentity.BlackLuckyCat;
import bee.beeshroom.comfycozy.tileentity.GoldLuckyCat;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit
{
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
            ForgeRegistries.TILE_ENTITIES, comfycozy.MOD_ID);

    public static final RegistryObject<TileEntityType<BlackLuckyCat>> LUCKYCAT_BLACK = TILE_ENTITY_TYPES.register("luckycat_black",
            () -> TileEntityType.Builder.create(BlackLuckyCat::new, BlockInit.LUCKYCAT_BLACK.get()).build(null));

    public static final RegistryObject<TileEntityType<GoldLuckyCat>> LUCKYCAT_GOLD = TILE_ENTITY_TYPES.register("luckycat_gold",
            () -> TileEntityType.Builder.create(GoldLuckyCat::new, BlockInit.LUCKYCAT_GOLD.get()).build(null));
}
