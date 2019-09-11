
package bee.beeshroom.ComfyCozy.blocks.furniture;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


//thank-you turtywurty for your custom block model tutorial on youtube, 

public class awning_pink extends BlockBase implements net.minecraftforge.common.IShearable {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
	 //   protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[], new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	
//this code is copied directly from another mod i made where this code worked fine.
	public static final AxisAlignedBB AWNING = new AxisAlignedBB(0.0D, 0.2875D, 0.0D, 1.0D, 0.48D, 1.0D);
	public static final AxisAlignedBB AWNING_WE = new AxisAlignedBB(0.0D, 0.2875D, 0.0D, 1.0D, 0.48D, 1.0D);
    
    
    
	    public awning_pink(String name, Material material) {
		super(name, material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
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
	        return AWNING;
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
	     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	     */
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
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
	                return AWNING_WE;
	            case NORTH:
	                return AWNING_WE;
	            case EAST:
	                return AWNING;
	            case WEST:
	                return AWNING;
	        }
		}
		
		public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
}

