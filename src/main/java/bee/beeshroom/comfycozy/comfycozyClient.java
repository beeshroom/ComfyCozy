package bee.beeshroom.comfycozy;

import bee.beeshroom.comfycozy.entities.Cushion.CushionEntity;
import bee.beeshroom.comfycozy.init.EntityTypes;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class comfycozyClient {

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityTypes.CUSHION.get(), EmptyRenderer::new);
    }

    private static class EmptyRenderer extends EntityRenderer<CushionEntity>
    {
        protected EmptyRenderer(EntityRendererManager renderManager)
        {
            super(renderManager);
        }

        @Override
        public boolean shouldRender(CushionEntity livingEntityIn, ClippingHelperImpl camera, double camX, double camY, double camZ) {
            return false;
        }

        @Override
        public ResourceLocation getEntityTexture(CushionEntity entity)
        {
            return null;
        }
    }
}
