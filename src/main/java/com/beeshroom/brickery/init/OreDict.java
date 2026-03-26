package com.beeshroom.brickery.init;

import net.minecraftforge.oredict.OreDictionary;

public class OreDict {
	public static void initOres() {
		OreDictionary.registerOre("blockBricks", ModBlocks.BLOCKMOSSYBRICKS);
		OreDictionary.registerOre("blockCobble", ModBlocks.BLOCKMIXEDCOBBLE);
		OreDictionary.registerOre("blockBricks", ModBlocks.BRICK_PATH);
		OreDictionary.registerOre("blockBricks", ModBlocks.BLOCK_BRICK_TILE);
		OreDictionary.registerOre("blockBricks", ModBlocks.BLOCK_DIRTY_BRICKS);
		OreDictionary.registerOre("blockBricks", ModBlocks.BLOCKCRACKEDBRICKS);
	
	}
}
