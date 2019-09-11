package bee.beeshroom.ComfyCozy.world.generation.generators;

import java.util.Random;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCinnamon extends WorldGenerator
{
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 20; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));

            if (worldIn.isAirBlock(blockpos))
            {
                BlockPos blockpos1 = blockpos.down();

                if (worldIn.getBlockState(blockpos1.west()).getBlock() == Blocks.GRASS || worldIn.getBlockState(blockpos1.east()).getBlock() == Blocks.GRASS || worldIn.getBlockState(blockpos1.north()).getBlock() == Blocks.GRASS || worldIn.getBlockState(blockpos1.south()).getBlock() == Blocks.GRASS)
                {
                    int j = 1 + rand.nextInt(rand.nextInt(1));

                    for (int k = 0; k < j; ++k)
                    {
                       // if (ModBlocks.CINNAMON_TREE.canBlockStay(worldIn, blockpos))
                      //  {
                            worldIn.setBlockState(blockpos.up(k), ModBlocks.CINNAMON_TREE.getDefaultState(), 1);
                      //   }
                    }
                }
            }
        }

        return true;
    }
}