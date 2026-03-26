package com.beeshroom.brickery.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockTerracottaBrick extends BlockBase {

    public BlockTerracottaBrick(EnumDyeColor color) {
        super(String.format("%s_terracotta_brick", color.getName()), Material.ROCK);
        setSoundType(SoundType.STONE);
        setHardness(2.0f);
        setResistance(21.0f);
        setHarvestLevel("pickaxe", 0);
    }

}