package bee.beeshroom.ComfyCozy.blocks.furniture;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.blocks.BlockBase;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockPane;
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
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
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

//thank-you turtywurty for your custom block model tutorial on youtube and thank-you

public class daruma extends BlockBase
{
	public static final PropertyBool EYE = PropertyBool.create("eye");
	 public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
   // public static final AxisAlignedBB STATUE_WE = new AxisAlignedBB(0.1875D, 0D, 0.1875D, 0.8125D, 0.7125D, 0.8125D);
    public static final AxisAlignedBB STATUE = new AxisAlignedBB(0.1875D, 0D, 0.1875D, 0.8125D, 0.7025D, 0.8125D);

    public daruma(String name, Material material) 
	    {
		super(name, material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(EYE, Boolean.valueOf(false)));
		setSoundType(SoundType.STONE);
		setHardness(0.8F);
		setResistance(0.6F);
		setHarvestLevel("pickaxe", 0);
	//	setRegistryName(name);
	//	setUnlocalizedName(name);
		
	//	setCreativeTab(Main.comfycozytab);
	//	ModBlocks.BLOCKS.add(this);
	//	ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}  
  
	  @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return STATUE;
    } 

	  @Override
	    public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return STATUE;
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
        return new BlockStateContainer(this, new IProperty[] {FACING, EYE});
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
        return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(EYE, Boolean.valueOf((meta & 4) != 0));
    }
    
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | getMetaForFacing((EnumFacing)state.getValue(FACING));

        if (((Boolean)state.getValue(EYE)).booleanValue())
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
    
   /* @Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{
    	  AxisAlignedBB axisalignedbb;
    	if (((Boolean)state.getValue(EYE)).booleanValue())
        {
    	
		switch(((EnumFacing)state.getValue(FACING)))
        {
            case SOUTH:
            default:
                return STATUE;
            case NORTH:
                return STATUE;
            case EAST:
                return STATUE;
            case WEST:
                return STATUE;
        }
        }
        else
        {
            axisalignedbb = STATUE;
        }

        return axisalignedbb;
    } */
	
 /*   public boolean onBlockActivated(EntityPlayer player, World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
                state = state.cycleProperty(EYE);
                worldIn.setBlockState(pos, state, 2);
                worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.3f);
                worldIn.notifyNeighborsOfStateChange(pos, this, false);
                return true;
    }	*/    	

	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
{
    return BlockFaceShape.UNDEFINED;
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
	        return worldIn.getBlockState(pos).isTopSolid() || worldIn.getBlockState(pos).getBlock() instanceof BlockFence || worldIn.getBlockState(pos).getBlock() instanceof BlockPane;
	    }
	    
	    
	    
	    
	    //redstone nonsense
	    
	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	        if (worldIn.isRemote)
	        {
	            return true;
	        }
	        else
	        {
	        	 state = state.cycleProperty(EYE);
	                worldIn.setBlockState(pos, state, 2);
	            float f = ((Boolean)state.getValue(EYE)).booleanValue() ? 0.4F : 0.1F;
	          //  worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, f);
	            worldIn.playSound((EntityPlayer)null, pos, SoundsHandler.BONK, SoundCategory.BLOCKS, 0.3F, f);  
	            worldIn.notifyNeighborsOfStateChange(pos, this, false);
	            EnumFacing enumfacing = ((EnumFacing)state.getValue(FACING));
	            worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing.getOpposite()), this, false);
	            return true;
	        }
	    }

	    /**
	     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
	     */
	    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	    {
	        if (((Boolean)state.getValue(EYE)).booleanValue())
	        {
	            worldIn.notifyNeighborsOfStateChange(pos, this, false);
	            EnumFacing enumfacing = ((EnumFacing)state.getValue(FACING));
	            worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing.getOpposite()), this, false);
	        }

	        super.breakBlock(worldIn, pos, state);
	    }

	    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	    {
	        return ((Boolean)blockState.getValue(EYE)).booleanValue() ? 15 : 0;
	    }

	    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	    {
	        if (!((Boolean)blockState.getValue(EYE)).booleanValue())
	        {
	            return 0;
	        }
	        else
	        {
	            return ((EnumFacing)blockState.getValue(FACING)) == side ? 15 : 0;
	        }
	    }

	    /**
	     * Can this block provide power. Only wire currently seems to have this change based on its state.
	     */
	    public boolean canProvidePower(IBlockState state)
	    {
	        return true;
	    }

}

