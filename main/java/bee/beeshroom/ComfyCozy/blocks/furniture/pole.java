package bee.beeshroom.ComfyCozy.blocks.furniture;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.blocks.BlockBase;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockFenceGate;
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
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


//thank-you turtywurty for your custom block model tutorial on youtube and thank-you to end rod code vanilla

public class pole extends BlockBase
{
//this code is copied directly from another mod i made where this code worked fine.
 //   public static final AxisAlignedBB BUNTING_VERT = new AxisAlignedBB(0.9375D, 0.0D, 0.46875D, 0.625D, 0.0D, 1.0D);
  //  public static final AxisAlignedBB POLE = new AxisAlignedBB(0.46875D, 0.9375D, 0.0D, 0.54125D, 0.625D, 1.0D);
	public static final AxisAlignedBB POLE = new AxisAlignedBB(0.4375D, 0D, 0.4375D, 0.5625D, 1D, 0.5625D);
	
	    public pole(String name, Material material) {
		super(name, material);
		//this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setSoundType(SoundType.CLOTH);
		setHardness(0.0F);
		setResistance(0.1F);
		setHarvestLevel("wood", 0);

	/*	setRegistryName(name);
		setUnlocalizedName(name);
		
		setCreativeTab(Main.comfycozytab);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));*/
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
	 /*   
	    public int getMetaFromState(IBlockState state)
	    {
	        return 0;
	    }
	    
	    
	    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing)
	    {
	        IBlockState iblockstate = worldIn.getBlockState(pos);
	        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos, facing);
	        Block block = iblockstate.getBlock();
	        boolean flag = blockfaceshape == BlockFaceShape.MIDDLE_POLE && (iblockstate.getMaterial() == this.blockMaterial || block instanceof bunting);
	        return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID || flag;
	    }

	    protected static boolean isExcepBlockForAttachWithPiston(Block p_194142_0_)
	    {
	        return Block.isExceptBlockForAttachWithPiston(p_194142_0_) || p_194142_0_ == Blocks.BARRIER || p_194142_0_ == Blocks.MELON_BLOCK || p_194142_0_ == Blocks.PUMPKIN || p_194142_0_ == Blocks.LIT_PUMPKIN;
	    }
	    
	    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	    {
	        return state.withProperty(NORTH, canPoleConnectTo(worldIn, pos, EnumFacing.NORTH))
	                    .withProperty(EAST,  canPoleConnectTo(worldIn, pos, EnumFacing.EAST))
	                    .withProperty(SOUTH, canPoleConnectTo(worldIn, pos, EnumFacing.SOUTH))
	                    .withProperty(WEST,  canPoleConnectTo(worldIn, pos, EnumFacing.WEST));
	    }
	
	    public IBlockState withRotation(IBlockState state, Rotation rot)
	    {
	        switch (rot)
	        {
	            case CLOCKWISE_180:
	                return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST)).withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST));
	            case COUNTERCLOCKWISE_90:
	                return state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH));
	            case CLOCKWISE_90:
	                return state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH)).withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH));
	            default:
	                return state;
	        }
	    }
	    
	    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	    {
	        switch (mirrorIn)
	        {
	            case LEFT_RIGHT:
	                return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
	            case FRONT_BACK:
	                return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
	            default:
	                return super.withMirror(state, mirrorIn);
	        }
	    }
	    

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH});
	    }

	    

	    @Override
	    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
	    {
	        return canConnectTo(world, pos.offset(facing), facing.getOpposite());
	    }

	    private boolean canPoleConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
	    {
	        BlockPos other = pos.offset(facing);
	        Block block = world.getBlockState(other).getBlock();
	        return block.canBeConnectedTo(world, other, facing.getOpposite()) || canConnectTo(world, other, facing.getOpposite());
	    }
	    */
	    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return BlockFaceShape.UNDEFINED;
	    }
}

