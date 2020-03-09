/* package bee.beeshroom.ComfyCozy.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//credit to Jabelar

public class cinnamon_leaves extends BlockBase //implements IShearable
{
    public cinnamon_leaves(String name, Material material)
    {
    	super(name, material);
    	//super(Material.WOOD);
        setHardness(0.6F);
		setResistance(0.3F);
		setHarvestLevel("axe", 0);
		
		//setRegistryName(name);
		//setUnlocalizedName(name);
		
		//setCreativeTab(Main.comfycozytab);
		//ModBlocks.BLOCKS.add(this);
		//ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
    
   /* public cinnamon_leaves()
    {
       // Main.proxy.setGraphicsLevel(this, true);
        setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    }*/

 

/*

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
    	return false;
    }
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return true;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
 
    public int quantityDropped()
    {
        return 1;
    }

    protected boolean canSilkHarvest()
    {
        return false;
    }
    

        /* (non-Javadoc)
         * @see net.minecraft.block.BlockLeaves#dropApple(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState, int)
         */
     /*   @Override
        protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
        {
            if (worldIn.rand.nextInt(chance) == 0)
            {
                spawnAsEntity(worldIn, pos, new ItemStack(Items.APPLE));
            }
        }*/
        
        
   /*     @Override
        public Item getItemDropped(IBlockState state, Random rand, int fortune)
        {
            return Item.getItemFromBlock(ModBlocks.CINNAMON_SAPLING);  
        }
        */

   
    /*    @Override
        public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
        {
            items.add(new ItemStack(this));
        }
        
        @Override
        protected ItemStack getSilkTouchDrop(IBlockState state)
        {
            return new ItemStack(Item.getItemFromBlock(this));
        }*/

        /**
         * Convert the given metadata into a BlockState for this Block.
         *
         * @param meta the meta
         * @return the state from meta
         */
  /*      @Override
        public IBlockState getStateFromMeta(int meta)
        {
            return getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
        }*/

        /**
         * Convert the BlockState into the correct metadata value.
         *
         * @param state the state
         * @return the meta from state
         */
    /*    @Override
        public int getMetaFromState(IBlockState state)
        {
            int i = 0;

            if (!state.getValue(DECAYABLE).booleanValue())
            {
                i |= 4;
            }

            if (state.getValue(CHECK_DECAY).booleanValue())
            {
                i |= 8;
            }

            return i;
        }*/

        /* (non-Javadoc)
         * @see net.minecraft.block.Block#createBlockState()
         */
        /*       @Override
      protected BlockStateContainer createBlockState()
        {
            return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
        }*/

        /**
         * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
         * returns the metadata of the dropped item based on the old metadata of the block.
         *
         * @param state the state
         * @return the int
         */
 /*       @Override
        public int damageDropped(IBlockState state)
        {
            return 0;
        }*/

        /**
         * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
         * Block.removedByPlayer
         *
         * @param worldIn the world in
         * @param player the player
         * @param pos the pos
         * @param state the state
         * @param te the te
         * @param stack the stack
         */
      
  /*      @Override
        public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
        {
            if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
            {
                player.addStat(StatList.getBlockStats(this));
            }
            else
            {
                super.harvestBlock(worldIn, player, pos, state, te, stack);
            }
        }
		
		public void onBlockClicked(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
			 super.onBlockClicked(worldIn, pos, playerIn);
	        ItemStack itemstack = playerIn.getHeldItem(hand);
	        ItemStack heldItem = playerIn.getHeldItem(hand);
	        ItemStack offStack = playerIn.getHeldItemOffhand();
			Item item = heldItem.getItem();
	        if (!itemstack.isEmpty() && (itemstack.getItem() == Items.SHEARS) || (item.getToolClasses(heldItem).contains("axe")) ) 
	        { 
	            worldIn.setBlockState(pos, ModBlocks.CINNAMON_LOG.getDefaultState().withProperty(cinnamon_log.REMAINS, Integer.valueOf(0)));
	            spawnAsEntity(worldIn, pos, new ItemStack(Blocks.LEAVES));
	            
	            if (itemstack.getItem() == Items.SHEARS)
	            {
	                itemstack.damageItem(1, playerIn);
	            }
	    }
	        if (itemstack.isEmpty() && offStack.isEmpty())
	        {
	        	worldIn.setBlockState(pos, ModBlocks.CINNAMON_LOG.getDefaultState().withProperty(cinnamon_log.REMAINS, Integer.valueOf(0)));
	        }
	        else {
	        	return; }
	    }

}*/
