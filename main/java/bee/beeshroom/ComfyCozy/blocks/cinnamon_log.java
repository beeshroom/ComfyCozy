package bee.beeshroom.ComfyCozy.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.handlers.SoundsHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class cinnamon_log extends BlockBase
{
    public static final PropertyInteger REMAINS = PropertyInteger.create("remains", 0, 4);
    protected static final AxisAlignedBB REED_AABB = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D);

    public cinnamon_log(String name, Material material)
    {
    	super(name, material);
    	// super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(REMAINS, Integer.valueOf(0)));
        this.setTickRandomly(true);
        setHardness(0.7F);
		setResistance(0.3F);
		setHarvestLevel("axe", 0);
    }
    //rustic reference
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	ItemStack heldItem = playerIn.getHeldItem(hand);
		Item item = heldItem.getItem();
    ItemStack itemstack = playerIn.getHeldItem(hand);
     if (!itemstack.isEmpty() && (item.getToolClasses(heldItem).contains("axe")))
    // (!itemstack.isEmpty() && (itemstack.getItem() == Items.IRON_SWORD || itemstack.getItem() == Items.GOLDEN_SWORD || itemstack.getItem() == Items.WOODEN_SWORD || itemstack.getItem() == Items.DIAMOND_SWORD || itemstack.getItem() == Items.WOODEN_AXE || itemstack.getItem() == Items.STONE_AXE || itemstack.getItem() == Items.IRON_AXE || itemstack.getItem() == Items.DIAMOND_AXE))
     {
    	 ItemStack stack = heldItem.copy();
			stack.damageItem(1, playerIn);
			playerIn.setHeldItem(hand, stack);
    	  return this.strip(worldIn, pos, state, playerIn);
    }
     else {
     return false; 
    		 
     }
     }
    
    private boolean strip(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        {
            worldIn.playSound((EntityPlayer)null, pos, SoundsHandler.STRIP, SoundCategory.BLOCKS, 1.2F, 1.0F); 
            int i = ((Integer)state.getValue(REMAINS)).intValue();
          //  world.spawnParticle(EnumParticleTypes.BLOCK_DUST, (double)((float)pos.getX()), (double)pos.getY(), (double)((float)pos.getZ()), hitZ, hitZ, hitZ);
            
            if (i < 3)
            {
            	Random rand = new Random(); 
   	    	 
	          //  worldIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
            	//  this.dropBlockAsItem(worldIn, pos, state, 0);
            	 spawnAsEntity(worldIn, pos, new ItemStack(ModItems.CINNAMON_STICK));
            	  
            	worldIn.setBlockState(pos, state.withProperty(REMAINS, Integer.valueOf(i + 1))); // , 3);  
            }
            if (i == 3)
            {
            	 spawnAsEntity(worldIn, pos, new ItemStack(ModItems.CINNAMON_STICK));
                worldIn.setBlockToAir(pos);
            }
            if (i == 4)
            {
                worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 0.8F, 1.0F); 
            	Random rand = new Random(); 
   	    	 
	          //  worldIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
            	//  this.dropBlockAsItem(worldIn, pos, state, 0);
            	 spawnAsEntity(worldIn, pos, new ItemStack(ModBlocks.CINNAMON_SAPLING));
            	  
            	worldIn.setBlockState(pos, state.withProperty(REMAINS, Integer.valueOf(0)));
            	
                
            }

            return true;
        }
    }
    
    
    
    
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return REED_AABB;
    }

    
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return REED_AABB;
    }
    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(REMAINS, Integer.valueOf(meta));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
   
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(REMAINS)).intValue();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {REMAINS});
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     * 
     * @return an approximation of the form of the given face
     */
 
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
 
  /*  private void dropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        this.dropBlockAsItem(worldIn, pos, state, 0);
    }*/

    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
/*    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    } */

   /* @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
    	  int i = ((Integer)state.getValue(REMAINS)).intValue();
    	if (i == 0)
    	{
    		
        super.getDrops(drops, world, pos, state, fortune);
        {
        	 drops.add(new ItemStack(ModBlocks.CINNAMON_LOG));
        }}
    	else
    	{
    		super.getDrops(drops, world, pos, state, fortune);
            {
                drops.add(new ItemStack(ModBlocks.STRIPPED_CINNAMON));
            }
    	}
    } */
    
/*   public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
    	int i = ((Integer)state.getValue(REMAINS)).intValue();
    	if (i == 0)
    	{
        return new ItemStack(ModBlocks.CINNAMON_LOG); 
        }
    	else 
    	{
    		 return new ItemStack(ModBlocks.STRIPPED_CINNAMON); 
    		 }
    }*/
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	int i = ((Integer)state.getValue(REMAINS)).intValue();
    	if (i == 0)
    	{
    		return Item.getItemFromBlock(ModBlocks.CINNAMON_LOG);
    	}
    	if (i == 4)
    	{
    		return Item.getItemFromBlock(ModBlocks.CINNAMON_SAPLING);
    	}
    	else 
    	{
        return ModItems.CINNAMON_STICK;
    	}
    }
    
  /*  @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
    		super.getDrops(drops, world, pos, state, fortune);
            {
                drops.add(new ItemStack(ModItems.CINNAMON_STICK));
            }
    }*/
 /*   public int quantityDropped()
    {
        return 1;
    }*/

    protected boolean canSilkHarvest()
    {
        return false;
    }
	
 /*   public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
    	int i = ((Integer)state.getValue(REMAINS)).intValue();
    	if (i == 4)
    	{
    		return worldIn.setBlockState(pos, state.withProperty(REMAINS, Integer.valueOf(0)));
    	}
    	else 
    	{
        return false;
    	}
    }*/
    
    
    
}