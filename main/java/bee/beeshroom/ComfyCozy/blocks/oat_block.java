package bee.beeshroom.ComfyCozy.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class oat_block extends BlockBase {
	
	//protected static final AxisAlignedBB OAT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
	protected static final AxisAlignedBB OAT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
	//protected static final AxisAlignedBB OAT_AABB = new AxisAlignedBB(0.0625D, 0.0625D, 0.0625D, 0.09375D, 0.0625D, 0.09375D);
	
	public oat_block(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.CLOTH);
		setHardness(0.7F);
		setResistance(0.4F);
		setHarvestLevel("shovel", 0);
	}
	
	 @Nullable
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return OAT_AABB;
	    }
	
}