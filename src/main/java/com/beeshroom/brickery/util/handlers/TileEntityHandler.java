package com.beeshroom.brickery.util.handlers;

import com.beeshroom.brickery.tileentity.TileEntityBrickFurnace;
import com.beeshroom.brickery.util.Reference;

import net.minecraftforge.fml.common.registry.GameRegistry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityBrickFurnace.class, new ResourceLocation(Reference.MOD_ID + ":brick_furnace"));
	}
}