/*package bee.beeshroom.ComfyCozy.blocks;

import java.util.Random;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class peach_door extends BlockDoor 
    {
    	protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
        protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
        protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
        
        public peach_door(String name, Material materialIn)
        {
            super(materialIn);
            this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, Boolean.valueOf(false)).withProperty(HINGE, BlockDoor.EnumHingePosition.LEFT).withProperty(POWERED, Boolean.valueOf(false)).withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER));
        
            setRegistryName(name);
    		setUnlocalizedName(name);
    		
    		setCreativeTab(Main.comfycozytab);
    		ModBlocks.BLOCKS.add(this);
    		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        }
        
        @Override
        public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
        {
        	state = state.getActualState(source, pos);
        	EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        	boolean flag = !((Boolean)state.getValue(OPEN)).booleanValue();
        	boolean flag1 = state.getValue(HINGE) == BlockDoor.EnumHingePosition.RIGHT;
        	switch(enumfacing)
        	{
        	case EAST:
        	default:
        		return flag ? EAST_AABB : (flag1 ? NORTH_AABB : SOUTH_AABB);
        	case SOUTH:
        		return flag ? SOUTH_AABB : (flag1 ? EAST_AABB : WEST_AABB);
        	case WEST:
                return flag ? WEST_AABB : (flag1 ? SOUTH_AABB : NORTH_AABB);
            case NORTH:
                return flag ? NORTH_AABB : (flag1 ? WEST_AABB : EAST_AABB);
        	}
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
        public Item getItemDropped(IBlockState state, Random rand, int fortune) 
        {
        	return Item.getItemFromBlock(this);
        }
        
        @Override
        public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) 
        {
        	return new ItemStack(Item.getItemFromBlock(this));
        }
    } */