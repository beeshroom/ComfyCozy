package bee.beeshroom.ComfyCozy.world.generation.generators;

import java.util.Random;

import bee.beeshroom.ComfyCozy.blocks.crops.strawberry_plant;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWhiteMushroom extends WorldGenerator
{
public boolean generate(World worldIn, Random rand, BlockPos position)
{
    for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position))
    {
        position = position.down();
    }

    for (int i = 0; i < 4; ++i)
    {
        BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

        if (worldIn.isAirBlock(blockpos) && ModBlocks.WHITE_MUSHROOM.canPlaceBlockAt(worldIn, blockpos))
        {
      	  worldIn.setBlockState(blockpos,  ModBlocks.WHITE_MUSHROOM.getDefaultState(), 2);
        }
    }

    return true;
}
}
 
 