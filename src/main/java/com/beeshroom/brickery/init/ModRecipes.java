package com.beeshroom.brickery.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

    public static void init() {
        GameRegistry.addSmelting(Blocks.BRICK_BLOCK, new ItemStack(ModBlocks.BLOCKCRACKEDBRICKS, 1), 3.5f);
    }

}
