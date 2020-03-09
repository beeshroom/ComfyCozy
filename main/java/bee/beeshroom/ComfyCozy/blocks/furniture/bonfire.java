package bee.beeshroom.ComfyCozy.blocks.furniture;

import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.blocks.BlockBase;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockWall;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


//thank-you turtywurty for your custom block model tutorial on youtube and thank-you to end rod code vanilla

public class bonfire extends BlockBase
{
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
//this code is copied directly from another mod i made where this code worked fine.
 //   public static final AxisAlignedBB BUNTING_VERT = new AxisAlignedBB(0.9375D, 0.0D, 0.46875D, 0.625D, 0.0D, 1.0D);
  //  public static final AxisAlignedBB POLE = new AxisAlignedBB(0.46875D, 0.9375D, 0.0D, 0.54125D, 0.625D, 1.0D);
    public static final AxisAlignedBB BONFIRE = new AxisAlignedBB(0.03125D, 0.0D, 0.03125D, 0.96875D, 0.9375D, 0.96875D);
    public static final AxisAlignedBB BONFIRE_WE = new AxisAlignedBB(0.03125D, 0.0D, 0.03125D, 0.96875D, 0.9375D, 0.96875D);
    //public static final AxisAlignedBB BONFIRE = new AxisAlignedBB(0.0D, .01D, 0.0D, 1D, .5D, 1D);
	//public static final AxisAlignedBB BONFIRE_WE = new AxisAlignedBB(0.0D, .01D, 0.0D, 1D, .5D, 1D);
	
	    public bonfire(String name, Material material) {
		super(name, material);
		//this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setSoundType(SoundType.WOOD);
		setHardness(0.5F);
		setResistance(0.1F);
		setHarvestLevel("axe", 0);
		setLightLevel(1.0f);
		

	/*	setRegistryName(name);
		setUnlocalizedName(name);
		
		setCreativeTab(Main.comfycozytab);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));*/
	}
	    
	    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	    {
	        entityIn.attackEntityFrom(DamageSource.IN_FIRE, 1.0F);
	    }
	
	  @Override
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return BONFIRE;
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
	     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	     */
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }
	    
	  /*  public int quantityDropped(Random random)
	    {
	        return 2;
	    }*/

	/*    private void dropBlock(World worldIn, BlockPos pos, IBlockState state)
	    {
	        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	        this.dropBlockAsItem(worldIn, pos, state, 0);
	    } */
	    public int quantityDropped(Random random)
	    {
	        return 1 + random.nextInt(2);
	    }
	    
	    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
	    	 return Items.AIR;
	    }
	    
	    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	    {
	        return new ItemStack(Items.COAL, 2, 1);
	    }

	    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
	    {
	        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
	    }

	    @Override
	    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	    {
	        super.getDrops(drops, world, pos, state, fortune);
	        drops.add(new ItemStack(Items.COAL, 2, 1));
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
		                return BONFIRE_WE;
		            case NORTH:
		                return BONFIRE_WE;
		            case EAST:
		                return BONFIRE;
		            case WEST:
		                return BONFIRE;
		        }
			}
			
		    
	    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return BlockFaceShape.UNDEFINED;
	    }
	    
	    @SideOnly(Side.CLIENT)
	    @SuppressWarnings("incomplete-switch")
	    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	    {
	            double d4 = rand.nextDouble() * 0.6D - 0.3D;

	            if (rand.nextDouble() < 0.1D)
	            {
	                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 3.2F, 1.0F, false);
	                double d3 = (double)pos.getX() + rand.nextDouble() * 0.10000000149011612D;
                    double d8 = (double)pos.getY() + rand.nextDouble();
                    double d13 = (double)pos.getZ() + rand.nextDouble();
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d3, d8, d13, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d3, d8, d13, 0.0D, 0.0D, 0.0D);
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
	        return worldIn.getBlockState(pos).isTopSolid();
	    }
}

