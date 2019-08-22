package bee.beeshroom.ComfyCozy.items;

import bee.beeshroom.ComfyCozy.Main;
import bee.beeshroom.ComfyCozy.init.ModItems;
import net.minecraft.item.Item;

public class ItemBase extends Item  {

	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.comfycozytab);
		
		ModItems.ITEMS.add(this);
		
	}
	
	/*@Override
	public void registerModels() 
	{
	Main.proxy.registerItemRenderer(this,0,"inventory");
		
	} */

}
