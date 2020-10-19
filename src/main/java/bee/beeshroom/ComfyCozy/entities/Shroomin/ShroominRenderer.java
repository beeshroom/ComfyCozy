package bee.beeshroom.comfycozy.entities.Shroomin;

import bee.beeshroom.comfycozy.comfycozy;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShroominRenderer extends MobRenderer<ShroominEntity, ShroominModel<ShroominEntity>>

    {
         //   private final float scale;
    private static final ResourceLocation RED = new ResourceLocation(comfycozy.MOD_ID,"textures/entity/shroomin/shroominred.png");
    private static final ResourceLocation BROWN = new ResourceLocation(comfycozy.MOD_ID,"textures/entity/shroomin/shroominbrown.png");
    private static final ResourceLocation REDTWO = new ResourceLocation(comfycozy.MOD_ID,"textures/entity/shroomin/shroominredtwo.png");
    private static final ResourceLocation BROWNFLAT = new ResourceLocation(comfycozy.MOD_ID,"textures/entity/shroomin/shroominbrownflat.png");


        private static final ResourceLocation BEE = new ResourceLocation(comfycozy.MOD_ID,"textures/entity/shroomin/shroominbee.png");
    private static final ResourceLocation GOOMBA = new ResourceLocation(comfycozy.MOD_ID,"textures/entity/shroomin/shroomingoomba.png");
    private static final ResourceLocation CUPCAKE = new ResourceLocation(comfycozy.MOD_ID,"textures/entity/shroomin/shroomincupcake.png");
    private static final ResourceLocation TOAD = new ResourceLocation(comfycozy.MOD_ID,"textures/entity/shroomin/shroomintoad.png");
    private static final ResourceLocation TOADETTE = new ResourceLocation(comfycozy.MOD_ID,"textures/entity/shroomin/shroomintoadette.png");
    private static final ResourceLocation TRUFFLE = new ResourceLocation(comfycozy.MOD_ID,"textures/entity/shroomin/shroomintruffle.png");



            public ShroominRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ShroominModel<>(), 0.3F);
             //   this.scale = scaleIn;
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(ShroominEntity entity) {
        String s = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());
        if (s != null && "Bee".equals(s) || s != null && "Beesh".equals(s) || s != null && "Beepo".equals(s) || s != null && "Beep".equals(s)) {
            return BEE;
        }
        if (s != null && "Goomba".equals(s)) {
            return GOOMBA;
        }
        if (s != null && "Cupcake".equals(s) || s != null && "Muffin".equals(s)) {
            return CUPCAKE;
        }
        if (s != null && "Toad".equals(s) || s != null && "Really Good Toad".equals(s)) {
            return TOAD;
        }
        if (s != null && "Toadette".equals(s)) {
            return TOADETTE;
        }
        if (s != null && "Truffle".equals(s) || s != null && "Truffles".equals(s)) {
            return TRUFFLE;
        } else {
            switch (entity.getShroominType()) {
                case 0:
                default:
                    return RED;
                case 1:
                    return BROWN;
                case 2:
                    return BROWNFLAT;
                case 3:
                    return REDTWO;
            }
        }
    }

            protected void preRenderCallback(ShroominEntity shroomin, MatrixStack matrixStackIn, float partialTickTime) {
                if(shroomin.isChild())
                {
                    matrixStackIn.scale(0.7F, 0.7F, 0.7F);
                }
                else {
                    matrixStackIn.scale(1.0F, 1.0F, 1.0F);
                }
            }


      /*      protected void preRenderCallback(ShroominEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
                int i = entitylivingbaseIn.getShroominSize();
                float f = 1.0F + 0.15F * (float)i;
                matrixStackIn.scale(f, f, f);
                matrixStackIn.translate(0.0D, 1.3125D, 0.1875D);
            } */


    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
 /*   public float handleRotationFloat(ParrotEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.flap);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.flapSpeed);
        return (MathHelper.sin(f) + 1.0F) * f1;
    } */
}