package bee.beeshroom.ComfyCozy.blocks.furniture;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.blocks.BlockBase;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLever;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
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
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


//thank-you turtywurty for your custom block model tutorial on youtube, 

public class carp_banner_2 extends BlockBase implements net.minecraftforge.common.IShearable {

//	 public static final PropertyEnum<BlockLever.EnumOrientation> FACING = PropertyEnum.<BlockLever.EnumOrientation>create("facing", BlockLever.EnumOrientation.class);
  public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool MODE = PropertyBool.create("mode");
	 //   protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[], new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	
//this code is copied directly from another mod i made where this code worked fine.
	public static final AxisAlignedBB POLE = new AxisAlignedBB(0.4375D, 0D, 0.4375D, 0.5625D, 1D, 0.5625D);
	//backup 	public static final AxisAlignedBB POLE = new AxisAlignedBB(0.4375D, 0D, 0.4375D, 0.5625D, 1D, 0.5625D);
	//public static final AxisAlignedBB POLE_SIDE = new AxisAlignedBB(0.4375D, 0.125D, 0.0D, 0.5625D, 1.125D, 1D);
	//public static final AxisAlignedBB POLE_WE = new AxisAlignedBB(0.4375D, 0D, 0.4375D, 0.5625D, 1D, 0.5625D);
  
  
  
