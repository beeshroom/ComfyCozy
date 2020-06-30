package bee.beeshroom.ComfyCozy.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class white_mushroom_block extends BlockBase {
	
	public static final PropertyEnum<white_mushroom_block.EnumType> VARIANT = PropertyEnum.<white_mushroom_block.EnumType>create("variant", white_mushroom_block.EnumType.class);
   // private final Block smallBlock;
	
	public white_mushroom_block(String name, Material material) 
	{
		super(name, material);
		setSoundType(SoundType.STONE);
		setHardness(0.5F);
		setResistance(0.3F);
		setHarvestLevel("axe", 0);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, white_mushroom_block.EnumType.ALL_OUTSIDE));
	   //   this.smallBlock = smallBlockIn;
	}
	
	 @Override
	    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
	        return Item.getItemFromBlock(ModBlocks.WHITE_MUSHROOM);
	    }
	 
	/* @Override
	    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	    {
	        items.add(new ItemStack(this));
	    } */

	    @Override
	    protected ItemStack getSilkTouchDrop(IBlockState state)
	    {
	        return new ItemStack(Item.getItemFromBlock(this));
	    }
	   /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return Math.max(0, random.nextInt(10) - 7);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        switch ((white_mushroom_block.EnumType)state.getValue(VARIANT))
        {
            case ALL_STEM:
                return MapColor.CLOTH;
            case ALL_INSIDE:
                return MapColor.SAND;
            case STEM:
                return MapColor.SAND;
            default:
                return super.getMapColor(state, worldIn, pos);
        }
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, white_mushroom_block.EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((white_mushroom_block.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        switch (rot)
        {
            case CLOCKWISE_180:

                switch ((white_mushroom_block.EnumType)state.getValue(VARIANT))
                {
                    case STEM:
                        break;
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH_EAST);
                    case NORTH:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH_WEST);
                    case WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.EAST);
                    case EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.WEST);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH_EAST);
                    case SOUTH:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH_WEST);
                    default:
                        return state;
                }

            case COUNTERCLOCKWISE_90:

                switch ((white_mushroom_block.EnumType)state.getValue(VARIANT))
                {
                    case STEM:
                        break;
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH_WEST);
                    case NORTH:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.WEST);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH_WEST);
                    case WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH);
                    case EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH_EAST);
                    case SOUTH:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.EAST);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH_EAST);
                    default:
                        return state;
                }

            case CLOCKWISE_90:

                switch ((white_mushroom_block.EnumType)state.getValue(VARIANT))
                {
                    case STEM:
                        break;
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH_EAST);
                    case NORTH:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.EAST);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH_EAST);
                    case WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH);
                    case EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH_WEST);
                    case SOUTH:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.WEST);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH_WEST);
                    default:
                        return state;
                }

            default:
                return state;
        }
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @SuppressWarnings("incomplete-switch")
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        white_mushroom_block.EnumType white_mushroom_block$enumtype = (white_mushroom_block.EnumType)state.getValue(VARIANT);

        switch (mirrorIn)
        {
            case LEFT_RIGHT:

                switch (white_mushroom_block$enumtype)
                {
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH_WEST);
                    case NORTH:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH_EAST);
                    case WEST:
                    case EAST:
                    default:
                        return super.withMirror(state, mirrorIn);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH_WEST);
                    case SOUTH:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH_EAST);
                }

            case FRONT_BACK:

                switch (white_mushroom_block$enumtype)
                {
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH_EAST);
                    case NORTH:
                    case SOUTH:
                    default:
                        break;
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.NORTH_WEST);
                    case WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.EAST);
                    case EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.WEST);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH_EAST);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, white_mushroom_block.EnumType.SOUTH_WEST);
                }
        }

        return super.withMirror(state, mirrorIn);
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis)
    {
        IBlockState state = world.getBlockState(pos);
        for (IProperty prop : (java.util.Set<IProperty<?>>)state.getProperties().keySet())
        {
            if (prop.getName().equals("variant"))
            {
                world.setBlockState(pos, state.cycleProperty(prop));
                return true;
            }
        }
        return false;
    }

    public static enum EnumType implements IStringSerializable
    {
        NORTH_WEST(1, "north_west"),
        NORTH(2, "north"),
        NORTH_EAST(3, "north_east"),
        WEST(4, "west"),
        CENTER(5, "center"),
        EAST(6, "east"),
        SOUTH_WEST(7, "south_west"),
        SOUTH(8, "south"),
        SOUTH_EAST(9, "south_east"),
        STEM(10, "stem"),
        ALL_INSIDE(0, "all_inside"),
        ALL_OUTSIDE(14, "all_outside"),
        ALL_STEM(15, "all_stem");

        private static final white_mushroom_block.EnumType[] META_LOOKUP = new white_mushroom_block.EnumType[16];
        private final int meta;
        private final String name;

        private EnumType(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static white_mushroom_block.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            white_mushroom_block.EnumType white_mushroom_block$enumtype = META_LOOKUP[meta];
            return white_mushroom_block$enumtype == null ? META_LOOKUP[0] : white_mushroom_block$enumtype;
        }

        public String getName()
        {
            return this.name;
        }

        static
        {
            for (white_mushroom_block.EnumType white_mushroom_block$enumtype : values())
            {
                META_LOOKUP[white_mushroom_block$enumtype.getMetadata()] = white_mushroom_block$enumtype;
            }
        }
    }
}