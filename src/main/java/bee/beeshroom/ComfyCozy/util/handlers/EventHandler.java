package bee.beeshroom.ComfyCozy.util.handlers;

import bee.beeshroom.ComfyCozy.events.ChairEvent;
//import bee.beeshroom.ComfyCozy.events.GolemEvent;
import net.minecraftforge.common.MinecraftForge;

public class EventHandler 
{
	public static void registerEvents()
	{
		ChairEvent chairEvent = new ChairEvent();
		
		MinecraftForge.EVENT_BUS.register(chairEvent);
		
/* GolemEvent golemEvent = new GolemEvent();
		
		MinecraftForge.EVENT_BUS.register(golemEvent); 
		*/
	} 
}