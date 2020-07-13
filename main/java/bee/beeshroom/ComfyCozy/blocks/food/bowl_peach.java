package bee.beeshroom.ComfyCozy.blocks.food;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.blocks.BlockBase;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


//thank-you turtywurty for your custom block model tutorial on youtube and to Cake code

public class bowl_peach extends BlockBase 
{
	 //   protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[], new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    public static final PropertyInteger BITES = PropertyInteger.create("bites", 0, 1);
	public static final AxisAlignedBB BOWL = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.12D, 0.6875D);

	    public bowl_peach(String name, Material material) 
	    {
		super(name, material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BITES, Integer.valueOf(0)));
		setSoundType(SoundType.WOOD);
		setHardness(0.3F);
		setResistance(0.1F);
		setHarvestLevel("axe", 0);
	}  
	    
	    @Override
	    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	    {
	        super.addInformation(stack, worldIn, tooltip, flagIn);
	        tooltip.add("Creative only! For Survival players: Sneak while holding any type of Oatmeal to place it as a block");
	    }
	    
	    
	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	        if (!worldIn.isRemote)
	        {
	            return this.eatOatmeal(worldIn, pos, state, playerIn);
	        }
	        else
	        {
	            ItemStack itemstack = playerIn.getHeldItem(hand);
	            return this.eatOatmeal(worldIn, pos, state, playerIn) || itemstack.isEmpty();
	        }
	    }
	    
	    
	    
	    
	    // CHANGE GOOD STATES HERE
	    
	    
	    private boolean eatOatmeal(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	    {
	        if (!player.canEat(false))
	        {
	            return false;
	        }
	        else
	        {
	            //player.getFoodStats().addStats(6, .6F);
	            int i = ((Integer)state.getValue(BITES)).intValue();

	            if (i < 1)
	            {
	                worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, 0.8F, 1.0F);    
	            	player.getFoodStats().addStats(8, .65F);
	                worldIn.setBlockState(pos, state.withProperty(BITES, Integer.valueOf(1)), 3);
	            }
	            else
	            {
	            spawnAsEntity(worldIn, pos, new ItemStack(Items.BOWL, 1));
	            worldIn.setBlockToAir(pos);
	            }

	            return true;
	        }
	    }
	 
	  public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
	    {
	        if (!worldIn.isRemote)
	        {
	            player.addStat(StatList.getBlockStats(this));
	            spawnAsEntity(worldIn, pos, new ItemStack(Items.BOWL, 1));
	        }
	        else
	        {
	            super.harvestBlock(worldIn, player, pos, state, te, stack);
	        }
	    }
	
	  protected boolean canSilkHarvest()
	    {
	        return false;
	    }
	
	  public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	    {
	        return super.canPlaceBlockAt(worldIn, pos) ? this.canBlockStay(worldIn, pos) : false;
	    }
	  
	    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	    {
	        if (!this.canBlockStay(worldIn, pos))
	        {
	        	spawnAsEntity(worldIn, pos, new ItemStack(Items.BOWL, 1));
	        	worldIn.setBlockToAir(pos);
	        }
	    }

	    private boolean canBlockStay(World worldIn, BlockPos pos)
	    {
	        return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
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
		
	    public EnumPushReaction getMobilityFlag(IBlockState state)
	    {
	        return EnumPushReaction.DESTROY;
	    }			
	    
	    
	    public IBlockState getStateFromMeta(int meta)
	    {
	        return this.getDefaultState().withProperty(BITES, Integer.valueOf(meta));
	    }

	    /**
	     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	     */
	/*    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	    {
	        worldIn.setBlockState(pos, state.withProperty(FULL);

	    }*/
	    
	    /**
	     * Returns the quantity of items to drop on block destruction.
	     */
	    public int quantityDropped(Random random)
	    {
	        return 1;
	    }

	    /**
	     * Get the Item that this Block should drop when harvested.
	     */
	    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
	        return Items.BOWL;
	    }
	    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	    {
	        return new ItemStack(ModItems.PEACH_OATMEAL);
	    }
	    
	    
	    /**
	     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	     */
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }

	    public int getMetaFromState(IBlockState state)
	    {
	        return ((Integer)state.getValue(BITES)).intValue();
	    }

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {BITES});
	    }
	    
	    @Override
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return BOWL;
	    }
	    
	    @Override
	    public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return BOWL;
	    }
	    
	    
	/*    @SideOnly(Side.CLIENT)
	    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	    {
	        double d0 = (double)pos.getX() + 0.5D;
	        double d1 = (double)pos.getY() + 0.7D;
	        double d2 = (double)pos.getZ() + 0.5D;
	        double d3 = 0.22D;
	        double d4 = 0.27D;
	        {
	           // worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	        }
	    } */
	    
	    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return BlockFaceShape.UNDEFINED;
	    }
}

