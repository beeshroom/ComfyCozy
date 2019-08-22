package bee.beeshroom.ComfyCozy.items;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class strawberry_seeds extends Item implements net.minecraftforge.common.IPlantable
{
	
	 private final Block strawberry_plant;
	    /** BlockID of the block the seeds can be planted on. */
	    private final Block soilBlockID;
	
	public strawberry_seeds(String name, Block crops, Block soil) 
	{
		this.strawberry_plant = crops;
        this.soilBlockID = soil;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.comfycozytab);
		
		
		ModItems.ITEMS.add(this);
	}
	
	
    /**
     * Called when a Block is right-clicked with this Item
     */
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos.up(), this.strawberry_plant.getDefaultState());

            if (player instanceof EntityPlayerMP)
            {
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos.up(), itemstack);
            }

            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) 
	{
		return EnumPlantType.Crop;
	}
	
	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) 
	{
		return ModBlocks.STRAWBERRY_PLANT.getDefaultState();
	}

} 
