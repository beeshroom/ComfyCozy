
package bee.beeshroom.ComfyCozy.items.food;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.util.math.BlockPos;


public class cinnamon  extends ItemFood
{

	public cinnamon(String name, int amount, float saturation, boolean isWolfFood) 
	{
		super(amount, saturation, isWolfFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.comfycozytab);
		setAlwaysEdible();
		
		ModItems.ITEMS.add(this);
	}
	
		@Override
		public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) 
		{
			
			entityLiving.addPotionEffect(new PotionEffect(MobEffects.POISON, 5, 1, false, false));
			entityLiving.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 25, 1, false, false));
			return super.onItemUseFinish(stack, worldIn, entityLiving);
		}
}