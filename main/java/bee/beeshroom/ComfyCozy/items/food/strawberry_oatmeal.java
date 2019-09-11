package bee.beeshroom.ComfyCozy.items.food;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class strawberry_oatmeal  extends ItemFood
{
	public strawberry_oatmeal(String name, int amount, float saturation, boolean isWolfFood) 
	{
		super(amount, saturation, isWolfFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.comfycozytab);
		//setAlwaysEdible();
		 this.setMaxStackSize(1);
		
		ModItems.ITEMS.add(this);
	}

	 public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	    {
	        super.onItemUseFinish(stack, worldIn, entityLiving);
	        return new ItemStack(Items.BOWL);
	    }
	

}