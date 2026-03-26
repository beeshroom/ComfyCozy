package com.beeshroom.brickery.init;

import java.util.ArrayList;
import java.util.List;

import com.beeshroom.brickery.blocks.BlockBase;
import com.beeshroom.brickery.blocks.BlockBrickFurnace;
import com.beeshroom.brickery.blocks.BlockFancyPlanks;
import com.beeshroom.brickery.blocks.BlockTerracottaBrick;
import com.beeshroom.brickery.blocks.EnumFancyPlanks;

import com.beeshroom.brickery.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<>();

    //bricks and cobble and stone
    public static final Block BLOCKMOSSYBRICKS = new BlockBase("mossy_bricks", Material.ROCK);
    public static final Block BLOCKCRACKEDBRICKS = new BlockBase("cracked_bricks", Material.ROCK);
    public static final Block BLOCK_DIRTY_BRICKS = new BlockBase("dirty_bricks", Material.ROCK);
    public static final Block BLOCK_BRICK_TILE = new BlockBase("brick_tile", Material.ROCK);
    public static final Block BRICK_PATH = new BlockBase("brick_path", Material.ROCK);
    public static final Block BLOCKMIXEDCOBBLE = new BlockBase("mixed_cobble", Material.ROCK);
    public static final Block TERRACOTTA_BRICK = new BlockBase("terracotta_brick", Material.ROCK);

    //furnace
    public static final Block BRICK_FURNACE = new BlockBrickFurnace("brick_furnace", false, true);
	public static final Block BRICK_FURNACE_LIT = new BlockBrickFurnace("brick_furnace_lit", true, false);
	
    
    //terracotta and fancy planks
    public static Block[] TERRACOTTA_BRICKS = new Block[16];
    public static Block[] FANCY_PLANKS = new Block[6];

    static {
        for (EnumDyeColor color : EnumDyeColor.values()) {
            TERRACOTTA_BRICKS[color.getMetadata()] = new BlockTerracottaBrick(color);
        }
        for(EnumFancyPlanks fancyPlanks : EnumFancyPlanks.values()) {
            FANCY_PLANKS[fancyPlanks.getID()] = new BlockFancyPlanks(fancyPlanks);
        }
    }
}