package bee.beeshroom.ComfyCozy.blocks;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class jam_block extends BlockBase {
	
	protected static final AxisAlignedBB JAM_AABB = new AxisAlignedBB(0.03125D, 0.0D, 0.03125D, 0.96875D, 0.875D, 0.96875D);
	
	public jam_block(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.SLIME);
		setHardness(0.0F);
		setResistance(0.8F);
		setHarvestLevel("shovel", 0);
		setLightOpacity(5);
	//	 this.slipperiness = 0.8F;
	}
	
	public boolean isStickyBlock(IBlockState state)
    {
        return state.getBlock() == ModBlocks.JAM_BLOCK;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return JAM_AABB;
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    { 
        entityIn.motionX *= 0.4D;
        entityIn.motionZ *= 0.4D;
        entityIn.motionY *= 0.7D;
        entityIn.setInWeb();
      //  entityIn.fall(fallDistance, 0.3F);
    }
    
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        {
            entityIn.fall(fallDistance, 0.0F);
        }
    }
	
}