	    public carp_banner_2(String name, Material material) {
		super(name, material);
       // this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, BlockLever.EnumOrientation.NORTH).withProperty(MODE, Boolean.valueOf(false)));
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(MODE, Boolean.valueOf(false)));
		//setSoundType(SoundType.CLOTH);
		setHardness(0.3F);
		setResistance(0.1F);
		setHarvestLevel("axe", 0);
	}  
	    
	    //reduce fall damage
	    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
	    {
	        entityIn.fall(fallDistance, 0.1F);
	    }
	    

	  
	  public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
	    {
	        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
	        {
	            player.addStat(StatList.getBlockStats(this));
	            spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this), 1));
	        }
	        else
	        {
	            super.harvestBlock(worldIn, player, pos, state, te, stack);
	        }
	    }

	    @Override public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) { return true; }
	    @Override
	    public java.util.List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
	        return com.google.common.collect.Lists.newArrayList(new ItemStack(Item.getItemFromBlock(this)));
	    }
	
	  protected boolean canSilkHarvest()
	    {
	        return true;
	    }
	
	  @Override
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return POLE;
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

	 
	
	
	  /*	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
  {
      return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
  }
	
	  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	    {
	        this.checkForDrop(worldIn, pos, state);
	    }

	    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
	    {
	        if (!this.canBlockStay(worldIn, pos))
	        {
	            this.dropBlockAsItem(worldIn, pos, state, 0);
	            worldIn.setBlockToAir(pos);
	            return false;
	        }
	        else
	        {
	            return true;
	        }
	    }
	    
	    private boolean canBlockStay(World worldIn, BlockPos pos)
	    {
	        return !worldIn.isAirBlock(pos.down()) || !worldIn.;
	    }  */
	    
	
	
	
	
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


	/*	@Override
		public void registerModels() {
			Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		}*/
		
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
	    
	  
	
	    
	    
	    
	  
	    // Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the IBlockstate

	   public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	    } 

	    
	     // Called by ItemBlocks after a block is set in the world, to allow post-place logic
	     
	    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	    {
	        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

	    }  
	    
	    
	    
	    
	    

	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }
	    
	    public IBlockState getStateFromMeta(int meta)
	    {
	        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta)).withProperty(MODE, Boolean.valueOf((meta & 4) != 0));
	    }

	    public int getMetaFromState(IBlockState state)
	    {
	        int i = 0;
	        i = i | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();

	        if (((Boolean)state.getValue(MODE)).booleanValue())
	        {
	            i |= 4;
	        }

	        return i;
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
	    
	    
	    
	    @Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
		{
			switch(((EnumFacing)state.getValue(FACING)))
	        {
	            case SOUTH:
	            default:
	                return POLE;
	            case NORTH:
	                return POLE;
	            case EAST:
	                return POLE;
	            case WEST:
	                return POLE;
	        }
		}  
		
		public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
  {
      return BlockFaceShape.UNDEFINED;
  }
		
		
		   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
		    {
		    	  ItemStack itemstack = playerIn.getHeldItem(hand);

		    	    //if (!itemstack.isEmpty() && itemstack.getItem() == Items.BONE)

		    	    if (!itemstack.isEmpty() && (itemstack.getItem() == ModItems.COZY_HAMMER))
		         {
		            state = state.cycleProperty(MODE);
		            worldIn.setBlockState(pos, state, 2);
		            worldIn.playSound((EntityPlayer)null, pos, SoundsHandler.HAMMER, SoundCategory.BLOCKS, 0.5F, 1.6F);
		            return true;
		         }
		         else
		         {
		         return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
				} }	 
		
		   



}
		
		
		// new nonsense
		
		
	/*	
		  public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
		    {
		        return state.withRotation(mirrorIn.toRotation(((BlockLever.EnumOrientation)state.getValue(FACING)).getFacing()));
		    }

		    protected BlockStateContainer createBlockState()
		    {
		        return new BlockStateContainer(this, new IProperty[] {FACING, MODE});
		    }
		
		
		
		  public IBlockState getStateFromMeta(int meta)
		    {
		        return this.getDefaultState().withProperty(FACING, BlockLever.EnumOrientation.byMetadata(meta & 7)).withProperty(MODE, Boolean.valueOf((meta & 8) > 0));
		    }

	
		    public int getMetaFromState(IBlockState state)
		    {
		        int i = 0;
		        i = i | ((BlockLever.EnumOrientation)state.getValue(FACING)).getMetadata();

		        if (((Boolean)state.getValue(MODE)).booleanValue())
		        {
		            i |= 8;
		        }

		        return i;
		    }
		
		
		
		  public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
		    {
		        return this.canBePlacedOn(worldIn, pos.down()) || this.canBePlacedOn(worldIn, pos.north()) || this.canBePlacedOn(worldIn, pos.east()) || this.canBePlacedOn(worldIn, pos.south()) || this.canBePlacedOn(worldIn, pos.west());
		    } 

		    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
		    {
		        if (!this.canBePlacedOn(worldIn, pos.down())) 
		        {
		            this.dropBlockAsItem(worldIn, pos, state, 0);
		            worldIn.setBlockToAir(pos);
		        }
		    }

		    private boolean canBePlacedOn(World worldIn, BlockPos pos)
		    {
		        return worldIn.getBlockState(pos).isTopSolid(); //|| worldIn.getBlockState(pos).getBlock() instanceof BlockFence;
		    }
		
}
		    
		    
		    //new nonnsense
		    
/*		    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
		    {
		        switch ((BlockLever.EnumOrientation)state.getValue(FACING))
		        {
		            case EAST:
		            default:
		                return POLE_SIDE;
		            case WEST:
		                return POLE_SIDE;
		            case SOUTH:
		                return POLE_SIDE;
		            case NORTH:
		                return POLE_SIDE;
		            case UP_Z:
		            case UP_X:
		                return POLE;
		            case DOWN_X:
		            case DOWN_Z:
		                return POLE;
		        }
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

		    protected static boolean canAttachTo(World worldIn, BlockPos p_181090_1_, EnumFacing p_181090_2_)
		    {
		        return carp_banner.canPlaceBlock(worldIn, p_181090_1_, p_181090_2_);
		    }
		    
		    
		    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
		    {
		        IBlockState iblockstate = this.getDefaultState().withProperty(MODE, Boolean.valueOf(false));

		        if (canAttachTo(worldIn, pos, facing))
		        {
		            return iblockstate.withProperty(FACING, BlockLever.EnumOrientation.forFacings(facing, placer.getHorizontalFacing()));
		        }
		        else
		        {
		            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
		            {
		                if (enumfacing != facing && canAttachTo(worldIn, pos, enumfacing))
		                {
		                    return iblockstate.withProperty(FACING, BlockLever.EnumOrientation.forFacings(enumfacing, placer.getHorizontalFacing()));
		                }
		            }

		            if (worldIn.getBlockState(pos.down()).isTopSolid())
		            {
		                return iblockstate.withProperty(FACING, BlockLever.EnumOrientation.forFacings(EnumFacing.UP, placer.getHorizontalFacing()));
		            }
		            else
		            {
		                return iblockstate;
		            }
		        }
		    }
		
		    
		    public static enum EnumOrientation implements IStringSerializable
		    {
		        DOWN_X(0, "down_x", EnumFacing.DOWN),
		        EAST(1, "east", EnumFacing.EAST),
		        WEST(2, "west", EnumFacing.WEST),
		        SOUTH(3, "south", EnumFacing.SOUTH),
		        NORTH(4, "north", EnumFacing.NORTH),
		        UP_Z(5, "up_z", EnumFacing.UP),
		        UP_X(6, "up_x", EnumFacing.UP),
		        DOWN_Z(7, "down_z", EnumFacing.DOWN);

		        private static final BlockLever.EnumOrientation[] META_LOOKUP = new BlockLever.EnumOrientation[values().length];
		        private final int meta;
		        private final String name;
		        private final EnumFacing facing;

		        private EnumOrientation(int meta, String name, EnumFacing facing)
		        {
		            this.meta = meta;
		            this.name = name;
		            this.facing = facing;
		        }

		        public int getMetadata()
		        {
		            return this.meta;
		        }

		        public EnumFacing getFacing()
		        {
		            return this.facing;
		        }

		        public String toString()
		        {
		            return this.name;
		        }

		        public static BlockLever.EnumOrientation byMetadata(int meta)
		        {
		            if (meta < 0 || meta >= META_LOOKUP.length)
		            {
		                meta = 0;
		            }

		            return META_LOOKUP[meta];
		        }

		        public static carp_banner.EnumOrientation forFacings(EnumFacing clickedSide, EnumFacing entityFacing)
		        {
		            switch (clickedSide)
		            {
		                case DOWN:

		                    switch (entityFacing.getAxis())
		                    {
		                        case X:
		                            return DOWN_X;
		                        case Z:
		                            return DOWN_Z;
		                        default:
		                            throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
		                    }

		                case UP:

		                    switch (entityFacing.getAxis())
		                    {
		                        case X:
		                            return UP_X;
		                        case Z:
		                            return UP_Z;
		                        default:
		                            throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
		                    }

		                case NORTH:
		                    return NORTH;
		                case SOUTH:
		                    return SOUTH;
		                case WEST:
		                    return WEST;
		                case EAST:
		                    return EAST;
		                default:
		                    throw new IllegalArgumentException("Invalid facing: " + clickedSide);
		            }
		        }

		        public String getName()
		        {
		            return this.name;
		        }

		        static
		        {
		            for (BlockLever.EnumOrientation blocklever$enumorientation : values())
		            {
		                META_LOOKUP[blocklever$enumorientation.getMetadata()] = blocklever$enumorientation;
		            }
		        }
		    }
}
*/
