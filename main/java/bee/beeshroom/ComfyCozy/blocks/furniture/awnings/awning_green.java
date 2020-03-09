package bee.beeshroom.ComfyCozy.blocks.furniture.awnings;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.blocks.BlockBase;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockTrapDoor;
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
import net.minecraft.init.SoundEvents;
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
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


//thank-you turtywurty for your custom block model tutorial on youtube, 

public class awning_green extends BlockBase implements net.minecraftforge.common.IShearable {

    public static final PropertyBool FLAT = PropertyBool.create("flat");
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
	 //   protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[], new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	
//this code is copied directly from another mod i made where this code worked fine.
	public static final AxisAlignedBB AWNING = new AxisAlignedBB(0.0D, 0.2875D, 0.0D, 1.0D, 0.48D, 1.0D);
	public static final AxisAlignedBB AWNING_WE = new AxisAlignedBB(0.0D, 0.2875D, 0.0D, 1.0D, 0.48D, 1.0D);
	public static final AxisAlignedBB AWNING_FLAT = new AxisAlignedBB(0.0D, 0.4875D, 0.0D, 1.0D, 0.69D, 1.0D);
    
	    public awning_green(String name, Material material) {
		super(name, material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(FLAT, Boolean.valueOf(false)));
		setSoundType(SoundType.CLOTH);
		setHardness(0.3F);
		setResistance(0.1F);
		setHarvestLevel("axe", 0);
	}  
	    
	    //reduce fall damage
	    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
	    {
	        entityIn.fall(fallDistance, 0.4F);
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
	
/*	  @Override
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return AWNING;
	    } */
	  

	  
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
	     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	     * blockstate.
	     */
	    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	    {
	        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	    }

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {FACING, FLAT});
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
	    
	    public IBlockState getStateFromMeta(int meta)
	    {
	        return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(FLAT, Boolean.valueOf((meta & 4) != 0));
	    }
	    
	    public int getMetaFromState(IBlockState state)
	    {
	        int i = 0;
	        i = i | getMetaForFacing((EnumFacing)state.getValue(FACING));

	        if (((Boolean)state.getValue(FLAT)).booleanValue())
	        {
	            i |= 4;
	        }

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
	    
	    public IBlockState withRotation(IBlockState state, Rotation rot)
	    {
	        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	    }
	    
	    @Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
		{
	    	  AxisAlignedBB axisalignedbb;
	    	if (((Boolean)state.getValue(FLAT)).booleanValue())
	        {
	    	
			switch(((EnumFacing)state.getValue(FACING)))
	        {
	            case SOUTH:
	            default:
	                return AWNING_FLAT;
	            case NORTH:
	                return AWNING_FLAT;
	            case EAST:
	                return AWNING_FLAT;
	            case WEST:
	                return AWNING_FLAT;
	        }
	        }
	        else
	        {
	            axisalignedbb = AWNING;
	        }

	        return axisalignedbb;
	    }
		
	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	    	  ItemStack itemstack = playerIn.getHeldItem(hand);

	    	    if (!itemstack.isEmpty() && (itemstack.getItem() == ModItems.COZY_HAMMER))
	         {
	            state = state.cycleProperty(FLAT);
	            worldIn.setBlockState(pos, state, 2);
	            worldIn.playSound((EntityPlayer)null, pos, SoundsHandler.HAMMER, SoundCategory.BLOCKS, 0.5F, 0.8F);
	            return true;
	         }
	         else
	         {
	         return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
			} }	    	
	
		public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
}

