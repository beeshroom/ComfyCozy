/*
package com.beeshroom.brickery.items.tools;

import com.beeshroom.brickery.Main;
import com.beeshroom.brickery.init.ModItems;
import com.beeshroom.brickery.util.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import java.util.List;

public class ToolSword extends ItemSword implements IHasModel {


    public ToolSword(String name, ToolMaterial material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(Main.CREATIVE_TAB);

        ModItems.ITEMS.add(this);

    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");

    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add("unimplemented");
    }


}
*/
