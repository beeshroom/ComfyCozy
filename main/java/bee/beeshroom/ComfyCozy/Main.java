package bee.beeshroom.ComfyCozy;

import java.io.File;

import bee.beeshroom.ComfyCozy.proxy.CommonProxy;
import bee.beeshroom.ComfyCozy.tabs.ComfyCozyTab;
import bee.beeshroom.ComfyCozy.util.Reference;
import bee.beeshroom.ComfyCozy.util.handlers.ConfigHandler;
import bee.beeshroom.ComfyCozy.util.handlers.MobDropsHandler;
import bee.beeshroom.ComfyCozy.util.handlers.RegistryHandler;
import bee.beeshroom.ComfyCozy.util.handlers.RegistryHandlerTwo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	public static File config;
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	
	public static final CreativeTabs comfycozytab = new ComfyCozyTab("comfycozytab");
	
	/*@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy; */
	{
	
	}
	

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) 
	{	
		RegistryHandlerTwo.preInitRegistries(event);
		ConfigHandler.registerConfig(event);
		MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
		//OreDictionaryCompat.registerOres();
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event) 
	{	
		RegistryHandlerTwo.initRegistries(event);
	//	OreDictionaryCompat.registerOres();
	}

	/*		@EventHandler
			public static void init(FMLInitializationEvent event)
			{
				//MinecraftForge.EVENT_BUS.register(new LootHandler());
	
			}
			@EventHandler
			public static void Postinit(FMLPostInitializationEvent event)
			{
			
			}
			*/
	
}
