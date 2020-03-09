package bee.beeshroom.ComfyCozy.entity;

import bee.beeshroom.ComfyCozy.entity.models.ModelFurnaceGolem;
import bee.beeshroom.ComfyCozy.entity.models.ModelMushy;
import bee.beeshroom.ComfyCozy.entity.layers.LayerSaddle;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFurnaceGolem extends RenderLiving<EntityFurnaceGolem>
{
    private static final ResourceLocation GOLEM_TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/furnace_golem/furnace_golem.png");
    private static final ResourceLocation GOLEM_LIT_TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/furnace_golem/furnace_golem_lit.png");
 
    public RenderFurnaceGolem(RenderManager p_i47198_1_)
    {
        super(p_i47198_1_, new ModelFurnaceGolem(), 0.7F);
    }
    
    protected ResourceLocation getEntityTexture(EntityFurnaceGolem entity)
    {
        return entity.isAttacking() ? GOLEM_LIT_TEXTURES : GOLEM_TEXTURES;
    }
    
    protected void preRenderCallback(EntityFurnaceGolem entitylivingbaseIn, float partialTickTime)
    {
        float f = 1.0F;
        float f1 = 4.5F;
        float f2 = 4.5F;
        //GlStateManager.scale(4.5F, 4.5F, 4.5F);
      //  GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
    
} 