package bee.beeshroom.ComfyCozy.blocks.furniture;

import java.util.Random;

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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//thank-you turtywurty for your custom block model tutorial on youtube and thank-you to end rod code from vanilla

public class glowstone_star extends BlockDirectional
{

	  //strawbcake {new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.5D, 0.8125D),
    //public static final AxisAlignedBB STAR = new AxisAlignedBB(0.03125D, 0.0D, 0.03125D, 0.96875D, 0.9375D, 0.96875D);
		   public static final AxisAlignedBB STAR = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.9375D, 0.8125D);
    
	    public glowstone_star(String name, Material material) 
	    {
		super(material);
		 this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setSoundType(SoundType.GLASS);
		setHardness(0.3F);
		setResistance(0.3F); 
		setLightLevel(0.9F);
		//setHarvestLevel("shears", 0);
		setRegistryName(name);
		setUnlocalizedName(name);
		
		setCreativeTab(Main.comfycozytab);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}  
	    
	    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
	        return Items.GLOWSTONE_DUST;
	    }

	    public int quantityDropped(Random random)
	    {
	        return 2;
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
	    
	    @Override
				public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
				{
					switch(((EnumFacing)state.getValue(FACING)))
			        {
			            case SOUTH:
			            default:
			                return STAR;
			            case NORTH:
			                return STAR;
			            case EAST:
			                return STAR;
			            case WEST:
			                return STAR;
			            case UP:
			                return STAR;
			            case DOWN:
			                return STAR;
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
	    
	/*    @SideOnly(Side.CLIENT)
	    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	    {
	        EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
	        double d0 = (double)pos.getX() + 0.5D;
	        double d1 = (double)pos.getY() + 0.7D;
	        double d2 = (double)pos.getZ() + 0.5D;
	        double d3 = 0.22D;
	        double d4 = 0.27D;

	        if (enumfacing.getAxis().isHorizontal())
	        {
	            EnumFacing enumfacing1 = enumfacing.getOpposite();
	            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.27D * (double)enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * (double)enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
	            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.27D * (double)enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * (double)enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
	        }
	        else
	        {
	            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	        }
	    }
	*/
}

