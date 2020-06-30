package bee.beeshroom.ComfyCozy.blocks.furniture;

import java.util.Random;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.blocks.BlockBase;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


//thank-you turtywurty for your custom block model tutorial on youtube, 
//took some vanilla ladder code

public class frame extends BlockHorizontal {

 // public static final PropertyDirection FACING = BlockHorizontal.FACING;
	 //   protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[], new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	 public static final PropertyInteger MODE = PropertyInteger.create("mode", 0, 2);
	 
//this code is copied directly from another mod i made where this code worked fine.
	public static final AxisAlignedBB FRAME = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB FRAME_WEST = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB FRAME_EAST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
    protected static final AxisAlignedBB FRAME_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
	
	//public static final AxisAlignedBB FRAME_WE = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
  
  
  
	    public frame(String name, Material material) 
	    {
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(MODE, Integer.valueOf(0)));
		setSoundType(SoundType.WOOD);
		setHardness(0.2F);
		setResistance(0.4F);
		//setHarvestLevel("axe", 0);
		setRegistryName(name);
		setUnlocalizedName(name);
		
		setCreativeTab(Main.comfycozytab);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}  

	/*    @Override
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	    	switch(((EnumFacing)blockState.getValue(FACING)))
	        {
	            case SOUTH:
	            default:
	                return FRAME_SOUTH;
	            case NORTH:
	                return FRAME;
	            case EAST:
	                return FRAME_EAST;
	            case WEST:
	                return FRAME_WEST;
	        }
	    } */

	    
	    @Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
		{
			switch(((EnumFacing)state.getValue(FACING)))
	        {
	        case SOUTH:
            default:
                return FRAME_SOUTH;
            case NORTH:
                return FRAME;
            case EAST:
                return FRAME_EAST;
            case WEST:
                return FRAME_WEST;
	        }
		}
		
	  
	/*  private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
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
	    } */
	  
	  

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

	    {

	        return 9;

	    }


	    @Override

	    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)

	    {

	        return 5;

	    }
		
		
	    public EnumPushReaction getMobilityFlag(IBlockState state)
	    {
	        return EnumPushReaction.DESTROY;
	    }			
	    
	    
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }
	    
	    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	    {
	        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

	        if (!this.canAttachTo(worldIn, pos.offset(enumfacing.getOpposite()), enumfacing))
	        {
	            this.dropBlockAsItem(worldIn, pos, state, 0);
	            worldIn.setBlockToAir(pos);
	        }

	        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
	    }
	    
	    /**
	     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
	     * IBlockstate
	     */
	  /*  @Override
	    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	    } */
	    @Override
	    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	        if (facing.getAxis().isHorizontal() && this.canAttachTo(worldIn, pos.offset(facing.getOpposite()), facing))
	        {
	            return this.getDefaultState().withProperty(FACING, facing);
	        }
	        else
	        {
	            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
	            {
	                if (this.canAttachTo(worldIn, pos.offset(enumfacing.getOpposite()), enumfacing))
	                {
	                    return this.getDefaultState().withProperty(FACING, enumfacing);
	                }
	            }

	            return this.getDefaultState();
	        }
	    }

	    /**
	     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	     */
	    @Override
	    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	    {
	        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

	    }
	  
	    
	    
	 
	    
	    
	    @Override
	    public IBlockState getStateFromMeta(int meta)
	    {
	    	/* EnumFacing enumfacing = EnumFacing.getFront(meta);

	         if (enumfacing.getAxis() == EnumFacing.Axis.Y)
	         {
	             enumfacing = EnumFacing.NORTH;
	         } */
	        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)).withProperty(MODE, Integer.valueOf((meta & 15) >> 2));
	    }

	    @Override
	    public int getMetaFromState(IBlockState state)
	    {
	        int i = 0;
	        i = i | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
	        i = i | ((Integer)state.getValue(MODE)).intValue() << 2;
	        return i;
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
	    }
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

	    
	    public IBlockState withRotation(IBlockState state, Rotation rot)
	    {
	        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	    }

	    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	    {
	        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	    }

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {FACING, MODE});
	    }
		
		public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
  {
      return BlockFaceShape.UNDEFINED;
  }	
		
	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	    	  ItemStack itemstack = playerIn.getHeldItem(hand);

	    	    if (!itemstack.isEmpty() && (itemstack.getItem() == ModItems.COZY_HAMMER))
	    	    	
	    	    	 if (!worldIn.isRemote)
	    		        {
	    		            return this.changeMode(worldIn, pos, state, playerIn);
	    		        }
	    	    	 else
	    		        {
	    		            //ItemStack itemstack = playerIn.getHeldItem(hand);
	    		            return this.changeMode(worldIn, pos, state, playerIn) || itemstack.isEmpty();
	    		        } 
	    	    return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	    	    }
	    
	    
	    private boolean changeMode(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	    {
	        {
	            worldIn.playSound((EntityPlayer)null, pos, SoundsHandler.HAMMER, SoundCategory.BLOCKS, 0.4F, 0.8F);
	            int i = ((Integer)state.getValue(MODE)).intValue();

	            if (i < 2)
	            {
	                worldIn.setBlockState(pos, state.withProperty(MODE, Integer.valueOf(i + 1))); // , 3);
	                
	            }
	            else
	            {
	            	worldIn.setBlockState(pos, state.withProperty(MODE, Integer.valueOf(0)));
	            }

	            return true;
	        }
	    }
	    
	    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
	    {
	        if (this.canAttachTo(worldIn, pos.west(), side))
	        {
	            return true;
	        }
	        else if (this.canAttachTo(worldIn, pos.east(), side))
	        {
	            return true;
	        }
	        else if (this.canAttachTo(worldIn, pos.north(), side))
	        {
	            return true;
	        }
	        else
	        {
	            return this.canAttachTo(worldIn, pos.south(), side);
	        }
	    }

	    private boolean canAttachTo(World p_193392_1_, BlockPos p_193392_2_, EnumFacing p_193392_3_)
	    {
	        IBlockState iblockstate = p_193392_1_.getBlockState(p_193392_2_);
	        boolean flag = isExceptBlockForAttachWithPiston(iblockstate.getBlock());
	        return !flag && iblockstate.getBlockFaceShape(p_193392_1_, p_193392_2_, p_193392_3_) == BlockFaceShape.SOLID && !iblockstate.canProvidePower();
	    }
	    
	    
}

