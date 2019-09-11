package bee.beeshroom.ComfyCozy.blocks.furniture;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//thank-you turtywurty for your custom block model tutorial on youtube and thank-you to end rod code from vanilla

public class bunting extends BlockDirectional
{

	//public static final PropertyInteger TIE = PropertyInteger.create("tie", 0, 1);
//this code is copied directly from another mod i made where this code worked fine.
 //   public static final AxisAlignedBB BUNTING_VERT = new AxisAlignedBB(0.9375D, 0.0D, 0.46875D, 0.625D, 0.0D, 1.0D);
    public static final AxisAlignedBB BUNTING_WE = new AxisAlignedBB(0.0D, 0.9375D, 0.46875D, 1.0D, 0.625D, 0.54125D);
    public static final AxisAlignedBB BUNTING = new AxisAlignedBB(0.46875D, 0.9375D, 0.0D, 0.54125D, 0.625D, 1.0D);

    
	    public bunting(String name, Material material) 
	    {
		super(material);
		 this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
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
	        IBlockState iblockstate = worldIn.getBlockState(pos.offset(facing.getOpposite()));

	     /*   if (iblockstate.getBlock() == Blocks.END_ROD)
	        {
	            EnumFacing enumfacing = (EnumFacing)iblockstate.getValue(FACING);

	            if (enumfacing == facing)
	            {
	                return this.getDefaultState().withProperty(FACING, facing.getOpposite());
	            }
	        }*/

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
	        return new BlockStateContainer(this, new IProperty[] {FACING});
	    }
	    
	    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	        switch (((EnumFacing)state.getValue(FACING)).getAxis())
	        {
	            case X:
	            default:
	                return BUNTING_WE;
	            case Z:
	                return BUNTING;
	          case Y:
	                return BUNTING;
	        }
	    } 
	    
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
	
}

