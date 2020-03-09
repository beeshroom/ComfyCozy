package bee.beeshroom.ComfyCozy.tabs;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ComfyCozyTab extends CreativeTabs
{
	public ComfyCozyTab(String label) {super("comfycozy");}
	public ItemStack getTabIconItem() { return new ItemStack(ModBlocks.FISHJAR_SALMON);}

	}

	
