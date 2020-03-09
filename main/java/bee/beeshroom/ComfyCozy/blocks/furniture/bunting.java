package bee.beeshroom.ComfyCozy.blocks.furniture;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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

//thank-you turtywurty for your custom block model tutorial on youtube and thank-you to end rod code from vanilla

public class bunting extends BlockHorizontal
{

	//public static final PropertyInteger TIE = PropertyInteger.create("tie", 0, 1);
//this code is copied directly from another mod i made where this code worked fine.
 //   public static final AxisAlignedBB BUNTING_VERT = new AxisAlignedBB(0.9375D, 0.0D, 0.46875D, 0.625D, 0.0D, 1.0D);
//    public static final AxisAlignedBB BUNTING_WE = new AxisAlignedBB(0.0D, 0.9375D, 0.46875D, 1.0D, 0.625D, 0.54125D);
   // public static final AxisAlignedBB BUNTING = new AxisAlignedBB(0.46875D, 0.9375D, 0.0D, 0.54125D, 0.625D, 1.0D);
//    public static final AxisAlignedBB BUNTING_VERT = new AxisAlignedBB(0.4375D, 0D, 0.4375D, 0.5625D, 1D, 0.5625D);
    
	public static final PropertyBool LONG = PropertyBool.create("long");
	 public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
    public static final AxisAlignedBB BUNTING_WE = new AxisAlignedBB(0.0D, 0.8775D, 0.375D, 1.0D, 0.525D, 0.625D);
    public static final AxisAlignedBB BUNTING = new AxisAlignedBB(0.375D, 0.8775D, 0.0D, 0.625D, 0.525D, 1.0D);
    //public static final AxisAlignedBB BUNTING_VERT = new AxisAlignedBB(0.4375D, 0D, 0.4375D, 0.5625D, 1D, 0.5625D);


    
	    public bunting(String name, Material material) 
	    {
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LONG, Boolean.valueOf(false)));
		setSoundType(SoundType.CLOTH);
		setHardness(0.0F);
		setResistance(0.1F);
		//setHarvestLevel("shears", 0);
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
	    
	  
	    /**
	     * Checks if this block can be placed exactly at the given position.
	     */
	    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	    {
	        return true;
	    }

	    /**
	     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
	     * IBlockstate
	     */
	    
	    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	    }
	    
	    
	/*    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
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
/*
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
	  
	    
	    //from fence gate code
	    public IBlockState getStateFromMeta(int meta)
	    {
	        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta)).withProperty(LONG, Boolean.valueOf((meta & 4) != 0));
	    }

	    public int getMetaFromState(IBlockState state)
	    {
	        int i = 0;
	        i = i | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();

	        if (((Boolean)state.getValue(LONG)).booleanValue())
	        {
	            i |= 4;
	        }

	        return i;
	    }
/*
	    protected static EnumFacing getFacing(int meta)
	    {
	        switch (meta & 3)
	        {
	            case 0:
	                return EnumFacing.NORTH;
	            case 1:
	                return EnumFacing.SOUTH;
	            case 2:
	                return EnumFacing.WEST;
	            case 3:
	            default:
	                return EnumFacing.EAST;
	        }
	    }
	    protected static int getMetaForFacing(EnumFacing facing)
	    {
	        switch (facing)
	        {
	            case NORTH:
	                return 0;
	            case SOUTH:
	                return 1;
	            case WEST:
	                return 2;
	            case EAST:
	            default:
	                return 3;
	        }
	    } */
	    
	    @Override
	 			public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	 			{
	 				switch(((EnumFacing)state.getValue(FACING)))
	 		        {
	 		            case SOUTH:
	 		            default:
	 		                return BUNTING;
	 		            case NORTH:
	 		                return BUNTING;
	 		            case EAST:
	 		                return BUNTING_WE;
	 		            case WEST:
	 		                return BUNTING_WE;
	 		        }
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
	        return new BlockStateContainer(this, new IProperty[] {FACING, LONG});
	    }
	    
	    
	/*    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	        switch (((EnumFacing)state.getValue(FACING)).getAxis())
	        {
	            case X:
	            default:
	                return BUNTING_WE;
	            case Z:
	                return BUNTING;
	          case Y:
	                return BUNTING_VERT;
	        }
	    } */
	    
	    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return BlockFaceShape.UNDEFINED;
	    }
	    /*
	    public boolean onBlockActivated(EntityPlayer player, World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	    	if(player.isSneaking() && facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, null))
	        {
	    		 worldIn.setBlockState(pos, state.withProperty(TIE, Integer.valueOf(1)), 3);
	           // getDefaultState().withProperty(TIE, Integer.valueOf(1));
	            return true;
	        }
	    	
	        else
	        {
	            return false;
	        }
	    }*/
	    
	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	    	  ItemStack itemstack = playerIn.getHeldItem(hand);

	    	    //if (!itemstack.isEmpty() && itemstack.getItem() == Items.BONE)

	    	    if (!itemstack.isEmpty() && (itemstack.getItem() == ModItems.COZY_HAMMER))
	         {
	            state = state.cycleProperty(LONG);
	            worldIn.setBlockState(pos, state, 2);
	            worldIn.playSound((EntityPlayer)null, pos, SoundsHandler.HAMMER, SoundCategory.BLOCKS, 0.5F, 1.6F);
	           // this.playSound(playerIn, worldIn, pos, ((Boolean)state.getValue(OPEN)).booleanValue());
	            return true;
	         }
	         else
	         {
	         return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
			} }	 
	    
	
}

