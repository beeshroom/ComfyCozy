package bee.beeshroom.ComfyCozy.entity.layers;

import bee.beeshroom.ComfyCozy.entity.EntityOatmealSheepCinnamon;
import bee.beeshroom.ComfyCozy.entity.RenderOatmealSheepCinnamon;
import bee.beeshroom.ComfyCozy.entity.models.ModelOatmealSheepCinnamon1;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerOatmealSheepOatsCinnamon implements LayerRenderer<EntityOatmealSheepCinnamon>
{//changed from textures/entity
	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/oatmealsheep/oatmealsheep_fur_cinnamon.png");
    private final RenderOatmealSheepCinnamon oatmealsheepRenderer;
    private final ModelOatmealSheepCinnamon1 oatmealsheepModel = new ModelOatmealSheepCinnamon1();

    public LayerOatmealSheepOatsCinnamon(RenderOatmealSheepCinnamon oatmealsheepRendererIn)
    {
        this.oatmealsheepRenderer = oatmealsheepRendererIn;
    }

    @Override
    public void doRenderLayer(EntityOatmealSheepCinnamon entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!entitylivingbaseIn.getSheared() && !entitylivingbaseIn.isInvisible())
        {
            this.oatmealsheepRenderer.bindTexture(TEXTURE);

       /*     if (entitylivingbaseIn.hasCustomName() && "Strawberry Oatmeal".equals(entitylivingbaseIn.getCustomNameTag()))
            {
              /*  int i1 = 25;
                int i = entitylivingbaseIn.ticksExisted / 25 + entitylivingbaseIn.getEntityId();
                int j = EnumDyeColor.values().length;
                int k = i % j;
                int l = (i + 1) % j;
                float f = ((float)(entitylivingbaseIn.ticksExisted % 25) + partialTicks) / 25.0F;
                float[] afloat1 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(k));
                float[] afloat2 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(l));
                GlStateManager.color(afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f); */
      //      }
 //           else
     //       {
           //     float[] afloat = EntityOatmealSheep.getDyeRgb(entitylivingbaseIn.getOatFlavor());
              //  GlStateManager.color(afloat[0], afloat[1], afloat[2]);
        //    } 

            this.oatmealsheepModel.setModelAttributes(this.oatmealsheepRenderer.getMainModel());
            this.oatmealsheepModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.oatmealsheepModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    public boolean shouldCombineTextures()
    {
        return true;
    }
}