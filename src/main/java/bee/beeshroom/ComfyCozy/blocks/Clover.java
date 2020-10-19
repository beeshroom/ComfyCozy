package bee.beeshroom.comfycozy.blocks;

import bee.beeshroom.comfycozy.init.BlockInit;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class Clover extends BushBlock implements IGrowable {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);


    public Clover(Properties builder) {
        super(builder);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Vec3d vec3d = state.getOffset(worldIn, pos);
        return SHAPE.withOffset(vec3d.x, vec3d.y, vec3d.z);
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
/*    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    } */

    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        return false;
    }

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        spawnAsEntity(worldIn, pos, new ItemStack(this)); }



    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(25) == 0) {
            int i = 5;
            int j = 4;

            for(BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4))) {
                if (worldIn.getBlockState(blockpos).getBlock() == this) {
                    --i;
                    if (i <= 0) {
                        return;
                    }
                }
            }

            BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);

            for(int k = 0; k < 4; ++k) {
                if (worldIn.isAirBlock(blockpos1) && state.isValidPosition(worldIn, blockpos1)) {
                    pos = blockpos1;
                }

                blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
            }

            if (worldIn.isAirBlock(blockpos1) && state.isValidPosition(worldIn, blockpos1)) {
                worldIn.setBlockState(blockpos1, state, 2);
            }
        }

    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isOpaqueCube(worldIn, pos);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        Block block = blockstate.getBlock();
      /*  if (block != Blocks.MYCELIUM && block != Blocks.PODZOL) {
            return worldIn.getLightSubtracted(pos, 0) < 13 && blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.util.Direction.UP, this);
        } else {
            return true;
        }*/
        if (block != Blocks.GRASS_BLOCK) {
           return blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.util.Direction.UP, this);
        } else {
            return true;
        }
    }

    public boolean grow(ServerWorld worldIn, BlockPos pos, BlockState state, Random rand) {
        worldIn.removeBlock(pos, false);
        //ConfiguredFeature<BigMushroomFeatureConfig, ?> configuredfeature;
    /*    if (this == Blocks.BROWN_MUSHROOM) {
            configuredfeature = Feature.HUGE_BROWN_MUSHROOM.withConfiguration(DefaultBiomeFeatures.BIG_BROWN_MUSHROOM);
        } else {
            if (this != Blocks.RED_MUSHROOM) {
                worldIn.setBlockState(pos, state, 3);
                return false;
            }

            configuredfeature = Feature.HUGE_RED_MUSHROOM.withConfiguration(DefaultBiomeFeatures.BIG_RED_MUSHROOM);
        }

        if (configuredfeature.place(worldIn, worldIn.getChunkProvider().getChunkGenerator(), rand, pos)) {
            return true;
        } else { */
            worldIn.setBlockState(pos, state, 3);
            return false;
      //  }
    }


    public boolean needsPostProcessing(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 0.9F;
    }


}