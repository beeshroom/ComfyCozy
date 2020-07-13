package bee.beeshroom.ComfyCozy.entity;

import bee.beeshroom.ComfyCozy.entity.layers.LayerOatmealSheepOatsGoldApple;
import bee.beeshroom.ComfyCozy.entity.models.ModelOatmealSheepGoldApple2;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOatmealSheepGoldApple extends RenderLiving<EntityOatmealSheepGoldApple>
{//changed from textures/entity
	private static final ResourceLocation SHEARED_OATMEALSHEEP_TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/oatmealsheep/oatmealsheepgold.png");

    public RenderOatmealSheepGoldApple(RenderManager p_i47195_1_)
    {
        super(p_i47195_1_, new ModelOatmealSheepGoldApple2(), 0.7F);
        this.addLayer(new LayerOatmealSheepOatsGoldApple(this));
    }	

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityOatmealSheepGoldApple entity)
    {
        return SHEARED_OATMEALSHEEP_TEXTURES;
    }
}