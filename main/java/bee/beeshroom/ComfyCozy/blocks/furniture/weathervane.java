package bee.beeshroom.ComfyCozy.blocks.furniture;

import bee.beeshroom.ComfyCozy.blocks.BlockBase;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


//thank-you turtywurty for your custom block model tutorial on youtube and thank-you to end rod code vanilla

public class weathervane extends BlockBase
{
	
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
//this code is copied directly from another mod i made where this code worked fine.
 //   public static final AxisAlignedBB BUNTING_VERT = new AxisAlignedBB(0.9375D, 0.0D, 0.46875D, 0.625D, 0.0D, 1.0D);
  //  public static final AxisAlignedBB POLE = new AxisAlignedBB(0.46875D, 0.9375D, 0.0D, 0.54125D, 0.625D, 1.0D);
/*	public static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
	public static final AxisAlignedBB AABB_WE = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D); */
	public static final AxisAlignedBB AABB = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
	public static final AxisAlignedBB AABB_WE = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
	
	    public weathervane(String name, Material material) {
		super(name, material);
		//this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setSoundType(SoundType.METAL);
		setHardness(5.5F);
		setResistance(3.5F);
		setHarvestLevel("pickaxe", 0);

	/*	setRegistryName(name);
		setUnlocalizedName(name);
		
		setCreativeTab(Main.comfycozytab);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));*/
	}
	
	  @Override
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return AABB;
	    }
	 
	
	@Override
	public BlockRenderLayer getBlockLayer() 
	{
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) 
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) 
	{
		return false;
	}
	  
	    /**
	     * Checks if this block can be placed exactly at the given position.
	     */
	    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	    {
	        return true;
	    }
	    
	    /**
	     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	     */
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }
	    

		  private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
		    {
		        if (!worldIn.isRemote)
		        {
		            IBlockState iblockstate = worldIn.getBlockState(pos.north());
		            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
		            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
		            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
		            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

		            if (enumfacing == EnumFacing.NORTH)
		            {
		                enumfacing = EnumFacing.SOUTH;
		                
		            }
		            else if (enumfacing == EnumFacing.SOUTH)
		            {
		                enumfacing = EnumFacing.NORTH;
		            }
		            else if (enumfacing == EnumFacing.WEST)
		            {
		                enumfacing = EnumFacing.EAST;
		            }
		            else if (enumfacing == EnumFacing.EAST)
		            {
		                enumfacing = EnumFacing.WEST;
		            }

		            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		        }
		    }

	      
	    /**
	     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
	     * IBlockstate
	     */
	    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	    }

	    /**
	     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	     */
	    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	    {
	        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

	    }
	    
	    
	    
	    /**
	     * Convert the given metadata into a BlockState for this Block
	     */
	    public IBlockState getStateFromMeta(int meta)
	    {
	        EnumFacing enumfacing = EnumFacing.getFront(meta);

	        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
	        {
	            enumfacing = EnumFacing.NORTH;
	        }

	        return this.getDefaultState().withProperty(FACING, enumfacing);
	    }

	    /**
	     * Convert the BlockState into the correct metadata value
	     */
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

	    /**
	     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	     * blockstate.
	     */
	    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	    {
	        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	    }

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {FACING});
	    }
	    
	    
	    
	    @Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
		{
			switch(((EnumFacing)state.getValue(FACING)))
	        {
	            case SOUTH:
	            default:
	                return AABB_WE;
	            case NORTH:
	                return AABB_WE;
	            case EAST:
	                return AABB;
	            case WEST:
	                return AABB;
	        }
		}
		
	    
	    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return BlockFaceShape.UNDEFINED;
	    }
}

