package bee.beeshroom.ComfyCozy.proxy;

//import com.google.common.util.concurrent.ListenableFuture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy
{
/*	@Override
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	} */
	
/*	@SuppressWarnings("deprecation")
	@Override
	public void render() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMagiball.class, new RenderSnowball<EntityMagiball>(Minecraft.getMinecraft().getRenderManager(), ItemInit.MAGIBALL, Minecraft.getMinecraft().getRenderItem()));
	} */
	
	/*public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule)
	{
        return Minecraft.getMinecraft().addScheduledTask(runnableToSchedule);
    }*/
	
	@Override
	public void registerModel(Item item, int metadata) 
	{
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
  /*  @Override
    public EntityPlayer getClientPlayer()
    {
        return Minecraft.getMinecraft().player;
    }*/
}