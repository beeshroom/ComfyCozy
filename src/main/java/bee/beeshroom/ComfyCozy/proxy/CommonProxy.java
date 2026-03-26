package bee.beeshroom.ComfyCozy.proxy;

//import com.google.common.util.concurrent.ListenableFuture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class CommonProxy 
{

	/*//unslashed this out of 6.24.2020 also... trying to fix mushroom beret on servers
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		
	} */
	
	//added 6.24.2020	vvv
	public void registerModel(Item item, int metadata) {}
	
	public void render()
	{
		
	}
	
	/*public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule)
	{
        throw new IllegalStateException("This should only be called from client side");
    }

    public EntityPlayer getClientPlayer()
    {
        throw new IllegalStateException("This should only be called from client side");
    }*/
}