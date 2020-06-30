package bee.beeshroom.ComfyCozy.blocks.furniture;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class cushion extends BlockColored 
{
	protected static final AxisAlignedBB CUSHION = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.375D, 0.9375D);
	
	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);

	    public cushion(String name, Material materialIn)
	    {
	        super(materialIn);
	        this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
	        setSoundType(SoundType.CLOTH);
			setHardness(0.3F);
			setResistance(0.3F);
			//setHarvestLevel("shears", 0);
			
			setRegistryName(name);
			setUnlocalizedName(name);
			
			setCreativeTab(Main.comfycozytab);
			ModBlocks.BLOCKS.add(this);
			ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	    }

	    /**
	     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
	     * returns the metadata of the dropped item based on the old metadata of the block.
	     */
	    public int damageDropped(IBlockState state)
	    {
	        return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
	    }

	   
	/*    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	    {
	        for (EnumDyeColor enumdyecolor : EnumDyeColor.values())
	        {
	            items.add(new ItemStack(this, 1, enumdyecolor.getMetadata()));
	        }
	    } */

	    /**
	     * Convert the given metadata into a BlockState for this Block
	     */
	    public IBlockState getStateFromMeta(int meta)
	    {
	        return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	    }

	    /**
	     * Convert the BlockState into the correct metadata value
	     */
	    public int getMetaFromState(IBlockState state)
	    {
	        return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
	    }

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {COLOR});
	    }
	    
	    
	    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	        return CUSHION;
	    }

	    public boolean isOpaqueCube(IBlockState state)
	    {
	        return false;
	    }

	    public boolean isFullCube(IBlockState state)
	    {
	        return false;
	    }

	    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	    {
	        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
	    }

	    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	    {
	        this.checkForDrop(worldIn, pos, state);
	    }

	    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
	    {
	        if (!this.canBlockStay(worldIn, pos))
	        {
	            this.dropBlockAsItem(worldIn, pos, state, 0);
	            worldIn.setBlockToAir(pos);
	            return false;
	        }
	        else
	        {
	            return true;
	        }
	    }

	    private boolean canBlockStay(World worldIn, BlockPos pos)
	    {
	        return !worldIn.isAirBlock(pos.down());
	    }

	    @SideOnly(Side.CLIENT)
	    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	    {
	        if (side == EnumFacing.UP)
	        {
	            return true;
	        }
	        else
	        {
	            return blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? true : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	        }
	    }

	

	    /**
	     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	     */
	    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	    {
	        for (int i = 0; i < 16; ++i)
	        {
	            items.add(new ItemStack(this, 1, i));
	        }
	    }
	    
	    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	    }
	}
