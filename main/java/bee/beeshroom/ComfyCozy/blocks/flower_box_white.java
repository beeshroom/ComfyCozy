package bee.beeshroom.ComfyCozy.blocks;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class flower_box_white extends BlockDirectional
{

    protected static final AxisAlignedBB BEAM = new AxisAlignedBB(0.03125D, 0.0D, 0.03125D, 0.96875D, 0.9375D, 0.96875D);
    protected static final AxisAlignedBB BEAM_NS = new AxisAlignedBB(0.125D, 0.3125D, 0.0D, 0.875D, 0.9375D, 1.0D);
    protected static final AxisAlignedBB BEAM_EW = new AxisAlignedBB(0.0D, 0.3125D, 0.125D, 1.0D, 0.9375D, 0.875D);
	
  /*  protected static final AxisAlignedBB BEAM = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
    protected static final AxisAlignedBB BEAM_EW = new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 1.0D);
    protected static final AxisAlignedBB BEAM_NS = new AxisAlignedBB(0.0D, 0.375D, 0.375D, 1.0D, 0.625D, 0.625D); */

    public flower_box_white(String name, Material material) 
    {
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN));
		setSoundType(SoundType.STONE);
		setHardness(1.5F);
		setResistance(1.0F);
		setHarvestLevel("pickaxe", 0);

		setRegistryName(name);
		setUnlocalizedName(name);
		
		setCreativeTab(Main.comfycozytab);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return BEAM;
    }
    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos.offset(facing.getOpposite()));

     /*   if (iblockstate.getBlock() == Blocks.END_ROD)
        {
            EnumFacing enumfacing = (EnumFacing)iblockstate.getValue(FACING);

            if (enumfacing == facing)
            {
                return this.getDefaultState().withProperty(FACING, facing.getOpposite());
            }
        }*/

        return this.getDefaultState().withProperty(FACING, facing);
    }
    
    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
  
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();
        iblockstate = iblockstate.withProperty(FACING, EnumFacing.getFront(meta));
        return iblockstate;
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }


    
    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withProperty(FACING, mirrorIn.mirror((EnumFacing)state.getValue(FACING)));
    }


    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (((EnumFacing)state.getValue(FACING)).getAxis())
        {
            case X:
            default:
                return BEAM_NS;
            case Z:
                return BEAM_EW;
          case Y:
                return BEAM;
        }
    } 
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    

	@Override
	public boolean isFertile(World world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			net.minecraftforge.common.IPlantable plantable) {
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));
		switch (plantType) {
            case Desert: return true;
            case Nether: return false;
            case Crop:   return true;
            case Cave:   return true;
            case Plains: return true;
            case Water:  return false;
            case Beach:  return true;
        }
		return super.canSustainPlant(state, world, pos, direction, plantable);
	}

    
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
    {
        return canPlaceBlock(worldIn, pos, side);
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (canPlaceBlock(worldIn, pos, enumfacing))
            {
                return true;
            }
        }

        return false;
    }

    protected static boolean canPlaceBlock(World worldIn, BlockPos pos, EnumFacing direction)
    {
        BlockPos blockpos = pos.offset(direction.getOpposite());
        IBlockState iblockstate = worldIn.getBlockState(blockpos);
        boolean flag = iblockstate.getBlockFaceShape(worldIn, blockpos, direction) == BlockFaceShape.SOLID;
        Block block = iblockstate.getBlock();

        if (direction == EnumFacing.UP)
        {
            return iblockstate.isTopSolid() || !isExceptionBlockForAttaching(block) && flag;
        }
        else
        {
            return !isExceptBlockForAttachWithPiston(block) && flag;
        }
    }
	
}