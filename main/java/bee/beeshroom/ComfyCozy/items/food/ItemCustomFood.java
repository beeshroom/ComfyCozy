package bee.beeshroom.ComfyCozy.items.food;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ItemCustomFood extends ItemFood
{
	public ItemCustomFood(String name, int amount, boolean iswolffood)
	{
		super(amount, iswolffood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.comfycozytab);
		
		ModItems.ITEMS.add(this);
		
	}
	/*@Override 
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	} */
}
