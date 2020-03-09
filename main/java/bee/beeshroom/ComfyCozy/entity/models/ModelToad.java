/*
//Made with Blockbench
package bee.beeshroom.ComfyCozy.entity.models;

import org.lwjgl.opengl.GL11;

import bee.beeshroom.ComfyCozy.entity.entitytoad.EntityToad;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelToad extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer leg;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	  private float jumpRotation;

	public ModelToad() {
		textureWidth = 32;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(body, -0.0873F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -3.0F, -4.25F, -4.0F, 6, 3, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -0.5F, -1.55F, -3.95F, 1, 1, 0, 0.0F, false));

		leg = new ModelRenderer(this);
		leg.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg.cubeList.add(new ModelBox(leg, 6, 10, -3.5F, -2.1F, 1.0F, 1, 2, 2, 0.0F, false));
		leg.cubeList.add(new ModelBox(leg, 4, 15, -3.5F, -0.1F, 0.0F, 1, 0, 1, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg2.cubeList.add(new ModelBox(leg2, 0, 10, 2.5F, -2.1F, 1.0F, 1, 2, 2, 0.0F, false));
		leg2.cubeList.add(new ModelBox(leg2, 4, 14, 2.5F, -0.1F, 0.0F, 1, 0, 1, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg3.cubeList.add(new ModelBox(leg3, 0, 14, -2.8F, -2.0F, -1.8F, 1, 2, 1, 0.0F, false));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg4.cubeList.add(new ModelBox(leg4, 12, 10, 1.8F, -2.0F, -1.8F, 1, 2, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		leg.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        float f = ageInTicks - (float)entityIn.ticksExisted;
        EntityToad entitytoad = (EntityToad)entityIn;
        this.jumpRotation = MathHelper.sin(entitytoad.setJumpCompletion(f) * (float)Math.PI);
        this.leg.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * 0.017453292F;
        this.leg2.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * 0.017453292F;
      // this.rabbitLeftFoot.rotateAngleX = this.jumpRotation * 50.0F * 0.017453292F;
      //  this.rabbitRightFoot.rotateAngleX = this.jumpRotation * 50.0F * 0.017453292F;
        this.leg3.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * 0.017453292F;
        this.leg4.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * 0.017453292F;
    }
    
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        this.jumpRotation = MathHelper.sin(((EntityRabbit)entitylivingbaseIn).setJumpCompletion(partialTickTime) * (float)Math.PI);
    }
}*/