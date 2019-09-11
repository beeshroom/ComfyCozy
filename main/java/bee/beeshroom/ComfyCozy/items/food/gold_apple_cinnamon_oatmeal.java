package bee.beeshroom.ComfyCozy.items.food;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class gold_apple_cinnamon_oatmeal  extends ItemFood
{
	public gold_apple_cinnamon_oatmeal(String name, int amount, float saturation, boolean isWolfFood) 
	{
		super(amount, saturation, isWolfFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.comfycozytab);
		setAlwaysEdible();
		   
	        this.setMaxStackSize(1);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) 
	{
		entityLiving.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 120, 1, false, false));
		entityLiving.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5, 2, false, false));
		super.onItemUseFinish(stack, worldIn, entityLiving);
		return new ItemStack(Items.BOWL);
	}
}