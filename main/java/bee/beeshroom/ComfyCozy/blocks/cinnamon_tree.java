/*package bee.beeshroom.ComfyCozy.blocks;

import java.util.Random;
import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class cinnamon_tree extends BlockBase implements IPlantable
{
	// public static final PropertyBool STRIPPED = PropertyBool.create("stripped");
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 12);
    protected static final AxisAlignedBB REED_AABB = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D);

    public cinnamon_tree(String name, Material material)
    {
    	super(name, material);
    	// super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(12)));
        this.setTickRandomly(true);
        setHardness(0.3F);
		setResistance(0.1F);
		setHarvestLevel("axe", 0);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return REED_AABB;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.CINNAMON_TREE || this.checkForDrop(worldIn, pos, state))
        {
            if (worldIn.isAirBlock(pos.up()))
            {
                int i;

                for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i)
                {
                    ;
                }

                if (i < 4)
                {
                    int j = ((Integer)state.getValue(AGE)).intValue();

                    if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true))
                    {
                    if (j == 12)
                    {
                        worldIn.setBlockState(pos.up(), this.getDefaultState());
                        
                        worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(0)), 4);
                        worldIn.setBlockState(pos, state.withProperty(AGE, 2));
                    }
                    else
                    {
                        worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(j + 1)), 4);
                    }
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                    }
                }
                 
                 /*
                if (i > 3 && i < 5)
                {
                	 int j = ((Integer)state.getValue(AGE)).intValue();

                     if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true))
                     {
                     if (j < 12) 
                     {
                        // worldIn.setBlockState(pos.up(), this.getDefaultState());
                         //worldIn.setBlockState(pos, state.withProperty(AGE, 13));
                       worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(13)));
                     }
                  else
                     {
                    	// worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(13)), 4);
                         worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(j + 1)), 4);
                  
                         
                     } 
                     net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                     }
                }*/
 /*           }
        }
    } 

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState state = worldIn.getBlockState(pos.down());
        Block block = state.getBlock();
        if (block.canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, this)) return true;

        if (block == this)
        {
        	  worldIn.setBlockState(pos, state.withProperty(AGE, 0));
            return true;
        }
        else if (block != Blocks.GRASS && block != Blocks.DIRT && block != Blocks.SAND)
        {
            return false;
        }
        else
        {
            BlockPos blockpos = pos.down();

            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                IBlockState iblockstate = worldIn.getBlockState(blockpos.offset(enumfacing));

                //i made this NOT blocks Barrier instead of == blocks Water. this seems fine? like i doubt anyone will ever notice.
                //this way you can plant this on any grass, not just grass next to water
                
                if (iblockstate.getBlock() != Blocks.BARRIER)
                {
                    return true;
                }
            }

            return false;
        }
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.checkForDrop(worldIn, pos, state);
    }

    protected final boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (this.canBlockStay(worldIn, pos))
        {
            return true;
        }
        else
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
            return false;
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos)
    {
        return this.canPlaceBlockAt(worldIn, pos);
    }

    
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return REED_AABB;
    }

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
    
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModBlocks.CINNAMON_TREE);
    }

    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

   
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return net.minecraftforge.common.EnumPlantType.Plains;
    }
    
    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos)
    {
        return this.getDefaultState();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }


 
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
 
    
 /*   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);
       // if (!((Boolean)state.getValue(STRIPPED)).booleanValue());
        
        if (!itemstack.isEmpty() && (itemstack.getItem() == Items.SHEARS || itemstack.getItem() == Items.WOODEN_AXE || itemstack.getItem() == Items.STONE_AXE || itemstack.getItem() == Items.IRON_AXE || itemstack.getItem() == Items.DIAMOND_AXE))
        	
    {
        	
        	worldIn.setBlockState(pos, state.withProperty(AGE, 2));
        	// worldIn.setBlockState(pos, state.withProperty(STRIPPED, Boolean.valueOf(true)), 2);
        	// worldIn.setBlockState(pos, ModBlocks.STRIPPED_CINNAMON.getDefaultState());
        	// itemstack.damageItem(1, playerIn);
        	 
        	if (!worldIn.isRemote) {
        		  worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_WOOD_HIT, SoundCategory.BLOCKS, 0.8F, 1.0F);   
        	}
            return true;
        } 
        else 
        {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
        
    } */

    
//}