package bee.beeshroom.ComfyCozy.entity.entitydirtypig;

import bee.beeshroom.ComfyCozy.entity.models.ModelDirtyPig;
import bee.beeshroom.ComfyCozy.entity.models.ModelMushy;
import bee.beeshroom.ComfyCozy.entity.layers.LayerSaddle;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDirtyPig extends RenderLiving<EntityDirtyPig>
{
    private static final ResourceLocation DIRTY_PIG_TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/dirty_pig/dirty_pig.png");

    public RenderDirtyPig(RenderManager p_i47198_1_)
    {
        super(p_i47198_1_, new ModelDirtyPig(), 0.7F);
       this.addLayer(new LayerSaddle(this));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityDirtyPig entity)
    {
        return DIRTY_PIG_TEXTURES;
    }
}