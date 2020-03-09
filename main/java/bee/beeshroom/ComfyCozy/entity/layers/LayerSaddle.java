package bee.beeshroom.ComfyCozy.entity.layers;

import bee.beeshroom.ComfyCozy.entity.entitydirtypig.EntityDirtyPig;
import bee.beeshroom.ComfyCozy.entity.entitydirtypig.RenderDirtyPig;
import bee.beeshroom.ComfyCozy.entity.models.ModelDirtyPig;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerSaddle implements LayerRenderer<EntityDirtyPig>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");
    private final RenderDirtyPig pigRenderer;
    private final ModelDirtyPig pigModel = new ModelDirtyPig(); //0.5F

    public LayerSaddle(RenderDirtyPig pigRendererIn)
    {
        this.pigRenderer = pigRendererIn;
    }

    public void doRenderLayer(EntityDirtyPig entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entitylivingbaseIn.getSaddled())
        {
            this.pigRenderer.bindTexture(TEXTURE);
            this.pigModel.setModelAttributes(this.pigRenderer.getMainModel());
            this.pigModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}