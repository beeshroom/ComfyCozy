package bee.beeshroom.ComfyCozy.blocks.crops;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class strawberry_plant extends BlockCrops 
{
	
		private static final AxisAlignedBB[] strawberry_plant = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D)};


				public strawberry_plant(String name)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			
			ModBlocks.BLOCKS.add(this);
		}
		
		
		@Override
		public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
		{
			if(!worldIn.isRemote)
			{
				if(this.isMaxAge(state))
				{
					worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.STRAWBERRY_SEEDS, 2)));
					worldIn.setBlockState(pos, ModBlocks.STRAWBERRY_PLANT.getDefaultState());
					return true;
				}
			}
			return false;
		}
		
		@Override
		protected Item getSeed() 
		{
			return ModItems.STRAWBERRY_SEEDS;
		}
		
		@Override
		protected Item getCrop() 
		{
			return ModItems.STRAWBERRY;
		}
		
		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
		{
			return strawberry_plant[((Integer)state.getValue(this.getAgeProperty())).intValue()];
		}
		
		
		  protected PropertyInteger getAgeProperty()
		    {
		        return AGE;
		    }

		    public int getMaxAge()
		    {
		        return 3;
		    }
		
		
	}