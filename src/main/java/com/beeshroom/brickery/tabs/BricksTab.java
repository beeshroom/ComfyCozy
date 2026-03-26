package com.beeshroom.brickery.tabs;

import com.beeshroom.brickery.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BricksTab extends CreativeTabs {
    public BricksTab(String label) {
        super(label);
    }

    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.BLOCKMOSSYBRICKS);
    }

}

	
