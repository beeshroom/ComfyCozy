package bee.beeshroom.ComfyCozy.entity.layers;

import java.util.logging.Logger;

import bee.beeshroom.ComfyCozy.entity.EntityOatmealSheep;
import bee.beeshroom.ComfyCozy.entity.models.ModelOatmealSheep1;
import bee.beeshroom.ComfyCozy.entity.renders.RenderOatmealSheep;
import bee.beeshroom.ComfyCozy.util.Reference;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerOatmealSheepOats implements LayerRenderer<EntityOatmealSheep>
{//changed from textures/entity
	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/oatmealsheep/oatmealsheep_fur.png");
	private static final ResourceLocation TEXTURE_STRAWBERRY = new ResourceLocation(Reference.MOD_ID + ":textures/entity/oatmealsheep/oatmealsheep_fur_strawberry.png");
	private static final ResourceLocation TEXTURE_CINNAMON = new ResourceLocation(Reference.MOD_ID + ":textures/entity/oatmealsheep/oatmealsheep_fur_cinnamon.png");
	private static final ResourceLocation TEXTURE_PEACH = new ResourceLocation(Reference.MOD_ID + ":textures/entity/oatmealsheep/oatmealsheep_fur_peach.png");
	private static final ResourceLocation TEXTURE_GAPPLE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/oatmealsheep/oatmealsheep_fur_gold_apple.png");
    private final RenderOatmealSheep oatmealsheepRenderer;
    private final ModelOatmealSheep1 oatmealsheepModel = new ModelOatmealSheep1();

    public LayerOatmealSheepOats(RenderOatmealSheep oatmealsheepRendererIn)
    {
        this.oatmealsheepRenderer = oatmealsheepRendererIn;
    }

    @Override
    public void doRenderLayer(EntityOatmealSheep entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!entitylivingbaseIn.getSheared() && !entitylivingbaseIn.isInvisible())
        {
            switch (entitylivingbaseIn.getOatFlavor())
        	{
        		default: 
        			oatmealsheepRenderer.bindTexture(TEXTURE); 
        			break;
        		case STRAWBERRY: 
        			oatmealsheepRenderer.bindTexture(TEXTURE_STRAWBERRY); 
        			break;
        		case CINNAMON: 
        			oatmealsheepRenderer.bindTexture(TEXTURE_CINNAMON); 
        			break;
        		case PEACH: 
        			oatmealsheepRenderer.bindTexture(TEXTURE_PEACH); 
        			break;
        		case GAPPLE: 
        			oatmealsheepRenderer.bindTexture(TEXTURE_GAPPLE); 
        			break;
        	}

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