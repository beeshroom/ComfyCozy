/*package bee.beeshroom.ComfyCozy.world.generation.generators;

import java.util.Random;

import bee.beeshroom.ComfyCozy.blocks.cinnamon_log;
import bee.beeshroom.ComfyCozy.blocks.crops.strawberry_plant;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenStrawberry extends WorldGenerator
{
    private strawberry_plant block;
    private int state;

    public WorldGenStrawberry(strawberry_plant blockIn)
    {
    	this.block = blockIn;
    }
    
    public void setGeneratedBlock(strawberry_plant blockIn)
    {
        this.state = 3;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < worldIn.getHeight() - 1))
            {
                worldIn.setBlockState(blockpos, ModBlocks.STRAWBERRY_PLANT.getDefaultState().withProperty(strawberry_plant.AGE, Integer.valueOf(3)));
               // ModBlocks.STRAWBERRY_PLANT.getDefaultState().withProperty(strawberry_plant.AGE, Integer.valueOf(3));
            }
        }

        return true;
    }
}*/