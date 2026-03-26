package bee.beeshroom.ComfyCozy.tabs;

import bee.beeshroom.ComfyCozy.init.ModBlocks;
import bee.beeshroom.ComfyCozy.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ComfyCozyTab extends CreativeTabs
{
	public ComfyCozyTab(String label) {super("comfycozy");}
	
	@Override
	@SideOnly(Side.CLIENT)
    public ItemStack createIcon()
	{
		return new ItemStack(ModBlocks.BUNTING);
	}
}