package bee.beeshroom.ComfyCozy.entity;

import bee.beeshroom.ComfyCozy.entity.layers.LayerOatmealSheepOats;
import bee.beeshroom.ComfyCozy.entity.models.ModelOatmealSheep2;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOatmealSheep extends RenderLiving<EntityOatmealSheep>
{//changed from textures/entity
	private static final ResourceLocation SHEARED_OATMEALSHEEP_TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/oatmealsheep/oatmealsheep.png");

    public RenderOatmealSheep(RenderManager p_i47195_1_)
    {
        super(p_i47195_1_, new ModelOatmealSheep2(), 0.7F);
        this.addLayer(new LayerOatmealSheepOats(this));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityOatmealSheep entity)
    {
        return SHEARED_OATMEALSHEEP_TEXTURES;
    }
}