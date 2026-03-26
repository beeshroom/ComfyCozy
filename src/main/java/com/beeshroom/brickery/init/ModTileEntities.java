package com.beeshroom.brickery.init;

import com.beeshroom.brickery.tileentity.TileEntityBrickFurnace;
import com.beeshroom.brickery.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityBrickFurnace.class, new ResourceLocation(Reference.MOD_ID + ":brick_furnace"));
	}
}