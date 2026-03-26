package bee.beeshroom.ComfyCozy.items.armor;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModItems;
import bee.beeshroom.ComfyCozy.util.handlers.ConfigHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//thankyou Harry's Tech Reviews Tutorial on Custom Armor Models on Youtube


public class mushroom_beret extends ItemArmor 
{
	public mushroom_beret(String name, CreativeTabs tab, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn)
	{
		super(materialIn, 1, equipmentSlotIn);
		setTranslationKey(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(1);
		
		if (ConfigHandler.MUSHROOM_BERET)
			ModItems.ITEMS.add(this);
	}
	
	//@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(this, 0);
	} 

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) 
	{
		if(itemStack != ItemStack.EMPTY)
		{
			if(itemStack.getItem() instanceof ItemArmor)
			{
				ModelMushroomBeret model = new ModelMushroomBeret();
				
				model.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				
				//model.isChild = _default.isChild;
				model.isRiding = _default.isRiding;
				model.isSneak = _default.isSneak;
				
	//	removed these on 6.24.2020 bc im assuming helmets dont care about right and left arm poses....
				//	model.rightArmPose = _default.rightArmPose;
			//	model.leftArmPose = _default.leftArmPose;
				
				return model;
			}
		}
		
		return null;
	}
	
}