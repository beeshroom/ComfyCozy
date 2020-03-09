
package bee.beeshroom.ComfyCozy.items.armor;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

//thankyou Harry's Tech Reviews Tutorial on Custom Armor Models on Youtube

public class ArmorBase extends ItemArmor
{

	public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(Main.comfycozytab);
			
			ModItems.ITEMS.add(this);
	}

}
