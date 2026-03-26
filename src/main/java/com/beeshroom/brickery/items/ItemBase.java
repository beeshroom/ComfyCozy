package com.beeshroom.brickery.items;

import com.beeshroom.brickery.Main;
import com.beeshroom.brickery.init.ModItems;
import com.beeshroom.brickery.util.IHasModel;
import com.beeshroom.brickery.util.handlers.ConfigHandler;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

    public ItemBase(String name) {
        if (!ConfigHandler.BRICKERY) return;
    	   setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(Main.CREATIVE_TAB);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

}
