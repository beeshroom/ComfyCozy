package com.beeshroom.brickery.blocks;

import java.util.Random;
import java.util.logging.Logger;

import com.beeshroom.brickery.Main;
import com.beeshroom.brickery.init.ModBlocks;
import com.beeshroom.brickery.init.ModItems;
import com.beeshroom.brickery.tileentity.TileEntityBrickFurnace;
import com.beeshroom.brickery.util.IHasModel;
import com.beeshroom.brickery.util.Reference;
import com.beeshroom.brickery.util.handlers.ConfigHandler;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBrickFurnace extends BlockContainer implements IHasModel
{

		public static final PropertyDirection FACING = BlockHorizontal.FACING;
		private final boolean isBurning;
		private static boolean keepInventory;
		
		public BlockBrickFurnace(String name, boolean isBurning, boolean state)
		{
			super(Material.ROCK);
			this.setHardness(3.5F);
			this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
			this.isBurning = isBurning;
			
			if (!isBurning) setCreativeTab(Main.CREATIVE_TAB);
			
			float light = isBurning ? 0.75F : 0;
			setLightLevel(light);
			
			setTranslationKey(name);
			setRegistryName(name);
			
			if (ConfigHandler.BRICK_FURNACE) {
				ModBlocks.BLOCKS.add(this);
				ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
			}
		}
		
		@Override
	    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
	        return Item.getItemFromBlock(ModBlocks.BRICK_FURNACE);
	    }
		
		@Override
	    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	    {
	        return new ItemStack(ModBlocks.BRICK_FURNACE);
	    }
	    /**
	     * Called after the block is set in the Chunk data, but before the Tile Entity is set
	     */
	    @Override
	    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	    {
	        this.setDefaultFacing(worldIn, pos, state);
	        int i = pos.getX();
			int j = pos.getY();
			int k = pos.getZ();
			worldIn.scheduleUpdate(new BlockPos(i, j, k), this, this.tickRate(worldIn));
	    }

	    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
	    {
	        if (!worldIn.isRemote)
	        {
	            IBlockState iblockstate = worldIn.getBlockState(pos.north());
	            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
	            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
	            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
	            EnumFacing enumfacing = state.getValue(FACING);

	            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
	            {
	                enumfacing = EnumFacing.SOUTH;
	            }
	            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
	            {
	                enumfacing = EnumFacing.NORTH;
	            }
	            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
	            {
	                enumfacing = EnumFacing.EAST;
	            }
	            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
	            {
	                enumfacing = EnumFacing.WEST;
	            }

	            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
	        }
	    }

	    @SideOnly(Side.CLIENT)
	    @SuppressWarnings("incomplete-switch")
	    @Override
	    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	    {
	        if (this.isBurning)
	        {
	            EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
	            double d0 = (double)pos.getX() + 0.5D;
	            double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
	            double d2 = (double)pos.getZ() + 0.5D;
	            //double d3 = 0.52D;
	            double d4 = rand.nextDouble() * 0.6D - 0.3D;

	            if (rand.nextDouble() < 0.1D)
	            {
	                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
	            }

	            switch (enumfacing)
	            {
	                case WEST:
	                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
	                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
	                    break;
	                case EAST:
	                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
	                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
	                    break;
	                case NORTH:
	                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
	                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
	                    break;
	                case SOUTH:
	                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
	                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
	            }
	        }
	    }
	    
	    @Override
	    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
	    	double i = pos.getX(); 
	    	double j = pos.getY(); 
	    	double k = pos.getZ();

	    	world.scheduleUpdate(new BlockPos(i, j, k), this, this.tickRate(world));
	    }
	    
	    /**
	     * Called when the block is right clicked by a player.
	     */
	    @Override
	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	        if (worldIn.isRemote)
	        {
	            return true;
	        }
	        else
	        {
	            TileEntity tileentity = worldIn.getTileEntity(pos);

	            if (tileentity instanceof TileEntityBrickFurnace)
	            {
	                playerIn.displayGUIChest((TileEntityBrickFurnace)tileentity);
	                playerIn.addStat(StatList.FURNACE_INTERACTION);
	            }

	            return true;
	        }
	    }

	    public static void setState(boolean active, World worldIn, BlockPos pos)
	    {
	        IBlockState iblockstate = worldIn.getBlockState(pos);
	        TileEntity tileentity = worldIn.getTileEntity(pos);
	        keepInventory = true;

	        if (active)
	        {
	            worldIn.setBlockState(pos, ModBlocks.BRICK_FURNACE_LIT.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
	            worldIn.setBlockState(pos, ModBlocks.BRICK_FURNACE_LIT.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
	        }
	        else
	        {
	            worldIn.setBlockState(pos, ModBlocks.BRICK_FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
	            worldIn.setBlockState(pos, ModBlocks.BRICK_FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
	        }

	        keepInventory = false;

	        if (tileentity != null)
	        {
	            tileentity.validate();
	            worldIn.setTileEntity(pos, tileentity);
	        }
	    }

	    /**
	     * Returns a new instance of a block's tile entity class. Called on placing the block.
	     */
	    @Override
	    public TileEntity createNewTileEntity(World worldIn, int meta)
	    {
	        return new TileEntityBrickFurnace();
	    }
	    
	    @Override
	    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	    }

	    /**
	     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	     */
	    @Override
	    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	    {
	        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

	        if (stack.hasDisplayName())
	        {
	            TileEntity tileentity = worldIn.getTileEntity(pos);

	            if (tileentity instanceof TileEntityBrickFurnace)
	            {
	                ((TileEntityBrickFurnace)tileentity).setCustomInventoryName(stack.getDisplayName());
	            }
	        }
	    }

	    /**
	     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
	     */
	    @Override
	    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	    {
	        if (!keepInventory)
	        {
	            TileEntity tileentity = worldIn.getTileEntity(pos);

	            if (tileentity instanceof TileEntityBrickFurnace)
	            {
	                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityBrickFurnace)tileentity);
	                worldIn.updateComparatorOutputLevel(pos, this);
	            }
	        }

	        super.breakBlock(worldIn, pos, state);
	    }

	    /**
	     * @deprecated call via {@link IBlockState#hasComparatorInputOverride()} whenever possible. Implementing/overriding
	     * is fine.
	     */
	    @Override
	    public boolean hasComparatorInputOverride(IBlockState state)
	    {
	        return true;
	    }

	    @Override
	    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
	    {
	        return Container.calcRedstone(worldIn.getTileEntity(pos));
	    }

	    @Override
	    public EnumBlockRenderType getRenderType(IBlockState state)
	    {
	        return EnumBlockRenderType.MODEL;
	    }

	    /**
	     * Convert the given metadata into a BlockState for this Block
	     */
	    @Override
	    public IBlockState getStateFromMeta(int meta)
	    {
	        EnumFacing enumfacing = EnumFacing.byIndex(meta);

	        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
	        {
	            enumfacing = EnumFacing.NORTH;
	        }

	        return this.getDefaultState().withProperty(FACING, enumfacing);
	    }

	    /**
	     * Convert the BlockState into the correct metadata value
	     */
	    @Override
	    public int getMetaFromState(IBlockState state)
	    {
	        return ((EnumFacing)state.getValue(FACING)).getIndex();
	    }

	    /**
	     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	     * blockstate.
	     * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever possible. Implementing/overriding is
	     * fine.
	     */
	    @Override
	    public IBlockState withRotation(IBlockState state, Rotation rot)
	    {
	        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	    }

	    /**
	     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	     * blockstate.
	     * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever possible. Implementing/overriding is fine.
	     */
	    @Override
	    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	    {
	        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	    }

	    @Override
	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {FACING});
	    }
	    
	    
	    @Override
		public int tickRate(World world) {
			return 1;
		}

	    @Override
		public void registerModels() {
			Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		}
	}