package bee.beeshroom.comfycozy.lists;

import bee.beeshroom.comfycozy.init.BlockInit;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;

import static bee.beeshroom.comfycozy.init.BlockInit.IRIS;

//Thankyou Mr. Pineapple

public class FeatureList
{
    public static final BlockState BUSH = IRIS.get().getDefaultState();
    public static final BlockState CLOVERS = BlockInit.CLOVER.get().getDefaultState();
           // .with(IRIS 3);

    public static final BlockClusterFeatureConfig IRIS_CONFIG = (new BlockClusterFeatureConfig.Builder(
            new SimpleBlockStateProvider(BUSH), new SimpleBlockPlacer()).tries(64)
            .whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock()))).func_227317_b_().build();

    public static final BlockClusterFeatureConfig CLOVERS_CONFIG = (new BlockClusterFeatureConfig.Builder(
       new SimpleBlockStateProvider(CLOVERS), new SimpleBlockPlacer()).tries(64)
            .whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock()))).func_227317_b_().build();


}
