/*package bee.beeshroom.ComfyCozy.world.generation.generators;

import java.util.Random;

import bee.beeshroom.ComfyCozy.blocks.turkey_tail_mushroom;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTurkeytail extends WorldGenerator
{ 
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (; position.getY() < 128; position = position.up())
        {
            if (worldIn.isAirBlock(position))
            {
                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL.facings())
                {
                    if (ModBlocks.TURKEY_TAIL_MUSHROOM.canPlaceBlockOnSide(worldIn, position, enumfacing))
                    {
                        IBlockState iblockstate = ModBlocks.TURKEY_TAIL_MUSHROOM.getDefaultState().withProperty(turkey_tail_mushroom.NORTH, Boolean.valueOf(enumfacing == EnumFacing.NORTH)).withProperty(turkey_tail_mushroom.EAST, Boolean.valueOf(enumfacing == EnumFacing.EAST)).withProperty(turkey_tail_mushroom.SOUTH, Boolean.valueOf(enumfacing == EnumFacing.SOUTH)).withProperty(turkey_tail_mushroom.WEST, Boolean.valueOf(enumfacing == EnumFacing.WEST));
                        worldIn.setBlockState(position, iblockstate, 2);
                        break;
                    }
                }
            }
            else
            {
                position = position.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
            }
        }

        return true;
    } 
}*/
