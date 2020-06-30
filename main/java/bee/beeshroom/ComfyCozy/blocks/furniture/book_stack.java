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

public class book_stack extends BlockHorizontal {

 // public static final PropertyDirection FACING = BlockHorizontal.FACING;
	 //   protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[], new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	 public static final PropertyInteger MODE = PropertyInteger.create("mode", 0, 2);
	 
//this code is copied directly from another mod i made where this code worked fine.
	public static final AxisAlignedBB BOOKS = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.565D, 0.75D);
	public static final AxisAlignedBB BOOKS_WE = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.565D, 0.75D);
  
  
  
	    public book_stack(String name, Material material) 
	    {
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(MODE, Integer.valueOf(0)));
		setSoundType(SoundType.WOOD);
		setHardness(0.2F);
		setResistance(0.1F);
		//setHarvestLevel("axe", 0);
		setRegistryName(name);
		setUnlocalizedName(name);
		
		setCreativeTab(Main.comfycozytab);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}  
	    
	    @Override
		public float getEnchantPowerBonus(World world, BlockPos pos) {
			return 1;
		}
	    
	    public int quantityDropped(Random random)
	    {
	        return 3;
	    }

	    /**
	     * Get the Item that this Block should drop when harvested.
	     */
	    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
	        return Items.BOOK;
	    }
	
	  protected boolean canSilkHarvest()
	    {
	        return true;
	    }
	
	  @Override
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return BOOKS;
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
	        return !worldIn.isAirBlock(pos.down());
	    }
	    */
	
	
	
	
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
	    
	    
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }
	    
	  
	
	    
	    
	    
	    /**
	     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
	     * IBlockstate
	     */
	    @Override
	    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
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
	    
	/*    public IBlockState getStateFromMeta(int meta)
	    {
	        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta)).withProperty(MODE, Integer.valueOf((meta & 15) >> 9));
	    }
	    public int getMetaFromState(IBlockState state)
	    {
	        int i = 0;
	        i = i | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
	        i = i | ((Integer)state.getValue(MODE)).intValue() << 9;
	        return i;
	    } */
	    
	    

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
	        return new BlockStateContainer(this, new IProperty[] {FACING, MODE});
	    }
	    
	    
	    
	    @Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
		{
			switch(((EnumFacing)state.getValue(FACING)))
	        {
	            case SOUTH:
	            default:
	                return BOOKS_WE;
	            case NORTH:
	                return BOOKS_WE;
	            case EAST:
	                return BOOKS;
	            case WEST:
	                return BOOKS;
	        }
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
	    
	    
	    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	    {
	        return this.canBePlacedOn(worldIn, pos.down());
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
	        return worldIn.getBlockState(pos).isTopSolid() || worldIn.getBlockState(pos).getBlock() instanceof BlockFence;
	    }
}

