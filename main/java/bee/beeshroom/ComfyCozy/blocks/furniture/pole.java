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
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
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
import net.minecraft.init.SoundEvents;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


//thank-you turtywurty for your custom block model tutorial on youtube and thank-you to end rod code vanilla

public class pole extends BlockHorizontal
{
	//  public static final PropertyDirection FACING = BlockHorizontal.FACING;
	 public static final PropertyInteger MODE = PropertyInteger.create("mode", 0, 2);
   // public static final PropertyBool TALL = PropertyBool.create("tall");
//this code is copied directly from another mod i made where this code worked fine.
 //   public static final AxisAlignedBB BUNTING_VERT = new AxisAlignedBB(0.9375D, 0.0D, 0.46875D, 0.625D, 0.0D, 1.0D);
  //  public static final AxisAlignedBB POLE = new AxisAlignedBB(0.46875D, 0.9375D, 0.0D, 0.54125D, 0.625D, 1.0D);
	public static final AxisAlignedBB POLE = new AxisAlignedBB(0.4375D, 0D, 0.4375D, 0.5625D, 1D, 0.5625D);
	
	    public pole(String name, Material material) {
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(MODE, Integer.valueOf(0)));
		setSoundType(SoundType.WOOD);
		setHardness(0.3F);
		setResistance(0.1F);
		setHarvestLevel("axe", 0);

		setRegistryName(name);
		setUnlocalizedName(name);
		
		setCreativeTab(Main.comfycozytab);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	    protected PropertyInteger getProperty()
	    {
	        return MODE;
	    }
	    
	  
	    
	    
	  @Override
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return POLE;
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
	     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	     */
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }

	    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	                return POLE;
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
	            worldIn.playSound((EntityPlayer)null, pos, SoundsHandler.HAMMER, SoundCategory.BLOCKS, 0.5F, 0.7F);
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
	        // return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
		  
	    
	    
	    
	    public boolean onBlockClicked(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	    	  ItemStack itemstack = playerIn.getHeldItem(hand);

	    	    if (!itemstack.isEmpty() && (itemstack.getItem() == ModItems.COZY_HAMMER))
	    	    	
	    	    	 if (!worldIn.isRemote)
	    		        {
	    		            return this.changeMode2(worldIn, pos, state, playerIn);
	    		        }
	    	    	 else
	    		        {
	    		            //ItemStack itemstack = playerIn.getHeldItem(hand);
	    		            return this.changeMode2(worldIn, pos, state, playerIn) || itemstack.isEmpty();
	    		        } 
	    	    return false; //super.onBlockClicked(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	    	    }
	    
	    private boolean changeMode2(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	    {
	        {
	            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_BAT_AMBIENT, SoundCategory.BLOCKS, 0.5F, 0.7F);
	            int i = ((Integer)state.getValue(MODE)).intValue();

	            if (i > 0)
	            {
	                worldIn.setBlockState(pos, state.withProperty(MODE, Integer.valueOf(i - 1))); // , 3);
	                
	            }
	            else
	            {
	            	worldIn.setBlockState(pos, state.withProperty(MODE, Integer.valueOf(7)));
	            }

	            return true;
	        }
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
	    
	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {FACING, MODE});
	    }

	    
	    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return BlockFaceShape.UNDEFINED;
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
		  
		    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
		    {
		        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
		    }

		    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
		    {
		        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

		    }
		    
		/*    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
		    {
		        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
		    }*/
		    
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
		    
	/*	    public IBlockState getStateFromMeta(int meta)
		    {
		        return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(MODE, Integer.valueOf((meta & 4) != 0));
		    }
		    
		    public int getMetaFromState(IBlockState state)
		    {
		        int i = 0;
		        i = i | getMetaForFacing((EnumFacing)state.getValue(FACING));

		        
		        if ((Integer)state.getValue(MODE)).intValue()
		        {
		            i |= 4;
		        }

		        return i;
		    } */
		    
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
		    
		/*    public IBlockState withMode(IBlockState state, Rotation rot)
		    {
		        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
		    } */
		    
		    public IBlockState withMode(IBlockState state, Rotation rot)
            {
                return state.withProperty(MODE, Integer.valueOf(rot.rotate(((Integer)state.getValue(MODE)).intValue(), 16)));
            }

            /**
             * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the
             * passed blockstate.
             */
            public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
            {
                return state.withProperty(MODE, Integer.valueOf(mirrorIn.mirrorRotation(((Integer)state.getValue(MODE)).intValue(), 16)));
            }
		
}

