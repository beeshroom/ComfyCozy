package bee.beeshroom.ComfyCozy.items;

import bee.beeshroom.ComfyCozy.Main;
import net.minecraft.item.ItemStack;

public class CraftTool extends ItemBase{

	public CraftTool(String name, int uses) {
		super(name);
		this.setCreativeTab(Main.comfycozytab);
		this.maxStackSize = 1;
		this.setMaxDamage(uses - 1);
	}
	@Override
	public boolean hasContainerItem(ItemStack stack){
		return stack.getItemDamage() < stack.getMaxDamage();
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack itemStack){
		ItemStack ret = new ItemStack(itemStack.getItem());
		ret.setItemDamage(itemStack.getItemDamage()+1);
        return ret;
    }

}