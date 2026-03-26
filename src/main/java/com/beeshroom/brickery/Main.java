package com.beeshroom.brickery;

import com.beeshroom.brickery.blocks.BlockBrickFurnace;
import com.beeshroom.brickery.init.ModRecipes;
import com.beeshroom.brickery.proxy.CommonProxy;
import com.beeshroom.brickery.tabs.BricksTab;
import com.beeshroom.brickery.tileentity.TileEntityBrickFurnace;
import com.beeshroom.brickery.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	@Instance
    public static Main instance;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
	
	   public static final CreativeTabs CREATIVE_TAB = new BricksTab("brickery");
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;


    
    
}
