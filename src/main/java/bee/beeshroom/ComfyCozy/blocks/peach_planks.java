package bee.beeshroom.ComfyCozy.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class peach_planks extends BlockBase {
	
	
	public peach_planks(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.WOOD);
		setHardness(2F);
		setResistance(3.0F);
		setHarvestLevel("axe", 0);
	}
	
	
	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		return super.canEntityDestroy(state, world, pos, entity);
	}

	@Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
	{return 9;}
	
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    { return 5;}
			
    
	
}