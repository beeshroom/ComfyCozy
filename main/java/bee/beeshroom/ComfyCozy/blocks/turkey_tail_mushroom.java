/*package bee.beeshroom.ComfyCozy.blocks;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class turkey_tail_mushroom extends BlockDirectional {
	   public static final PropertyBool UP = PropertyBool.create("up");
	    public static final PropertyBool NORTH = PropertyBool.create("north");
	    public static final PropertyBool EAST = PropertyBool.create("east");
	    public static final PropertyBool SOUTH = PropertyBool.create("south");
	    public static final PropertyBool WEST = PropertyBool.create("west");
	public static final AxisAlignedBB MUSHROOM_WE = new AxisAlignedBB(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D);
    public static final AxisAlignedBB MUSHROOM = new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D);
    public static final AxisAlignedBB MUSHROOM_VERT = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.4D, 0.9375D);

	
	  public turkey_tail_mushroom(String name, Material material) 
	    {
			super(material);
			this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
			setSoundType(SoundType.PLANT);
			setHardness(0.0F);
			setResistance(0.1F);
			setHarvestLevel("axe", 0);

			setRegistryName(name);
			setUnlocalizedName(name);
			
			setCreativeTab(Main.comfycozytab);
			ModBlocks.BLOCKS.add(this);
			ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		}
	    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
	    {
	        return true;
	    }
	
	  @Override
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return NULL_AABB;
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
		
		
	    public EnumPushReaction getMobilityFlag(IBlockState state)
	    { return EnumPushReaction.DESTROY;}			
	    
	  
	  
	    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	    {
	        return true;
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

	/*        return this.getDefaultState().withProperty(FACING, facing);
	    }
	    
	  
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
	    
	    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) 
	    {
	        switch (((EnumFacing)state.getValue(FACING)).getAxis())
	        {
	            case X:
	            default:
	                return MUSHROOM_WE;
	            case Z:
	                return MUSHROOM;
	          case Y:
	                return MUSHROOM_VERT;
	        }
	    } 
	    
	    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return BlockFaceShape.UNDEFINED;
	    }
	    
	    public static PropertyBool getPropertyFor(EnumFacing side)
	    {
	        switch (side)
	        {
	            case UP:
	                return UP;
	            case NORTH:
	                return NORTH;
	            case SOUTH:
	                return SOUTH;
	            case WEST:
	                return WEST;
	            case EAST:
	                return EAST;
	            default:
	                throw new IllegalArgumentException(side + " is an invalid choice");
	        }
	    }

}
*/

